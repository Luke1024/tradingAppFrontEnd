package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.client.dto.DataPointDto;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VisibleTimeStampsFilterTest {

    private VisibleTimeStampsFilter visibleTimeStampsFilter = new VisibleTimeStampsFilter();

    @Test
    public void selectVisibleTimeStamps() {
        double min = 2.0;
        double max = 3.0;
        LocalDateTime now = LocalDateTime.now();
        List<DataPointDto> pointValues = new ArrayList<>();

        for(int i=0; i<7; i++){
            pointValues.add(generateDataPoint(min, max, now, i));
        }

        ChartDataDto chartDataDto = mock(ChartDataDto.class, Mockito.RETURNS_DEEP_STUBS);

        when(chartDataDto.getChartConfig().getTextElementWidth()).thenReturn(200);
        when(chartDataDto.getChartConfig().getChartBoxWidth()).thenReturn(1000);
        when(chartDataDto.getCurrencyOverviewDto().getDataPoints()).thenReturn(pointValues);

        List<TimeStampCoord> timeStampCoords = visibleTimeStampsFilter.process(chartDataDto);

        for(TimeStampCoord timeStampCoord : timeStampCoords){
            System.out.println(timeStampCoord.getIndex());
        }

        Assert.assertEquals(1, timeStampCoords.get(0).getIndex());
        Assert.assertEquals(3, timeStampCoords.get(1).getIndex());
        Assert.assertEquals(5, timeStampCoords.get(2).getIndex());
    }

    private DataPointDto generateDataPoint(double min, double max, LocalDateTime now, int i) {
        LocalDateTime date = now.plusHours(i);
        double value = min + new Random().nextDouble() * (max - min);
        return new DataPointDto(date, value);
    }

    private TimeStampCoord processToTimeStampCoord(DataPointDto dataPointDto, int i) {
        return new TimeStampCoord(0, dataPointDto.getTimeStamp(), i);
    }
}