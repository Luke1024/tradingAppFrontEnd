package frontend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChartConfig {

    @Value("${chart.drawer.fontSizeBottomMarginMultiplier}")
    private int fontSizeBottomMarginMultiplier;
    @Value("${chart.drawer.fontSizeRightMarginMultiplier}")
    private int fontSizeRightMarginMultiplier;

    @Value("${chart.drawer.frameLeftMarginPix}")
    private int frameLeftMarginPix;
    @Value("${chart.drawer.frameTopMarginPix}")
    private int frameTopMarginPix;

    @Value("${chart.drawer.frameThicknessInPix}")
    private int frameThicknessInPix;
    @Value("${chart.drawer.frameColorRGB}")
    private String frameColorRGB;

    @Value("${chart.drawer.gridThicknessInPix}")
    private int gridThicknessInPix;
    @Value("${chart.drawer.gridColorRGB}")
    private String gridColorRGB;

    @Value("${chart.drawer.lineThicknessInPix}")
    private int lineThicknessInPix;
    @Value("${chart.drawer.lineColorRGB}")
    private String lineColorRGB;


    public int getFontSizeBottomMarginMultiplier() {
        return fontSizeBottomMarginMultiplier;
    }

    public int getFontSizeRightMarginMultiplier() {
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
}
