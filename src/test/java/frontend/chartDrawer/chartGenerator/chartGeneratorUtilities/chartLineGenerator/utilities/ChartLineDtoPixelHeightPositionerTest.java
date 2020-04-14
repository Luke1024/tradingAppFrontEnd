package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartLineGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChartLineDtoPixelHeightPositionerTest {

    private ChartLinePixelHeightPositioner chartLinePixelHeightPositioner = new ChartLinePixelHeightPositioner();

    @Test
    public void testHeightPositioning() {
        ChartDataDto chartDataDto = mock(ChartDataDto.class, Mockito.RETURNS_DEEP_STUBS);

        when(chartDataDto.getChartConfig().getLineChartBoxHeightRangeInPercentage()).thenReturn(80);
        when(chartDataDto.getChartConfig().getChartBoxHeight()).thenReturn(200);

        List<Double> values = new ArrayList<>();
        values.add(0.0);
        values.add(0.2);
        values.add(0.4);
        values.add(1.0);

        List<Integer> pixelHeights = new ArrayList<>();
        pixelHeights.add(20);
        pixelHeights.add(52);
        pixelHeights.add(84);
        pixelHeights.add(180);

        Assert.assertEquals(pixelHeights, chartLinePixelHeightPositioner.process(values, chartDataDto));
    }
}