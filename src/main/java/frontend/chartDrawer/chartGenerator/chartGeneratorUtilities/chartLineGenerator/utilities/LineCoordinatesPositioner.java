package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartLineGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Line;

import java.util.ArrayList;
import java.util.List;

public class LineCoordinatesPositioner {

    public List<Line> process(List<Integer> valuesScaledToPixels, ChartDataDto chartDataDto) {
        double step = computeStepSize(chartDataDto, valuesScaledToPixels);

        List<Line> lines = new ArrayList<>();
        for(int i=0; i<valuesScaledToPixels.size()-1; i++){
            int x1 = (int) step * i;
            int y1 = valuesScaledToPixels.get(i);
            int x2 = (int) step * (i + 1);
            int y2 = valuesScaledToPixels.get(i + 1);
            lines.add(new Line(x1,y1,x2,y2));
        }
        return lines;
    }

    private double computeStepSize(ChartDataDto chartDataDto, List<Integer> valuesScaledToPixels){
        int chartBoxWidth = chartDataDto.getChartConfig().getChartBoxWidth();
        int dataPointsNumber = valuesScaledToPixels.size();

        return ((double) chartBoxWidth) / dataPointsNumber;
    }
}
