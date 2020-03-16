package frontend.chartDrawer.dataObjects;

public class TpSlLineData {
    private double tpLineHeight = 0;
    private double slLineHeight = 0;

    public TpSlLineData() {
    }

    public TpSlLineData(double tpLineHeight, double slLineHeight) {
        this.tpLineHeight = tpLineHeight;
        this.slLineHeight = slLineHeight;
    }

    public double getTpLineHeight() {
        return tpLineHeight;
    }

    public double getSlLineHeight() {
        return slLineHeight;
    }
}
