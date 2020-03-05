package frontend.chartDrawer.utilities.processor.utilities;

import frontend.chartDrawer.utilities.processor.utilities.utilities.ChartLayout;

import java.awt.*;

public class GeometricProcessor {
    //settings
    private static final int chartFrameLeftMarginPix = 2;
    private static final int chartFrameTopMarginPix = 2;

    public ChartLayout draw(ChartLayout chartLayout, int width, int height) {
        Rectangle backGround = drawBackGround(chartLayout);
        Rectangle chartFrame = drawChartFrame(chartLayout, width, height);
        return chartLayout;
    }

    private Rectangle drawBackGround(ChartLayout chartLayout){
        return new Rectangle(0,0, chartLayout.getWidth(), chartLayout.getHeight());
    }

    private Rectangle drawChartFrame(ChartLayout chartLayout, int marginWidth, int marginHeight) {
        return new Rectangle(0,0, chartLayout.getWidth(), chartLayout.getHeight());
    }
}
