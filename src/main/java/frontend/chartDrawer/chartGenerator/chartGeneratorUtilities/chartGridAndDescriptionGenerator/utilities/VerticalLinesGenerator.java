package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.Line;

import java.util.ArrayList;
import java.util.List;

public class VerticalLinesGenerator {
    private int chartBoxX;
    private int chartBoxY;
    private int chartBoxHeight;
    private int lineThickness;
    private Color lineColor;

    public List<Line> process(ChartDataDto chartDataDto, List<TimeStampCoord> timeStampCoords) {
        this.chartBoxX = chartDataDto.getChartConfig().getChartBoxLeftBottomCornerX();
        this.chartBoxY = chartDataDto.getChartConfig().getChartBoxLeftBottomCornerY();
        this.chartBoxHeight = chartDataDto.getChartConfig().getChartBoxHeight();
        this.lineThickness = chartDataDto.getChartConfig().getGridThicknessInPix();
        this.lineColor = new Color(chartDataDto.getChartConfig().getGridColorRGB());

        List<Line> lines = new ArrayList<>();
        for(int i=0; i<timeStampCoords.size(); i++){
            lines.add(positionLine(timeStampCoords.get(i)));
        }

        return lines;
    }

    private Line positionLine(TimeStampCoord timeStampCoord){
        int x = chartBoxX + timeStampCoord.getX();
        int y1 = chartBoxY;
        int y2 = chartBoxY - chartBoxHeight;

        return new Line(lineColor,lineThickness, x, y1, x, y2);
    }
}
