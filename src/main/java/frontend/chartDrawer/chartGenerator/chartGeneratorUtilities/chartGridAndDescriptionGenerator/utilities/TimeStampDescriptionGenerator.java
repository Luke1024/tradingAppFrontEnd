package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.timeStampDescriptionGeneratorUtilities.TimeStampDescriptionPositioner;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.timeStampDescriptionGeneratorUtilities.TimeStampTextEditorEngine;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.TextField;

import java.util.List;

public class TimeStampDescriptionGenerator {

    private TimeStampDescriptionPositioner timeStampDescriptionPositioner = new TimeStampDescriptionPositioner();
    private TimeStampTextEditorEngine timeStampTextEditorEngine = new TimeStampTextEditorEngine();

    public List<TextField> process(ChartDataDto chartDataDto, List<TimeStampCoord> timeStampCoords) {

        List<TextField> timeStampDescriptionTextFields = timeStampDescriptionPositioner.process(chartDataDto, timeStampCoords);
        timeStampDescriptionTextFields = timeStampTextEditorEngine.process(timeStampDescriptionTextFields, timeStampCoords, chartDataDto);
        return addColorAndFontSize(timeStampDescriptionTextFields, chartDataDto);
    }

    private List<TextField> addColorAndFontSize(List<TextField> timeStampDescriptionTextFields, ChartDataDto chartDataDto) {
        Color textColor = new Color(chartDataDto.getChartConfig().getTextColorRGB());
        int fontSize = chartDataDto.getChartConfig().getDescriptionFontSize();

        for(TextField textField : timeStampDescriptionTextFields){
            textField.setColor(textColor);
            textField.setFontSize(fontSize);
        }
        return timeStampDescriptionTextFields;
    }
}
