package frontend.chartDrawer.utilities.processor.utilities.test2;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.ChartPart;
import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.Color;
import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.Rectangle;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ChartGenerator {
    @Autowired
    private ChartConfig chartConfig;
    private List<ChartPart> chartParts = new ArrayList<>();

    public Image generateChart(CurrencyOverviewDto currencyOverviewDto) {
        drawBackGround();
        drawChartBorder();
        drawChartVerticalGrid();
    }

    private void drawBackGround(){
        Color backGroundColor = new Color(chartConfig.getBackGroundColor());
        Rectangle rectangle = new Rectangle(backGroundColor, backGroundColor, true,
                0,0, chartConfig.getChartWidth(), chartConfig.getChartHeight());

        chartParts.add(rectangle);
    }

    private void drawChartBorder() {
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
        chartParts.add(borderRectangle);
    }


}
