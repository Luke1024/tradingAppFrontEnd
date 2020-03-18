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

    private ChartParameters chartParameters;
    private ChartGridAndDescriptionGenerator gridAndDescriptionGenerator = new ChartGridAndDescriptionGenerator();
    private ChartLineGenerator chartLineGenerator = new ChartLineGenerator();
    private ChartParametersProcessor chartParametersProcessor = new ChartParametersProcessor();
    private ChartPartsDrawer chartPartsDrawer = new ChartPartsDrawer();

    public Image generateChart(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame) {
        List<ChartPart> chartParts = new ArrayList<>();

        chartParameters = chartParametersProcessor.process(currencyOverviewDto, viewTimeFrame);

        //all parts are positioned based on typical coordinate system, y coordinates need to reverse when drawing in awt
        chartParts.add(generateBackGround());
        chartParts.add(generateChartBorder());
        chartParts.addAll(chartLineGenerator.generate(chartParameters));
        chartParts.addAll(gridAndDescriptionGenerator.generate(chartParameters));
        chartParts.addAll(addRetrievedTimestamp());

        //reverse y coordinates

        List<ChartPart> partsWithReversedCoordinates = reverseCoordinates(chartParts);

        return chartPartsDrawer.draw(partsWithReversedCoordinates);
    }

    private Rectangle generateBackGround(){
        Color backGroundColor = new Color(chartConfig.getBackGroundColor());
        Rectangle rectangle = new Rectangle(backGroundColor, backGroundColor, true,
                0,0, chartConfig.getChartWidth(), chartConfig.getChartHeight());
        return rectangle;
    }

    private Rectangle generateChartBorder() {
        int fontSize = chartConfig.getFontSize();
        int marginRight = (int) (fontSize * chartConfig.getFontSizeRightMarginMultiplier());
        int marginBottom = (int) (fontSize * chartConfig.getFontSizeBottomMarginMultiplier());
        int marginLeft = chartConfig.getFrameLeftMarginPix();
        int marginTop = chartConfig.getFrameTopMarginPix();

        int x = marginLeft;
        int y = marginTop;
        int width = chartConfig.getChartWidth() - marginLeft - marginRight;
        int height = chartConfig.getChartHeight() - marginTop - marginBottom;

        Color borderColor = new Color(chartConfig.getBorderColorRGB());

        Rectangle borderRectangle = new Rectangle(borderColor, borderColor, false, x,y,width,height);
        chartBox = borderRectangle;

        return borderRectangle;
    }
}
