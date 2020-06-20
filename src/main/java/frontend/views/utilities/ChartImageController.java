package frontend.views.utilities;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.ChartGenerator;
import frontend.client.BackEndClient;
import frontend.config.ChartConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ChartImageController {

    private Logger logger = Logger.getLogger(ChartImageController.class.getName());
    private ChartConfig chartConfig;
    private ChartStatusSaver chartStatusSaver;
    private ChartGenerator chartGenerator = new ChartGenerator();
    private BackEndClient backEndClient = new BackEndClient();
    private AvailableViews availableViews = new AvailableViews();
    private ChartImageGetter chartImageGetter = new ChartImageGetter(chartGenerator, backEndClient);
    private double moveLevel = 0.3;
    private double moveMoreLevel = 1.0;

    public ChartImageController(ChartGenerator chartGenerator, BackEndClient backEndClient,
                                AvailableViews availableViews) {
        this.chartGenerator = chartGenerator;
        this.backEndClient = backEndClient;
        this.availableViews = availableViews;
    }

    public Image setCurrencyPair(String currencyPair){
        View view = getDefaultView();
        this.chartStatusSaver = new ChartStatusSaver(currencyPair, view.getRequiredPointTimeFrame(),
                view.getTimeFrameInfoForChartGenerator(), view.getRequiredPointNumber(), 0, null);
        logger.log(Level.INFO, "CurrencyPair " + currencyPair + " set, " + this.chartStatusSaver.toString());
        return updateImage();
    }

    private View getDefaultView(){
        if(this.availableViews != null){
            if(this.availableViews.getDefaultView() != null){
                return this.availableViews.getDefaultView();
            } else {
                logger.log(Level.WARNING, "DefaultView is null.");
            }
        } else {
            logger.log(Level.WARNING, "AvailableViews is null.");
        }
        return null;
    }

    public Image setTimeFrame(View view) {
        if (this.chartStatusSaver != null) {
            if (view != null) {
                logger.log(Level.INFO, "View loaded " + view.toString());
                this.chartStatusSaver.setPointCount(view.getRequiredPointNumber());
                this.chartStatusSaver.setPointsBeforeLast(0);
                this.chartStatusSaver.setViewTimeFrame(view.getTimeFrameInfoForChartGenerator());
                this.chartStatusSaver.setPointTimeFrame(view.getRequiredPointTimeFrame());
                return updateImage();
            } else {
                logger.log(Level.WARNING, "View is null.");
                return null;
            }
        } else {
            logger.log(Level.WARNING, "ChartStatusSaver is null.");
            return null;
        }
    }

    public Image resetView(){
        View defaultView = getDefaultView();
        this.chartStatusSaver.setPointCount(defaultView.getRequiredPointNumber());
        this.chartStatusSaver.setPointsBeforeLast(0);
        this.chartStatusSaver.setViewTimeFrame(defaultView.getTimeFrameInfoForChartGenerator());
        this.chartStatusSaver.setPointTimeFrame(defaultView.getRequiredPointTimeFrame());
        return updateImage();
    }

    public Image moveLeft(){
        int pointsBeforeLastPoint = this.chartStatusSaver.getPointsBeforeLast();
        int pointCountMoved = (int) (pointsBeforeLastPoint + this.chartStatusSaver.getPointCount() * moveLevel);
        this.chartStatusSaver.setPointsBeforeLast(pointCountMoved);
        logger.log(Level.INFO, "Points moved left " + pointCountMoved);
        return updateImage();
    }

    public Image moveRight(){
        int pointsBeforeLastPoint = this.chartStatusSaver.getPointsBeforeLast();
        int pointCountMoved = pointsBeforeLastPoint -((int) (this.chartStatusSaver.getPointCount() * moveLevel));

        if(pointCountMoved<0){
            pointCountMoved = 0;
        }
        this.chartStatusSaver.setPointsBeforeLast(pointCountMoved);
        logger.log(Level.INFO, "Points moved right " + pointCountMoved);

        return updateImage();
    }

    public Image moveMoreLeft(){
        int pointsBeforeLastPoint = this.chartStatusSaver.getPointsBeforeLast();
        int pointCountMoved = (int) (pointsBeforeLastPoint + this.chartStatusSaver.getPointCount() * moveMoreLevel);
        this.chartStatusSaver.setPointsBeforeLast(pointCountMoved);
        logger.log(Level.INFO, "Points moved left " + pointCountMoved);
        return updateImage();
    }

    public Image moveMoreRight(){
        int pointsBeforeLastPoint = this.chartStatusSaver.getPointsBeforeLast();
        int pointCountMoved = pointsBeforeLastPoint - ((int) (this.chartStatusSaver.getPointCount() * moveMoreLevel));

        if(pointCountMoved<0){
            pointCountMoved = 0;
        }
        this.chartStatusSaver.setPointsBeforeLast(pointCountMoved);
        logger.log(Level.INFO, "Points moved right " + pointCountMoved);

        return updateImage();
    }

    private Image updateImage() {
        if (chartStatusSaver != null) {
            ChartStatusSaver modifiedChartStatusSaver = chartImageGetter.getImage(this.chartStatusSaver, chartConfig);
            if (modifiedChartStatusSaver != null) {
                this.chartStatusSaver = modifiedChartStatusSaver;
                if (modifiedChartStatusSaver.getImage() != null) {
                    return modifiedChartStatusSaver.getImage();
                } else return null;
            } else return null;
        } else return null;
    }

    public void setChartConfig(ChartConfig chartConfig) {
        this.chartConfig = chartConfig;
    }
}
