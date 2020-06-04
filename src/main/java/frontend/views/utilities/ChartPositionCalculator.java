package frontend.views.utilities;

import frontend.client.dto.PointTimeFrame;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChartPositionCalculator {

    private ChartScallingSettings chartScallingSettings;

    private Logger logger = Logger.getLogger(ChartPositionCalculator.class.getName());


    public ChartPositionCalculator(ChartScallingSettings chartScallingSettings) {
        this.chartScallingSettings = chartScallingSettings;
    }

    public ChartStatusSaver zoomPlus(ChartStatusSaver chartStatusSaver) {
        if( ! settingsOk(chartStatusSaver)) return chartStatusSaver;

        PointTimeFrame pointTimeFrame = chartStatusSaver.getPointTimeFrame();
        int numberOfDataPoints = chartStatusSaver.getPointCount();
        LocalDateTime lastPoint = chartStatusSaver.getStop();

        double zoomScalling = chartScallingSettings.getZoomScalling();

        int zoomedPointCount = (int) (numberOfDataPoints * (1 - zoomScalling));
        int lastPointMoverCount = (numberOfDataPoints - zoomedPointCount)/2;

        int hourMultiplier = pointTimeFrameToHourMultiplier(pointTimeFrame);

        LocalDateTime movedLastPoint = lastPoint.minusHours(lastPointMoverCount*hourMultiplier);

        chartStatusSaver.
    }

    private boolean settingsOk(ChartStatusSaver chartStatusSaver){
        if(statusSaverCheck(chartStatusSaver)) {
            if(chartScallingSettings != null){
                return true;
            } else {
                logger.log(Level.WARNING, "ChartScallingSettings is null.");
            }
        } else {
            logger.log(Level.WARNING, "Null pointer detected in ChartStatusSaver.");
        }
        return false;
    }

    private boolean statusSaverCheck(ChartStatusSaver chartStatusSaver){
        if(chartStatusSaver == null) return false;
        if(chartStatusSaver.getCurrencyPair() == null) return false;
        if(chartStatusSaver.getPointTimeFrame() == null) return false;
        if(chartStatusSaver.getViewTimeFrame() == null) return false;
        if(chartStatusSaver.getStop() == null) return false;
        return true;
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
