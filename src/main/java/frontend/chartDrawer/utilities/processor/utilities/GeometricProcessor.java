package frontend.chartDrawer.utilities.processor.utilities;

import frontend.chartDrawer.utilities.processor.utilities.utilities.ChartLayout;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

public class GeometricProcessor {

    @Autowired
    private ChartConfig chartConfig;

    public ChartLayout draw(ChartLayout chartLayout) {
        Rectangle backGround = drawBackGround(chartLayout);
        ChartBoxSize chartBoxSize = computeChartBoxSize();
        Rectangle chartFrame = drawChartFrame(chartLayout, chartBoxSize);

        return chartLayout;
    }

    private Rectangle drawBackGround(ChartLayout chartLayout){
        return new Rectangle(0,0, chartLayout.getWidth(), chartLayout.getHeight());
    }

    private Rectangle drawChartFrame(ChartLayout chartLayout, ChartBoxSize chartBoxSize) {
        return new Rectangle(0,0, chartBoxSize.width, chartBoxSize.height);
    }

    public ChartBoxSize computeChartBoxSize(){
        int fontSize = chartConfig.getFontSize();
        int marginHeight = (int) (fontSize * chartConfig.getFontSizeBottomMarginMultiplier());
        int marginWidth = (int) (fontSize * chartConfig.getFontSizeRightMarginMultiplier());
        ChartBoxSize chartBoxSize = new ChartBoxSize();
        chartBoxSize.height = chartConfig.getChartHeight() - marginHeight;
        chartBoxSize.width = chartConfig.getChartWidth() - marginWidth;
        return chartBoxSize;
    }

    private class ChartBoxSize {
        int height;
        int width;
    }
}
