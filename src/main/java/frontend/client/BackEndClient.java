package frontend.client;

import com.google.gson.Gson;
import frontend.client.dto.DataPointDto;
import frontend.client.dto.PairDataRequest;
import frontend.client.dto.PairDataRequestDto;
import frontend.config.ClientConfig;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackEndClient {

    private Logger logger = Logger.getLogger(BackEndClient.class.getName());

    private RestTemplate restTemplate = new RestTemplate();

    private ClientConfig clientConfig = new ClientConfig();

    private Gson gson = new Gson();

    public List<String> getAvailableCurrencyPairs(){
        URI url = UriComponentsBuilder.fromHttpUrl(clientConfig.getGetAvailablePairs()).build().encode().toUri();
        String[] currencies = restTemplate.getForObject(url, String[].class);
        return Arrays.asList(currencies);
    }

    public List<DataPointDto> getCurrencyPairDataPoints(PairDataRequest pairDataRequest){
        PairDataRequestDto pairDataRequestDto = mapToPairDataRequestDto(pairDataRequest);
        if(pairDataRequestDto == null){
            logger.log(Level.WARNING, "Problem with mapping PairDataRequest to Dto.");
            return null;
        }
        URI url = UriComponentsBuilder.fromHttpUrl(clientConfig.getServerAdress() + clientConfig.getGetCurrencyPairDataPoints() +
                serialize(pairDataRequest)).build().encode().toUri();
        DataPointDto[] dataPointDtos = restTemplate.getForObject(url, DataPointDto[].class);
        return Arrays.asList(dataPointDtos);
    }

    private String serialize(PairDataRequest pairDataRequest){
         return gson.toJson(pairDataRequest);
    }

    private PairDataRequestDto mapToPairDataRequestDto(PairDataRequest pairDataRequest){
        try {
            return new PairDataRequestDto(pairDataRequest.getCurrencyName(),
                    pairDataRequest.getNumberOfDataPoints(),
                    pairDataRequest.getPointTimeFrame().name(),
                    pairDataRequest.isFromLastPoint(),
                    pairDataRequest.getAdoptedlastPoint().toString());
        } catch (Exception e){
            return null;
        }
    }
}
