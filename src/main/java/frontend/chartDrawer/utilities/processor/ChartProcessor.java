package frontend.chartDrawer.utilities.processor;

import com.vaadin.flow.component.Text;
import frontend.chartDrawer.utilities.processor.utilities.TextProcessor;
import frontend.chartDrawer.utilities.processor.utilities.utilities.ChartLayout;
import frontend.chartDrawer.utilities.processor.utilities.utilities.Line;
import frontend.chartDrawer.utilities.processor.utilities.utilities.Rectangle;
import frontend.chartDrawer.utilities.processor.utilities.utilities.utilities.LineProcessor;
import frontend.chartDrawer.utilities.processor.utilities.utilities.utilities.RectangleProcessor;
import frontend.client.dto.OverviewDtoPack;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ChartProcessor {
    @Autowired
    private ChartConfig chartConfig;

    private RectangleProcessor rectangleProcessor = new RectangleProcessor();
    private LineProcessor lineProcessor = new LineProcessor();
    private TextProcessor textProcessor = new TextProcessor();

    public ChartLayout processChart(OverviewDtoPack overviewDtoPack) {
        List<Rectangle> rectangleList = rectangleProcessor.process();
        List<Line> lineList = lineProcessor.process(overviewDtoPack);
        List<Text> textList = textProcessor.process(overviewDtoPack);

        return chartLayout;
    }
}
