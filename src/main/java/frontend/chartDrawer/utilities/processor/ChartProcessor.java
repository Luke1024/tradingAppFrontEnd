package frontend.chartDrawer.utilities.processor;

import com.vaadin.flow.component.Text;
import frontend.chartDrawer.utilities.processor.utilities.ChartLayout;
import frontend.chartDrawer.utilities.processor.utilities.Line;
import frontend.chartDrawer.utilities.processor.utilities.Rectangle;
import frontend.chartDrawer.utilities.processor.utilities.lineprocessor.LineProcessor;
import frontend.chartDrawer.utilities.processor.utilities.rectangleprocessor.RectangleProcessor;
import frontend.chartDrawer.utilities.processor.utilities.textprocessor.TextProcessor;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ChartProcessor {
    @Autowired
    private ChartConfig chartConfig;

    private RectangleProcessor rectangleProcessor = new RectangleProcessor();
    private LineProcessor lineProcessor = new LineProcessor();
    private TextProcessor textProcessor = new TextProcessor();

    public ChartLayout processChart(CurrencyOverviewDto currencyOverviewDto) {
        List<Rectangle> rectangleList = rectangleProcessor.process();
        List<Line> lineList = lineProcessor.process(currencyOverviewDto);
        List<Text> textList = textProcessor.process(currencyOverviewDto);

        return chartLayout;
    }
}
