package frontend.chartDrawer.chartGenerator;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.ChartParametersProcessor;
import frontend.chartDrawer.chartGenerator.chartParts.*;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.ChartGridAndDescriptionGenerator;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.ChartLineGenerator;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.ChartPartsDrawer;
import frontend.client.dto.CurrencyOverviewDto;

import java.util.*;

public class ChartGenerator {

    private ChartGridAndDescriptionGenerator gridAndDescriptionGenerator = new ChartGridAndDescriptionGenerator();
    private ChartLineGenerator chartLineGenerator = new ChartLineGenerator();
    private ChartParametersProcessor chartParametersProcessor = new ChartParametersProcessor();
    private ChartPartsDrawer chartPartsDrawer = new ChartPartsDrawer();

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

        List<ChartPart> partsWithReversedCoordinates = reverseCoordinates(chartParts);

        return chartPartsDrawer.draw(partsWithReversedCoordinates, chartParameters);
    }

    private Rectangle generateBackGround(ChartParameters chartParameters) {
        Color backGroundColor = chartParameters.getBackGround().getColor();

        Rectangle rectangle = new Rectangle(backGroundColor, backGroundColor, true,
                0,0, chartParameters.getUniversal().getWidth(), chartParameters.getUniversal().getWidth());
        return rectangle;
    }

    private Rectangle generateChartBorder(ChartParameters chartParameters) {
        int x = chartParameters.getChartBox().getX();
        int y = chartParameters.getChartBox().getY();
        int width = chartParameters.getChartBox().getWidth();
        int height = chartParameters.getChartBox().getHeight();

        Color borderColor = chartParameters.getChartBox().getColor();

        return new Rectangle(borderColor, borderColor, false, x,y,width,height);
    }
}
