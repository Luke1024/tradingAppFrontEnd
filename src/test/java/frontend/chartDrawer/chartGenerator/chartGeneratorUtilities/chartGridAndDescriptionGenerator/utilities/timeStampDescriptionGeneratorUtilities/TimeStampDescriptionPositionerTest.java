package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.timeStampDescriptionGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.TimeStampCoord;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Text;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TimeStampDescriptionPositionerTest {

    private TimeStampDescriptionPositioner timeStampDescriptionPositioner = new TimeStampDescriptionPositioner();

    @Test
    public void testPositioning() {
        ChartDataDto chartDataDto = mock(ChartDataDto.class, Mockito.RETURNS_DEEP_STUBS);

        List<TimeStampCoord> timeStampCoords = mock(List.class, Mockito.RETURNS_DEEP_STUBS);

        TimeStampCoord timeStampCoord0 = new TimeStampCoord(100, LocalDateTime.now(),0);
        TimeStampCoord timeStampCoord1 = new TimeStampCoord(200, LocalDateTime.now(),2);
        TimeStampCoord timeStampCoord2 = new TimeStampCoord(300, LocalDateTime.now(),4);

        when(chartDataDto.getChartConfig().getChartBoxLeftBottomCornerX()).thenReturn(100);
        when(chartDataDto.getChartConfig().getChartBoxLeftBottomCornerY()).thenReturn(150);
        when(chartDataDto.getChartConfig().getTextElementHeight()).thenReturn(30);

        when(timeStampCoords.size()).thenReturn(3);
        when(timeStampCoords.get(0)).thenReturn(timeStampCoord0);
        when(timeStampCoords.get(1)).thenReturn(timeStampCoord1);
        when(timeStampCoords.get(2)).thenReturn(timeStampCoord2);

        Text text0 = new Text(200, 135);
        Text text1 = new Text(300, 135);
        Text text2 = new Text(400, 135);

        Assert.assertEquals(text0.toString(), timeStampDescriptionPositioner.process(chartDataDto, timeStampCoords).get(0).toString());
        Assert.assertEquals(text1.toString(), timeStampDescriptionPositioner.process(chartDataDto, timeStampCoords).get(1).toString());
        Assert.assertEquals(text2.toString(), timeStampDescriptionPositioner.process(chartDataDto, timeStampCoords).get(2).toString());
    }
}