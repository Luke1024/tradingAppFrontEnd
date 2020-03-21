package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.Text;

import java.util.ArrayList;
import java.util.List;

public class ChartTimeStampDescriptionPositioner {

    public List<Text> process(ChartParameters chartParameters, List<ChartGridAndDescriptionGenerator.TimeStampCoord> timeStampCoords) {
        int chartBoxX = chartParameters.getChartBox().getX();
        int chartBoxY = chartParameters.getChartBox().getY();
        int horizontalMarginFromCenter = chartParameters.getText().getHorizontalMarginFromCenter();
        int fontSize = chartParameters.getText().getFontSize();
        Color textColor = chartParameters.getText().getColor();


        List<Text> textsData = new ArrayList<>();
        for(int i=0; i<timeStampCoords.size(); i++) {

        }
    }
}
