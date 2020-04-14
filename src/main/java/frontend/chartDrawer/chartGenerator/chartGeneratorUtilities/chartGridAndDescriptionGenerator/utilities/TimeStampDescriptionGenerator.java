package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.timeStampDescriptionGeneratorUtilities.TimeStampDescriptionPositioner;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.timeStampDescriptionGeneratorUtilities.TimeStampTextEditorEngine;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.TextFieldDto;

import java.util.List;

public class TimeStampDescriptionGenerator {

    private TimeStampDescriptionPositioner timeStampDescriptionPositioner = new TimeStampDescriptionPositioner();
    private TimeStampTextEditorEngine timeStampTextEditorEngine = new TimeStampTextEditorEngine();

    public List<TextFieldDto> process(ChartDataDto chartDataDto, List<TimeStampCoord> timeStampCoords) {

        List<TextFieldDto> timeStampDescriptionTextFieldDtos = timeStampDescriptionPositioner.process(chartDataDto, timeStampCoords);
        timeStampDescriptionTextFieldDtos = timeStampTextEditorEngine.process(timeStampDescriptionTextFieldDtos, timeStampCoords, chartDataDto);
        return addColorAndFontSize(timeStampDescriptionTextFieldDtos, chartDataDto);
    }

    private List<TextFieldDto> addColorAndFontSize(List<TextFieldDto> timeStampDescriptionTextFieldDtos, ChartDataDto chartDataDto) {
        Color textColor = new Color(chartDataDto.getChartConfig().getTextColorRGB());
        int fontSize = chartDataDto.getChartConfig().getDescriptionFontSize();

        for(TextFieldDto textFieldDto : timeStampDescriptionTextFieldDtos){
            textFieldDto.setColor(textColor);
            textFieldDto.setFontSize(fontSize);
        }
        return timeStampDescriptionTextFieldDtos;
    }
}
