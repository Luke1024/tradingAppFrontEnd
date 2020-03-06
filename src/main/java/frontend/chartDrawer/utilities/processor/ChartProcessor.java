package frontend.chartDrawer.utilities.processor;

import frontend.chartDrawer.utilities.processor.utilities.TextProcessor;
import frontend.chartDrawer.utilities.processor.utilities.GeometricProcessor;
import frontend.chartDrawer.utilities.processor.utilities.utilities.ChartLayout;
import frontend.client.dto.OverviewDtoPack;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class ChartProcessor {
    @Autowired
    private ChartConfig chartConfig;

    //settings
    private GeometricProcessor geometricProcessor = new GeometricProcessor();
    private TextProcessor textProcessor = new TextProcessor();

    public ChartLayout processChart(int zoom, OverviewDtoPack overviewDtoPack) {

        ChartLayout chartLayout = new ChartLayout();
        geometricProcessor.draw(chartLayout);
        //textProcessor.draw(chartLayout, chartBoxSize.width, chartBoxSize.height);
        return chartLayout;
    }

}
