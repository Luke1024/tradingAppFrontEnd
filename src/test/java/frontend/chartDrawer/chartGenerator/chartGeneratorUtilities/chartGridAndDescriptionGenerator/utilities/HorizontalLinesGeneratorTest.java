package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.LineDto;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HorizontalLinesGeneratorTest {

    private HorizontalLinesGenerator generator = new HorizontalLinesGenerator();

    @Test
    public void testGeneratingLines(){
        ChartDataDto chartDataDto = mock(ChartDataDto.class, Mockito.RETURNS_DEEP_STUBS);

        when(chartDataDto.getChartConfig().getChartBoxLeftBottomCornerX()).thenReturn(10);
        when(chartDataDto.getChartConfig().getChartBoxWidth()).thenReturn(190);
        when(chartDataDto.getChartConfig().getGridColorRGB()).thenReturn("30,30,30");
        when(chartDataDto.getChartConfig().getGridThicknessInPix()).thenReturn(2);

        List<ValueCoord> valueCoords = new ArrayList<>(Arrays.asList(
                new ValueCoord(50),
                new ValueCoord(80),
                new ValueCoord(110)
        ));

        List<LineDto> expectedLineDtos = new ArrayList<>();
        expectedLineDtos.addAll(Arrays.asList(
                new LineDto(new Color("30,30,30"),2,10,50,200,50),
                new LineDto(new Color("30,30,30"),2,10,80,200,80),
                new LineDto(new Color("30,30,30"),2,10,110,200,110)
        ));

        Assert.assertEquals(expectedLineDtos.toString(), generator.process(valueCoords, chartDataDto).toString());
    }
}