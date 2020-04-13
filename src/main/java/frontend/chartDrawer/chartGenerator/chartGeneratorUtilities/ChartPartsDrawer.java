package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartPartDrawerUtilities.*;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.mappers.ColorMapper;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.ChartPart;
import frontend.chartDrawer.chartGenerator.chartParts.Line;
import frontend.chartDrawer.chartGenerator.chartParts.TextField;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ChartPartsDrawer {
    private ByteArrayOutputStream imagebuffer = null;
    int reloads = 0;

    private ColorMapper colorMapper = new ColorMapper();
    private ScenePreparator scenePreparator = new ScenePreparator();
    private ObjectSorter objectSorter = new ObjectSorter();
    private RectangleDrawer rectangleDrawer = new RectangleDrawer();
    private LineDrawer lineDrawer = new LineDrawer();
    private TextDrawer textDrawer = new TextDrawer();

    public Image draw (List<ChartPart> chartPartsToDraw, ChartDataDto chartDataDto) {

        ScenePreparator.Prepared prepared = scenePreparator.prepare(chartDataDto);
        Graphics2D scene = prepared.getScene();
        BufferedImage image = prepared.getImage();

        ObjectSorter.Parts parts = objectSorter.sort(chartPartsToDraw);

        for(ChartPart chartPart : chartPartsToDraw) {
            drawerExecutor(chartPart,scene);
        }
        try{
            imagebuffer = new ByteArrayOutputStream();
            ImageIO.write(image, "png", imagebuffer);
        } catch (IOException e) {
            return null;
        }

        StreamResource resource = new StreamResource("image",() -> new ByteArrayInputStream(imagebuffer.toByteArray()));
        Image retrievedImage = new Image(resource,"chart");

        return retrievedImage;
    }

    private Graphics2D drawerExecutor(ChartPart chartPart, Graphics2D scene) {

        if(chartPart instanceof Line) drawLine(chartPart, scene);
        if(chartPart instanceof frontend.chartDrawer.chartGenerator.chartParts.Rectangle) drawRectangle(chartPart,scene);
        if(chartPart instanceof frontend.chartDrawer.chartGenerator.chartParts.TextField) drawText(chartPart,scene);

        return scene;
    }

    private Graphics2D drawLine(ChartPart chartPart, Graphics2D scene) {
        Line line = (Line) chartPart;
        double x1 = line.getX1();
        double y1 = line.getY1();
        double x2 = line.getX2();
        double y2 = line.getY2();
        Color color = colorMapper.mapToAwtColor(line.getColor());
        float thickness = line.getThickness();

        scene.setPaint(color);
        scene.setStroke(new BasicStroke(thickness));
        scene.draw(new Line2D.Double(x1,y1,x2,y2));
        return scene;
    }

    private Graphics2D drawRectangle(ChartPart chartPart, Graphics2D scene) {
        frontend.chartDrawer.chartGenerator.chartParts.Rectangle rectangle =
                (frontend.chartDrawer.chartGenerator.chartParts.Rectangle) chartPart;

        int x = rectangle.getX();
        int y = rectangle.getY();
        int width = rectangle.getWidth();
        int height = rectangle.getHeight();
        Color color = colorMapper.mapToAwtColor(rectangle.getColor());
        float thickness = (float) rectangle.getThickness();
        boolean fill = rectangle.isFill();
        Color fillColor = colorMapper.mapToAwtColor(rectangle.getFillColor());


        scene.setPaint(color);
        scene.setStroke(new BasicStroke(thickness));
        scene.draw(new Rectangle(x,y,width,height));

        if(fill){
            scene.setPaint(fillColor);
            scene.fill(new Rectangle.Double(x - (thickness / 2),y-thickness/2,
                    width-thickness,height-thickness));
        }
        return scene;
    }

    private Graphics2D drawText(ChartPart chartPart, Graphics2D scene){
        frontend.chartDrawer.chartGenerator.chartParts.TextField textField = (TextField) chartPart;

        Color textColor = colorMapper.mapToAwtColor(textField.getColor());
        int x = textField.getCenterX();
        int y = textField.getCenterY();
        int fontSize = textField.getFontSize();
        String content = textField.getContent();

        scene.setPaint(textColor);
        scene.setFont(new Font("",Font.PLAIN, fontSize));
        scene.drawString(content,x,y);

        return scene;
    }
}
