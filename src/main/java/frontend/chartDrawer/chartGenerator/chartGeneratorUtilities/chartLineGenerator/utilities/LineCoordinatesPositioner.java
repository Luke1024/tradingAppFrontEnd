package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartLineGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.LineDto;

import java.util.ArrayList;
import java.util.List;

public class LineCoordinatesPositioner {

    public List<LineDto> process(List<Integer> valuesScaledToPixels, ChartDataDto chartDataDto) {
        double step = computeStepSize(chartDataDto, valuesScaledToPixels);

        List<LineDto> connectPointsWithLineDtos = connectLines(valuesScaledToPixels, step);
        return moveLinesToMatchChartBoxPositioning(connectPointsWithLineDtos, chartDataDto);
    }

    private double computeStepSize(ChartDataDto chartDataDto, List<Integer> valuesScaledToPixels){
        int chartBoxWidth = chartDataDto.getChartConfig().getChartBoxWidth();
        int dataPointsNumber = valuesScaledToPixels.size();

        return ((double) chartBoxWidth) / (dataPointsNumber-1);
    }

    private List<LineDto> connectLines(List<Integer> valuesScaledToPixels, double step){
        List<LineDto> lineDtos = new ArrayList<>();

        for(int i=0; i<valuesScaledToPixels.size()-1; i++){
            int x1 = (int) (step * i);
            int y1 = valuesScaledToPixels.get(i);
            int x2 = (int) (step * (i + 1));
            int y2 = valuesScaledToPixels.get(i + 1);
            lineDtos.add(new LineDto(x1,y1,x2,y2));
        }
        return lineDtos;
    }

    private List<LineDto> moveLinesToMatchChartBoxPositioning(List<LineDto> lineDtos, ChartDataDto chartDataDto){
        int topChartBoxMargin = chartDataDto.getChartConfig().getChartBoxLeftBottomCornerY();
        int leftChartBoxMargin = chartDataDto.getChartConfig().getChartBoxLeftBottomCornerX();

        List<LineDto> movedLineDtos = new ArrayList<>();

        for(LineDto lineDto : lineDtos){
            int x1 = lineDto.getX1() + leftChartBoxMargin;
            int y1 = lineDto.getY1() + topChartBoxMargin;
            int x2 = lineDto.getX2() + leftChartBoxMargin;
            int y2 = lineDto.getY2() + topChartBoxMargin;
            movedLineDtos.add(new LineDto(lineDto.getColor(), lineDto.getThickness(),x1,y1,x2,y2));
        }
        return movedLineDtos;
    }
}
