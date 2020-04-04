package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.timeStampDescriptionGenerator;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.TimeStampCoord;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Text;

import java.util.ArrayList;
import java.util.List;

public class TimeStampDescriptionPositioner {
    private int chartBoxX;
    private int chartBoxY;
    private int textElementHeight;

    public List<Text> process(ChartDataDto chartDataDto, List<TimeStampCoord> timeStampCoords) {
        this.chartBoxX = chartDataDto.getChartConfig().getChartBoxLeftBottomCornerX();
        this.chartBoxY = chartDataDto.getChartConfig().getChartBoxLeftBottomCornerY();
        this.textElementHeight = chartDataDto.getChartConfig().getTextElementHeight();

        List<Text> textsData = new ArrayList<>();
        for(int i=0; i<timeStampCoords.size(); i++) {
            textsData.add(positionText(timeStampCoords.get(i), i));
        }

        return textsData;
    }

    private Text positionText(TimeStampCoord timeStampCoord, int i) {
        int x = timeStampCoord.getX() + chartBoxX;
        int y = chartBoxY - textElementHeight;
        return new Text(x,y);
    }
}
