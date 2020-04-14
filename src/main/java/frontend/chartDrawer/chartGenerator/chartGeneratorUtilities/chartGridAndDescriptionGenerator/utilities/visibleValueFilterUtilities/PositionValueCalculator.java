package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.visibleValueFilterUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.ValueCoord;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PositionValueCalculator {

    public List<ValueCoord> process(List<ValueCoord> valueCoords, ChartDataDto chartDataDto) {
        Ranges ranges = computeRanges(valueCoords, chartDataDto);
        return calculateValues(ranges, valueCoords);
    }

    private Ranges computeRanges(List<ValueCoord> valueCoords, ChartDataDto chartDataDto){
        List<Double> values = chartDataDto.getCurrencyOverviewDto()
                .getDataPoints().stream().map(dataPointDto -> dataPointDto.getValue())
                .collect(Collectors.toList());

        double minValue = Collections.min(values);
        double maxValue = Collections.max(values);

        List<Integer> coordsValues = valueCoords.stream().map(valueCoord -> valueCoord.getY())
                .collect(Collectors.toList());

        int minCoord = Collections.min(coordsValues);
        int maxCoord = Collections.max(coordsValues);

        return new Ranges(minValue, maxValue, minCoord, maxCoord);
    }

    private List<ValueCoord> calculateValues(Ranges ranges, List<ValueCoord> valueCoords) {

        for(ValueCoord valueCoord : valueCoords){
            valueCoord.setValue(computeSingleValue(valueCoord, ranges));
        }
        return valueCoords;
    }

    private double computeSingleValue(ValueCoord valueCoord, Ranges ranges) {
        int coordRange = ranges.maxCoord - ranges.minCoord;
        double valueRange = ranges.maxValue - ranges.minValue;

        double heightCoefficient = ((double) (valueCoord.getY() - ranges.minCoord))/coordRange;

        return valueRange * heightCoefficient + ranges.minValue;
    }

    private class Ranges {
        double minValue;
        double maxValue;
        int minCoord;
        int maxCoord;

        public Ranges(double minValue, double maxValue, int minCoord, int maxCoord) {
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.minCoord = minCoord;
            this.maxCoord = maxCoord;
        }
    }

}
