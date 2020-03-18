package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.*;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ChartGridAndDescriptionGenerator {

    @Autowired
    private ChartConfig chartConfig;

    private ChartAnnotationRuleEngine chartAnnotationRuleEngine;

    public List<ChartPart> generate(ChartParameters chartParameters) {
        List<ChartPart> chartParts = new ArrayList<>();
        chartParts.addAll(generateVerticalLinesWithTextDescription(chartParameters));
        return chartParts;
    }

    private List<ChartPart> generateVerticalLinesWithTextDescription(ChartParameters chartParameters) {
        CurrencyOverviewDto currencyOverviewDto = chartParameters.getUniversal().getCurrencyOverviewDto();
        int chartBoxWidth = chartParameters.getChartBox().getWidth();
        int numberOfDataPoints = currencyOverviewDto.getDataPoints().size();

        List<Integer> dataPointsIndexes = chartAnnotationRuleEngine.process(chartParameters);
    }
/*
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

    private List<Integer> requestAlternativeLinePlacing(CurrencyOverviewDto currencyOverviewDto, Rectangle chartBox) {

    }

    private List<Line> generateVerticalLinesBasedOnListOfPositions(List<Integer> xPositions, Rectangle chartBox) {
        int y1 = chartBox.getY();
        int y2 = chartBox.getY() + chartBox.getHeight();

        Color lineColor = new Color(chartConfig.getGridColorRGB());

        List<Line> lines = new ArrayList<>();

        for(Integer position : xPositions) {
            lines.add(new Line(lineColor,position,y1,position,y2));
        }
        return lines;
    }
    */
}
