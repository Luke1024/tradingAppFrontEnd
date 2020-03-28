package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartVisualizer;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.mappers.ColorMapper;
import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.chartDrawer.chartGenerator.chartParts.ChartPart;
import frontend.chartDrawer.chartGenerator.chartParts.Line;
import frontend.chartDrawer.chartGenerator.chartParts.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.util.List;

@Component
public class Visualizer extends JApplet {

    @Autowired
    private ColorMapper colorMapper;

    private ChartParameters parameters = null;
    private List<ChartPart> chartParts = null;

    public void visualize(List<ChartPart> chartPartList, ChartParameters chartParameters) {
        parameters = chartParameters;
        chartParts = chartPartList;

        JFrame f = new JFrame("Chart Visualizer");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        JApplet applet = new Visualizer();
        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();
        f.setSize(new Dimension(parameters.getUniversal().getWidth(), parameters.getUniversal().getHeight()));
        f.setVisible(true);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(colorMapper.mapToAwtColor(parameters.getBackGround().getColor()));

        for(ChartPart chartPart : chartParts){
             drawerExecutor(chartPart,g2);
        }
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
