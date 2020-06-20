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
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChartImageGetter {

    private ChartGenerator chartGenerator;
    private BackEndClient backEndClient;
    private Logger logger = Logger.getLogger(ChartImageGetter.class.getName());
    private ChartConfig chartConfig;

    public ChartImageGetter(ChartGenerator chartGenerator, BackEndClient backEndClient) {
        this.chartGenerator = chartGenerator;
        this.backEndClient = backEndClient;
    }

    public ChartStatusSaver getImage(ChartStatusSaver chartStatusSaver, ChartConfig chartConfig){
        if(chartConfig == null){
            chartConfig = new ChartConfig();
        } else {
            chartConfig = chartConfig;
        }


        if(chartStatusSaver == null){
            logger.log(Level.WARNING, "ChartStatusSaver is null.");
            return null;
        } else {
            PairDataRequest pairDataRequest = generatePairDataRequest(chartStatusSaver);
            if(pairDataRequest == null) {
                logger.log(Level.WARNING, "PairDataRequest is null.");
                return null;
            }
            List<DataPointDto> dataPointDtos = getDataPointDtos(pairDataRequest);
            if(dataPointDtos == null) {
                logger.log(Level.WARNING, "List<DataPointDto> is null");
                return null;
            } else {
                logger.log(Level.INFO, "Received " + dataPointDtos.size() + " data points.");
            }
            Image image = downloadImage(chartStatusSaver, pairDataRequest, dataPointDtos, chartConfig);
            chartStatusSaver.setImage(image);
            return chartStatusSaver;
        }
    }

    private PairDataRequest generatePairDataRequest(ChartStatusSaver chartStatusSaver){

        if(chartStatusSaver.getCurrencyPair() == null) {
            logger.log(Level.WARNING, "CurrencyPair in ChartStatusSaver is null.");
            return null;
        }

        String currencyPair = chartStatusSaver.getCurrencyPair();

        if (chartStatusSaver.getPointCount() == 0){
            logger.log(Level.WARNING, "Requsted point number is 0.");
            return null;
        }
        int pointCount = chartStatusSaver.getPointCount();

        if (chartStatusSaver.getPointTimeFrame() == null){
            logger.log(Level.WARNING, "PointTimeFrame in ChartStatusSaver is null.");
            return null;
        }
        PointTimeFrame pointTimeFrame = chartStatusSaver.getPointTimeFrame();

        return new PairDataRequest(currencyPair, pointCount, pointTimeFrame, chartStatusSaver.getPointsBeforeLast());
    }

    private List<DataPointDto> getDataPointDtos(PairDataRequest pairDataRequest) {
        List<DataPointDto> dataPointDtos = null;
        if(pairDataRequest != null){
            dataPointDtos = backEndClient.getCurrencyPairDataPoints(pairDataRequest);
        }
        return dataPointDtos;
    }

    private Image downloadImage(ChartStatusSaver chartStatusSaver, PairDataRequest pairDataRequest,
                                List<DataPointDto> dataPointDtos, ChartConfig chartConfig) {
        Image chartImage = null;
        if(dataPointDtos != null){
            ChartDataDto chartDataDto = buildChartDataDto(dataPointDtos, pairDataRequest, chartStatusSaver, chartConfig);
            return chartGenerator.generateChart(chartDataDto);
        }
        return chartImage;
    }

    private ChartDataDto buildChartDataDto(List<DataPointDto> dataPointDtos, PairDataRequest pairDataRequest,
                                           ChartStatusSaver chartStatusSaver, ChartConfig chartConfig){

        CurrencyOverviewDto currencyOverviewDto = null;
        try {
            currencyOverviewDto = new CurrencyOverviewDto(pairDataRequest.getCurrencyName(), LocalDateTime.now(), dataPointDtos);
        } catch (Exception e){
            logger.log(Level.WARNING, "Can't build Currency overviewDto, " + e.toString());
        }
        if(currencyOverviewDto != null){
            try {
                return new ChartDataDto(currencyOverviewDto, chartStatusSaver.getViewTimeFrame(), chartConfig);
            } catch (Exception e){
                logger.log(Level.WARNING, "Can't build CharDataDto, " + e.toString());
            }
        }
        return null;
    }
}
