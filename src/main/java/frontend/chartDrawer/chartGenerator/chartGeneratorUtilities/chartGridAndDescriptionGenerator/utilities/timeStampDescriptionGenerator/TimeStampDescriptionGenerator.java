package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.timeStampDescriptionGenerator;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.TimeStampCoord;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.TimeStampTextEditorEngine;
import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.chartDrawer.chartGenerator.chartParts.Text;
import java.util.List;

public class TimeStampDescriptionGenerator {

    private TimeStampDescriptionPositioner timeStampDescriptionPositioner = new TimeStampDescriptionPositioner();
    private TimeStampTextEditorEngine timeStampTextEditorEngine = new TimeStampTextEditorEngine();

    public List<Text> process(ChartParameters chartParameters, List<TimeStampCoord> timeStampCoords) {

        List<Text> timeStampDescriptionsPositioned = timeStampDescriptionPositioner.process(chartParameters, timeStampCoords);
        List<Text> completeTimeStampsObjects = timeStampTextEditorEngine.process(timeStampDescriptionsPositioned, chartParameters, timeStampCoords);

        return completeTimeStampsObjects;
    }
}
