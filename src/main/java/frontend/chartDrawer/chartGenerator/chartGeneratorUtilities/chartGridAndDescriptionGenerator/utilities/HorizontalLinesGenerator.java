package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.LineDto;

import java.util.ArrayList;
import java.util.List;

public class HorizontalLinesGenerator {

    public List<LineDto> process(List<ValueCoord> valueCoords, ChartDataDto chartDataDto) {
        int chartBoxLeftEdge = chartDataDto.getChartConfig().getChartBoxLeftBottomCornerX();
        int chartBoxWidth = chartDataDto.getChartConfig().getChartBoxWidth();

        int xStart = chartBoxLeftEdge;
        int xStop = chartBoxLeftEdge + chartBoxWidth;

        Color lineColor = new Color(chartDataDto.getChartConfig().getGridColorRGB());
        int thickness = chartDataDto.getChartConfig().getGridThicknessInPix();

        List<LineDto> horizontalLineDtos = new ArrayList<>();
        for(ValueCoord valueCoord : valueCoords) {
            int y = valueCoord.getY();
            horizontalLineDtos.add(new LineDto(lineColor, thickness, xStart, y, xStop, y));
        }
        return horizontalLineDtos;
    }

}
