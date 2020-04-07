package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.Line;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VerticalLinesGeneratorTest {

    private VerticalLinesGenerator verticalLinesGenerator = new VerticalLinesGenerator();

    @Test
    public void generateLinesTest() {
        ChartDataDto chartDataDto = mock(ChartDataDto.class, Mockito.RETURNS_DEEP_STUBS);

        List<TimeStampCoord> timeStampCoords = mock(List.class, Mockito.RETURNS_DEEP_STUBS);

        TimeStampCoord timeStampCoord0 = new TimeStampCoord(0, LocalDateTime.now(),0);
        TimeStampCoord timeStampCoord1 = new TimeStampCoord(50, LocalDateTime.now(),2);
        TimeStampCoord timeStampCoord2 = new TimeStampCoord(120, LocalDateTime.now(),4);

        when(chartDataDto.getChartConfig().getChartBoxLeftBottomCornerX()).thenReturn(100);
        when(chartDataDto.getChartConfig().getChartBoxLeftBottomCornerY()).thenReturn(150);
        when(chartDataDto.getChartConfig().getChartBoxHeight()).thenReturn(120);
        when(chartDataDto.getChartConfig().getGridThicknessInPix()).thenReturn(3);
        when(chartDataDto.getChartConfig().getGridColorRGB()).thenReturn("0,0,0");
        when(timeStampCoords.size()).thenReturn(3);
        when(timeStampCoords.get(0)).thenReturn(timeStampCoord0);
        when(timeStampCoords.get(1)).thenReturn(timeStampCoord1);
        when(timeStampCoords.get(2)).thenReturn(timeStampCoord2);

        Line line0 = new Line(new Color("0,0,0"),3,100,150,100,30);
        Line line1 = new Line(new Color("0,0,0"),3,150,150,150,30);
        Line line2 = new Line(new Color("0,0,0"),3,220,150,220,30);

        Assert.assertEquals(line0.toString(),verticalLinesGenerator.process(chartDataDto, timeStampCoords).get(0).toString());
        Assert.assertEquals(line1.toString(),verticalLinesGenerator.process(chartDataDto, timeStampCoords).get(1).toString());
        Assert.assertEquals(line2.toString(),verticalLinesGenerator.process(chartDataDto, timeStampCoords).get(2).toString());
    }
}