package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.gridAndDescriptionGeneratorUtilities.timeStampDescriptionGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.ChartGridAndDescriptionGenerator;
import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.chartDrawer.chartGenerator.chartParts.Color;
import frontend.chartDrawer.chartGenerator.chartParts.Text;

import java.util.ArrayList;
import java.util.List;

public class TimeStampDescriptionPositioner {
    private int chartBoxX;
    private int chartBoxY;
    private int verticalMarginFromCenter;

    public List<Text> process(ChartParameters chartParameters, List<ChartGridAndDescriptionGenerator.TimeStampCoord> timeStampCoords) {
        this.chartBoxX = chartParameters.getChartBox().getX();
        this.chartBoxY = chartParameters.getChartBox().getY();
        this.verticalMarginFromCenter = chartParameters.getText().getVerticalMarginFromCenter();

        List<Text> textsData = new ArrayList<>();
        for(int i=0; i<timeStampCoords.size(); i++) {
            textsData.add(positionText(timeStampCoords.get(i), i));
        }

        return textsData;
    }

    private Text positionText(ChartGridAndDescriptionGenerator.TimeStampCoord timeStampCoord, int i) {
        int x = timeStampCoord.getX() + chartBoxX;
        int y = chartBoxY + verticalMarginFromCenter;
        return new Text(x,y);
    }
}
