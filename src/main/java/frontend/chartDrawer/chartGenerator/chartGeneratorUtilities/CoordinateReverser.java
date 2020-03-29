package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class CoordinateReverser {
    public List<ChartPart> reverse(List<ChartPart> chartParts, ChartParameters chartParameters) {
        int height = chartParameters.getUniversal().getHeight();

        List<ChartPart> reverserdChartParts = new ArrayList<>();
        for(ChartPart chartPart : chartParts){
            if(chartPart instanceof Line) reverserdChartParts.add(reverseLine(chartPart,height));
            if(chartPart instanceof Rectangle) reverserdChartParts.add(reverseRectangle(chartPart,height));
            if(chartPart instanceof Text) reverserdChartParts.add(reverseText(chartPart,height));
        }
        return reverserdChartParts;
    }

    private ChartPart reverseLine(ChartPart chartPart, int height) {
        Line line = (Line) chartPart;

        Color color = line.getColor();
        int thickness = line.getThickness();
        int x1 = line.getX1();
        int y1 = height - line.getY1();
        int x2 = height - line.getX2();
        int y2 = line.getY2();

        return new Line(color,thickness,x1,y1,x2,y2);
    }

    private ChartPart reverseRectangle(ChartPart chartPart, int imageHeight){
        Rectangle rectangle = (Rectangle) chartPart;

        Color color = rectangle.getColor();
        double thickness = rectangle.getThickness();
        Color fillColor = rectangle.getFillColor();
        boolean isFill = rectangle.isFill();
        int x = rectangle.getX();
        int y = imageHeight - rectangle.getY();
        int width = rectangle.getWidth();
        int height = rectangle.getHeight();

        return new Rectangle(color, thickness, fillColor, isFill, x, y, width, height);
    }

    private ChartPart reverseText(ChartPart chartPart, int height){
        Text text = (Text) chartPart;

        Color color = text.getColor();
        int x = text.getX();
        int y = height - text.getY();
        int fontSize = text.getFontSize();
        String content = text.getContent();

        return new Text(color,x,y,fontSize,content);
    }
}
