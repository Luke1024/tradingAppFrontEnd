package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.*;
import frontend.chartDrawer.chartGenerator.chartParts.*;

import java.util.ArrayList;
import java.util.List;

public class ChartGridAndDescriptionGenerator {

    private VisibleTimeStampsFilter visibleTimeStampsFilter = new VisibleTimeStampsFilter();
    private TimeStampDescriptionGenerator timeStampDescriptionGenerator = new TimeStampDescriptionGenerator();
    private VerticalLinesGenerator verticalLinesGenerator = new VerticalLinesGenerator();
    private VisibleValueFilter visibleValueFilter = new VisibleValueFilter();
    private ValueDescriptionGenerator priceDescriptionGenerator = new ValueDescriptionGenerator();
    private HorizontalLinesGenerator horizontalLinesGenerator = new HorizontalLinesGenerator();

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
        List<ValueCoord> priceCoords = visibleValueFilter.process(chartDataDto);
        return chartParts;
    }
}
