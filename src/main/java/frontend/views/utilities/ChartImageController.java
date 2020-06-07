package frontend.views.utilities;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.ChartGenerator;
import frontend.client.BackEndClient;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChartImageController {

    private Logger logger = Logger.getLogger(ChartImageController.class.getName());
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
                this.chartStatusSaver.setPointCount(view.getRequiredPointNumber());
                this.chartStatusSaver.setPointsBeforeLast(0);
            } else {
                logger.log(Level.WARNING, "View is null.");
                return updateImage();
            }
        } else {
            logger.log(Level.WARNING, "ChartStatusSaver is null.");
            return null;
        }
        return null;
    }

    public Image resetView(){
        View defaultView = getDefaultView();
        this.chartStatusSaver.setPointCount(defaultView.getRequiredPointNumber());
        this.chartStatusSaver.setPointsBeforeLast(0);
        return updateImage();
    }

    public Image moveLeft(){
        int pointsBeforeLastPoint = this.chartStatusSaver.getPointsBeforeLast();
        int pointCountMoved = (int) (pointsBeforeLastPoint + this.chartStatusSaver.getPointCount() * moveLevel);
        this.chartStatusSaver.setPointsBeforeLast(pointCountMoved);

        return updateImage();
    }

    public Image moveRight(){
        int pointsBeforeLastPoint = this.chartStatusSaver.getPointsBeforeLast();
        int pointCountMoved = -((int) (pointsBeforeLastPoint + this.chartStatusSaver.getPointCount() * moveLevel));

        if(pointCountMoved<0){
            pointCountMoved = 0;
        }
        this.chartStatusSaver.setPointsBeforeLast(pointCountMoved);

        return updateImage();
    }

    public Image moveMoreLeft(){
        int pointsBeforeLastPoint = this.chartStatusSaver.getPointsBeforeLast();
        int pointCountMoved = (int) (pointsBeforeLastPoint + this.chartStatusSaver.getPointCount() * moveMoreLevel);
        this.chartStatusSaver.setPointsBeforeLast(pointCountMoved);

        return updateImage();
    }

    public Image moveMoreRight(){
        int pointsBeforeLastPoint = this.chartStatusSaver.getPointsBeforeLast();
        int pointCountMoved = -((int) (pointsBeforeLastPoint + this.chartStatusSaver.getPointCount() * moveMoreLevel));

        if(pointCountMoved<0){
            pointCountMoved = 0;
        }
        this.chartStatusSaver.setPointsBeforeLast(pointCountMoved);

        return updateImage();
    }

    private Image updateImage() {
        if (chartStatusSaver != null) {
            ChartStatusSaver modifiedChartStatusSaver = chartImageGetter.getImage(this.chartStatusSaver);
            if (modifiedChartStatusSaver != null) {
                this.chartStatusSaver = modifiedChartStatusSaver;
                if (modifiedChartStatusSaver.getImage() != null) {
                    return modifiedChartStatusSaver.getImage();
                } else return null;
            } else return null;
        } else return null;
    }
}
