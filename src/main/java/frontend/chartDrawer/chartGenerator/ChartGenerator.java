package frontend.chartDrawer.chartGenerator;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.chartParts.ChartPart;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.Rectangle;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.ChartGridAndDescriptionGenerator;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.ChartLineGenerator;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.ChartPartsDrawer;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.chartDrawer.chartGenerator.configDto.ChartParameters;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class ChartGenerator {
    @Autowired
    private ChartConfig chartConfig;
    private Rectangle chartBox;
    private ChartParameters chartParameters;
    private ChartGridAndDescriptionGenerator gridAndDescriptionGenerator = new ChartGridAndDescriptionGenerator();
    private ChartLineGenerator chartLineGenerator = new ChartLineGenerator();
    private ChartPartsDrawer chartPartsDrawer = new ChartPartsDrawer();


    public Image generateChart(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame) {
        List<ChartPart> chartParts = new ArrayList<>();

        chartParameters = processChartParameters.process(currencyOverviewDto, viewTimeFrame);

        //all parts are positioned based on typical coordinate system, y coordinates need to reverse when drawing in awt
        chartParts.add(generateBackGround());
        chartParts.add(generateChartBorder());
        chartParts.addAll(chartLineGenerator.generate(currencyOverviewDto, chartBox));
        chartParts.addAll(gridAndDescriptionGenerator.generate(currencyOverviewDto, chartBox, viewTimeFrame));
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

    public class ChartParameters {
        public class Universal {
            private CurrencyOverviewDto currencyOverviewDto;
            private double step;
        }

        public class BackGround {
            private Color color;
            private int width;
            private int height;

        }

        public class Border {
            private Color color;
            private int x;
            private int y;
            private int width;
            private int height;
            private int thickness;

        }

        public class Line {
            private Color color;
            private int x;
            private int y;
            private int width;
            private int height;

        }

        public class VerticalGrid {
            private Color color;
            private
        }

    }
}
