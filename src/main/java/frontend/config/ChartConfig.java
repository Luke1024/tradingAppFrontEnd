package frontend.config;

public class ChartConfig {

    //chart font
    private int descriptionFontSize;
    private String textColorRGB;

    //chart size
    private int chartWidth;
    private int chartHeight;
    private int maxMinHeightRangePercentage;

    //chart scalling
    private int timeFrameAxisDistanceInPixels;
    private double zoomingMultiplier;
    private int defaultZoomLevel;

    //font size margin multiplier
    private double fontSizeBottomMarginMultiplier;
    private double fontSizeRightMarginMultiplier;

    //frame margin left and top
    private int frameLeftMarginPix;
    private int frameTopMarginPix;

    //frame rectangle settings
    private int frameThicknessInPix;
    private String frameColorRGB;

    //grid settings
    private int minimalDistanceBetweenGridLines;
    private int gridThicknessInPix;
    private String gridColorRGB;

    //chart line settings
    private int lineThicknessInPix;
    private String lineColorRGB;

    //chart background color
    private String backGroundColor;
    private String chartBackGroundColor;


    public int getDescriptionFontSize() {
        return descriptionFontSize;
    }

    public String getTextColorRGB() {
        return textColorRGB;
    }

    public int getChartWidth() {
        return chartWidth;
    }

    public int getChartHeight() {
        return chartHeight;
    }

    public int getMaxMinHeightRangePercentage() {
        return maxMinHeightRangePercentage;
    }

    public int getTimeFrameAxisDistanceInPixels() {
        return timeFrameAxisDistanceInPixels;
    }

    public double getZoomingMultiplier() {
        return zoomingMultiplier;
    }

    public int getDefaultZoomLevel() {
        return defaultZoomLevel;
    }

    public double getFontSizeBottomMarginMultiplier() {
        return fontSizeBottomMarginMultiplier;
    }

    public double getFontSizeRightMarginMultiplier() {
        return fontSizeRightMarginMultiplier;
    }

    public int getFrameLeftMarginPix() {
        return frameLeftMarginPix;
    }

    public int getFrameTopMarginPix() {
        return frameTopMarginPix;
    }

    public int getFrameThicknessInPix() {
        return frameThicknessInPix;
    }

    public String getFrameColorRGB() {
        return frameColorRGB;
    }

    public int getMinimalDistanceBetweenGridLines() {
        return minimalDistanceBetweenGridLines;
    }

    public int getGridThicknessInPix() {
        return gridThicknessInPix;
    }

    public String getGridColorRGB() {
        return gridColorRGB;
    }

    public int getLineThicknessInPix() {
        return lineThicknessInPix;
    }

    public String getLineColorRGB() {
        return lineColorRGB;
    }

    public String getBackGroundColor() {
        return backGroundColor;
    }

    public String getChartBackGroundColor() {
        return chartBackGroundColor;
    }


    public void setDescriptionFontSize(int descriptionFontSize) {
        this.descriptionFontSize = descriptionFontSize;
    }

    public void setTextColorRGB(String textColorRGB) {
        this.textColorRGB = textColorRGB;
    }

    public void setChartWidth(int chartWidth) {
        this.chartWidth = chartWidth;
    }

    public void setChartHeight(int chartHeight) {
        this.chartHeight = chartHeight;
    }

    public void setMaxMinHeightRangePercentage(int maxMinHeightRangePercentage) {
        this.maxMinHeightRangePercentage = maxMinHeightRangePercentage;
    }

    public void setTimeFrameAxisDistanceInPixels(int timeFrameAxisDistanceInPixels) {
        this.timeFrameAxisDistanceInPixels = timeFrameAxisDistanceInPixels;
    }

    public void setZoomingMultiplier(double zoomingMultiplier) {
        this.zoomingMultiplier = zoomingMultiplier;
    }

    public void setDefaultZoomLevel(int defaultZoomLevel) {
        this.defaultZoomLevel = defaultZoomLevel;
    }

    public void setFontSizeBottomMarginMultiplier(double fontSizeBottomMarginMultiplier) {
        this.fontSizeBottomMarginMultiplier = fontSizeBottomMarginMultiplier;
    }

    public void setFontSizeRightMarginMultiplier(double fontSizeRightMarginMultiplier) {
        this.fontSizeRightMarginMultiplier = fontSizeRightMarginMultiplier;
    }

    public void setFrameLeftMarginPix(int frameLeftMarginPix) {
        this.frameLeftMarginPix = frameLeftMarginPix;
    }

    public void setFrameTopMarginPix(int frameTopMarginPix) {
        this.frameTopMarginPix = frameTopMarginPix;
    }

    public void setFrameThicknessInPix(int frameThicknessInPix) {
        this.frameThicknessInPix = frameThicknessInPix;
    }

    public void setFrameColorRGB(String frameColorRGB) {
        this.frameColorRGB = frameColorRGB;
    }

    public void setMinimalDistanceBetweenGridLines(int minimalDistanceBetweenGridLines) {
        this.minimalDistanceBetweenGridLines = minimalDistanceBetweenGridLines;
    }

    public void setGridThicknessInPix(int gridThicknessInPix) {
        this.gridThicknessInPix = gridThicknessInPix;
    }

    public void setGridColorRGB(String gridColorRGB) {
        this.gridColorRGB = gridColorRGB;
    }

    public void setLineThicknessInPix(int lineThicknessInPix) {
        this.lineThicknessInPix = lineThicknessInPix;
    }

    public void setLineColorRGB(String lineColorRGB) {
        this.lineColorRGB = lineColorRGB;
    }

    public void setBackGroundColor(String backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public void setChartBackGroundColor(String chartBackGroundColor) {
        this.chartBackGroundColor = chartBackGroundColor;
    }

    @Override
    public String toString() {
        return "ChartConfig{" +
                "descriptionFontSize=" + descriptionFontSize +
                ", textColorRGB='" + textColorRGB + '\'' +
                ", chartWidth=" + chartWidth +
                ", chartHeight=" + chartHeight +
                ", maxMinHeightRangePercentage=" + maxMinHeightRangePercentage +
                ", timeFrameAxisDistanceInPixels=" + timeFrameAxisDistanceInPixels +
                ", zoomingMultiplier=" + zoomingMultiplier +
                ", defaultZoomLevel=" + defaultZoomLevel +
                ", fontSizeBottomMarginMultiplier=" + fontSizeBottomMarginMultiplier +
                ", fontSizeRightMarginMultiplier=" + fontSizeRightMarginMultiplier +
                ", frameLeftMarginPix=" + frameLeftMarginPix +
                ", frameTopMarginPix=" + frameTopMarginPix +
                ", frameThicknessInPix=" + frameThicknessInPix +
                ", frameColorRGB='" + frameColorRGB + '\'' +
                ", minimalDistanceBetweenGridLines=" + minimalDistanceBetweenGridLines +
                ", gridThicknessInPix=" + gridThicknessInPix +
                ", gridColorRGB='" + gridColorRGB + '\'' +
                ", lineThicknessInPix=" + lineThicknessInPix +
                ", lineColorRGB='" + lineColorRGB + '\'' +
                ", backGroundColor='" + backGroundColor + '\'' +
                ", chartBackGroundColor='" + chartBackGroundColor + '\'' +
                '}';
    }
}
