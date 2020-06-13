package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.CurrencyOverviewDto;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.config.ChartConfig;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class IncomingObjectTester {

    public boolean isObjectCorrect(ChartDataDto chartDataDto) {
        if(chartDataDto == null) return false;
        else {
            boolean currencyCorrect = currencyOverviewDtoIsCorrect(chartDataDto.getCurrencyOverviewDto());
            boolean viewCorrect = viewTimeFrameIsCorrect(chartDataDto.getViewTimeFrame());
            boolean chartConfig = chartConfigCorrect(chartDataDto.getChartConfig());
            if (currencyCorrect && viewCorrect && chartConfig) return true;
            else return false;
        }
    }

    private boolean currencyOverviewDtoIsCorrect(CurrencyOverviewDto currencyOverviewDto) {
        if(currencyOverviewDto == null) return false;
        else {
            if(currencyOverviewDto.getCurrencyName() == null) return false;
            if(currencyOverviewDto.getLastRetrieved() == null) return false;
            if(currencyOverviewDto.getDataPoints() == null) return false;
            else return false;
        }
    }

    private boolean viewTimeFrameIsCorrect(ViewTimeFrame viewTimeFrame){
        if(viewTimeFrame == null) return false;
        return true;
    }

    private boolean chartConfigCorrect(ChartConfig chartConfig) {
        if(chartConfig == null) return false;
        else {
            List<Field> chartConfigFields = Arrays.asList(chartConfig.getClass().getDeclaredFields());
            for (Field field : chartConfigFields) {
                if (field == null) {
                    return false;
                }
            }
        }
        return true;
    }
}