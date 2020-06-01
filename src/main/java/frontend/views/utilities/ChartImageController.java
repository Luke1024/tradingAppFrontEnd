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
    private ChartScallingMovingSettings chartScallingMovingSettings = new ChartScallingMovingSettings();
    private ChartPositionCalculator chartPositionCalculator = new ChartPositionCalculator(chartScallingMovingSettings);
    private ChartImageGetter chartImageGetter = new ChartImageGetter(chartGenerator, backEndClient);

    public ChartImageController(ChartGenerator chartGenerator, BackEndClient backEndClient,
                                AvailableViews availableViews) {
        this.chartGenerator = chartGenerator;
        this.backEndClient = backEndClient;
        this.availableViews = availableViews;
    }

    public Image setCurrencyPair(String currencyPair){
        View view = getDefaultView();
        this.chartStatusSaver = new ChartStatusSaver(currencyPair, view,false);
        return chartImageGetter.getImage(this.chartStatusSaver);
    }

    private View getDefaultView(){
        if(this.availableViews != null){
            if(this.availableViews.getDefaultView() != null){
                return this.availableViews.getDefaultView();
            }
        }
        return null;
    }

    public Image setTimeFrame(View view){
        if(this.chartStatusSaver != null){
            this.chartStatusSaver.setView(view);
            this.chartStatusSaver.setViewIgnore(false);
        }
        return chartImageGetter.getImage(this.chartStatusSaver);
    }

    private Image updateImage(ChartStatusSaver chartStatusSaver){
        if(chartStatusSaver != null) {
            this.chartStatusSaver = chartStatusSaver;
        }
        return chartImageGetter.getImage(this.chartStatusSaver);
    }

    public Image zoomPlus(){
        return updateImage(chartPositionCalculator.zoomPlus(this.chartStatusSaver));
    }

    public Image zoomMinus(){
        return updateImage(chartPositionCalculator.zoomMinus(this.chartStatusSaver));
    }

    public Image moveLeft(){
        return updateImage(chartPositionCalculator.zoomLeft(this.chartStatusSaver));
    }

    public Image moveRight(){
        return updateImage(chartPositionCalculator.zoomRight(this.chartStatusSaver));
    }

    public Image moveMoreLeft(){
        return updateImage(chartPositionCalculator.zoomMoreLeft(this.chartStatusSaver));
    }

    public Image moveMoreRight(){
        return updateImage(chartPositionCalculator.zoomMoreRight(this.chartStatusSaver));
    }

    public Image resetView(){
        View defaultView = getDefaultView();
        this.chartStatusSaver.setView(defaultView);
        this.chartStatusSaver.setViewIgnore(false);
        return chartImageGetter.getImage(this.chartStatusSaver);
    }
}
