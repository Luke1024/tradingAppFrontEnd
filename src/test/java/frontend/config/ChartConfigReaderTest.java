package frontend.config;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class ChartConfigReaderTest {

    private ChartConfigReader chartConfigReader = new ChartConfigReader();

    @Test
    @Ignore
    public void readConfigFileTest() throws IOException {
        ChartConfig chartConfig = chartConfigReader.readConfigFile(
                "C:\\Users\\Luke\\IdeaProjects\\tradingAppFrontEnd\\src\\test\\resources\\chart_config.properties");

        //chart font
        Assert.assertEquals(20,chartConfig.getDescriptionFontSize());
        Assert.assertTrue(chartConfig.getTextColorRGB().equals("255,255,255"));

        //text settings
        Assert.assertTrue(chartConfig.getTextElementWidth() == 150);
        Assert.assertTrue(chartConfig.getTextElementHeight() == 40);

        //image size
        Assert.assertTrue(chartConfig.getChartWidth() == 1000);
        Assert.assertTrue(chartConfig.getChartHeight() == 200);

        //chartbox size
        Assert.assertTrue(chartConfig.getChartBoxWidth() == 700);
        Assert.assertTrue(chartConfig.getChartBoxHeight() == 150);

        //chartbox position
        Assert.assertTrue(chartConfig.getChartBoxLeftBottomCornerX() == 10);
        Assert.assertTrue(chartConfig.getChartBoxLeftBottomCornerY() == 10);

        //chartbox line settings
        Assert.assertTrue(chartConfig.getChartBoxLineThicknessInPix() == 2);
        Assert.assertTrue(chartConfig.getChartBoxLineColorRGB().equals("0,0,255"));

        //grid settings
        Assert.assertTrue(chartConfig.getGridThicknessInPix() == 1);
        Assert.assertTrue(chartConfig.getGridColorRGB().equals("0,255,0"));

        //chart line settings
        Assert.assertTrue(chartConfig.getLineThicknessInPix() == 2);
        Assert.assertTrue(chartConfig.getLineColorRGB().equals("255,0,0"));
        Assert.assertTrue(chartConfig.getLineChartBoxHeightRangeInPercentage() == 80);

        //background settings
        Assert.assertTrue(chartConfig.getBackGroundColor().equals("0,0,0"));
        Assert.assertTrue(chartConfig.getChartBoxBackGroundColor().equals("10,10,10"));
    }

    @Test
    @Ignore
    public void readConfigFileTestWithoutSomeValues() throws IOException {
        ChartConfig chartConfig = chartConfigReader.readConfigFile(
                "/home/luke/test_vaadin_project/vaadin_experimenting/src/test/resources/chart_config_with_values_missing.properties");

        //chart font
        Assert.assertTrue(chartConfig.getDescriptionFontSize() == 18);
        Assert.assertTrue(chartConfig.getTextColorRGB().equals("200,200,200"));

        //text settings
        Assert.assertTrue(chartConfig.getTextElementWidth() == 150);
        Assert.assertTrue(chartConfig.getTextElementHeight() == 40);

        //image size
        Assert.assertTrue(chartConfig.getChartWidth() == 1000);
        Assert.assertTrue(chartConfig.getChartHeight() == 200);

        //chartbox size
        Assert.assertTrue(chartConfig.getChartBoxWidth() == 700);
        Assert.assertTrue(chartConfig.getChartBoxHeight() == 150);

        //chartbox position
        Assert.assertTrue(chartConfig.getChartBoxLeftBottomCornerX() == 10);
        Assert.assertTrue(chartConfig.getChartBoxLeftBottomCornerY() == 10);

        //chartbox line settings
        Assert.assertTrue(chartConfig.getChartBoxLineThicknessInPix() == 2);
        Assert.assertTrue(chartConfig.getChartBoxLineColorRGB().equals("0,0,255"));

        //grid settings
        Assert.assertTrue(chartConfig.getGridThicknessInPix() == 1);
        Assert.assertTrue(chartConfig.getGridColorRGB().equals("0,255,0"));

        //chart line settings
        Assert.assertTrue(chartConfig.getLineThicknessInPix() == 2);
        Assert.assertTrue(chartConfig.getLineColorRGB().equals("255,0,0"));
        Assert.assertTrue(chartConfig.getLineChartBoxHeightRangeInPercentage() == 80);

        //background settings
        Assert.assertTrue(chartConfig.getBackGroundColor().equals("50,50,50"));
        Assert.assertTrue(chartConfig.getChartBoxBackGroundColor().equals("10,10,10"));
    }
}