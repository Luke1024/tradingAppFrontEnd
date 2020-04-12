package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.visibleValueFilterUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.ValueCoord;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.client.dto.DataPointDto;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PositionValueCalculatorTest {

    private PositionValueCalculator positionValueCalculator = new PositionValueCalculator();

    @Test
    public void calculatingValueTest() {
        ChartDataDto chartDataDto = mock(ChartDataDto.class, Mockito.RETURNS_DEEP_STUBS);

        LocalDateTime localDateTime = LocalDateTime.now();

        List<DataPointDto> dataPointDtos = new ArrayList<>();
        dataPointDtos.addAll(Arrays.asList(
                new DataPointDto(localDateTime,1.5),
                new DataPointDto(localDateTime, 2.0),
                new DataPointDto(localDateTime,4.5)
        ));

        when(chartDataDto.getCurrencyOverviewDto().getDataPoints()).thenReturn(dataPointDtos);

        List<ValueCoord> inputValueCoords = new ArrayList<>();

        inputValueCoords.addAll(Arrays.asList(
                new ValueCoord(50),
                new ValueCoord(75),
                new ValueCoord(80),
                new ValueCoord(100)
        ));

        List<ValueCoord> expectedtValueCoords = new ArrayList<>();
        expectedtValueCoords.addAll(Arrays.asList(
                new ValueCoord(50, 1.5),
                new ValueCoord(75, 3.0),
                new ValueCoord(80,3.3),
                new ValueCoord(100, 4.5)
        ));

        Assert.assertEquals(expectedtValueCoords.toString(),
                positionValueCalculator.process(inputValueCoords, chartDataDto).toString());
    }

}