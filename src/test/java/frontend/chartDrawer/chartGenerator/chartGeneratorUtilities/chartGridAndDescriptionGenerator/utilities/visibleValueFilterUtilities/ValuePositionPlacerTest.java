package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.visibleValueFilterUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.ValueCoord;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValuePositionPlacerTest {

    private ValuePositionPlacer valuePositionPlacer = new ValuePositionPlacer();

    @Test
    public void testPositionPlacing(){
        ChartDataDto chartDataDto = mock(ChartDataDto.class, Mockito.RETURNS_DEEP_STUBS);
        when(chartDataDto.getChartConfig().getTextElementHeight()).thenReturn(30);

        RangeAnalyzer.Range range = new RangeAnalyzer.Range(50, 125);

        List<ValueCoord> expectedValueCoords = new ArrayList<>(Arrays.asList(
                new ValueCoord(50),
                new ValueCoord(80),
                new ValueCoord(110)
        ));

        Assert.assertEquals(expectedValueCoords.toString(),valuePositionPlacer.place(chartDataDto, range).toString());
    }
}