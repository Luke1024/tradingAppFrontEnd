package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.gridAndDescriptionGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.ChartGridAndDescriptionGenerator;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.gridAndDescriptionGeneratorUtilities.timeStampDescriptionGeneratorUtilities.TimeStampDescriptionPositioner;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.gridAndDescriptionGeneratorUtilities.timeStampDescriptionGeneratorUtilities.TimeStampTextEditorEngine;
import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.chartDrawer.chartGenerator.chartParts.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TimeStampDescriptionGenerator {

    @Autowired
    private TimeStampDescriptionPositioner timeStampDescriptionPositioner;
    @Autowired
    private TimeStampTextEditorEngine timeStampTextEditorEngine;

    public List<Text> process(ChartParameters chartParameters, List<ChartGridAndDescriptionGenerator.TimeStampCoord> timeStampCoords) {

        List<Text> timeStampDescriptionsPositioned = timeStampDescriptionPositioner.process(chartParameters, timeStampCoords);
        List<Text> completeTimeStampsObjects = timeStampTextEditorEngine.process(timeStampDescriptionsPositioned, chartParameters, timeStampCoords);

        return completeTimeStampsObjects;
    }
}
