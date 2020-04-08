package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartLineGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;

import java.util.ArrayList;
import java.util.List;

public class ChartLinePixelHeightPositioner {
    public List<Integer> process(List<Double> scaledValuesInDataPoints, ChartDataDto chartDataDto) {
        int verticalRangePercent = filterIncorrectPercentRange(chartDataDto.getChartConfig()
                .getLineChartBoxHeightRangeInPercentage());

        int chartLineRangeInPix = getChartLineRangeInPix(verticalRangePercent, chartDataDto);
        int bottomMargin = getBottomMargin(verticalRangePercent, chartLineRangeInPix);

        return computePixelHeight(scaledValuesInDataPoints,chartLineRangeInPix,bottomMargin);
    }

    private int filterIncorrectPercentRange(int heightRange) {
        if(heightRange > 100) return 100;
        if(heightRange < 0) return 0;
        return heightRange;
    }

    private int getChartLineRangeInPix(int verticalRangePercent, ChartDataDto chartDataDto) {
        int chartBoxHeight = chartDataDto.getChartConfig().getChartBoxHeight();
        return (int) ((double) verticalRangePercent/100 * chartBoxHeight);
    }

    private int getBottomMargin(int verticalRangePercent, int chartLineRangeInPix) {
        int bottomAndTopMargin = (int) (((double) 100 - verticalRangePercent) * chartLineRangeInPix);
        return bottomAndTopMargin/2;
    }

    private List<Integer> computePixelHeight(List<Double> scaledValuesInDataPoints,
                                             int chartLineRangeInPix, int bottomMargin) {

        List<Integer> chartLinePixelHeights = new ArrayList<>();

        for(Double value : scaledValuesInDataPoints) {
            chartLinePixelHeights.add((int) (value * chartLineRangeInPix + bottomMargin));
        }
        return chartLinePixelHeights;
    }
}
