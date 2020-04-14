package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartPartDrawerUtilities.*;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.mappers.ColorMapper;
import frontend.chartDrawer.chartGenerator.chartParts.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ChartPartsDrawer {
    private ByteArrayOutputStream imagebuffer = null;

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

        PartsListsDto partsListsDto = objectSorter.sort(chartPartsToDraw);

        scene = rectangleDrawer.draw(scene, partsListsDto.getRectangleDtos());
        scene = lineDrawer.draw(scene, partsListsDto.getLineDtos());
        scene = textDrawer.draw(scene, partsListsDto.getTextFieldDtos());

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

}
