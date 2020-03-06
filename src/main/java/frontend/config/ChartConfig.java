package frontend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChartConfig {

    //chart font
    @Value("${chart.drawer.fontsize}")
    private int fontSize;

    //chart size
    @Value("${chart.drawer.chartWidth}")
    private int chartWidth;
    @Value("${chart.drawer.chartHeight}")
    private int chartHeight;
    @Value("${chart.drawer.maxMinHeightRangePercentage}")
    private int maxMinHeightRangePercentage;

    //chart scalling
    @Value("${chart.drawer.timeFrameAxisDistanceInPixels}")
    private int timeFrameAxisDistanceInPixels;
    @Value("${chart.drawer.zoomingMultiplier}")
    private double zoomingMultiplier;
    @Value("${chart.drawer.defaultZoomLevel}")
    private int defaultZoomLevel;

    //font size margin multiplier
    @Value("${chart.drawer.fontSizeBottomMarginMultiplier}")
    private double fontSizeBottomMarginMultiplier;
    @Value("${chart.drawer.fontSizeRightMarginMultiplier}")
    private double fontSizeRightMarginMultiplier;

    //frame margin left and top
    @Value("${chart.drawer.frameLeftMarginPix}")
    private int frameLeftMarginPix;
    @Value("${chart.drawer.frameTopMarginPix}")
    private int frameTopMarginPix;

    //frame rectangle settings
    @Value("${chart.drawer.frameThicknessInPix}")
    private int frameThicknessInPix;
    @Value("${chart.drawer.frameColorRGB}")
    private String frameColorRGB;

    //grid settings
    @Value("${chart.drawer.gridThicknessInPix}")
    private int gridThicknessInPix;
    @Value("${chart.drawer.gridColorRGB}")
    private String gridColorRGB;

    //chart line settings
    @Value("${chart.drawer.lineThicknessInPix}")
    private int lineThicknessInPix;
    @Value("${chart.drawer.lineColorRGB}")
    private String lineColorRGB;

    //chart background color
    @Value("${chart.drawer.backgroundColor}")
    private String backGroundColor;
    @Value("${chart.drawer.chartBackGroundColor}")
    private String chartBackGroundColor;

    public int getFontSize() {
        return fontSize;
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
