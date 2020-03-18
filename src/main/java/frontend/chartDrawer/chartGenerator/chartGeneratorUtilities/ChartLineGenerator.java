package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.Line;
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

    public List<Line> generate(ChartParameters chartParameters) {

        List<Double> scaledValuesInDataPoints = minMaxScalling(chartParameters);
        List<Integer> valuesScaledToPixels = minMaxScallingToPixelValues(scaledValuesInDataPoints, chartParameters);
        List<Line> chartLineLines = drawLinesBetweenPoints(valuesScaledToPixels, chartParameters);

        return chartLineLines;
    }

    private int getChartLineRangeInPercentage(int chartRangeInPercentage) {
        if(chartRangeInPercentage > 100) {
            return 100;
        } else return chartRangeInPercentage;
    }

    private List<Double> minMaxScalling(ChartParameters chartParameters) {
        CurrencyOverviewDto currencyOverviewDto = chartParameters.getUniversal().getCurrencyOverviewDto();

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

    private List<Integer> minMaxScallingToPixelValues(List<Double> scaledValuesInDataPoints, ChartParameters chartParameters) {
        int verticalRangePercent = chartParameters.getLine().getVerticalRangePercent();
        int heightInPix = chartParameters.getChartBox().getHeight();

        int verticalRangePix =  verticalRangePercent * heightInPix;

        List<Integer> pixelHeightValues = new ArrayList<>();

        for(Double scaledValue : scaledValuesInDataPoints){
            pixelHeightValues.add((int) (scaledValue * verticalRangePix));
        }
        return pixelHeightValues;
    }

    private List<Line> drawLinesBetweenPoints(List<Integer> valuesScaledToPixels, ChartParameters chartParameters) {
        double step = chartParameters.getChartBox().getStepPix();

        Color color = new Color(chartConfig.getLineColorRGB());
        List<Line> lines = new ArrayList<>();

        for(int i=0; i<valuesScaledToPixels.size()-1; i++){
            lines.add(new Line(color,(int) step*i, valuesScaledToPixels.get(i), (int) step*(i+1),
                    valuesScaledToPixels.get(i+1)));
        }
        return lines;
    }

}
