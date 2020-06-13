package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.timeStampDescriptionGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.TimeStampCoord;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.TextFieldDto;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TimeStampTextEditorEngine {

    public List<TextFieldDto> process(List<TextFieldDto> timeStampDescriptionsPositioned, List<TimeStampCoord> timeStampCoords,
                                      ChartDataDto chartDataDto) {

        ViewTimeFrame viewTimeFrame = chartDataDto.getViewTimeFrame();

        List<String> content = filterContentBasedOnViewTimeFrameRules(timeStampCoords, viewTimeFrame);

        return addContentToTextField(timeStampDescriptionsPositioned, content);
    }

    private List<String> filterContentBasedOnViewTimeFrameRules(List<TimeStampCoord> timeStampCoord,
                                                                ViewTimeFrame viewTimeFrame) {
        List<String> contentFiltered = new ArrayList<>();

        if(viewTimeFrame == ViewTimeFrame.D1) {
            contentFiltered = hoursOnly(timeStampCoord);
        }
        if(viewTimeFrame == ViewTimeFrame.W1 || viewTimeFrame == ViewTimeFrame.M1) {
            contentFiltered = monthAndDayNumber(timeStampCoord);
        }
        if(viewTimeFrame == ViewTimeFrame.Y1) {
            contentFiltered = yearNumberAndMonth(timeStampCoord);
        }
        if(viewTimeFrame == ViewTimeFrame.MAX) {
            contentFiltered = year(timeStampCoord);
        }
        return contentFiltered;
    }

    private List<String> hoursOnly(List<TimeStampCoord> timeStampCoords) {
        List<String> content = new ArrayList<>();

        for(TimeStampCoord timeStamp : timeStampCoords) {
            content.add(timeStamp.getTimeStamp().getHour() + ":00");
        }
        return content;
    }

    private List<String> monthAndDayNumber(List<TimeStampCoord> timeStampCoords) {
        List<String> content = new ArrayList<>();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);

        for(TimeStampCoord timeStamp : timeStampCoords) {
            content.add(timeStamp.getTimeStamp().format(dateTimeFormatter));
        }
        return content;
    }

    private List<String> yearNumberAndMonth(List<TimeStampCoord> timeStampCoords) {
        List<String> content = new ArrayList<>();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);

        for(TimeStampCoord timeStamp : timeStampCoords) {
            content.add(timeStamp.getTimeStamp().format(dateTimeFormatter));
        }
        return content;
    }

    private List<String> year(List<TimeStampCoord> timeStampCoords) {
        List<String> content = new ArrayList<>();

        for(TimeStampCoord timeStamp : timeStampCoords) {
            content.add(String.valueOf(timeStamp.getTimeStamp().getYear()));
        }

        return content;
    }

    private List<TextFieldDto> addContentToTextField(List<TextFieldDto> timeStampDescriptionPositioned, List<String> content) {
        for(int i=0;i<content.size();i++){
            timeStampDescriptionPositioned.get(i).setContent(content.get(i));
        }
        return timeStampDescriptionPositioned;
    }
}
