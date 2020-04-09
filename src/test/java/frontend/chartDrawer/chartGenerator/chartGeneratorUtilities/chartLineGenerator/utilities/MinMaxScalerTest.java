package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartLineGenerator.utilities;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MinMaxScalerTest {

    private MinMaxScaler minMaxScaler = new MinMaxScaler();

    @Test
    public void scale() {
        List<Double> values = new ArrayList<>();
        values.add(1.5);
        values.add(3.0);
        values.add(4.5);

        List<Double> scaledValues = new ArrayList<>();
        scaledValues.add(0.0);
        scaledValues.add(0.5);
        scaledValues.add(1.0);

        Assert.assertEquals(scaledValues,minMaxScaler.scale(values));
    }
}