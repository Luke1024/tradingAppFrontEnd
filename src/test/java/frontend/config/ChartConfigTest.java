package frontend.config;

import frontend.chartDrawer.utilities.processor.utilities.utilities.Color;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChartConfigTest {

    @Autowired
    private ChartConfig chartConfig;

    @Test
    public void getFontSizeBottomMarginMultiplier() {
        Assert.assertEquals(2,chartConfig.getFontSizeBottomMarginMultiplier());
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
        Assert.assertEquals(new Color(0,0,0), (new Color(chartConfig.getFrameColorRGB())));
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