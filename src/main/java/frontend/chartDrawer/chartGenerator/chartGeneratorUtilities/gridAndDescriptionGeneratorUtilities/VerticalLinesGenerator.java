package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.gridAndDescriptionGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.ChartGridAndDescriptionGenerator;
import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.Line;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VerticalLinesGenerator {
    private int chartBoxX;
    private int chartBoxY;
    private int chartBoxHeight;
    private int lineThickness;
    private Color lineColor;

    public List<Line> process(ChartParameters chartParameters, List<ChartGridAndDescriptionGenerator.TimeStampCoord> timeStampCoords){
        this.chartBoxX = chartParameters.getChartBox().getX();
        this.chartBoxY = chartParameters.getChartBox().getY();
        this.chartBoxHeight = chartParameters.getChartBox().getHeight();
        this.lineThickness = chartParameters.getLine().getThickness();
        this.lineColor = chartParameters.getLine().getColor();

        List<Line> lines = new ArrayList<>();
        for(int i=0; i<timeStampCoords.size(); i++){
            lines.add(positionLine(timeStampCoords.get(i)));
        }

        return lines;
    }

    private Line positionLine(ChartGridAndDescriptionGenerator.TimeStampCoord timeStampCoord){
        int x = chartBoxX + timeStampCoord.getX();
        int y1 = chartBoxY;
        int y2 = chartBoxY + chartBoxHeight;

        return new Line(lineColor,lineThickness, x, y1, x, y2);
    }
}
