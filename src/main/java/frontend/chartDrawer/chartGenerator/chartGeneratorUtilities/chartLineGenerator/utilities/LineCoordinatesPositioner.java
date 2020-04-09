package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartLineGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Line;

import java.util.ArrayList;
import java.util.List;

public class LineCoordinatesPositioner {

    public List<Line> process(List<Integer> valuesScaledToPixels, ChartDataDto chartDataDto) {
        double step = computeStepSize(chartDataDto, valuesScaledToPixels);

        List<Line> connectPointsWithLines = connectLines(valuesScaledToPixels, step);
        return moveLinesToMatchChartBoxPositioning(connectPointsWithLines, chartDataDto);
    }

    private double computeStepSize(ChartDataDto chartDataDto, List<Integer> valuesScaledToPixels){
        int chartBoxWidth = chartDataDto.getChartConfig().getChartBoxWidth();
        int dataPointsNumber = valuesScaledToPixels.size();

        return ((double) chartBoxWidth) / dataPointsNumber;
    }

    private List<Line> connectLines(List<Integer> valuesScaledToPixels, double step){
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

    private List<Line> moveLinesToMatchChartBoxPositioning(List<Line> lines, ChartDataDto chartDataDto){
        int topChartBoxMargin = chartDataDto.getChartConfig().getChartBoxLeftBottomCornerY();
        int leftChartBoxMargin = chartDataDto.getChartConfig().getChartBoxLeftBottomCornerX();

        List<Line> movedLines = new ArrayList<>();

        for(Line line : lines){
            int x1 = line.getX1() + leftChartBoxMargin;
            int y1 = line.getY1() + topChartBoxMargin;
            int x2 = line.getX2() + leftChartBoxMargin;
            int y2 = line.getY2() + topChartBoxMargin;
            movedLines.add(new Line(line.getColor(),line.getThickness(),x1,y1,x2,y2));
        }
        return movedLines;
    }
}
