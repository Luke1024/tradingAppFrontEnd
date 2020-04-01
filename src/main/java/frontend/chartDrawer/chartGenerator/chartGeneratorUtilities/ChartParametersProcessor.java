package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.config.ChartConfigWithConfiguration;

public class ChartParametersProcessor {

    private ChartConfigWithConfiguration chartConfigWithConfiguration;

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
        int chartWidth = chartConfigWithConfiguration.getChartWidth();
        int chartHeight = chartConfigWithConfiguration.getChartHeight();

        return new ChartParameters.Universal(currencyOverviewDto, chartWidth, chartHeight, timeFrame);
    }

    private ChartParameters.BackGround processBackgroundParameters() {
        return new ChartParameters.BackGround(new Color(chartConfigWithConfiguration.getBackGroundColor()));
    }

    private ChartParameters.ChartBox processChartBoxParameters(CurrencyOverviewDto currencyOverviewDto) {
        if(currencyOverviewDto.getDataPoints() != null) {
            int fontSize = chartConfigWithConfiguration.getFontSize();
            int marginRight = (int) (fontSize * chartConfigWithConfiguration.getFontSizeRightMarginMultiplier());
            int marginBottom = (int) (fontSize * chartConfigWithConfiguration.getFontSizeBottomMarginMultiplier());
            int marginLeft = chartConfigWithConfiguration.getFrameLeftMarginPix();
            int marginTop = chartConfigWithConfiguration.getFrameTopMarginPix();

            int x = marginLeft;
            int y = marginTop;
            int width = chartConfigWithConfiguration.getChartWidth() - marginLeft - marginRight;
            int height = chartConfigWithConfiguration.getChartHeight() - marginTop - marginBottom;
            Color color = new Color(chartConfigWithConfiguration.getBorderColorRGB());
            int thickness = chartConfigWithConfiguration.getFrameThicknessInPix();
            double step = ((double) (width))/currencyOverviewDto.getDataPoints().size();
            return new ChartParameters.ChartBox(x,y,width,height,step,color,thickness);
        } else {
            return new ChartParameters.ChartBox(0,0,0,0,
                    0,new Color(0,0,0),0);
        }
    }

    private ChartParameters.Line processLineParameters() {
        return new ChartParameters.Line(new Color(chartConfigWithConfiguration.getLineColorRGB()), chartConfigWithConfiguration.getLineThicknessInPix(),
                chartConfigWithConfiguration.getMaxMinHeightRangePercentage());
    }

    private ChartParameters.VerticalGrid processVerticalGridParameters() {
        return new ChartParameters.VerticalGrid(new Color(chartConfigWithConfiguration.getLineColorRGB()), chartConfigWithConfiguration.getGridThicknessInPix());
    }

    private ChartParameters.HorizontalGrid processHorizontalGridParameters() {
        return new ChartParameters.HorizontalGrid(new Color(chartConfigWithConfiguration.getLineColorRGB()), chartConfigWithConfiguration.getGridThicknessInPix());
    }

    private ChartParameters.Text processTextParameters() {
        int horizontalMarginFromCenter = chartConfigWithConfiguration.getFrameLeftMarginPix()/2;
        int verticalMarginFromCenter = chartConfigWithConfiguration.getFrameTopMarginPix()/2;

        return new ChartParameters.Text(new Color(chartConfigWithConfiguration.getTextColorRGB()),
                chartConfigWithConfiguration.getFontSize(), horizontalMarginFromCenter, verticalMarginFromCenter);
    }
}
