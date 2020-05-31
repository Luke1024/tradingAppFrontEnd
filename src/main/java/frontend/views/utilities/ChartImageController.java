package frontend.views.utilities;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.ChartGenerator;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.CurrencyOverviewDto;
import frontend.client.BackEndClient;
import frontend.client.dto.DataPointDto;
import frontend.client.dto.PairDataRequest;
import frontend.config.ChartConfig;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChartImageController {

    private ChartStatusSaver chartStatusSaver = null;
    private ChartGenerator chartGenerator;
    private BackEndClient backEndClient;
    private AvailableViews availableViews;
    private ChartPositionCalculator chartPositionCalculator;

    public ChartImageController(ChartGenerator chartGenerator, BackEndClient backEndClient,
                                AvailableViews availableViews) {
        this.chartGenerator = chartGenerator;
        this.backEndClient = backEndClient;
        this.availableViews = availableViews;
    }

    public Image setCurrencyPair(String currencyPair){
        View defaultView = getDefaultView();
        return getDataAndGenerateImage(currencyPair, defaultView);
    }

    private Image getDataAndGenerateImage() {
        PairDataRequest pairDataRequest = generatePairDataRequest(currencyPair, view);

        List<DataPointDto> dataPointDtos = getDataPointDtos(pairDataRequest);
        Image chartImage = getImage(view, pairDataRequest, dataPointDtos);
        if(chartImage != null) {
            setChartStatus(currencyPair, view, dataPointDtos);
            return chartImage;
        } else return null;
    }

    private Image getCustomDataAndGenerateImage(){

    }

    private View getDefaultView() {
        View defaultView = null;
        if(this.availableViews != null) {
            if (this.availableViews.getDefaultView() != null) {
                defaultView = availableViews.getDefaultView();
            }
        }
        return defaultView;
    }

    private PairDataRequest generatePairDataRequest(String currencyPair, View view){
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

    private Image getImage(View defaultView, PairDataRequest pairDataRequest, List<DataPointDto> dataPointDtos) {
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



    private void setChartStatus(String currencyPair, View defaultView, List<DataPointDto> dataPointDtos) {
        this.chartStatusSaver = new ChartStatusSaver(currencyPair, defaultView,
                defaultView.getRequiredPointNumber(), dataPointDtos.get(dataPointDtos.size() - 1).getTimeStamp());
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



    public Image setTimeFrame(View view){
        return getDataAndGenerateImage();
    }

    public Image zoomPlus(){
        System.out.println("+");
    }

    public Image zoomMinus(){
        System.out.println("-");
    }

    public Image moveLeft(){
        System.out.println("<-");
    }

    public Image moveRight(){
        System.out.println("->");
    }

    public Image moveMoreLeft(){
        System.out.println("<<");
    }

    public Image moveMoreRight(){
        System.out.println(">>");
    }

    public Image resetView(){

    }
}
