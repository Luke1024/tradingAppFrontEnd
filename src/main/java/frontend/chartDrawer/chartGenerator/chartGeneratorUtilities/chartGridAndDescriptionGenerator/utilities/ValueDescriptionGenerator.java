package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.TextField;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ValueDescriptionGenerator {
    public List<TextField> process(List<ValueCoord> valueCoords, ChartDataDto chartDataDto) {
        List<String> valueInStringRestrictedToPip = convertToStringRestrictedByPip(valueCoords);
        List<TextField> textFields = positionTextFields(valueCoords, chartDataDto);
        textFields = addContent(valueInStringRestrictedToPip, textFields);
        return setColorAndFont(textFields, chartDataDto);
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



    private List<TextField> positionTextFields(List<ValueCoord> valueCoords, ChartDataDto chartDataDto) {
        List<TextField> textFields = new ArrayList<>();
        int x = computeXPosition(chartDataDto);

        for(ValueCoord valueCoord : valueCoords){
            textFields.add(new TextField(x, valueCoord.getY()));
        }
        return textFields;
    }

    private int computeXPosition(ChartDataDto chartDataDto) {
        int chartBoxLeftEdge = chartDataDto.getChartConfig().getChartBoxLeftBottomCornerX();
        int chartBoxWidth = chartDataDto.getChartConfig().getChartBoxWidth();
        int textFieldHalfWidth = chartDataDto.getChartConfig().getTextElementWidth()/2;

        int chartBoxRightEdgeX = chartBoxLeftEdge + chartBoxWidth;

        return chartBoxRightEdgeX + textFieldHalfWidth;
    }

    private List<TextField> addContent(List<String> valueInStringRestrictedToPip, List<TextField> textFields) {
        for(int i=0; i<textFields.size(); i++) {
            textFields.get(i).setContent(valueInStringRestrictedToPip.get(i));
        }
        return textFields;
    }

    private List<TextField> setColorAndFont(List<TextField> textFields, ChartDataDto chartDataDto) {
        for(int i=0; i<textFields.size(); i++) {
            textFields.get(i).setColor(new Color(chartDataDto.getChartConfig().getTextColorRGB()));
            textFields.get(i).setFontSize(chartDataDto.getChartConfig().getDescriptionFontSize());
        }
        return textFields;
    }
}
