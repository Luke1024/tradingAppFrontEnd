package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.timeStampDescriptionGeneratorUtilities.TimeStampDescriptionPositioner;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.timeStampDescriptionGeneratorUtilities.TimeStampTextEditorEngine;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.TextField;

import java.util.List;

public class TimeStampDescriptionGenerator {

    private TimeStampDescriptionPositioner timeStampDescriptionPositioner = new TimeStampDescriptionPositioner();
    private TimeStampTextEditorEngine timeStampTextEditorEngine = new TimeStampTextEditorEngine();

    public List<TextField> process(ChartDataDto chartDataDto, List<TimeStampCoord> timeStampCoords) {

        List<TextField> timeStampDescriptionsPositioned = timeStampDescriptionPositioner.process(chartDataDto, timeStampCoords);
        List<TextField> completeTimeStampsObjects = timeStampTextEditorEngine.process(timeStampDescriptionsPositioned, timeStampCoords, chartDataDto);

        return completeTimeStampsObjects;
    }
}
