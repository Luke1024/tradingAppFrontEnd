package frontend.views.utilities;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.ChartGenerator;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.CurrencyOverviewDto;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.BackEndClient;
import frontend.client.dto.DataPointDto;
import frontend.client.dto.PairDataRequest;
import frontend.client.dto.PointTimeFrame;
import frontend.config.ChartConfig;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChartImageGetter {

    private ChartGenerator chartGenerator;
    private BackEndClient backEndClient;

    public ChartImageGetter(ChartGenerator chartGenerator, BackEndClient backEndClient) {
        this.chartGenerator = chartGenerator;
        this.backEndClient = backEndClient;
    }

    public Image getImage(ChartStatusSaver chartStatusSaver){

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
            return downloadImage(chartStatusSaver.getView(), pairDataRequest, dataPointDtos);
        }
    }

    private PairDataRequest generatePairDataRequest(ChartStatusSaver chartStatusSaver){

        if(chartStatusSaver.getCurrencyPair() == null) return null;
        String currencyPair = chartStatusSaver.getCurrencyPair();

        if(chartStatusSaver.getView() == null) return null;
        View view = chartStatusSaver.getView();

        if(view.getRequiredPointTimeFrame() == null) return null;
        int pointCount = view.getRequiredPointNumber();

        if(view.getRequiredPointTimeFrame() == null) return null;
        PointTimeFrame pointTimeFrame = view.getRequiredPointTimeFrame();

        if(chartStatusSaver.isViewIgnore()) {
            if(chartStatusSaver.getStop() == null) return null;
            LocalDateTime lastPoint = chartStatusSaver.getStop();
            return new PairDataRequest(currencyPair, pointCount, pointTimeFrame, lastPoint);
        } else {
            return new PairDataRequest(currencyPair, pointCount,
                    view.getRequiredPointTimeFrame());
        }
    }

    private List<DataPointDto> getDataPointDtos(PairDataRequest pairDataRequest) {
        List<DataPointDto> dataPointDtos = null;
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

    private ChartDataDto buildChartDataDto(List<DataPointDto> dataPointDtos, PairDataRequest pairDataRequest, View view){

        CurrencyOverviewDto currencyOverviewDto = null;
        try {
            currencyOverviewDto = new CurrencyOverviewDto(pairDataRequest.getCurrencyName(), LocalDateTime.now(), dataPointDtos);
        } catch (Exception e){}
        if(currencyOverviewDto != null){
            try {
                return new ChartDataDto(currencyOverviewDto, view.getTimeFrameInfoForChartGenerator(), new ChartConfig());
            } catch (Exception e){}
        }
        return null;
    }
}
