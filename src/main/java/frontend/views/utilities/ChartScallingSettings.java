package frontend.views.utilities;

public class ChartScallingSettings {
    private double moveLevel = 0.3;
    private double moveMoreLevel = 1.0;
    private double zoomScalling = 0.3;
    private int maxPoints = 500;

    public double getMoveLevel() {
        return moveLevel;
    }

    public double getMoveMoreLevel() {
        return moveMoreLevel;
    }

    public double getZoomScalling() {
        return zoomScalling;
    }

    public int getMaxPoints() {
        return maxPoints;
    }
}
