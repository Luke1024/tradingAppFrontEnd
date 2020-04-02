package frontend.config;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ChartConfigReaderTest {

    private ChartConfigReader chartConfigReader = new ChartConfigReader();

    @Test
    public void readConfigFileTest() throws IOException {
        ChartConfig chartConfig = chartConfigReader.readConfigFile(
                "/home/luke/test_vaadin_project/vaadin_experimenting/src/test/resources/chart_config.properties");

        Assert.assertTrue(chartConfig.getDescriptionFontSize() == 20);

        Assert.assertTrue(chartConfig.getTextColorRGB().equals("255,255,255"));
        Assert.assertTrue(chartConfig.getChartWidth() == 1000);
        Assert.assertTrue(chartConfig.getChartHeight() == 200);
        Assert.assertTrue(chartConfig.getMaxMinHeightRangePercentage() == 80);
        Assert.assertTrue(chartConfig.getTimeFrameAxisDistanceInPixels() == 20);
        Assert.assertTrue(chartConfig.getZoomingMultiplier() == 1.2);
        Assert.assertTrue(chartConfig.getDefaultZoomLevel() == 0);
        Assert.assertTrue(chartConfig.getFontSizeBottomMarginMultiplier() == 2);
        Assert.assertTrue(chartConfig.getFontSizeRightMarginMultiplier() == 5);
        Assert.assertTrue(chartConfig.getFrameLeftMarginPix() == 5);
        Assert.assertTrue(chartConfig.getFrameTopMarginPix() == 5);
        Assert.assertTrue(chartConfig.getFrameThicknessInPix() == 2);
        Assert.assertTrue(chartConfig.getFrameColorRGB().equals("0,0,255"));
        Assert.assertTrue(chartConfig.getMinimalDistanceBetweenGridLines() == 3);
        Assert.assertTrue(chartConfig.getGridThicknessInPix() == 1);
        Assert.assertTrue(chartConfig.getGridColorRGB().equals("0,255,0"));
        Assert.assertTrue(chartConfig.getLineThicknessInPix() == 2);
        Assert.assertTrue(chartConfig.getLineColorRGB().equals("255,0,0"));
        Assert.assertTrue(chartConfig.getBackGroundColor().equals("50,50,50"));
        Assert.assertTrue(chartConfig.getChartBackGroundColor().equals("10,10,10"));
    }

    @Test
    public void readConfigFileTestWithoutSomeValues() throws IOException {
        ChartConfig chartConfig = chartConfigReader.readConfigFile(
                "/home/luke/test_vaadin_project/vaadin_experimenting/src/test/resources/chart_config_without_some_values.properties");

        Assert.assertTrue(chartConfig.getDescriptionFontSize() == 20);
        Assert.assertTrue(chartConfig.getTextColorRGB().equals("255,255,255"));
        Assert.assertTrue(chartConfig.getChartWidth() == 1000);
        Assert.assertTrue(chartConfig.getChartHeight() == 200);
        Assert.assertTrue(chartConfig.getMaxMinHeightRangePercentage() == 80);
        Assert.assertTrue(chartConfig.getTimeFrameAxisDistanceInPixels() == 20);
        Assert.assertTrue(chartConfig.getZoomingMultiplier() == 1.2);
        Assert.assertTrue(chartConfig.getDefaultZoomLevel() == 0);
        Assert.assertTrue(chartConfig.getFontSizeBottomMarginMultiplier() == 2);
        Assert.assertTrue(chartConfig.getFontSizeRightMarginMultiplier() == 5);
        Assert.assertTrue(chartConfig.getFrameLeftMarginPix() == 5);
        Assert.assertTrue(chartConfig.getFrameTopMarginPix() == 5);
        Assert.assertTrue(chartConfig.getFrameThicknessInPix() == 2);
        Assert.assertTrue(chartConfig.getFrameColorRGB().equals("0,0,255"));
        Assert.assertTrue(chartConfig.getMinimalDistanceBetweenGridLines() == 3);
        Assert.assertTrue(chartConfig.getGridThicknessInPix() == 1);
        Assert.assertTrue(chartConfig.getGridColorRGB().equals("0,255,0"));
        Assert.assertTrue(chartConfig.getLineThicknessInPix() == 2);
        Assert.assertTrue(chartConfig.getLineColorRGB().equals("255,0,0"));
        Assert.assertTrue(chartConfig.getBackGroundColor().equals("20,20,20"));
        Assert.assertTrue(chartConfig.getChartBackGroundColor().equals("5,5,5"));
    }
}