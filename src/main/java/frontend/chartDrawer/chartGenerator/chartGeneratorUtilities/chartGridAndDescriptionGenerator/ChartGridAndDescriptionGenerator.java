package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.TimeStampCoord;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.timeStampDescriptionGenerator.TimeStampDescriptionGenerator;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.VerticalLinesGenerator;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.VisibleTimeStampsFilter;
import frontend.chartDrawer.chartGenerator.chartParts.*;

import java.util.ArrayList;
import java.util.List;

public class ChartGridAndDescriptionGenerator {

    private VisibleTimeStampsFilter visibleTimeStampsFilter = new VisibleTimeStampsFilter();
    private TimeStampDescriptionGenerator timeStampDescriptionGenerator = new TimeStampDescriptionGenerator();
    private VerticalLinesGenerator verticalLinesGenerator = new VerticalLinesGenerator();

    public List<ChartPart> generate(ChartDataDto chartDataDto) {
        List<ChartPart> chartParts = new ArrayList<>();
        chartParts.addAll(generateVerticalLinesWithTextDescription(chartDataDto));
        chartParts.addAll(generateHorizontalLinesWithTextDescription(chartDataDto));
        return chartParts;
    }

    private List<ChartPart> generateVerticalLinesWithTextDescription(ChartDataDto chartDataDto) {

        List<ChartPart> chartParts = new ArrayList<>();
        List<TimeStampCoord> timeStampCoords = visibleTimeStampsFilter.process(chartDataDto);
        chartParts.addAll(timeStampDescriptionGenerator.process(chartDataDto, timeStampCoords));
        chartParts.addAll(verticalLinesGenerator.process(chartDataDto, timeStampCoords));

        return chartParts;
    }

    private List<ChartPart> generateHorizontalLinesWithTextDescription(ChartDataDto chartDataDto) {
        List<ChartPart> chartParts = new ArrayList<>();
        //List<TimeStampCoord> priceCoords = visiblePriceFilter.process(chartParameters);
        return chartParts;
    }
}
