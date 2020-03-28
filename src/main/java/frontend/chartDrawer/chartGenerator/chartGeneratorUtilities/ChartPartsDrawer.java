package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.mappers.ColorMapper;
import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.chartDrawer.chartGenerator.chartParts.ChartPart;
import frontend.chartDrawer.chartGenerator.chartParts.Line;
import frontend.chartDrawer.chartGenerator.chartParts.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ChartPartsDrawer {
    private ByteArrayOutputStream imagebuffer = null;
    int reloads = 0;

    @Autowired
    private ColorMapper colorMapper;

    public Image draw (List<ChartPart> chartPartsToDraw, ChartParameters chartParameters) {

        int imageWidth = chartParameters.getUniversal().getWidth();
        int imageHeight = chartParameters.getUniversal().getHeight();
        frontend.chartDrawer.chartGenerator.chartParts.Color backgroudColor = chartParameters.getBackGround().getColor();

        Color imageBackGroundColor = colorMapper.mapToAwtColor(backgroudColor);

        BufferedImage image = new BufferedImage (imageWidth, imageHeight,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D scene = image.createGraphics();
        //scene.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        scene.setColor(imageBackGroundColor);

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
        if(chartPart instanceof Text) drawText(chartPart,scene);

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
        Text text = (Text) chartPart;

        Color textColor = colorMapper.mapToAwtColor(text.getColor());
        int x = text.getX();
        int y = text.getY();
        int fontSize = text.getFontSize();
        String content = text.getContent();

        scene.setPaint(textColor);
        scene.setFont(new Font("",Font.PLAIN, fontSize));
        scene.drawString(content,x,y);

        return scene;
    }
}
