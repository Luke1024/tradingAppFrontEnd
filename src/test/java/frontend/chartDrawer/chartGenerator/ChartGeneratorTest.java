package frontend.chartDrawer.chartGenerator;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.CurrencyOverviewDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChartGeneratorTest {

    @Autowired
    private ChartGenerator chartGenerator;

    @Test
    public void generateChart() {
        CurrencyOverviewDto currencyOverviewDto = new CurrencyOverviewDto();

        Image image = chartGenerator.generateChart(currencyOverviewDto, ViewTimeFrame.D1);
        System.out.println(image.getSrc());

    }
}