package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.timeStampDescriptionGenerator;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.TimeStampCoord;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Text;

import java.util.List;

public class TimeStampDescriptionGenerator {

    private TimeStampDescriptionPositioner timeStampDescriptionPositioner = new TimeStampDescriptionPositioner();
    private TimeStampTextEditorEngine timeStampTextEditorEngine = new TimeStampTextEditorEngine();

    public List<Text> process(ChartDataDto chartDataDto, List<TimeStampCoord> timeStampCoords) {

        List<Text> timeStampDescriptionsPositioned = timeStampDescriptionPositioner.process(chartDataDto, timeStampCoords);
        List<Text> completeTimeStampsObjects = timeStampTextEditorEngine.process(timeStampDescriptionsPositioned, timeStampCoords, chartDataDto);

        return completeTimeStampsObjects;
    }
}
