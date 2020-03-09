package frontend.chartDrawer.utilities.processor;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.utilities.processor.utilities.Container;
import frontend.chartDrawer.utilities.processor.utilities.assemblyLine.ChartElementsDrawer;
import frontend.chartDrawer.utilities.processor.utilities.assemblyLine.ChartElementsGenerator;
import frontend.chartDrawer.utilities.processor.utilities.containerParts.Line;
import frontend.chartDrawer.utilities.processor.utilities.containerParts.Rectangle;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ChartProcessor {
    @Autowired
    private ChartConfig chartConfig;

    private ChartElementsGenerator chartElementsGenerator = new ChartElementsGenerator();
    private ChartElementsDrawer chartElementsDrawer = new ChartElementsDrawer();

    public Image generateChartImage(CurrencyOverviewDto currencyOverviewDto) {
        Container container = new Container();

        container.setCurrencyOverviewDto(currencyOverviewDto);

        chartElementsGenerator.generate(container);
        chartElementsDrawer.draw(container);

        return container.getImage();





        ElementPositioning positioning = preprocessor.preprocess;

        List<Rectangle> rectangleList = rectangleProcessor.process();
        List<Line> lineList = lineProcessor.process(currencyOverviewDto);
        List<Text> textList = textProcessor.process(currencyOverviewDto);

        return chartLayout;
    }

    private class ElementPositioning {
        int imageWidth;
        int imageHeight;
        int chartBoxWidth;
        int chartBoxHeight;

        private ElementPositioning() {}
    }
}
