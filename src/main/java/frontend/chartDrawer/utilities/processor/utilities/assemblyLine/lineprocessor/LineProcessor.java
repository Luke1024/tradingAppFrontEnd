package frontend.chartDrawer.utilities.processor.utilities.assemblyLine.lineprocessor;

import frontend.chartDrawer.utilities.processor.utilities.containerParts.Line;
import frontend.chartDrawer.utilities.processor.utilities.containerParts.Color;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class LineProcessor {

    private ChartLineProcessor lineProcessor = new ChartLineProcessor();

    @Autowired
    private ChartConfig chartConfig;

    public List<Line> process(CurrencyOverviewDto currencyOverviewDto) {
        List<Line> lineList = new ArrayList<>();

        Box box = computeChartBorders(currencyOverviewDto);

        lineList.addAll(processGrid(box,currencyOverviewDto));
        lineList.add(lineProcessor.processChartLine(currencyOverviewDto));
        lineList.add(processLegendLines(currencyOverviewDto));

        return lineList;
    }

    private Box computeChartBorders(CurrencyOverviewDto currencyOverviewDto) {
        int bottomMargin = (int) (chartConfig.getFontSizeBottomMarginMultiplier() * chartConfig.getFontSize());
        int rightMargin = (int) (chartConfig.getFontSizeRightMarginMultiplier() * chartConfig.getFontSize());
        int topMargin = chartConfig.getFrameTopMarginPix();
        int leftMargin = chartConfig.getFrameLeftMarginPix();
        int imageWidth = chartConfig.getChartWidth();
        int imageHeight = chartConfig.getChartHeight();

        Box box = new Box();

        box.x1 = leftMargin;
        box.y1 = topMargin;
        box.x2 = imageWidth - rightMargin;
        box.y2 = imageHeight - bottomMargin;

        return box;
    }

    private List<Line> processGrid(Box box ,CurrencyOverviewDto currencyOverviewDto) {
        int gridDistance = computeGridDistance(currencyOverviewDto.getDataPoints().size());
        return processGridLines(gridDistance, box);
    }

    private List<Line> processGridLines(int gridDistance, Box box) {
        List<Line> gridLines = new ArrayList<>();

        int width = box.x2 - box.x1;
        int height = box.y2 - box.y1;
        int numberOfLines = width/gridDistance;

        Color color = new Color(chartConfig.getGridColorRGB());

        for(int i=0; i<numberOfLines; i++) {
            gridLines.add(new Line(color,box.x1 + i*gridDistance, box.y1, box.x1 + i*gridDistance, box.y1 + height));
        }
        return gridLines;
    }

    private int computeGridDistance(int gridSize) {
        return chartConfig.getChartWidth()/gridSize;
    }

    private class Box{
        public Box() {}
        int x1;
        int y1;
        int x2;
        int y2;
    }
}
