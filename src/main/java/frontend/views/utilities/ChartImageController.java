package frontend.views.utilities;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.ChartGenerator;
import frontend.client.BackEndClient;
import frontend.client.dto.PointTimeFrame;

import java.time.LocalDateTime;
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
                view.getTimeFrameInfoForChartGenerator(), view.getRequiredPointNumber(),null);
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
                this.chartStatusSaver.setStop(null);
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
        this.chartStatusSaver.setStop(null);
        return updateImage();
    }

    public Image moveLeft(){
        LocalDateTime lastPoint = this.chartStatusSaver.getStop();
        if(lastPoint != null) {
            PointTimeFrame pointTimeFrame = this.chartStatusSaver.getPointTimeFrame();
            int pointCountMoved = (int) (this.chartStatusSaver.getPointCount() * moveLevel);
            int hoursMoved = pointCountMoved * pointTimeFrameToHourMultiplier(pointTimeFrame);
            LocalDateTime lastPointMoved = lastPoint.minusHours(hoursMoved);
            this.chartStatusSaver.setStop(lastPointMoved);
        } else {
            logger.log(Level.WARNING, "LastPoint is null.");
        }
        return updateImage();
    }

    public Image moveRight(){
        LocalDateTime lastPoint = this.chartStatusSaver.getStop();
        PointTimeFrame pointTimeFrame = this.chartStatusSaver.getPointTimeFrame();

        int pointCountMoved = (int) (this.chartStatusSaver.getPointCount() * moveLevel);
        int hoursMoved = pointCountMoved * pointTimeFrameToHourMultiplier(pointTimeFrame);

        LocalDateTime lastPointMoved = lastPoint.plusHours(hoursMoved);

        this.chartStatusSaver.setStop(lastPointMoved);

        return updateImage();
    }

    public Image moveMoreLeft(){
        LocalDateTime lastPoint = this.chartStatusSaver.getStop();
        PointTimeFrame pointTimeFrame = this.chartStatusSaver.getPointTimeFrame();

        int pointCountMoved = (int) (this.chartStatusSaver.getPointCount() * moveMoreLevel);
        int hoursMoved = pointCountMoved * pointTimeFrameToHourMultiplier(pointTimeFrame);

        LocalDateTime lastPointMoved = lastPoint.minusHours(hoursMoved);

        this.chartStatusSaver.setStop(lastPointMoved);

        return updateImage();
    }

    public Image moveMoreRight(){
        LocalDateTime lastPoint = this.chartStatusSaver.getStop();
        PointTimeFrame pointTimeFrame = this.chartStatusSaver.getPointTimeFrame();

        int pointCountMoved = (int) (this.chartStatusSaver.getPointCount() * moveMoreLevel);
        int hoursMoved = pointCountMoved * pointTimeFrameToHourMultiplier(pointTimeFrame);

        LocalDateTime lastPointMoved = lastPoint.plusHours(hoursMoved);

        this.chartStatusSaver.setStop(lastPointMoved);

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

    private int pointTimeFrameToHourMultiplier(PointTimeFrame pointTimeFrame){
        if(pointTimeFrame == PointTimeFrame.H1) return 1;
        if(pointTimeFrame == PointTimeFrame.H5) return 5;
        if(pointTimeFrame == PointTimeFrame.D1) return 24;
        if(pointTimeFrame == PointTimeFrame.W1) return 168;
        if(pointTimeFrame == PointTimeFrame.M1) return 720;
        return 1;
    }
}
