package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.timeStampDescriptionGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.TimeStampCoord;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.TextField;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TimeStampTextFieldEditorEngineTest {

    private ChartDataDto chartDataDto = mock(ChartDataDto.class);
    private TimeStampTextEditorEngine timeStampTextEditorEngine = new TimeStampTextEditorEngine();
    private static List<TextField> timeStampDescriptionsPositioned = new ArrayList<>();
    private static List<TimeStampCoord> timeStampCoords = new ArrayList<>();
    private static TextField textField0 = new TextField(100, 135);
    private static TextField textField1 = new TextField(200, 135);
    private static TimeStampCoord timeStampCoord0 = new TimeStampCoord(100,
            LocalDateTime.of(2019,1,1,9,0),0);
    private static TimeStampCoord timeStampCoord1 = new TimeStampCoord(200,
            LocalDateTime.of(2020,2,25,11,0),2);

    @BeforeClass
    public static void fillLists() {
        timeStampDescriptionsPositioned.add(textField0);
        timeStampDescriptionsPositioned.add(textField1);

        timeStampCoords.add(timeStampCoord0);
        timeStampCoords.add(timeStampCoord1);
    }

    @Test
    public void testD1() {
        when(chartDataDto.getViewTimeFrame()).thenReturn(ViewTimeFrame.D1);

        TextField finalTextField0 = new TextField(100,135);
        finalTextField0.setContent("9:00");
        TextField finalTextField1 = new TextField(200,135);
        finalTextField1.setContent("11:00");

        Assert.assertEquals(finalTextField0.toString(),
                timeStampTextEditorEngine.process(timeStampDescriptionsPositioned,
                        timeStampCoords, chartDataDto).get(0).toString());

        Assert.assertEquals(finalTextField1.toString(),
                timeStampTextEditorEngine.process(timeStampDescriptionsPositioned,
                        timeStampCoords, chartDataDto).get(1).toString());
    }

    @Test
    public void testW1() {
        when(chartDataDto.getViewTimeFrame()).thenReturn(ViewTimeFrame.W1);

        TextField finalTextField0 = new TextField(100,135);
        finalTextField0.setContent("Jan 1");
        TextField finalTextField1 = new TextField(200,135);
        finalTextField1.setContent("Feb 25");

        Assert.assertEquals(finalTextField0.toString(),
                timeStampTextEditorEngine.process(timeStampDescriptionsPositioned,
                        timeStampCoords, chartDataDto).get(0).toString());

        Assert.assertEquals(finalTextField1.toString(),
                timeStampTextEditorEngine.process(timeStampDescriptionsPositioned,
                        timeStampCoords, chartDataDto).get(1).toString());
    }

    @Test
    public void testM1() {
        when(chartDataDto.getViewTimeFrame()).thenReturn(ViewTimeFrame.M1);

        TextField finalTextField0 = new TextField(100,135);
        finalTextField0.setContent("Jan 2019");
        TextField finalTextField1 = new TextField(200,135);
        finalTextField1.setContent("Feb 2020");

        Assert.assertEquals(finalTextField0.toString(),
                timeStampTextEditorEngine.process(timeStampDescriptionsPositioned,
                        timeStampCoords, chartDataDto).get(0).toString());

        Assert.assertEquals(finalTextField1.toString(),
                timeStampTextEditorEngine.process(timeStampDescriptionsPositioned,
                        timeStampCoords, chartDataDto).get(1).toString());
    }

    @Test
    public void testMAX() {
        when(chartDataDto.getViewTimeFrame()).thenReturn(ViewTimeFrame.MAX);

        TextField finalTextField0 = new TextField(100,135);
        finalTextField0.setContent("2019");
        TextField finalTextField1 = new TextField(200,135);
        finalTextField1.setContent("2020");

        Assert.assertEquals(finalTextField0.toString(),
                timeStampTextEditorEngine.process(timeStampDescriptionsPositioned,
                        timeStampCoords, chartDataDto).get(0).toString());

        Assert.assertEquals(finalTextField1.toString(),
                timeStampTextEditorEngine.process(timeStampDescriptionsPositioned,
                        timeStampCoords, chartDataDto).get(1).toString());
    }
}