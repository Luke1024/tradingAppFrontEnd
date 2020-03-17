package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.Line;
import frontend.chartDrawer.chartGenerator.chartParts.Rectangle;
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

    public List<Line> generate(CurrencyOverviewDto currencyOverviewDto, Rectangle chartBox) {

        Parameters chartLineParameters = computeChartLineGenerationParameters(currencyOverviewDto,chartBox);

        List<Double> scaledValuesInDataPoints = minMaxScalling(currencyOverviewDto);
        List<Integer> valuesScaledToPixels = minMaxScallingToPixelValues(scaledValuesInDataPoints, chartLineParameters);
        List<Line> chartLineLines = drawLinesBetweenPoints(valuesScaledToPixels, chartLineParameters);

        return chartLineLines;
    }

    private Parameters computeChartLineGenerationParameters(CurrencyOverviewDto currencyOverviewDto, Rectangle chartBox) {

        int chartLineHeightRangeInPercentage = getChartLineRangeInPercentage(chartConfig.getMaxMinHeightRangePercentage());
        int topAndBottomMarginInPix = (chartBox.getHeight() * (100-chartLineHeightRangeInPercentage))/2;

        int heightRange = chartBox.getHeight()-topAndBottomMarginInPix*2;
        double stepInPixels = ((double) chartBox.getWidth())/currencyOverviewDto.getDataPoints().size();

        return new Parameters(0, topAndBottomMarginInPix, heightRange, stepInPixels);
    }

    private int getChartLineRangeInPercentage(int chartRangeInPercentage) {
        if(chartRangeInPercentage > 100){
            return 100;
        } else return chartRangeInPercentage;
    }



    private List<Double> minMaxScalling(CurrencyOverviewDto currencyOverviewDto) {
        double max = getHighestValueInDataPoints(currencyOverviewDto);
        double min = getLowestValueInDataPoints(currencyOverviewDto);

        List<Double> scaledValues = new ArrayList<>();
        List<DataPointDto> dataPointDtoList = currencyOverviewDto.getDataPoints();
        for(DataPointDto dataPointDto : dataPointDtoList) {
            double value = dataPointDto.getValue();
            double valueScaled = (value - min)/(max - min);
            scaledValues.add(valueScaled);
        }
        return scaledValues;
    }

    private double getHighestValueInDataPoints(CurrencyOverviewDto currencyOverviewDto){
        List<DataPointDto> dataPoints = currencyOverviewDto.getDataPoints();
        return dataPoints.stream().max(Comparator.comparing(DataPointDto::getValue)).map(d -> d.getValue()).orElseThrow(NoSuchElementException::new);
    }

    private double getLowestValueInDataPoints(CurrencyOverviewDto currencyOverviewDto){
        List<DataPointDto> dataPoints = currencyOverviewDto.getDataPoints();
        return dataPoints.stream().min(Comparator.comparing(DataPointDto::getValue)).map(d -> d.getValue()).orElseThrow(NoSuchElementException::new);
    }



    private List<Integer> minMaxScallingToPixelValues(List<Double> scaledValuesInDataPoints, Parameters chartLineParameters) {

        List<Integer> pixelHeightValues = new ArrayList<>();

        for(Double scaledValue : scaledValuesInDataPoints){
            pixelHeightValues.add((int) (scaledValue*chartLineParameters.heightRange));
        }
        return pixelHeightValues;
    }



    private List<Line> drawLinesBetweenPoints(List<Integer> valuesScaledToPixels, Parameters chartLineParameters) {
        double step = chartLineParameters.stepInPix;

        Color color = new Color(chartConfig.getLineColorRGB());
        List<Line> lines = new ArrayList<>();

        for(int i=0; i<valuesScaledToPixels.size()-1; i++){
            lines.add(new Line(color,(int) step*i, valuesScaledToPixels.get(i), (int) step*(i+1),
                    valuesScaledToPixels.get(i+1)));
        }
        return lines;
    }

    private class Parameters {
        int x;
        int y;
        int heightRange;
        double stepInPix;

        public Parameters(int x, int y, int heightRange, double stepInPix) {
            this.x = x;
            this.y = y;
            this.heightRange = heightRange;
            this.stepInPix = stepInPix;
        }
    }
}
