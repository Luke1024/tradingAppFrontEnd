package frontend.config;

public class ChartConfig {

    //chart font
    private int descriptionFontSize = 20;
    private String textColorRGB = "255,255,255";

    //chart size
    private int chartWidth = 1000;
    private int chartHeight = 200;
    private int maxMinHeightRangePercentage = 80;

    //chart scalling
    private int timeFrameAxisDistanceInPixels = 20;
    private double zoomingMultiplier = 1.2;
    private int defaultZoomLevel = 0;

    //font size margin multiplier
    private double fontSizeBottomMarginMultiplier = 2;
    private double fontSizeRightMarginMultiplier = 5;

    //frame margin left and top
    private int frameLeftMarginPix = 5;
    private int frameTopMarginPix = 5;

    //frame rectangle settings
    private int frameThicknessInPix = 2;
    private String frameColorRGB = "0,0,255";

    //grid settings
    private int minimalDistanceBetweenGridLines = 3;
    private int gridThicknessInPix = 1;
    private String gridColorRGB = "0,255,0";

    //chart line settings
    private int lineThicknessInPix = 2;
    private String lineColorRGB = "255,0,0";

    //chart background color
    private String backGroundColor = "50,50,50";
    private String chartBackGroundColor = "10,10,10";


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
