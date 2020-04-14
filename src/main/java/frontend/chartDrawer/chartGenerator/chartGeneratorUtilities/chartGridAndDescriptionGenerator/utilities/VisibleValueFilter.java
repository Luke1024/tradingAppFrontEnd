package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.visibleValueFilterUtilities.PositionValueCalculator;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.visibleValueFilterUtilities.RangeAnalyzer;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.visibleValueFilterUtilities.ValuePositionPlacer;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;

import java.util.List;

public class VisibleValueFilter {

    private RangeAnalyzer rangeAnalyzer = new RangeAnalyzer();
    private ValuePositionPlacer valuePositionPlacer = new ValuePositionPlacer();
    private PositionValueCalculator positionValueCalculator = new PositionValueCalculator();

    public List<ValueCoord> process(ChartDataDto chartDataDto) {

        RangeAnalyzer.Range range = rangeAnalyzer.minMaxTextFieldPosition(chartDataDto);
        List<ValueCoord> valueCoords = valuePositionPlacer.place(chartDataDto, range);
        return positionValueCalculator.process(valueCoords, chartDataDto);
    }
}