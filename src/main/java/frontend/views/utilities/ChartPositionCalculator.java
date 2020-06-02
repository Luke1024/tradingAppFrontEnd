package frontend.views.utilities;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChartPositionCalculator {

    private ChartScallingSettings chartScallingSettings;

    private Logger logger = Logger.getLogger(ChartPositionCalculator.class.getName());


    public ChartPositionCalculator(ChartScallingSettings chartScallingSettings) {
        this.chartScallingSettings = chartScallingSettings;
    }

    public ChartStatusSaver zoomPlus(ChartStatusSaver chartStatusSaver) {
        if (!settingsOk(chartStatusSaver)) return chartStatusSaver;

        int numberOfDataPoints;
        LocalDateTime lastPoint = null;

        if(chartStatusSaver.isViewIgnore()){
            numberOfDataPoints = chartStatusSaver.getPointCount();
            lastPoint = chartStatusSaver.getStop();
        } else {
            numberOfDataPoints = chartStatusSaver.getView().getRequiredPointNumber();
        }
        chartStatusSaver.setViewIgnore(true);

        ChartStatusSaver modifiedSaver = computeZoomPlus(chartStatusSaver);
        if (modifiedSaver == null) {
            return chartStatusSaver;
        } else {
            return modifiedSaver;
        }
    }

    private ChartStatusSaver computeZoomPlus(ChartStatusSaver chartStatusSaver){
        this.chartScallingSettings.getMaxPoints();
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
        if(chartStatusSaver.isViewIgnore()){
            if(chartStatusSaver.getStop() == null) return false;
            if(chartStatusSaver.getPointCount() == 0) return false;
        } else {
            if(chartStatusSaver.getView() == null) return false;
        }
        return true;
    }
}
