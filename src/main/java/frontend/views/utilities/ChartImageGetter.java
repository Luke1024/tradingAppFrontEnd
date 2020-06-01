package frontend.views.utilities;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.client.dto.DataPointDto;
import frontend.client.dto.PairDataRequest;

import java.util.ArrayList;
import java.util.List;

public class ChartImageGetter {


    public Image getImage(ChartStatusSaver chartStatusSaver){
        PairDataRequest pairDataRequest = generatePairDataRequest();

        List<DataPointDto> dataPointDtos = getDataPointDtos(pairDataRequest);
        Image chartImage = downloadImage(view, pairDataRequest, dataPointDtos);
        if(chartImage != null) {
            setChartStatus(currencyPair, view, dataPointDtos);
            return chartImage;
        } else return null;
    }

    private PairDataRequest generatePairDataRequest(){
        if(this.chartStatusSaver == null){
            return null;
        }


        if(view.getRequiredPointTimeFrame() != null) {
            return new PairDataRequest(currencyPair, view.getRequiredPointNumber(),
                    view.getRequiredPointTimeFrame());
        } else {
            return null;
        }
    }

    private List<DataPointDto> getDataPointDtos(PairDataRequest pairDataRequest) {
        List<DataPointDto> dataPointDtos = new ArrayList<>();
        if(pairDataRequest != null){
            dataPointDtos = backEndClient.getCurrencyPairDataPoints(pairDataRequest);
        }
        return dataPointDtos;
    }

    private Image downloadImage(View defaultView, PairDataRequest pairDataRequest, List<DataPointDto> dataPointDtos) {
        Image chartImage = null;
        if(dataPointDtos != null){
            chartImage = getChartImage(dataPointDtos, pairDataRequest, defaultView);
        }
        return chartImage;
    }

    private Image getChartImage(List<DataPointDto> dataPointDtos, PairDataRequest pairDataRequest, View view){
        ChartDataDto chartDataDto = buildChartDataDto(dataPointDtos, pairDataRequest, view);
        return chartGenerator.generateChart(chartDataDto);
    }
}
