package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartVisualizer;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartPartDrawerUtilities.*;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.mappers.ColorMapper;
import frontend.chartDrawer.chartGenerator.chartParts.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Visualizer {
    private List<ChartPart> chartParts = null;

    private ScenePreparator scenePreparator = new ScenePreparator();
    private ObjectSorter objectSorter = new ObjectSorter();
    private RectangleDrawer rectangleDrawer = new RectangleDrawer();
    private LineDrawer lineDrawer = new LineDrawer();
    private TextDrawer textDrawer = new TextDrawer();

    public void visualize(List<ChartPart> chartPartsList, ChartDataDto chartDataDto) {
        ScenePreparator.Prepared prepared = scenePreparator.prepare(chartDataDto);
        Graphics2D scene = prepared.getScene();
        BufferedImage image = prepared.getImage();

        PartsListsDto partsListsDto = objectSorter.sort(chartPartsList);

        scene = rectangleDrawer.draw(scene, partsListsDto.getRectangleDtos());
        scene = lineDrawer.draw(scene, partsListsDto.getLineDtos());
        textDrawer.draw(scene, partsListsDto.getTextFieldDtos());

        try{
            File outputFile = new File("image.png");
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
        }

    }
}
