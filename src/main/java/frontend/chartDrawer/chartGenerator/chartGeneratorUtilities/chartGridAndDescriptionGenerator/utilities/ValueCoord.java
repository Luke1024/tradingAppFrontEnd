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

    public int getY() {
        return y;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ValueCoord{" +
                "y=" + y +
                ", value=" + value +
                '}';
    }
}
