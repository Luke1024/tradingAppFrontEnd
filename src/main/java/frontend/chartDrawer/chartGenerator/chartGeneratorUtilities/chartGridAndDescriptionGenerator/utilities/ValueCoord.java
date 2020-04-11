package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

public class ValueCoord {
    private int y;
    private double value;

    public ValueCoord(int y) {
        this.y = y;
    }

    public ValueCoord(int y, double value) {
        this.y = y;
        this.value = value;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public double getValue() {
        return value;
    }
}
