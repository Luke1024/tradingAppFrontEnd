package frontend.chartDrawer.utilities.processor.utilities.test2.chartGeneratorUtilities;

import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.Color;
import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.Line;
import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.Rectangle;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ChartGridAndDescriptionGenerator {

    @Autowired
    private ChartConfig chartConfig;

    public List<Line> generate(CurrencyOverviewDto currencyOverviewDto, Rectangle chartBox){


        int numberOfDataPoints = currencyOverviewDto.getDataPoints().size();

        int lineToLineDistanceInPixels = chartBox.getWidth()/numberOfDataPoints;
        int minimalDistanceBetweenGridLines = chartConfig.getMinimalDistanceBetweenGridLines();

        while(lineToLineDistanceInPixels >= minimalDistanceBetweenGridLines){
            lineToLineDistanceInPixels = lineToLineDistanceInPixels/10;
        }

        int stepInPixels = chartBox.getWidth()/lineToLineDistanceInPixels;

        int startingPositionX = chartBox.getX();
        int y1 = chartBox.getY();
        int y2 = chartBox.getY() + chartBox.getHeight();

        Color lineColor = new Color(chartConfig.getGridColorRGB());

        List<Line> lines = new ArrayList<>();

        for(int i=0; i<lineToLineDistanceInPixels; i++) {
            int x = startingPositionX + (i + 1)* stepInPixels;
            lines.add(new Line(lineColor,x,y1,x,y2));
        }
        return lines;
    }
}
