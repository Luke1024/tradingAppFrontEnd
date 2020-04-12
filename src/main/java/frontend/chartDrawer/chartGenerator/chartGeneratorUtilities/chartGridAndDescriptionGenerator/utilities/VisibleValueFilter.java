package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.visibleValueFilterUtilities.PositionValueCalculator;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.visibleValueFilterUtilities.RangeAnalyzer;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.visibleValueFilterUtilities.ValuePositionPlacer;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;

import java.util.List;

public class VisibleValueFilter {

    private RangeAnalyzer rangeAnalyzer = new RangeAnalyzer();
    private ValuePositionPlacer valuePositionPlacer = new ValuePositionPlacer();
    private PositionValueCalculator positionValueCalculator = new PositionValueCalculator();

    public List<ValueCoord> process(ChartDataDto chartDataDto) {

        RangeAnalyzer.Range range = rangeAnalyzer.minMaxTextFieldPosition(chartDataDto);
        List<ValueCoord> valueCoords = valuePositionPlacer.place(chartDataDto, range);
        return positionValueCalculator.process(valueCoords, chartDataDto);
    }
}

/*
    private int determineIndexOfLastChangingNumber(double minValue, double maxValue) {
        String min = Double.toString(minValue);
        String max = Double.toString(maxValue);

        return StringUtils.indexOfDifference(min,max);
    }
}
*/


    //private static final int minimalNumberOfPricePoints = 3;
    //private static final double legendHeightRange = 0.6; //0.6 means 60%
/*
    public List<ChartGridAndDescriptionGenerator.TimeStampCoord> process(ChartParameters chartParameters) {

        int textElementHeight = getTextElementHeight(chartParameters);
        int legendHeight = computeHeightRange(chartParameters);
        int maxNumberOfPriceFields = getMaxNumberOfPriceFields(chartParameters, legendHeight);
        int minDistanceBetweenPriceFieldCentroids = getMinDistanceBetweenPriceFieldCentroids(chartParameters);
    }

    private int getTextElementHeight(ChartParameters chartParameters){
        int verticalMarginFromCenter = chartParameters.getText().getVerticalMarginFromCenter();
        return verticalMarginFromCenter*2;
    }

    private int computeHeightRange(ChartParameters chartParameters) {
        int chartBoxHeight = chartParameters.getChartBox().getHeight();
        int textFieldVerticalMargin = chartParameters.getText().getVerticalMarginFromCenter();
        return chartBoxHeight - textFieldVerticalMargin*2;
    }




    private int getMaxNumberOfPriceFields(ChartParameters chartParameters, int legendHeightRange) {
        int chartBoxHeight = chartParameters.getChartBox().getHeight();
        double legendHeightRangeChecked = checkHeightRange();
    }

    private double checkHeightRange(){
        if(legendHeightRange<=1) return legendHeightRange;
        else return 1;
    }


    private int getMinDistanceBetweenPriceFieldCentroids(ChartParameters chartParameters) {
        int chartBoxHeight = chartParameters.getChartBox().getHeight();
        int textFieldHeight
    }
    */
