package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartPartDrawerUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.mappers.ColorMapper;
import frontend.chartDrawer.chartGenerator.chartParts.RectangleDto;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class RectangleDrawer {

    private ColorMapper colorMapper = new ColorMapper();

    public Graphics2D draw(Graphics2D scene, List<RectangleDto> rectangleDtos) {
        for(RectangleDto rectangleDto : rectangleDtos) {
            scene = drawRectangle(rectangleDto, scene);
        }
        return scene;
    }

    private Graphics2D drawRectangle(RectangleDto rectangleDto, Graphics2D scene) {
        int x = rectangleDto.getX();
        int y = rectangleDto.getY();
        int width = rectangleDto.getWidth();
        int height = rectangleDto.getHeight();
        Color color = colorMapper.mapToAwtColor(rectangleDto.getColor());
        float thickness = (float) rectangleDto.getThickness();
        boolean fill = rectangleDto.isFill();
        Color fillColor = colorMapper.mapToAwtColor(rectangleDto.getFillColor());

        scene.setPaint(color);
        scene.setStroke(new BasicStroke(thickness));
        scene.draw(new java.awt.Rectangle(x,y,width,height));

        if(fill){
            scene.setPaint(fillColor);
            scene.fill(new Rectangle2D.Double(x - (thickness / 2), y - thickness/2,
                    width-thickness, height-thickness));
        }
        return scene;
    }
}
