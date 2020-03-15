package frontend.chartDrawer.utilities.processor.utilities.test2.chartGeneratorUtilities;

import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.ChartPart;
import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.Color;
import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.Line;
import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.Rectangle;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.client.dto.DataPointDto;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class ChartLineGenerator {

    @Autowired
    private ChartConfig chartConfig;

    public List<ChartPart> generate(CurrencyOverviewDto currencyOverviewDto, Rectangle chartBox) {
        int chartLineRangeInPercentage = getChartLineRangeInPercentage(chartConfig.getMaxMinHeightRangePercentage());

        int topAndBottomMargin = (chartBox.getHeight() * (100-chartLineRangeInPercentage))/2;

        int startingPositionX = chartBox.getX();
        int highestPositionY1 = chartBox.getY();
        int lowestPositionY2 = chartBox.getY() + chartBox.getHeight();
        int chartHeight = chartBox.getHeight();

        int stepInPixels = chartBox.getWidth()/currencyOverviewDto.getDataPoints().size();

        double highestValuePoint = getHighestValueInDataPoints(currencyOverviewDto);
        double lowestValuePoint = getLowestValueInDataPoints(currencyOverviewDto);

        List<Double> scaledValuesInDataPoints = minMaxScalling(lowestValuePoint, highestValuePoint, currencyOverviewDto);
        List<Integer> valuesScaledToPixels = minMaxScallingToPixelValues(scaledValuesInDataPoints, chartHeight, topAndBottomMargin);
        List<Line> chartLineLines = drawLinesBetweenPoints(valuesScaledToPixels, stepInPixels);
        List<Line> chartLineLinesPositioned = moveChartLineToRequiredPosition(chartLineLines, startingPositionX, lowestPositionY2);
    }

    private int getChartLineRangeInPercentage(int chartRangeInPercentage){
        if(chartRangeInPercentage > 100){
            return 100;
        } else return chartRangeInPercentage;
    }

    private double getHighestValueInDataPoints(CurrencyOverviewDto currencyOverviewDto){
        List<DataPointDto> dataPoints = currencyOverviewDto.getDataPoints();
        return dataPoints.stream().max(Comparator.comparing(DataPointDto::getValue)).map(d -> d.getValue()).orElseThrow(NoSuchElementException::new);
    }

    private double getLowestValueInDataPoints(CurrencyOverviewDto currencyOverviewDto){
        List<DataPointDto> dataPoints = currencyOverviewDto.getDataPoints();
        return dataPoints.stream().min(Comparator.comparing(DataPointDto::getValue)).map(d -> d.getValue()).orElseThrow(NoSuchElementException::new);
    }

    private List<Double> minMaxScalling(double min, double max, CurrencyOverviewDto currencyOverviewDto) {
        List<Double> scaledValues = new ArrayList<>();
        List<DataPointDto> dataPointDtoList = currencyOverviewDto.getDataPoints();
        for(DataPointDto dataPointDto : dataPointDtoList) {
            double value = dataPointDto.getValue();
            double valueScaled = (value - min)/(max - min);
            scaledValues.add(valueScaled);
        }
        return scaledValues;
    }

    private List<Integer> minMaxScallingToPixelValues(List<Double> scaledValuesInDataPoints, int chartHeight, int topAndBottomMargin) {
        int chartRangeInPixels = chartHeight - topAndBottomMargin;

        List<Integer> pixelHeightValues = new ArrayList<>();

        for(Double scaledValue : scaledValuesInDataPoints){
            pixelHeightValues.add((int) (scaledValue*chartRangeInPixels));
        }
        return pixelHeightValues;
    }

    private List<Line> drawLinesBetweenPoints(List<Integer> valuesScaledToPixels, int stepInPixels) {
        int positionIndex = 0;
        Color color = new Color(chartConfig.getLineColorRGB());
        List<Line> lines = new ArrayList<>();

        for(int i=0; i<valuesScaledToPixels.size()-1; i++){
            lines.add(new Line(color, positionIndex*i, valuesScaledToPixels.get(i), positionIndex*(i+1), valuesScaledToPixels.get(i+1)));
        }
        return lines;
    }

    private List<Line> moveChartLineToRequiredPosition(List<Line> lines, int startingPositionX, int lowestPositionY2) {
        List<Line> positionedLines = new ArrayList<>();

        for(Line line : lines){
            int x = line.getX1() + startingPositionX;
            positionedLines.add(new Line(line.getColor(), x, ));
        }
    }
}
