package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartPartDrawerUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartPart;
import frontend.chartDrawer.chartGenerator.chartParts.LineDto;
import frontend.chartDrawer.chartGenerator.chartParts.RectangleDto;
import frontend.chartDrawer.chartGenerator.chartParts.TextFieldDto;

import java.util.ArrayList;
import java.util.List;

public class ObjectSorter {

    public PartsListsDto sort(List<ChartPart> chartParts) {
        List<RectangleDto> rectangleDtos = new ArrayList<>();
        List<LineDto> lineDtos = new ArrayList<>();
        List<TextFieldDto> textFieldDtos = new ArrayList<>();

        for(ChartPart chartPart : chartParts) {
            if(chartPart instanceof RectangleDto) rectangleDtos.add((RectangleDto) chartPart);
            if(chartPart instanceof LineDto) lineDtos.add((LineDto) chartPart);
            if(chartPart instanceof TextFieldDto) textFieldDtos.add((TextFieldDto) chartPart);
        }
        return new PartsListsDto(rectangleDtos, lineDtos, textFieldDtos);
    }


}
