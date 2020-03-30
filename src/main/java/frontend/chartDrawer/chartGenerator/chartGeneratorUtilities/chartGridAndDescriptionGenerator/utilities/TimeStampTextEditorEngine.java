package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.Text;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TimeStampTextEditorEngine {
    private final static double charWidthInPixFontSizeMultiplier = 1;
    private final static double charHeightInPixFontSizeMultiplier = 1.5;

    public List<Text> process(List<Text> timeStampDescriptionsPositioned, ChartParameters chartParameters,
                              List<TimeStampCoord> timeStampCoords) {

        ViewTimeFrame viewTimeFrame = chartParameters.getUniversal().getViewTimeFrame();

        List<String> content = filterContentBasedOnViewTimeFrameRules(timeStampCoords, viewTimeFrame);

        return addContentAndParametersToText(timeStampDescriptionsPositioned, content, chartParameters);
    }

    private List<String> filterContentBasedOnViewTimeFrameRules(List<TimeStampCoord> timeStampCoord,
                                                                ViewTimeFrame viewTimeFrame) {
        List<String> contentFiltered = new ArrayList<>();

        switch(viewTimeFrame){
            case D1: contentFiltered = hoursOnly(timeStampCoord);
            case W1:
            case M1: contentFiltered = monthAndDayNumber(timeStampCoord);
            case Y1: contentFiltered = yearNumberAndMonth(timeStampCoord);
            case MAX: contentFiltered = year(timeStampCoord);
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

        for(TimeStampCoord timeStamp : timeStampCoords) {
            content.add(timeStamp.getTimeStamp().getMonth() + " " + timeStamp.getTimeStamp().getDayOfMonth());
        }
        return content;
    }

    private List<String> yearNumberAndMonth(List<TimeStampCoord> timeStampCoords) {
        List<String> content = new ArrayList<>();

        for(TimeStampCoord timeStamp : timeStampCoords) {
            content.add(timeStamp.getTimeStamp().getYear() + " " + timeStamp.getTimeStamp().getMonth());
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

    private List<Text> addContentAndParametersToText(List<Text> timeStampDescriptionPositioned, List<String> content,
                                                     ChartParameters chartParameters) {

        Color color = chartParameters.getText().getColor();
        int fontSize = chartParameters.getText().getFontSize();

        List<Text> finishedTextObjects = new ArrayList<>();
        for(int i=0;i<content.size();i++){
            Text timeStamp = timeStampDescriptionPositioned.get(i);
            String timeStampContent = content.get(i);

            int x = (int) computeXPosition(timeStamp, fontSize, timeStampContent);
            int y = (int) computeYPosition(fontSize, timeStampContent);

            finishedTextObjects.add(new Text(color,x,y,fontSize,timeStampContent));
        }
        return finishedTextObjects;
    }

    private double computeXPosition(Text timeStamp, int fontSize, String timeStampContent) {
        int textLenght = timeStampContent.length();
        return textLenght * fontSize * charWidthInPixFontSizeMultiplier;
    }

    private double computeYPosition(int fontSize, String timeStampContent) {
        return fontSize * charHeightInPixFontSizeMultiplier;
    }
}
