package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartVisualizer;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.mappers.ColorMapper;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.ChartPart;
import frontend.chartDrawer.chartGenerator.chartParts.Line;
import frontend.chartDrawer.chartGenerator.chartParts.Text;

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
        int imageWidth = 2000;//chartParameters.getUniversal().getWidth();
        int imageHeight = 2000; //chartParameters.getUniversal().getHeight();
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
            File outputFile = new File("image.jpg");
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
        }

    }

    /*
    public void visualize(List<ChartPart> chartPartList, ChartParameters chartParameters) {
        System.out.println("execution go so far");

        parameters = chartParameters;
        chartParts = chartPartList;

        JFrame f = new JFrame("Chart Visualizer");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);}
        });
        JApplet applet = new Visualizer();
        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();
        f.setSize(new Dimension(parameters.getUniversal().getWidth(), parameters.getUniversal().getHeight()));
        f.setVisible(true);

        Graphics2D g2 = new Graphics2D() {
        };
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(colorMapper.mapToAwtColor(parameters.getBackGround().getColor()));

        for(ChartPart chartPart : chartParts) {
            drawerExecutor(chartPart, g2);
        }
    }
    */

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
        scene.setFont(new Font("", Font.PLAIN, fontSize));
        scene.drawString(content,x,y);

        return scene;
    }
}
