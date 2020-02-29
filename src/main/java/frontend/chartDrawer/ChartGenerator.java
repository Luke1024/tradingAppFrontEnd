package frontend.chartDrawer;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import frontend.chartDrawer.utilities.drawer.ChartDrawer;
import frontend.chartDrawer.utilities.processor.ChartProcessor;
import frontend.chartDrawer.utilities.dataObjects.ChartControlData;
import frontend.chartDrawer.utilities.processor.utilities.ChartLayout;
import frontend.client.dto.OverviewDtoPack;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ChartGenerator {
    private ByteArrayOutputStream imagebuffer = null;
    private ChartProcessor chartProcessor;
    private ChartDrawer chartDrawer;

    int reloads = 0;

    public Image drawBasicChart(ChartControlData chartControlData, OverviewDtoPack overviewDtoPack) {
        ChartLayout chartLayout = chartProcessor.processChart(chartControlData, overviewDtoPack);
        Image image = chartDrawer.drawChart(chartLayout);


        BufferedImage image = new BufferedImage (chartControlData.getWidth(),
                chartControlData.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D drawable = image.createGraphics();


        drawable.setColor(Color.green);
        drawable.fill(new Rectangle(
                sceneDto.getBoxLeftUpperCornerX(),
                sceneDto.getBoxLeftUpperCornerY(),
                sceneDto.getBoxWidth(),
                sceneDto.getBoxHeight()));
        try{
            imagebuffer = new ByteArrayOutputStream();
            ImageIO.write(image, "png", imagebuffer);
        } catch (IOException e) {
            return null;
        }
        StreamResource resource = new StreamResource("image",() -> new ByteArrayInputStream(imagebuffer.toByteArray()));
        return  new Image(resource, "scene");
    }

    public Image getBlank(){
        return getStream(new SceneDto(400,400,0,0,0,0));
    }
}
