package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoordinateReverserTest {

    private CoordinateReverser coordinateReverser = new CoordinateReverser();

    @Test
    public void testAssertEquals() {
        ChartDataDto chartDataDto = mock(ChartDataDto.class, Mockito.RETURNS_DEEP_STUBS);

        when(chartDataDto.getChartConfig().getChartHeight()).thenReturn(200);

        List<ChartPart> listOfParts = new ArrayList<>();

        listOfParts.addAll(Arrays.asList(
                new Line(40,60,120,140),
                new Rectangle(30,40,60,80),
                new TextField(20,40)
        ));


        List<ChartPart> reversedParts = new ArrayList<>();

        reversedParts.addAll(Arrays.asList(
                new Line(40,140,120,60),
                new Rectangle(30,160,60,80),
                new TextField(20,160)
        ));

        Assert.assertEquals(reversedParts.toString(), coordinateReverser.reverse(listOfParts, chartDataDto).toString());
    }
}