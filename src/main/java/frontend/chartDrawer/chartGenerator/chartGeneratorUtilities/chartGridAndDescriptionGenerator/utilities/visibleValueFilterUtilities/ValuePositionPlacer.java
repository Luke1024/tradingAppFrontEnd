package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.visibleValueFilterUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.ValueCoord;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;

import java.util.ArrayList;
import java.util.List;

public class ValuePositionPlacer {
    public List<ValueCoord> place(ChartDataDto chartDataDto, RangeAnalyzer.Range range) {
        int textElementHeight = chartDataDto.getChartConfig().getTextElementHeight();
        int minPosition = range.min;
        int maxPosition = range.max;


        List<ValueCoord> coords = new ArrayList<>();
        int cursor = minPosition;
        while(true) {
            coords.add(new ValueCoord(cursor));
            if(placeAvailable(maxPosition, textElementHeight, cursor)){
                cursor = cursor + textElementHeight;
            } else {
                break;
            }
        }
        return coords;
    }

    private boolean placeAvailable(int maxPosition, int textElementHeight, int cursor) {
        if(maxPosition >= cursor + textElementHeight) return true;
        else return false;
    }
}
