package frontend.client;

import com.google.gson.Gson;
import frontend.client.dto.DataPointDto;
import frontend.client.dto.DataPointDtoPack;
import frontend.client.dto.PairDataRequest;
import frontend.client.dto.PairDataRequestDto;
import frontend.config.ClientConfig;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackEndClient {

    private Logger logger = Logger.getLogger(BackEndClient.class.getName());

    private RestTemplate restTemplate = new RestTemplate();

    private ClientConfig clientConfig = new ClientConfig();

    private Gson gson = new Gson();

    public List<String> getAvailableCurrencyPairs(){
        URI url = UriComponentsBuilder.fromHttpUrl(clientConfig.getServerAdress() + clientConfig.getGetAvailablePairs()).build().encode().toUri();
        String[] currencies = restTemplate.getForObject(url, String[].class);
        return Arrays.asList(currencies);
    }

    public List<DataPointDto> getCurrencyPairDataPoints(PairDataRequest pairDataRequest){
        PairDataRequestDto pairDataRequestDto = mapToPairDataRequestDto(pairDataRequest);
        if(pairDataRequestDto == null){
            logger.log(Level.WARNING, "Problem with mapping PairDataRequest to Dto.");
            return null;
        }
        URI url = UriComponentsBuilder.fromHttpUrl(clientConfig.getServerAdress() + clientConfig.getCurrencyPairDataPoints()).build().encode().toUri();
        DataPointDtoPack dataPointDtoPack = null;
        try {
            dataPointDtoPack = restTemplate.postForObject(url, pairDataRequest, DataPointDtoPack.class);
        } catch (Exception e){
            logger.log(Level.WARNING, (Supplier<String>) e);
        }
        if(dataPointDtoPack.getDataPointDtoList() != null){
            return dataPointDtoPack.getDataPointDtoList();
        } else {
            logger.log(Level.WARNING, "DataPointDto list is null.");
            return null;
        }
    }

    private PairDataRequestDto mapToPairDataRequestDto(PairDataRequest pairDataRequest){
        if(pairDataRequest != null){
            return new PairDataRequestDto(pairDataRequest.getCurrencyName(),
                    pairDataRequest.getNumberOfDataPoints(),
                    pairDataRequest.getPointTimeFrame().name(),
                    pairDataRequest.getPointsBeforeLast());
        } else {
            return null;
        }
    }
}
