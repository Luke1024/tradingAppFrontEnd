package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.*;
import java.util.ArrayList;
import java.util.List;

public class CoordinateReverser {
    public List<ChartPart> reverse(List<ChartPart> chartParts, ChartDataDto chartDataDto) {
        int height = chartDataDto.getChartConfig().getChartHeight();

        List<ChartPart> reverserdChartParts = new ArrayList<>();
        for(ChartPart chartPart : chartParts){
            if(chartPart instanceof LineDto) reverserdChartParts.add(reverseLine(chartPart,height));
            if(chartPart instanceof RectangleDto) reverserdChartParts.add(reverseRectangle(chartPart,height));
            if(chartPart instanceof TextFieldDto) reverserdChartParts.add(reverseText(chartPart,height));
        }
        return reverserdChartParts;
    }

    private ChartPart reverseLine(ChartPart chartPart, int height) {
        LineDto lineDto = (LineDto) chartPart;

        Color color = lineDto.getColor();
        int thickness = lineDto.getThickness();
        int x1 = lineDto.getX1();
        int y1 = height - lineDto.getY1();
        int x2 = lineDto.getX2();
        int y2 = height - lineDto.getY2();

        return new LineDto(color,thickness,x1,y1,x2,y2);
    }

    private ChartPart reverseRectangle(ChartPart chartPart, int imageHeight){
        RectangleDto rectangleDto = (RectangleDto) chartPart;

        Color color = rectangleDto.getColor();
        double thickness = rectangleDto.getThickness();
        Color fillColor = rectangleDto.getFillColor();
        boolean isFill = rectangleDto.isFill();
        int x = rectangleDto.getX();
        int y = imageHeight - rectangleDto.getY() - rectangleDto.getHeight();
        int width = rectangleDto.getWidth();
        int height = rectangleDto.getHeight();

        return new RectangleDto(color, thickness, fillColor, isFill, x, y, width, height);
    }

    private ChartPart reverseText(ChartPart chartPart, int height){
        TextFieldDto textFieldDto = (TextFieldDto) chartPart;

        Color color = textFieldDto.getColor();
        int x = textFieldDto.getCenterX();
        int y = height - textFieldDto.getCenterY();
        int fontSize = textFieldDto.getFontSize();
        String content = textFieldDto.getContent();

        return new TextFieldDto(color,x,y,fontSize,content);
    }
}
