package frontend.chartDrawer.chartGenerator.chartParts;

import frontend.client.dto.CurrencyOverviewDto;
import frontend.config.ChartConfig;

public class ChartDataDto {
    private CurrencyOverviewDto currencyOverviewDto;
    private ViewTimeFrame viewTimeFrame;
    private ChartConfig chartConfig;

    public ChartDataDto(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame, ChartConfig chartConfig) {
        this.currencyOverviewDto = currencyOverviewDto;
        this.viewTimeFrame = viewTimeFrame;
        this.chartConfig = chartConfig;
    }

    public CurrencyOverviewDto getCurrencyOverviewDto() {
        return currencyOverviewDto;
    }

    public ViewTimeFrame getViewTimeFrame() {
        return viewTimeFrame;
    }

    public ChartConfig getChartConfig() {
        return chartConfig;
    }
}
