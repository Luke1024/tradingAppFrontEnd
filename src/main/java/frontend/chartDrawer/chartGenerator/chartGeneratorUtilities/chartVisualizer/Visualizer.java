package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartVisualizer;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.mappers.ColorMapper;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.ChartPart;
import frontend.chartDrawer.chartGenerator.chartParts.Line;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Visualizer {
    private ColorMapper colorMapper = new ColorMapper();
    private List<ChartPart> chartParts = null;

    private ByteArrayOutputStream imageBuffer = null;

    public void visualize(List<ChartPart> chartPartsList, ChartDataDto chartDataDto) {
        chartParts = chartPartsList;
        int imageWidth = chartDataDto.getChartConfig().getChartWidth();
        int imageHeight = chartDataDto.getChartConfig().getChartHeight();
        frontend.chartDrawer.chartGenerator.chartParts.Color backGround =
                new frontend.chartDrawer.chartGenerator.chartParts.Color(
                        chartDataDto.getChartConfig().getBackGroundColor());

        Color imageBackGroundColor = colorMapper.mapToAwtColor(backGround);

        BufferedImage image = new BufferedImage (imageWidth, imageHeight,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D scene = image.createGraphics();
        scene.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        scene.setColor(imageBackGroundColor);

        for(ChartPart chartPart : chartParts) {
            drawerExecutor(chartPart,scene);
        }
        try{
            File outputFile = new File("image.png");
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
        }

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
        frontend.chartDrawer.chartGenerator.chartParts.TextField textField = (frontend.chartDrawer.chartGenerator.chartParts.TextField) chartPart;

        Color textColor = colorMapper.mapToAwtColor(textField.getColor());
        int x = textField.getCenterX();
        int y = textField.getCenterY();
        int fontSize = textField.getFontSize();
        String content = textField.getContent();

        scene.setPaint(textColor);
        scene.setFont(new Font("", Font.PLAIN, fontSize));
        scene.drawString(content,x,y);

        return scene;
    }
}
