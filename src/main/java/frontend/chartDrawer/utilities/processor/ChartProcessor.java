package frontend.chartDrawer.utilities.processor;

import frontend.chartDrawer.utilities.dataObjects.ChartControlData;
import frontend.chartDrawer.utilities.processor.utilities.TextProcessor;
import frontend.chartDrawer.utilities.processor.utilities.GeometricProcessor;
import frontend.chartDrawer.utilities.processor.utilities.utilities.ChartLayout;
import frontend.client.dto.OverviewDtoPack;

public class ChartProcessor {
    //settings
    private static final double fontSizeHeightMarginMultiplier = 2;
    private static final double fontSizeWidthMarginMultiplier = 3;

    private GeometricProcessor geometricProcessor = new GeometricProcessor();
    private TextProcessor textProcessor = new TextProcessor();

    public ChartLayout processChart(ChartControlData chartControlData, OverviewDtoPack overviewDtoPack) {
        ChartBoxSize chartBoxSize = computeChartBoxSize(chartControlData);

        ChartLayout chartLayout = new ChartLayout();
        geometricProcessor.draw(chartLayout, chartBoxSize.width, chartBoxSize.height);
        //textProcessor.draw(chartLayout, chartBoxSize.width, chartBoxSize.height);
        return chartLayout;
    }

    public ChartBoxSize computeChartBoxSize(ChartControlData chartControlData){
        int fontSize = chartControlData.getFontSize();
        int marginHeight = (int) (fontSize * fontSizeHeightMarginMultiplier);
        int marginWidth = (int) (fontSize * fontSizeWidthMarginMultiplier);
        ChartBoxSize chartBoxSize = new ChartBoxSize();
        chartBoxSize.height = chartControlData.getHeight() - marginHeight;
        chartBoxSize.width = chartControlData.getWidth() - marginWidth;
        return chartBoxSize;
    }

    private class ChartBoxSize {
        int height;
        int width;
    }
}
