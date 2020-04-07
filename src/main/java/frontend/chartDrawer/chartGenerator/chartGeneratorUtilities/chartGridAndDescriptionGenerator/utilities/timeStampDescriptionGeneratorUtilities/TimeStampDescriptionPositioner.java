package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.timeStampDescriptionGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.TimeStampCoord;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.TextField;

import java.util.ArrayList;
import java.util.List;

public class TimeStampDescriptionPositioner {
    private int chartBoxX;
    private int chartBoxY;
    private int textElementHeight;

    public List<TextField> process(ChartDataDto chartDataDto, List<TimeStampCoord> timeStampCoords) {
        this.chartBoxX = chartDataDto.getChartConfig().getChartBoxLeftBottomCornerX();
        this.chartBoxY = chartDataDto.getChartConfig().getChartBoxLeftBottomCornerY();
        this.textElementHeight = chartDataDto.getChartConfig().getTextElementHeight();

        List<TextField> textsData = new ArrayList<>();
        for(int i=0; i<timeStampCoords.size(); i++) {
            textsData.add(positionText(timeStampCoords.get(i), i));
        }
        return textsData;
    }

    private TextField positionText(TimeStampCoord timeStampCoord, int i) {
        int x = timeStampCoord.getX() + chartBoxX;
        int y = chartBoxY - textElementHeight/2;
        return new TextField(x,y);
    }
}
