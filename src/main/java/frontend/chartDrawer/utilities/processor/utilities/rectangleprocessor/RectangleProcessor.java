package frontend.chartDrawer.utilities.processor.utilities.rectangleprocessor;

import frontend.chartDrawer.utilities.processor.utilities.Rectangle;
import frontend.chartDrawer.utilities.processor.utilities.Color;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RectangleProcessor {

    @Autowired
    private ChartConfig chartConfig;

    public List<Rectangle> process(){
        List<Rectangle> rectangleList = new ArrayList<>();

        rectangleList.add(drawBackGround());
        ChartBoxSize chartBoxSize = computeChartBoxSize();
        rectangleList.add(drawChartFrame(chartBoxSize));

        return rectangleList;
    }

    private Rectangle drawBackGround(){
        return new Rectangle(new Color(chartConfig.getBackGroundColor()),0,0, chartConfig.getChartWidth(), chartConfig.getChartHeight());
    }

    private Rectangle drawChartFrame(ChartBoxSize chartBoxSize) {
        return new Rectangle(new Color(chartConfig.getChartBackGroundColor()) ,0,0, chartBoxSize.width, chartBoxSize.height);
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
