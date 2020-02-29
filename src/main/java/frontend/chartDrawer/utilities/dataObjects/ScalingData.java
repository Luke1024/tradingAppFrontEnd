package frontend.chartDrawer.utilities.dataObjects;

public class ScalingData {
    private int zoomLevel;
    private int timeFrameAxisDistanceInPixels;
    private double geometricScallingMultiplier;

    public ScalingData(int zoomLevel, int timeFrameAxisDistanceInPixels, double geometricScallingMultiplier) {
        this.zoomLevel = zoomLevel;
        this.timeFrameAxisDistanceInPixels = timeFrameAxisDistanceInPixels;
        this.geometricScallingMultiplier = geometricScallingMultiplier;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public int getTimeFrameAxisDistanceInPixels() {
        return timeFrameAxisDistanceInPixels;
    }

    public double getGeometricScallingMultiplier() {
        return geometricScallingMultiplier;
    }
}
