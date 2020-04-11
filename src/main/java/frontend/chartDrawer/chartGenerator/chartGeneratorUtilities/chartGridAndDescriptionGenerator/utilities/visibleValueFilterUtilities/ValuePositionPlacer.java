package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.visibleValueFilterUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.ValueCoord;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;

import java.util.List;

public class ValuePositionPlacer {
    public void /* List<ValueCoord>*/ place(ChartDataDto chartDataDto, RangeAnalyzer.Range range) {
        int textElementHeight = chartDataDto.getChartConfig().getTextElementHeight();
        int minPosition = range.min;
        int maxPosition = range.max;

        //int cursor = minPosition;
        //while(true) {

        //}
    }
}
