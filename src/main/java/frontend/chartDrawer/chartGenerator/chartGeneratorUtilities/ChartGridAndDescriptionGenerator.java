package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.Line;
import frontend.chartDrawer.chartGenerator.chartParts.Rectangle;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ChartGridAndDescriptionGenerator {

    @Autowired
    private ChartConfig chartConfig;

    private List<Line> chartParts = new ArrayList<>();

    public List<Line> generate(CurrencyOverviewDto currencyOverviewDto, Rectangle chartBox) {
        chartParts.addAll(generateVerticalLines(currencyOverviewDto, chartBox));
        return chartParts;
    }

    private List<Line> generateVerticalLines(CurrencyOverviewDto currencyOverviewDto, Rectangle chartBox) {

        int numberOfDataPoints = currencyOverviewDto.getDataPoints().size();

        int distanceBetweenLines = chartBox.getWidth() / numberOfDataPoints;
        int minimalDistanceBetweenGridLines = chartConfig.getMinimalDistanceBetweenGridLines();

        if (distanceBetweenLines >= minimalDistanceBetweenGridLines) {
            return generateVerticalLinesBasedOnDistance(distanceBetweenLines, chartBox);
        } else {
            List<Integer> xPositions = requestAlternativeLinePlacing(currencyOverviewDto);
            return generateVerticalLinesBasedOnListOfPositions(xPositions);
        }
    }

    private List<Line> generateVerticalLinesBasedOnDistance(int distanceBetweenLines, Rectangle chartBox) {
        int startingPositionX = chartBox.getX();
        int y1 = chartBox.getY();
        int y2 = chartBox.getY() + chartBox.getHeight();

        Color lineColor = new Color(chartConfig.getGridColorRGB());

        List<Line> lines = new ArrayList<>();

        for(int i=0; i<distanceBetweenLines; i++) {
            int x = startingPositionX + (i + 1)* distanceBetweenLines;
            lines.add(new Line(lineColor,x,y1,x,y2));
        }
        return lines;
    }

    private List<Line> requestAlternativeLinePlacing(CurrencyOverviewDto currencyOverviewDto) {

    }

    private List<Line> generateVerticalLinesBasedOnListOfPositions(List<Integer> xPositions) {

    }
}
