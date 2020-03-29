package frontend.chartDrawer.chartGenerator;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.*;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartVisualizer.Visualizer;
import frontend.chartDrawer.chartGenerator.chartParts.*;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.Rectangle;
import frontend.client.dto.CurrencyOverviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChartGenerator {
    private Logger logger = Logger.getLogger(ChartGenerator.class.getName());

    private IncomingObjectTester incomingObjectTester = new IncomingObjectTester();
    private ChartGridAndDescriptionGenerator gridAndDescriptionGenerator = new ChartGridAndDescriptionGenerator();
    private ChartLineGenerator chartLineGenerator = new ChartLineGenerator();
    private ChartParametersProcessor chartParametersProcessor = new ChartParametersProcessor();
    private ChartPartsDrawer chartPartsDrawer = new ChartPartsDrawer();
    private CoordinateReverser coordinateReverser = new CoordinateReverser();
    private Visualizer visualizer = new Visualizer();

    public Image generateChart(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame) {

        if (incomingObjectTester.isObjectsCorrect(currencyOverviewDto, viewTimeFrame)) {
            return executeChartGeneration(currencyOverviewDto, viewTimeFrame);
        } else {
            logger.log(Level.WARNING, "Objects are not properly initialized.");
            return new Image();
        }
    }

    public void visualizeChart(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame){
        if (incomingObjectTester.isObjectsCorrect(currencyOverviewDto, viewTimeFrame)) {
            ChartParameters chartParameters = chartParametersProcessor.process(currencyOverviewDto, viewTimeFrame);
            List<ChartPart> parts = generateParts(chartParameters);
            visualizer.visualize(parts, chartParameters);
        } else {
            logger.log(Level.WARNING, "Objects are not properly initialized.");
        }
    }

    private Image executeChartGeneration(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame) {
        ChartParameters chartParameters = chartParametersProcessor.process(currencyOverviewDto, viewTimeFrame);

        List<ChartPart> parts = generateParts(chartParameters);
        return drawParts(parts, chartParameters);
    }

    private List<ChartPart> generateParts(ChartParameters chartParameters) {

        List<ChartPart> chartParts = new ArrayList<>();
        //all parts are positioned based on typical coordinate system, y coordinates need to reverse when drawing in awt
        chartParts.add(generateBackGround(chartParameters));
        chartParts.add(generateChartBorder(chartParameters));
        chartParts.addAll(chartLineGenerator.generate(chartParameters));
        chartParts.addAll(gridAndDescriptionGenerator.generate(chartParameters));
        //chartParts.addAll(addRetrievedTimestamp());

        //reverse y coordinates
        return coordinateReverser.reverse(chartParts, chartParameters);
    }

    private Image drawParts(List<ChartPart> parts, ChartParameters chartParameters) {
        return chartPartsDrawer.draw(parts, chartParameters);
    }

    private Rectangle generateBackGround(ChartParameters chartParameters) {
        Color backGroundColor = chartParameters.getBackGround().getColor();

        Rectangle rectangle = new Rectangle(backGroundColor,2,backGroundColor, true,
                0,0, chartParameters.getUniversal().getWidth(), chartParameters.getUniversal().getWidth());
        return rectangle;
    }

    private Rectangle generateChartBorder(ChartParameters chartParameters) {
        int x = chartParameters.getChartBox().getX();
        int y = chartParameters.getChartBox().getY();
        int width = chartParameters.getChartBox().getWidth();
        int height = chartParameters.getChartBox().getHeight();

        Color borderColor = chartParameters.getChartBox().getColor();
        int thickness = chartParameters.getChartBox().getThickness();

        return new Rectangle(borderColor,thickness,borderColor,false, x,y,width,height);
    }
}
