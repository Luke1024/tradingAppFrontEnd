package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartLineGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Line;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LineCoordinatesPositionerTest {

    private LineCoordinatesPositioner lineCoordinatesPositioner = new LineCoordinatesPositioner();

    @Test
    public void testLineCoordinatesPositioning() {
        ChartDataDto chartDataDto = mock(ChartDataDto.class, Mockito.RETURNS_DEEP_STUBS);
        when(chartDataDto.getChartConfig().getChartBoxWidth()).thenReturn(100);
        when(chartDataDto.getChartConfig().getChartBoxLeftBottomCornerX()).thenReturn(10);
        when(chartDataDto.getChartConfig().getChartBoxLeftBottomCornerY()).thenReturn(20);

        List<Integer> valuesInPixelHeights = new ArrayList<>();
        valuesInPixelHeights.addAll(Arrays.asList(20,60,150));

        List<Line> positionedLines = new ArrayList<>();

        positionedLines.addAll(Arrays.asList(
                new Line(10,40,43,80),
                new Line(43,80,76,170)
        ));

        Assert.assertEquals(positionedLines.toString(),lineCoordinatesPositioner.process(valuesInPixelHeights, chartDataDto).toString());
    }
}