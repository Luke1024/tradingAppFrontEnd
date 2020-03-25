package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.mappers.ColorMapper;
import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.chartDrawer.chartGenerator.chartParts.ChartPart;
import frontend.chartDrawer.chartGenerator.chartParts.Line;
import frontend.chartDrawer.chartGenerator.chartParts.Text;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ChartPartsDrawer {
    ByteArrayOutputStream imagebuffer = null;

    private ColorMapper colorMapper = new ColorMapper();

    public Image draw (List<ChartPart> chartPartsToDraw, ChartParameters chartParameters) {
        int imageWidth = chartParameters.getUniversal().getWidth();
        int imageHeight = chartParameters.getUniversal().getHeight();
        frontend.chartDrawer.chartGenerator.chartParts.Color backgroudColor = chartParameters.getBackGround().getColor();

        Color imageBackGroundColor = colorMapper.mapToAwtColor(backgroudColor);

        BufferedImage image = new BufferedImage (imageWidth, imageHeight,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D scene = image.createGraphics();

        scene.setColor(imageBackGroundColor);

        for(ChartPart chartPart : chartPartsToDraw) {
            scene.draw(objectMapperToAwt(chartPart));
        }

        try{
            imagebuffer = new ByteArrayOutputStream();
            ImageIO.write(image, "png", imagebuffer);
        } catch (IOException e) {
            return null;
        }
        StreamResource resource = new StreamResource("image",() -> new ByteArrayInputStream(imagebuffer.toByteArray()));
        return  new Image(resource, "scene");
    }

    private Shape objectMapperToAwt(ChartPart chartPart) {

        Shape emptyShape = new Rectangle();

        if(chartPart instanceof Line) emptyShape = convertLineToAwt(chartPart);
        if(chartPart instanceof frontend.chartDrawer.chartGenerator.chartParts.Rectangle) emptyShape = convertRectangleToAwt(chartPart);
        if(chartPart instanceof Text) emptyShape = convertTextToAwt(chartPart);

        return emptyShape;
    }

    private Shape convertLineToAwt(ChartPart chartPart) {
        Line line = (Line) chartPart;
        double x1 = line.getX1();
        double y1 = line.getY1();
        double x2 = line.getX2();
        double y2 = line.getY2();

        Line2D convertedLine = new Line2D.Double(x1,y1,x2,y2);
        convertedLine.
        return new ;
    }
}
