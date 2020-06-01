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
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChartImageController {

    private Logger logger = Logger.getLogger(ChartImageController.class.getName());
    private ChartStatusSaver chartStatusSaver = null;
    private ChartGenerator chartGenerator;
    private BackEndClient backEndClient;
    private AvailableViews availableViews;
    private ChartPositionCalculator chartPositionCalculator;
    private ChartImageGetter chartImageGetter = new ChartImageGetter();

    public ChartImageController(ChartGenerator chartGenerator, BackEndClient backEndClient,
                                AvailableViews availableViews) {
        this.chartGenerator = chartGenerator;
        this.backEndClient = backEndClient;
        this.availableViews = availableViews;
    }

    public Image setCurrencyPair(String currencyPair){
        View view = getDefaultView();
        setCurrencyPair(currencyPair, view);
        return getDataAndGenerateImage();
    }







    private Image getDataAndGenerateImage() {
        chartImageGetter.getImage(this.chartStatusSaver);


    }


    private void setCurrencyPair(String currencyPair, View view){
        if(currencyPair != null && view != null && view.getRequiredPointTimeFrame() != null) {
            chartStatusSaver = new ChartStatusSaver(currencyPair, view, false, view.getRequiredPointNumber());
        } else {
            logger.log(Level.WARNING, "Problem with setting CurrencyPair.");
        }
    }

    private View getDefaultView(){
        if(this.availableViews != null){
            if(this.availableViews.getDefaultView() != null){
                return this.availableViews.getDefaultView();
            }
        }
        return null;
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
