package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.gridAndDescriptionGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.ChartGridAndDescriptionGenerator;
import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.chartDrawer.chartGenerator.chartParts.Text;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;

import java.util.ArrayList;
import java.util.List;

public class TimeStampTextEditorEngine {
    public List<Text> process(List<Text> timeStampDescriptionsPositioned, ChartParameters chartParameters,
                              List<ChartGridAndDescriptionGenerator.TimeStampCoord> timeStampCoords) {

        ViewTimeFrame viewTimeFrame = chartParameters.getUniversal().getViewTimeFrame();

        List<String> content = filterContentBasedOnViewTimeFrameRules(timeStampCoords, viewTimeFrame);

    }

    private List<String> filterContentBasedOnViewTimeFrameRules(List<ChartGridAndDescriptionGenerator.TimeStampCoord> timeStampCoord,
                                                                ViewTimeFrame viewTimeFrame) {
        switch(viewTimeFrame){
            case D1: return hoursOnly(timeStampCoord);
            case W1:
            case M1: return monthAndDayNumber(timeStampCoord);
            case Y1: return yearNumberAndMonth(timeStampCoord);
            case MAX: return year(timeStampCoord);
        }
    }

    private List<String> hoursOnly(List<ChartGridAndDescriptionGenerator.TimeStampCoord> timeStampCoords) {
        List<String> content = new ArrayList<>();

        for(ChartGridAndDescriptionGenerator.TimeStampCoord timeStamp : timeStampCoords) {
            content.add(timeStamp.getTimeStamp().getHour() + ":00");
        }
        return content;
    }

    private List<String> monthAndDayNumber(List<ChartGridAndDescriptionGenerator.TimeStampCoord> timeStampCoords) {
        List<String> content = new ArrayList<>();

        for(ChartGridAndDescriptionGenerator.TimeStampCoord timeStamp : timeStampCoords) {
            content.add(timeStamp.getTimeStamp().getMonth() + " " + timeStamp.getTimeStamp().getDayOfMonth());
        }
        return content;
    }

    private List<String> yearNumberAndMonth(List<ChartGridAndDescriptionGenerator.TimeStampCoord> timeStampCoords) {
        List<String> content = new ArrayList<>();

        for(ChartGridAndDescriptionGenerator.TimeStampCoord timeStamp : timeStampCoords) {
            content.add(timeStamp.getTimeStamp().getYear() + " " + timeStamp.getTimeStamp().getMonth());
        }
        return content;
    }

    private List<String> year(List<ChartGridAndDescriptionGenerator.TimeStampCoord> timeStampCoords) {
        List<String> content = new ArrayList<>();

        for(ChartGridAndDescriptionGenerator.TimeStampCoord timeStamp : timeStampCoords) {
            content.add(String.valueOf(timeStamp.getTimeStamp().getYear()));
        }

        return content;
    }
}
