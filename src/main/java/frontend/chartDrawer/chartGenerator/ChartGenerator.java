package frontend.chartDrawer.chartGenerator;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.*;
import frontend.chartDrawer.chartGenerator.chartParts.*;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.Rectangle;
import frontend.client.dto.CurrencyOverviewDto;

import java.util.*;
import java.util.List;

public class ChartGenerator {

    private ChartGridAndDescriptionGenerator gridAndDescriptionGenerator = new ChartGridAndDescriptionGenerator();
    private ChartLineGenerator chartLineGenerator = new ChartLineGenerator();
    private ChartParametersProcessor chartParametersProcessor = new ChartParametersProcessor();
    private ChartPartsDrawer chartPartsDrawer = new ChartPartsDrawer();
    private CoordinateReverser coordinateReverser = new CoordinateReverser();

    public Image generateChart(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame) {
        List<ChartPart> chartParts = new ArrayList<>();

        ChartParameters chartParameters = chartParametersProcessor.process(currencyOverviewDto, viewTimeFrame);

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
