package frontend.chartDrawer.chartGenerator;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartVisualizer.Visualizer;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.client.dto.DataPointDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChartGeneratorTest {

    @Autowired
    private ChartGenerator chartGenerator;

    private DataPointDto dataPointDtoCorrect = new DataPointDto(LocalDateTime.now(), 0.23);

    private List<DataPointDto> dataPointDtoListCorrect = Arrays.asList(dataPointDtoCorrect, dataPointDtoCorrect);

    private CurrencyOverviewDto currencyOverviewDtoCorrect =
            new CurrencyOverviewDto("EURUSD", LocalDateTime.now(), dataPointDtoListCorrect);

    @Test
    public void textChartGeneratorWhenCurrencyOverviewDtoNull() {
        CurrencyOverviewDto currencyOverviewDto = new CurrencyOverviewDto();

        Image image = chartGenerator.generateChart(currencyOverviewDtoCorrect, ViewTimeFrame.D1);
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

        chartGenerator.visualizeChart(currencyOverviewDto, ViewTimeFrame.D1);
    }

    private DataPointDto generateDataPoint(double min, double max, LocalDateTime now, int i) {
        LocalDateTime date = now.plusHours(i);
        double value = min + new Random().nextDouble() * (max - min);
        return new DataPointDto(date, value);
    }
}