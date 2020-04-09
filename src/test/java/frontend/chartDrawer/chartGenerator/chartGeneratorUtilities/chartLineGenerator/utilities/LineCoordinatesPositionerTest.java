package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartLineGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Line;
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

        List<Integer> valuesInPixelHeights = new ArrayList<>();
        valuesInPixelHeights.addAll(Arrays.asList(20,60,150));

        List<Line> positionedLines = new ArrayList<>();

        positionedLines.addAll(Arrays.asList(
                new Line(0,20,33,60),
                new Line(33,60,66,150)
        ));

        positionedLines = lineCoordinatesPositioner.process(valuesInPixelHeights, chartDataDto);
    }

}