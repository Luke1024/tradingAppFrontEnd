package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartLineGenerator;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartLineGenerator.utilities.LineCoordinatesPositioner;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartLineGenerator.utilities.MinMaxScaler;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartLineGenerator.utilities.ChartLinePixelHeightPositioner;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.Line;
import frontend.client.dto.DataPointDto;

import java.util.List;
import java.util.stream.Collectors;

public class ChartLineGenerator {

    private MinMaxScaler minMaxScaler = new MinMaxScaler();
    private ChartLinePixelHeightPositioner chartLinePixelHeightPositioner = new ChartLinePixelHeightPositioner();
    private LineCoordinatesPositioner lineCoordinatesPositioner = new LineCoordinatesPositioner();

    public List<Line> generate(ChartDataDto chartDataDto) {

        List<Double> scaledValuesInDataPoints = minMaxScalling(chartDataDto);
        List<Integer> pixelsHeights = processValuesToPixelHeight(scaledValuesInDataPoints, chartDataDto);
        List<Line> chartLineLines = drawLinesBetweenPoints(pixelsHeights, chartDataDto);
        return setColorAndThickness(chartLineLines, chartDataDto);
    }

    private List<Double> minMaxScalling(ChartDataDto chartDataDto) {
        List<DataPointDto> dataPointDtos = chartDataDto.getCurrencyOverviewDto().getDataPoints();
        List<Double> pointPriceValues = dataPointDtos.stream()
                .map(dataPointDto -> dataPointDto.getValue())
                .collect(Collectors.toList());

        return minMaxScaler.scale(pointPriceValues);
    }

    private List<Integer> processValuesToPixelHeight(List<Double> scaledValuesInDataPoints, ChartDataDto chartDataDto) {
        return chartLinePixelHeightPositioner.process(scaledValuesInDataPoints, chartDataDto);
    }

    private List<Line> drawLinesBetweenPoints(List<Integer> valuesScaledToPixels, ChartDataDto chartDataDto) {
        return lineCoordinatesPositioner.process(valuesScaledToPixels, chartDataDto);
    }

    private List<Line> setColorAndThickness(List<Line> chartLineLines, ChartDataDto chartDataDto){
        Color lineColor = new Color(chartDataDto.getChartConfig().getLineColorRGB());
        int lineThickness = chartDataDto.getChartConfig().getLineThicknessInPix();

        for(Line line : chartLineLines) {
            line.setColor(lineColor);
            line.setThickness(lineThickness);
        }
        return chartLineLines;
    }
}
