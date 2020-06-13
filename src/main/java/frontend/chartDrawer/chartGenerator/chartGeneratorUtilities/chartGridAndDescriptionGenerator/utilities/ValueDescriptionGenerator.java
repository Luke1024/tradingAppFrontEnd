package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.TextFieldDto;

import java.util.ArrayList;
import java.util.List;

public class ValueDescriptionGenerator {
    public List<TextFieldDto> process(List<ValueCoord> valueCoords, ChartDataDto chartDataDto) {
        List<String> valueInStringRestrictedToPip = convertToStringRestrictedByPip(valueCoords);
        List<TextFieldDto> textFieldDtos = positionTextFields(valueCoords, chartDataDto);
        textFieldDtos = addContent(valueInStringRestrictedToPip, textFieldDtos);
        return setColorAndFont(textFieldDtos, chartDataDto);
    }

    private List<String> convertToStringRestrictedByPip(List<ValueCoord> valueCoords) {
        List<String> converted = new ArrayList<>();
        for(ValueCoord valueCoord : valueCoords) {
            converted.add(convertToString(valueCoord));
        }
        return converted;
    }

    private String convertToString(ValueCoord valueCoord){
        return String.format("%.4f",valueCoord.getValue());
    }

    private List<TextFieldDto> positionTextFields(List<ValueCoord> valueCoords, ChartDataDto chartDataDto) {
        List<TextFieldDto> textFieldDtos = new ArrayList<>();
        int x = computeXPosition(chartDataDto);

        for(ValueCoord valueCoord : valueCoords){
            textFieldDtos.add(new TextFieldDto(x, valueCoord.getY()));
        }
        return textFieldDtos;
    }

    private int computeXPosition(ChartDataDto chartDataDto) {
        int chartBoxLeftEdge = chartDataDto.getChartConfig().getChartBoxLeftBottomCornerX();
        int chartBoxWidth = chartDataDto.getChartConfig().getChartBoxWidth();
        int textFieldHalfWidth = chartDataDto.getChartConfig().getTextElementWidth()/2;

        int chartBoxRightEdgeX = chartBoxLeftEdge + chartBoxWidth;

        return chartBoxRightEdgeX + textFieldHalfWidth;
    }

    private List<TextFieldDto> addContent(List<String> valueInStringRestrictedToPip, List<TextFieldDto> textFieldDtos) {
        for(int i = 0; i< textFieldDtos.size(); i++) {
            textFieldDtos.get(i).setContent(valueInStringRestrictedToPip.get(i));
        }
        return textFieldDtos;
    }

    private List<TextFieldDto> setColorAndFont(List<TextFieldDto> textFieldDtos, ChartDataDto chartDataDto) {
        for(int i = 0; i< textFieldDtos.size(); i++) {
            textFieldDtos.get(i).setColor(new Color(chartDataDto.getChartConfig().getTextColorRGB()));
            textFieldDtos.get(i).setFontSize(chartDataDto.getChartConfig().getDescriptionFontSize());
        }
        return textFieldDtos;
    }
}
