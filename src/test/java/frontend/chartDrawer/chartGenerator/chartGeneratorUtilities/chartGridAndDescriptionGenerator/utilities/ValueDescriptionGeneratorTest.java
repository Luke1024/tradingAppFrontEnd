package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.TextField;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import sun.awt.SunHints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValueDescriptionGeneratorTest {

    private ValueDescriptionGenerator generator = new ValueDescriptionGenerator();

    @Test
    public void testDescriptionGenerating(){
        ChartDataDto chartDataDto = mock(ChartDataDto.class, Mockito.RETURNS_DEEP_STUBS);

        when(chartDataDto.getChartConfig().getChartBoxLeftBottomCornerX()).thenReturn(10);
        when(chartDataDto.getChartConfig().getChartBoxWidth()).thenReturn(190);
        when(chartDataDto.getChartConfig().getTextElementWidth()).thenReturn(60);
        when(chartDataDto.getChartConfig().getTextColorRGB()).thenReturn("50,50,50");
        when(chartDataDto.getChartConfig().getDescriptionFontSize()).thenReturn(20);

        List<ValueCoord> valueCoords = new ArrayList<>();
        valueCoords.addAll(Arrays.asList(
                new ValueCoord(50,0.1232223423),
                new ValueCoord(75,1.21336757),
                new ValueCoord(100, 21.1242352)
        ));

        List<TextField> expectedTextFields = new ArrayList<>();
        expectedTextFields.addAll(Arrays.asList(
                new TextField(new Color("50,50,50"),230,50,20,"0,1232"),
                new TextField(new Color("50,50,50"),230,75,20,"1,2134"),
                new TextField(new Color("50,50,50"),230,100,20,"21,1242")
        ));


        Assert.assertEquals(expectedTextFields.toString(), generator.process(valueCoords,chartDataDto).toString());
    }
}