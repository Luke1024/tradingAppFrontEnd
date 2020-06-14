package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartPartDrawerUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.mappers.ColorMapper;
import frontend.chartDrawer.chartGenerator.chartParts.LineDto;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;

public class LineDrawer {

    private ColorMapper colorMapper = new ColorMapper();

    public Graphics2D draw(Graphics2D scene, List<LineDto> lines){
        for(LineDto lineDto : lines) drawLine(lineDto, scene);
        return scene;
    }

    private Graphics2D drawLine(LineDto lineDto, Graphics2D scene){
        double x1 = lineDto.getX1();
        double y1 = lineDto.getY1();
        double x2 = lineDto.getX2();
        double y2 = lineDto.getY2();
        Color color = colorMapper.mapToAwtColor(lineDto.getColor());
        float thickness = lineDto.getThickness();

        scene.setPaint(color);
        scene.setStroke(new BasicStroke(thickness));
        scene.draw(new Line2D.Double(x1, y1, x2, y2));
        return scene;
    }
}
