package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.gridAndDescriptionGeneratorUtilities;

import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisiblePriceFilter {

    @Autowired
    private ChartConfig chartConfig;

    private static final int minimalNumberOfPricePoints = 3;
    private static final double legendHeightRange = 0.6; //0.6 means 60%
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
}
