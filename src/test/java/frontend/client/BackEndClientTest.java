package frontend.client;

import frontend.client.dto.DataPointDto;
import frontend.client.dto.PairDataRequest;
import frontend.client.dto.PointTimeFrame;
import org.junit.Test;

import java.util.List;

public class BackEndClientTest {

    private BackEndClient backEndClient = new BackEndClient();

    @Test
    public void testGenerateUriGetCurrencyPairDataPoints(){
        PairDataRequest pairDataRequest = new PairDataRequest("EUR/USD", 5, PointTimeFrame.H1);

        List<DataPointDto> dataPointDtoList = backEndClient.getCurrencyPairDataPoints(pairDataRequest);

        for(DataPointDto dataPointDto : dataPointDtoList){
            System.out.println(dataPointDto.toString());
        }
    }
}