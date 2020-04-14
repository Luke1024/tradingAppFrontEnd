package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartPartDrawerUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.LineDto;
import frontend.chartDrawer.chartGenerator.chartParts.RectangleDto;
import frontend.chartDrawer.chartGenerator.chartParts.TextFieldDto;

import java.util.List;

public class PartsListsDto {
    private List<RectangleDto> rectangleDtos;
    private List<LineDto> lineDtos;
    private List<TextFieldDto> textFieldDtos;

    public PartsListsDto(List<RectangleDto> rectangleDtos, List<LineDto> lineDtos, List<TextFieldDto> textFieldDtos) {
        this.rectangleDtos = rectangleDtos;
        this.lineDtos = lineDtos;
        this.textFieldDtos = textFieldDtos;
    }

    public List<RectangleDto> getRectangleDtos() {
        return rectangleDtos;
    }

    public List<LineDto> getLineDtos() {
        return lineDtos;
    }

    public List<TextFieldDto> getTextFieldDtos() {
        return textFieldDtos;
    }
}
