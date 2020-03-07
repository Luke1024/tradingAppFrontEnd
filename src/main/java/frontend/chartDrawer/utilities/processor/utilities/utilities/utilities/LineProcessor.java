package frontend.chartDrawer.utilities.processor.utilities.utilities.utilities;

import frontend.chartDrawer.utilities.processor.utilities.utilities.Line;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class LineProcessor {

    @Autowired
    private ChartConfig chartConfig;

    public List<Line> process(CurrencyOverviewDto currencyOverviewDto) {
        List<Line> lineList = new ArrayList<>();

        lineList.add(drawGrid(currencyOverviewDto));
        lineList.add(drawChartLine(currencyOverviewDto));
        lineList.add(drawLegendLines(currencyOverviewDto));

        return lineList;
    }

    public List<Line> drawGrid(CurrencyOverviewDto currencyOverviewDto){

    }
}
