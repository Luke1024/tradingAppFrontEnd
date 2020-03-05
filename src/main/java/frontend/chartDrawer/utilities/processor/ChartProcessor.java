package frontend.chartDrawer.utilities.processor;

import frontend.chartDrawer.utilities.dataObjects.ChartControlData;
import frontend.chartDrawer.utilities.drawer.utilities.LineAndBackGroundDrawer;
import frontend.chartDrawer.utilities.processor.utilities.ChartLayout;
import frontend.client.dto.OverviewDtoPack;

public class ChartProcessor {
    //settings
    private static final double fontSizeHeightMarginMultiplier = 2;
    private static final double fontSizeWidthMarginMultiplier = 3;

    private LineAndBackGroundDrawer lineDrawer = new LineAndBackGroundDrawer();
    private DescriptionDrawer descriptionDrawer = new DescriptionDrawer();

    public ChartLayout processChart(ChartControlData chartControlData, OverviewDtoPack overviewDtoPack) {
        ChartBoxSize chartBoxSize = computeChartBoxSize(chartControlData);

        ChartLayout chartLayout = new ChartLayout();
        lineDrawer.draw(chartLayout);
        descriptionDrawer.draw(chartLayout);
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
