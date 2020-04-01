package frontend.config;

import org.junit.Assert;
import org.junit.Test;


public class ChartConfigWithConfigurationTest {

    private ChartConfigWithConfiguration chartConfigWithConfiguration = new ChartConfigWithConfiguration();

    @Test
    public void getFontSizeBottomMarginMultiplier() {
        Assert.assertEquals(2, chartConfigWithConfiguration.getFontSizeBottomMarginMultiplier());
    }

    @Test
    public void getFontSizeRightMarginMultiplier() {
    }

    @Test
    public void getFrameLeftMarginPix() {
    }

    @Test
    public void getFrameTopMarginPix() {
    }

    @Test
    public void getFrameThicknessInPix() {
    }

    @Test
    public void getFrameColorRGB() {
    }

    @Test
    public void getGridThicknessInPix() {
    }

    @Test
    public void getGridColorRGB() {
    }

    @Test
    public void getLineThicknessInPix() {
    }

    @Test
    public void getLineColorRGB() {
    }
}