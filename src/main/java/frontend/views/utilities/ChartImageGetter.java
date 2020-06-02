package frontend.views.utilities;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.ChartGenerator;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.CurrencyOverviewDto;
import frontend.client.BackEndClient;
import frontend.client.dto.DataPointDto;
import frontend.client.dto.PairDataRequest;
import frontend.client.dto.PointTimeFrame;
import frontend.config.ChartConfig;

import java.time.LocalDateTime;
import java.util.List;

public class ChartImageGetter {

    private ChartGenerator chartGenerator;
    private BackEndClient backEndClient;

    public ChartImageGetter(ChartGenerator chartGenerator, BackEndClient backEndClient) {
        this.chartGenerator = chartGenerator;
        this.backEndClient = backEndClient;
    }

    public ChartStatusSaver getImage(ChartStatusSaver chartStatusSaver){

        if(chartStatusSaver == null){
            return null;
        } else {
            PairDataRequest pairDataRequest = generatePairDataRequest(chartStatusSaver);
            if(pairDataRequest == null) {
                return null;
            }
            List<DataPointDto> dataPointDtos = getDataPointDtos(pairDataRequest);
            if(dataPointDtos == null) {
                return null;
            }
            Image image = downloadImage(chartStatusSaver, pairDataRequest, dataPointDtos);
            chartStatusSaver.setImage(image);
        }
        return null;
    }

    private PairDataRequest generatePairDataRequest(ChartStatusSaver chartStatusSaver){

        if(chartStatusSaver.getCurrencyPair() == null) return null;
        String currencyPair = chartStatusSaver.getCurrencyPair();

        if(chartStatusSaver.getPointCount() == 0) return null;
        int pointCount = chartStatusSaver.getPointCount();

        if(chartStatusSaver.getPointTimeFrame() == null) return null;
        PointTimeFrame pointTimeFrame = chartStatusSaver.getPointTimeFrame();

        if(chartStatusSaver.getStop() == null){
            return new PairDataRequest(currencyPair, pointCount, pointTimeFrame);
        } else {
            return new PairDataRequest(currencyPair, pointCount, pointTimeFrame, chartStatusSaver.getStop());
        }
    }

    private List<DataPointDto> getDataPointDtos(PairDataRequest pairDataRequest) {
        List<DataPointDto> dataPointDtos = null;
        if(pairDataRequest != null){
            dataPointDtos = backEndClient.getCurrencyPairDataPoints(pairDataRequest);
        }
        return dataPointDtos;
    }

    private Image downloadImage(ChartStatusSaver chartStatusSaver, PairDataRequest pairDataRequest,
                                List<DataPointDto> dataPointDtos) {
        Image chartImage = null;
        if(dataPointDtos != null){
            ChartDataDto chartDataDto = buildChartDataDto(dataPointDtos, pairDataRequest, chartStatusSaver);
            return chartGenerator.generateChart(chartDataDto);
        }
        return chartImage;
    }

    private ChartDataDto buildChartDataDto(List<DataPointDto> dataPointDtos, PairDataRequest pairDataRequest,
                                           ChartStatusSaver chartStatusSaver){

        CurrencyOverviewDto currencyOverviewDto = null;
        try {
            currencyOverviewDto = new CurrencyOverviewDto(pairDataRequest.getCurrencyName(), LocalDateTime.now(), dataPointDtos);
        } catch (Exception e){}
        if(currencyOverviewDto != null){
            try {
                return new ChartDataDto(currencyOverviewDto, chartStatusSaver.getViewTimeFrame(), new ChartConfig());
            } catch (Exception e){}
        }
        return null;
    }
}
