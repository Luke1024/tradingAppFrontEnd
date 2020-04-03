package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.ChartParametersProcessor;
import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.when;


public class VisibleTimeStampsFilterTest {

    @MockBean
    private ChartParameters chartParameters;

    private VisibleTimeStampsFilter visibleTimeStampsFilter = new VisibleTimeStampsFilter();

    @Test
    public void process() {
        /*
        when(chartParameters.getText().getHorizontalMarginFromCenter()).thenReturn();
        when(chartParameters.getChartBox().getWidth()).thenReturn();
        when(chartParameters.getUniversal().getCurrencyOverviewDto().getDataPoints()).thenReturn();

        List<TimeStampCoord> timeStampCoords = visibleTimeStampsFilter.process(chartParameters);

*/

    }
}