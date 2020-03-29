package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.gridAndDescriptionGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.ChartGridAndDescriptionGenerator;
import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.client.dto.DataPointDto;
import frontend.config.ChartConfig;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VisibleTimeStampsFilter {

    private ChartConfig chartConfig = new ChartConfig();

    public List<ChartGridAndDescriptionGenerator.TimeStampCoord> process(ChartParameters chartParameters) {

        int textElementWidth = getTextElementWidth(chartParameters);
        int width = chartParameters.getChartBox().getWidth();

        List<DataPointDto> dataPointDtoList = chartParameters.getUniversal().getCurrencyOverviewDto().getDataPoints();

        double step = ((double) dataPointDtoList.size()) / width;

        int cursor = 0;
        List<ChartGridAndDescriptionGenerator.TimeStampCoord> availableTimeStamps = new ArrayList<>();
        for(int i=0; i<dataPointDtoList.size(); i++) {
            int x = (int) (i * step);
            int availablePosition = cursor + textElementWidth;
            if (x > availablePosition && checkIfSpaceAvailable(x,width,textElementWidth)) {
                cursor = availablePosition;
                LocalDateTime timeStamp = dataPointDtoList.get(i).getTimeStamp();
                availableTimeStamps.add(new ChartGridAndDescriptionGenerator.TimeStampCoord(x, timeStamp, i));
            }
        }
        return availableTimeStamps;
    }

    private int getTextElementWidth(ChartParameters chartParameters){
        int horizontalMarginFromCenter = chartParameters.getText().getHorizontalMarginFromCenter();
        return horizontalMarginFromCenter*2;
    }

    private boolean checkIfSpaceAvailable(int x, int width, int textElementsWidth) {
        return x - textElementsWidth - width > 0;
    }
}
