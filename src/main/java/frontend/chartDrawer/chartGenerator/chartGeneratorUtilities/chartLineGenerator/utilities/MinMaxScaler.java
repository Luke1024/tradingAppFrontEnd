package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartLineGenerator.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinMaxScaler {
    public List<Double> scale(List<Double> pointPriceValues) {
        double max = getHighestValueInDataPoints(pointPriceValues);
        double min = getLowestValueInDataPoints(pointPriceValues);
        return scale(pointPriceValues, max, min);
    }

    private double getHighestValueInDataPoints(List<Double> pointPriceValues) {
        return Collections.max(pointPriceValues);
    }

    private double getLowestValueInDataPoints(List<Double> pointPriceValues) {
        return Collections.min(pointPriceValues);
    }

    private List<Double> scale(List<Double> pointPriceValues, double max, double min) {
        List<Double> scaledValues = new ArrayList<>();
        for(Double value : pointPriceValues) {
            scaledValues.add((value - min)/(max - min));
        }
        return scaledValues;
    }
}
