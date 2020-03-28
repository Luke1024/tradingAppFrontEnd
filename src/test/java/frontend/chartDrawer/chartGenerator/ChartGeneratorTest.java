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
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChartGeneratorTest {

    @Autowired
    private Visualizer visualizer;

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

        Random random = new Random();
        random.nextDouble();





        CurrencyOverviewDto currencyOverviewDto = new CurrencyOverviewDto();
    }
}