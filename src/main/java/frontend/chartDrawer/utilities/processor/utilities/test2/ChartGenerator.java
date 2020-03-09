package frontend.chartDrawer.utilities.processor.utilities.test2;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.ChartPart;
import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.Color;
import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.Line;
import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.Rectangle;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.client.dto.DataPointDto;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class ChartGenerator {
    @Autowired
    private ChartConfig chartConfig;
    private List<ChartPart> chartParts = new ArrayList<>();
    private Rectangle chartBox;

    public Image generateChart(CurrencyOverviewDto currencyOverviewDto) {
        drawBackGround();
        drawChartBorder();
        drawChartVerticalGrid(currencyOverviewDto);
        drawChartLine(currencyOverviewDto);
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
        chartBox = borderRectangle;
        chartParts.add(borderRectangle);
    }

    private void drawChartVerticalGrid(CurrencyOverviewDto currencyOverviewDto){
        int numberOfDataPoints = currencyOverviewDto.getDataPoints().size();

        int lineToLineDistanceInPixels = chartBox.getWidth()/numberOfDataPoints;
        int minimalDistanceBetweenGridLines = chartConfig.getMinimalDistanceBetweenGridLines();

        while(lineToLineDistanceInPixels >= minimalDistanceBetweenGridLines){
            lineToLineDistanceInPixels = lineToLineDistanceInPixels/10;
        }

        int stepInPixels = chartBox.getWidth()/lineToLineDistanceInPixels;

        int startingPositionX = chartBox.getX();
        int y1 = chartBox.getY();
        int y2 = chartBox.getY() + chartBox.getHeight();

        Color lineColor = new Color(chartConfig.getGridColorRGB());

        for(int i=0; i<lineToLineDistanceInPixels; i++) {
            int x = startingPositionX + (i + 1)* stepInPixels;
            Line line = new Line(lineColor,x,y1,x,y2);
        }
    }

    private void drawChartLine(CurrencyOverviewDto currencyOverviewDto) {
        int chartLineRangeInPercentage = getChartLineRangeInPercentage(chartConfig.getMaxMinHeightRangePercentage());

        int topAndBottomMargin = (chartBox.getHeight() * (100-chartLineRangeInPercentage))/2;

        int startingPositionX = chartBox.getX();
        int highestPositionY1 = chartBox.getY();
        int lowestPositionY2 = chartBox.getY() + chartBox.getHeight();

        int stepInPixels = chartBox.getWidth()/currencyOverviewDto.getDataPoints().size();

        double highestValuePoint = getHighestValueInDataPoints(currencyOverviewDto);
        double lowestValuePoint = getLowestValueInDataPoints(currencyOverviewDto);
    }

    private int getChartLineRangeInPercentage(int chartRangeInPercentage){
        if(chartRangeInPercentage > 100){
            return 100;
        } else return chartRangeInPercentage;
    }

    private double getHighestValueInDataPoints(CurrencyOverviewDto currencyOverviewDto){
        List<DataPointDto> dataPoints = currencyOverviewDto.getDataPoints();
        return dataPoints.stream().max(Comparator.comparing(DataPointDto::getValue)).map(d -> d.getValue()).orElseThrow(NoSuchElementException::new);
    }
}
