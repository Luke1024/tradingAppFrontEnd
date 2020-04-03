package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.config.ChartConfig;
import frontend.config.ChartConfigReader;

import java.io.IOException;

public class ChartParametersProcessor {

    private ChartConfig chartConfig;

    public ChartParameters process(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame timeFrame){
        this.chartConfig = new ChartConfig();
        return executeProcessing(currencyOverviewDto, timeFrame);
    }

    public ChartParameters process(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame timeFrame, String pathToConfigFile) throws IOException {
        ChartConfigReader chartConfigReader = new ChartConfigReader();
        this.chartConfig = chartConfigReader.readConfigFile(pathToConfigFile);

        return executeProcessing(currencyOverviewDto, timeFrame);
    }

    private ChartParameters executeProcessing(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame timeFrame) {
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
        if(currencyOverviewDto.getDataPoints() != null) {
            int fontSize = chartConfig.getDescriptionFontSize();
            int marginRight = (int) (fontSize * chartConfig.getFontSizeRightMarginMultiplier());
            int marginBottom = (int) (fontSize * chartConfig.getFontSizeBottomMarginMultiplier());
            int marginLeft = chartConfig.getFrameLeftMarginPix();
            int marginTop = chartConfig.getFrameTopMarginPix();

            int x = marginLeft;
            int y = marginTop;
            int width = chartConfig.getChartWidth() - marginLeft - marginRight;
            int height = chartConfig.getChartHeight() - marginTop - marginBottom;
            Color color = new Color(chartConfig.getFrameColorRGB());
            int thickness = chartConfig.getFrameThicknessInPix();
            double step = ((double) (width))/currencyOverviewDto.getDataPoints().size();
            return new ChartParameters.ChartBox(x,y,width,height,step,color,thickness);
        } else {
            return new ChartParameters.ChartBox(0,0,0,0,
                    0,new Color(0,0,0),0);
        }
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
        int horizontalMarginFromCenter = (int) (chartConfig.getFontSizeRightMarginMultiplier()/2);
        int verticalMarginFromCenter = (int) (chartConfig.getFontSizeBottomMarginMultiplier()/2);

        return new ChartParameters.Text(new Color(chartConfig.getTextColorRGB()),
                chartConfig.getDescriptionFontSize(), horizontalMarginFromCenter, verticalMarginFromCenter);
    }
}
