package frontend.chartDrawer.utilities.dataObjects;

public class ChartControlData {
    private int width;
    private int height;
    private int maxminHeightRangePercentage;
    private int fontSize;
    private ScalingData scalingData;
    private TpSlLineData tpSlLineData;

    public ChartControlData(int width, int height, int maxminHeightRangePercentage, int fontSize, ScalingData scalingData) {
        this.width = width;
        this.height = height;
        this.maxminHeightRangePercentage = maxminHeightRangePercentage;
        this.fontSize = fontSize;
        this.scalingData = scalingData;
    }

    public ChartControlData(int width, int height, int maxminHeightRangePercentage, int fontSize, ScalingData scalingData, TpSlLineData tpSlLineData) {
        this.width = width;
        this.height = height;
        this.maxminHeightRangePercentage = maxminHeightRangePercentage;
        this.fontSize = fontSize;
        this.scalingData = scalingData;
        this.tpSlLineData = tpSlLineData;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMaxminHeightRangePercentage() {
        return maxminHeightRangePercentage;
    }

    public int getFontSize() {
        return fontSize;
    }

    public ScalingData getScalingData() {
        return scalingData;
    }

    public TpSlLineData getTpSlLineData() {
        return tpSlLineData;
    }
}
