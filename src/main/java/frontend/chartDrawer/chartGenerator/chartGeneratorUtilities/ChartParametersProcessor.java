package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.client.dto.TimeFrame;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class ChartParametersProcessor {

    @Autowired
    private ChartConfig chartConfig;

    public ChartParameters process(CurrencyOverviewDto currencyOverviewDto, TimeFrame timeFrame) {
        return new ChartParameters(Arrays.asList(
                processUniversalParameters(currencyOverviewDto),
                processBackgroundParameters(),
                processChartBoxParameters(currencyOverviewDto),
                processLineParameters(),
                processVerticalGridParameters(),
                processHorizontalGridParameters(),
                processTextParameters()));
    }

    private ChartParameters.ParametersDto processUniversalParameters(CurrencyOverviewDto currencyOverviewDto) {
        int chartWidth = chartConfig.getChartWidth();
        int chartHeight = chartConfig.getChartHeight();

        return new ChartParameters.Universal(currencyOverviewDto, chartWidth, chartHeight);
    }

    private ChartParameters.ParametersDto processBackgroundParameters() {
        return new ChartParameters.BackGround(new Color(chartConfig.getBackGroundColor()));
    }

    private ChartParameters.ParametersDto processChartBoxParameters(CurrencyOverviewDto currencyOverviewDto) {
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

    private ChartParameters.ParametersDto processLineParameters() {
        return new ChartParameters.Line(new Color(chartConfig.getLineColorRGB()), chartConfig.getLineThicknessInPix());
    }

    private ChartParameters.ParametersDto processVerticalGridParameters() {
        return new ChartParameters.VerticalGrid(new Color(chartConfig.getLineColorRGB()), chartConfig.getGridThicknessInPix());
    }

    private ChartParameters.ParametersDto processHorizontalGridParameters() {
        return new ChartParameters.HorizontalGrid(new Color(chartConfig.getLineColorRGB()), chartConfig.getGridThicknessInPix());
    }

    private ChartParameters.ParametersDto processTextParameters() {
        return new ChartParameters.Text(new Color(chartConfig.getTextColorRGB()), chartConfig.getFontSize());
    }
}
