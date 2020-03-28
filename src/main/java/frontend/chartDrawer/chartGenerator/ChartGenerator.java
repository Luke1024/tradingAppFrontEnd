package frontend.chartDrawer.chartGenerator;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.*;
import frontend.chartDrawer.chartGenerator.chartParts.*;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.Rectangle;
import frontend.client.dto.CurrencyOverviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;

@Service
public class ChartGenerator {

    @Autowired
    private IncomingObjectTester incomingObjectTester;
    @Autowired
    private ChartGridAndDescriptionGenerator gridAndDescriptionGenerator;
    @Autowired
    private ChartLineGenerator chartLineGenerator;
    @Autowired
    private ChartParametersProcessor chartParametersProcessor;
    @Autowired
    private ChartPartsDrawer chartPartsDrawer;
    @Autowired
    private CoordinateReverser coordinateReverser;

    public Image generateChart(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame) {

        if (incomingObjectTester.isObjectsCorrect(currencyOverviewDto, viewTimeFrame)) {
            return executeChartGeneration(currencyOverviewDto, viewTimeFrame);
        } else {
            return new Image();
        }
    }

    private Image executeChartGeneration(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame) {

        ChartParameters chartParameters = chartParametersProcessor.process(currencyOverviewDto, viewTimeFrame);

        List<ChartPart> chartParts = new ArrayList<>();

        //all parts are positioned based on typical coordinate system, y coordinates need to reverse when drawing in awt
        chartParts.add(generateBackGround(chartParameters));
        chartParts.add(generateChartBorder(chartParameters));
        chartParts.addAll(chartLineGenerator.generate(chartParameters));
        chartParts.addAll(gridAndDescriptionGenerator.generate(chartParameters));
        //chartParts.addAll(addRetrievedTimestamp());

        //reverse y coordinates

        List<ChartPart> partsWithReversedCoordinates = coordinateReverser.reverse(chartParts, chartParameters);

        return chartPartsDrawer.draw(partsWithReversedCoordinates, chartParameters);
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
