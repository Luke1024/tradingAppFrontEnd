package frontend.chartDrawer;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.utilities.drawer.ChartDrawer;
import frontend.chartDrawer.utilities.processor.ChartProcessor;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

public class ChartPipeline {
    private ByteArrayOutputStream imagebuffer = null;
    private ChartProcessor chartProcessor;
    private ChartDrawer chartDrawer;

    @Autowired
    private ChartConfig chartConfig;

    int reloads = 0;

    public Image drawChart(CurrencyOverviewDto currencyOverviewDto) {
        Image chartImage = chartProcessor.generateChartImage(currencyOverviewDto);

        BufferedImage image = new BufferedImage(chartConfig.getChartWidth(),
                chartConfig.getChartHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D drawable = image.createGraphics();

        /*
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

*/
        return new Image();
    }
}
