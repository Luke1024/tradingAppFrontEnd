package frontend.chartDrawer.chartGenerator;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.client.dto.DataPointDto;
import frontend.config.ChartConfig;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ChartGeneratorTest {

    private ChartGenerator chartGenerator = new ChartGenerator();

    private DataPointDto dataPointDtoCorrect = new DataPointDto(LocalDateTime.now(), 0.23);

    private List<DataPointDto> dataPointDtoListCorrect = Arrays.asList(dataPointDtoCorrect, dataPointDtoCorrect);

    private CurrencyOverviewDto currencyOverviewDtoCorrect =
            new CurrencyOverviewDto("EURUSD", LocalDateTime.now(), dataPointDtoListCorrect);

    @Test
    public void testWhenChartDataDtoNull() {
        ChartDataDto chartDataDto = null;
        Image image = chartGenerator.generateChart(chartDataDto);
    }

    @Test
    public void generateChart() {
        double min = 2.0;
        double max = 3.0;
        int pointRange = 120;
        LocalDateTime now = LocalDateTime.now();

        Random random = new Random();
        List<DataPointDto> pointValues = new ArrayList<>();

        for(int i=0; i<120; i++){
            pointValues.add(generateDataPoint(min, max, now, i));
        }
        CurrencyOverviewDto currencyOverviewDto = new CurrencyOverviewDto("EURUSD", now.plusDays(120), pointValues);

        ChartConfig chartConfig = new ChartConfig();

        ChartDataDto chartDataDto = new ChartDataDto(currencyOverviewDto, ViewTimeFrame.D1, chartConfig);

        chartGenerator.visualizeChart(chartDataDto);
    }

    private DataPointDto generateDataPoint(double min, double max, LocalDateTime now, int i) {
        LocalDateTime date = now.plusHours(i);
        double value = min + new Random().nextDouble() * (max - min);
        return new DataPointDto(date, value);
    }
}