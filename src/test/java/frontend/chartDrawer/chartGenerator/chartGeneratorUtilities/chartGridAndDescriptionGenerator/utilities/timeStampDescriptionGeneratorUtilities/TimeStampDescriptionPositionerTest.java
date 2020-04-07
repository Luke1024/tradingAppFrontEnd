package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.timeStampDescriptionGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.TimeStampCoord;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.TextField;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TimeStampDescriptionPositionerTest {

    private TimeStampDescriptionPositioner timeStampDescriptionPositioner = new TimeStampDescriptionPositioner();

    @Test
    public void testPositioning() {
        ChartDataDto chartDataDto = mock(ChartDataDto.class, Mockito.RETURNS_DEEP_STUBS);

        List<TimeStampCoord> timeStampCoords = new ArrayList<>();

        TimeStampCoord timeStampCoord0 = new TimeStampCoord(100, LocalDateTime.now(),0);
        TimeStampCoord timeStampCoord1 = new TimeStampCoord(200, LocalDateTime.now(),2);
        TimeStampCoord timeStampCoord2 = new TimeStampCoord(300, LocalDateTime.now(),4);

        timeStampCoords.add(timeStampCoord0);
        timeStampCoords.add(timeStampCoord1);
        timeStampCoords.add(timeStampCoord2);

        when(chartDataDto.getChartConfig().getChartBoxLeftBottomCornerX()).thenReturn(100);
        when(chartDataDto.getChartConfig().getChartBoxLeftBottomCornerY()).thenReturn(150);
        when(chartDataDto.getChartConfig().getTextElementHeight()).thenReturn(30);

        TextField textField0 = new TextField(200, 135);
        TextField textField1 = new TextField(300, 135);
        TextField textField2 = new TextField(400, 135);

        Assert.assertEquals(textField0.toString(), timeStampDescriptionPositioner.process(chartDataDto, timeStampCoords).get(0).toString());
        Assert.assertEquals(textField1.toString(), timeStampDescriptionPositioner.process(chartDataDto, timeStampCoords).get(1).toString());
        Assert.assertEquals(textField2.toString(), timeStampDescriptionPositioner.process(chartDataDto, timeStampCoords).get(2).toString());
    }
}