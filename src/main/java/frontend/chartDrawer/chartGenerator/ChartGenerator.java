package frontend.chartDrawer.chartGenerator;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.*;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.ChartGridAndDescriptionGenerator;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartLineGenerator.ChartLineGenerator;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartVisualizer.Visualizer;
import frontend.chartDrawer.chartGenerator.chartParts.*;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.Rectangle;
import frontend.config.ChartConfig;

import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChartGenerator {
    private Logger logger = Logger.getLogger(ChartGenerator.class.getName());

    private IncomingObjectTester incomingObjectTester = new IncomingObjectTester();
    private ChartGridAndDescriptionGenerator gridAndDescriptionGenerator = new ChartGridAndDescriptionGenerator();
    private ChartLineGenerator chartLineGenerator = new ChartLineGenerator();
    private CoordinateReverser coordinateReverser = new CoordinateReverser();
    private ChartPartsDrawer chartPartsDrawer = new ChartPartsDrawer();
    private Visualizer visualizer = new Visualizer();

    public Image generateChart(ChartDataDto chartDataDto) {

        if (incomingObjectTester.isObjectCorrect(chartDataDto)) {
            return executeChartGeneration(chartDataDto);
        } else {
            logger.log(Level.WARNING, "Objects are not properly initialized.");
            return new Image();
        }
    }

    public void visualizeChart(ChartDataDto chartDataDto){

        if (incomingObjectTester.isObjectCorrect(chartDataDto)) {
            List<ChartPart> parts = generateParts(chartDataDto);
            visualizer.visualize(parts, chartDataDto);
        } else {
            logger.log(Level.WARNING, "Objects are not properly initialized.");
        }
    }

    private Image executeChartGeneration(ChartDataDto chartDataDto) {

        List<ChartPart> parts = generateParts(chartDataDto);
        return drawParts(parts, chartDataDto);
    }

    private List<ChartPart> generateParts(ChartDataDto chartDataDto) {

        List<ChartPart> chartParts = new ArrayList<>();
        //all parts are positioned based on typical coordinate system, y coordinates need to reverse when drawing in awt
        chartParts.add(generateBackGround(chartDataDto.getChartConfig()));
        chartParts.add(generateChartBoxBorder(chartDataDto.getChartConfig()));
        chartParts.addAll(chartLineGenerator.generate(chartDataDto));
        chartParts.addAll(gridAndDescriptionGenerator.generate(chartDataDto));
        //chartParts.addAll(addRetrievedTimestamp());

        //reverse y coordinates
        return coordinateReverser.reverse(chartParts, chartDataDto);
    }

    private Image drawParts(List<ChartPart> parts, ChartDataDto chartDataDto) {
        return chartPartsDrawer.draw(parts, chartDataDto);
    }

    private Rectangle generateBackGround(ChartConfig chartConfig) {
        Color backGroundColor = new Color(chartConfig.getBackGroundColor());

        Rectangle rectangle = new Rectangle(backGroundColor,2,backGroundColor, true,
                0,0,chartConfig.getChartWidth(), chartConfig.getChartHeight());
        return rectangle;
    }

    private Rectangle generateChartBoxBorder(ChartConfig chartConfig) {
        int x = chartConfig.getChartBoxLeftBottomCornerX();
        int y = chartConfig.getChartBoxLeftBottomCornerY();

        int width = chartConfig.getChartBoxWidth();
        int height = chartConfig.getChartBoxHeight();

        Color borderColor = new Color(chartConfig.getChartBoxLineColorRGB());
        int thickness = chartConfig.getChartBoxLineThicknessInPix();

        return new Rectangle(borderColor,thickness,borderColor,false, x,y,width,height);
    }
}
