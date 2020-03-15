package frontend.chartDrawer.utilities.processor.utilities.test2;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.ChartPart;
import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.Color;
import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.Rectangle;
import frontend.chartDrawer.utilities.processor.utilities.test2.chartGeneratorUtilities.ChartGridAndDescriptionGenerator;
import frontend.chartDrawer.utilities.processor.utilities.test2.chartGeneratorUtilities.ChartLineGenerator;
import frontend.chartDrawer.utilities.processor.utilities.test2.chartGeneratorUtilities.ChartPartsDrawer;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class ChartGenerator {
    @Autowired
    private ChartConfig chartConfig;
    private Rectangle chartBox;
    private ChartGridAndDescriptionGenerator gridAndDescriptionGenerator = new ChartGridAndDescriptionGenerator();
    private ChartLineGenerator chartLineGenerator = new ChartLineGenerator();
    private ChartPartsDrawer chartPartsDrawer = new ChartPartsDrawer();


    public Image generateChart(CurrencyOverviewDto currencyOverviewDto) {
        List<ChartPart> chartParts = new ArrayList<>();

        //all parts are positioned based on typical coordinate system, y coordinates need to reverse when drawing in awt
        chartParts.add(generateBackGround());
        chartParts.add(generateChartBorder());
        chartParts.addAll(gridAndDescriptionGenerator.generate(currencyOverviewDto, chartBox));
        chartParts.addAll(chartLineGenerator.generate(currencyOverviewDto, chartBox));
        chartParts.addAll(addRetrievedTimestamp());

        //reverse y coordinates


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
