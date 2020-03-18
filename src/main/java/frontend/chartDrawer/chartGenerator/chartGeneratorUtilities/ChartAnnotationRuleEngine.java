package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.CurrencyOverviewDto;

import java.util.List;

import static frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame.*;

public class ChartAnnotationRuleEngine {

    public List<Integer> process(ChartParameters chartParameters){
        ViewTimeFrame viewTimeFrame = chartParameters.getUniversal().getViewTimeFrame();

        CurrencyOverviewDto currencyOverviewDto = chartParameters.getUniversal().getCurrencyOverviewDto();

        switch(viewTimeFrame){
            case:D1
                return d1;
            case:W1
                return w1;
            case:M1
                return m1;
            case:Y1
                return y1;
            case:MAX
                return max;
        }
    }

    private List<Integer> d1(CurrencyOverviewDto currencyOverviewDto){
        currencyOverviewDto.getDataPoints().get(0).getTimeStamp().getHour()
    }
}
