package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class ChartParametersProcessor {

    @Autowired
    private ChartConfig chartConfig;

    public ChartParameters process(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame timeFrame) {
        return new ChartParameters(
                processUniversalParameters(currencyOverviewDto, timeFrame),
                processBackgroundParameters(),
                processChartBoxParameters(currencyOverviewDto),
                processLineParameters(),
                processVerticalGridParameters(),
                processHorizontalGridParameters(),
                processTextParameters());
    }

    private ChartParameters.Universal processUniversalParameters(
            CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame timeFrame) {

        int chartWidth = chartConfig.getChartWidth();
        int chartHeight = chartConfig.getChartHeight();

        return new ChartParameters.Universal(currencyOverviewDto, chartWidth, chartHeight, timeFrame);
    }

    private ChartParameters.BackGround processBackgroundParameters() {
        return new ChartParameters.BackGround(new Color(chartConfig.getBackGroundColor()));
    }

    private ChartParameters.ChartBox processChartBoxParameters(CurrencyOverviewDto currencyOverviewDto) {
        int fontSize = chartConfig.getFontSize();
        int marginRight = (int) (fontSize * chartConfig.getFontSizeRightMarginMultiplier());
        int marginBottom = (int) (fontSize * chartConfig.getFontSizeBottomMarginMultiplier());
        int marginLeft = chartConfig.getFrameLeftMarginPix();
        int marginTop = chartConfig.getFrameTopMarginPix();

        int x = marginLeft;
        int y = marginTop;
        int width = chartConfig.getChartWidth() - marginLeft - marginRight;
        int height = chartConfig.getChartHeight() - marginTop - marginBottom;
        Color color = new Color(chartConfig.getBorderColorRGB());
        int thickness = chartConfig.getFrameThicknessInPix();
        double step = ((double) (width))/currencyOverviewDto.getDataPoints().size();

        return new ChartParameters.ChartBox(x,y,width,height,step,color,thickness);
    }

    private ChartParameters.Line processLineParameters() {
        return new ChartParameters.Line(new Color(chartConfig.getLineColorRGB()), chartConfig.getLineThicknessInPix(),
                chartConfig.getMaxMinHeightRangePercentage());
    }

    private ChartParameters.VerticalGrid processVerticalGridParameters() {
        return new ChartParameters.VerticalGrid(new Color(chartConfig.getLineColorRGB()), chartConfig.getGridThicknessInPix());
    }

    private ChartParameters.HorizontalGrid processHorizontalGridParameters() {
        return new ChartParameters.HorizontalGrid(new Color(chartConfig.getLineColorRGB()), chartConfig.getGridThicknessInPix());
    }

    private ChartParameters.Text processTextParameters() {
        int horizontalMarginFromCenter = chartConfig.getFrameLeftMarginPix()/2;
        int verticalMarginFromCenter = chartConfig.getFrameTopMarginPix()/2;

        return new ChartParameters.Text(new Color(chartConfig.getTextColorRGB()),
                chartConfig.getFontSize(), horizontalMarginFromCenter, verticalMarginFromCenter);
    }
}
