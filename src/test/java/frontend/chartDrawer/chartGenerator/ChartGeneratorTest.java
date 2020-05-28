package frontend.chartDrawer.chartGenerator;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.CurrencyOverviewDto;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.DataPointDto;
import frontend.config.ChartConfig;
import frontend.config.ChartConfigReader;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ChartGeneratorTest {

    private ChartGenerator chartGenerator = new ChartGenerator();

    private DataPointDto dataPointDtoCorrect = new DataPointDto(LocalDateTime.now(), 0.23);

    private List<DataPointDto> dataPointDtoListCorrect = Arrays.asList(dataPointDtoCorrect, dataPointDtoCorrect);

    private ChartConfigReader chartConfigReader = new ChartConfigReader();

    private CurrencyOverviewDto currencyOverviewDtoCorrect =
            new CurrencyOverviewDto("EURUSD", LocalDateTime.now(), dataPointDtoListCorrect);

    @Test
    public void testWhenChartDataDtoNull() {
        ChartDataDto chartDataDto = null;
        Image image = chartGenerator.generateChart(chartDataDto);
    }

    @Test
    public void generateChart() throws IOException {
        double min = 2.0;
        double max = 3.0;
        LocalDateTime now = LocalDateTime.now();

        Random random = new Random();
        List<DataPointDto> pointValues = new ArrayList<>();

        for(int i=0; i<3; i++){
            pointValues.add(generateDataPoint(min, max, now, i));
        }
        CurrencyOverviewDto currencyOverviewDto = new CurrencyOverviewDto("EURUSD", now.plusDays(120), pointValues);

        ChartConfig chartConfig = chartConfigReader.readConfigFile("/home/luke/test_vaadin_project/vaadin_experimenting/src/test/resources/chart_config.properties");

        ChartDataDto chartDataDto = new ChartDataDto(currencyOverviewDto, ViewTimeFrame.W1, chartConfig);

        chartGenerator.visualizeChart(chartDataDto);
    }

    private DataPointDto generateDataPoint(double min, double max, LocalDateTime now, int i) {
        LocalDateTime date = now.plusHours(i);
        double value = min + new Random().nextDouble() * (max - min);
        return new DataPointDto(date, value);
    }
}