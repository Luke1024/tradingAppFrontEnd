package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartLineGenerator.ChartLineGenerator;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.client.dto.DataPointDto;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ChartLineDtoGeneratorTest {

    private ChartLineGenerator chartLineGenerator = new ChartLineGenerator();

    @Test
    public void minMaxScallingTest() {
        ChartDataDto chartDataDto = mock(ChartDataDto.class, RETURNS_DEEP_STUBS);

        LocalDateTime localDateTime = LocalDateTime.now();
        List<DataPointDto> dataPointDtos = new ArrayList<>();

        DataPointDto dataPointDto1 = new DataPointDto(localDateTime, 1.5);
        DataPointDto dataPointDto2 = new DataPointDto(localDateTime, 3.0);
        DataPointDto dataPointDto3 = new DataPointDto(localDateTime, 4.5);

        dataPointDtos.add(dataPointDto1);
        dataPointDtos.add(dataPointDto2);
        dataPointDtos.add(dataPointDto3);

        CurrencyOverviewDto currencyOverviewDto = new CurrencyOverviewDto("EURUSD",
                localDateTime, dataPointDtos);

        List<Double> scaledValues = new ArrayList<>();

        scaledValues.add(0.0);
        scaledValues.add(0.5);
        scaledValues.add(1.0);

        when(chartDataDto.getCurrencyOverviewDto()).thenReturn(currencyOverviewDto);

        //Assert.assertEquals(scaledValues, chartLineGenerator.);
    }
}