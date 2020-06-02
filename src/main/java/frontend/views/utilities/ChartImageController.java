package frontend.views.utilities;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.ChartGenerator;
import frontend.client.BackEndClient;

import java.util.logging.Logger;

public class ChartImageController {

    private Logger logger = Logger.getLogger(ChartImageController.class.getName());
    private ChartStatusSaver chartStatusSaver = null;
    private ChartGenerator chartGenerator;
    private BackEndClient backEndClient;
    private AvailableViews availableViews;
    private ChartScallingSettings chartScallingSettings = new ChartScallingSettings();
    private ChartPositionCalculator chartPositionCalculator = new ChartPositionCalculator(chartScallingSettings);
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

    private Image updateImage(ChartStatusSaver chartStatusSaver){
        if(chartStatusSaver != null) {
            this.chartStatusSaver = chartStatusSaver;
        }
        return chartImageGetter.getImage(this.chartStatusSaver);
    }
}
