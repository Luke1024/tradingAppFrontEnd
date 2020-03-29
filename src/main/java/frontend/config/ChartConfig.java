package frontend.config;
import org.springframework.stereotype.Component;

public class ChartConfig {

    //chart font
    private static int fontSize = 20;
    private static String textColorRGB = "255,255,255";

    //chart size
    private static int chartWidth = 1000;
    private static int chartHeight = 200;
    private static int maxMinHeightRangePercentage = 80;

    //chart scalling
    private static int timeFrameAxisDistanceInPixels = 20;
    private static double zoomingMultiplier = 1.2;
    private static int defaultZoomLevel = 0;

    //font size margin multiplier
    private static double fontSizeBottomMarginMultiplier = 2;
    private static double fontSizeRightMarginMultiplier = 5;

    //frame margin left and top
    private static int frameLeftMarginPix = 5;
    private static int frameTopMarginPix = 5;

    //frame rectangle settings
    private static int frameThicknessInPix = 2;
    private static String frameColorRGB = "0,0,255";

    //grid settings
    private static int minimalDistanceBetweenGridLines = 3;
    private static int gridThicknessInPix = 1;
    private static String gridColorRGB = "0,255,0";

    //chart line settings
    private static int lineThicknessInPix = 2;
    private static String lineColorRGB = "255,0,0";

    //chart background color
    private static String backGroundColor = "50,50,50";
    private static String chartBackGroundColor = "10,10,10";

    public int getFontSize() {
        return fontSize;
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

    public String getBorderColorRGB() {
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
}
