package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.LineDto;

import java.util.ArrayList;
import java.util.List;

public class VerticalLinesGenerator {
    private int chartBoxX;
    private int chartBoxY;
    private int chartBoxHeight;
    private int lineThickness;
    private Color lineColor;

    public List<LineDto> process(ChartDataDto chartDataDto, List<TimeStampCoord> timeStampCoords) {
        this.chartBoxX = chartDataDto.getChartConfig().getChartBoxLeftBottomCornerX();
        this.chartBoxY = chartDataDto.getChartConfig().getChartBoxLeftBottomCornerY();
        this.chartBoxHeight = chartDataDto.getChartConfig().getChartBoxHeight();
        this.lineThickness = chartDataDto.getChartConfig().getGridThicknessInPix();
        this.lineColor = new Color(chartDataDto.getChartConfig().getGridColorRGB());

        List<LineDto> lineDtos = new ArrayList<>();
        for(int i=0; i<timeStampCoords.size(); i++){
            lineDtos.add(positionLine(timeStampCoords.get(i)));
        }

        return lineDtos;
    }

    private LineDto positionLine(TimeStampCoord timeStampCoord){
        int x = chartBoxX + timeStampCoord.getX();
        int y1 = chartBoxY;
        int y2 = chartBoxY + chartBoxHeight;

        return new LineDto(lineColor,lineThickness, x, y1, x, y2);
    }
}
