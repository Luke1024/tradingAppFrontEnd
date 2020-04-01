package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.client.dto.DataPointDto;
import frontend.config.ChartConfigWithConfiguration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VisibleTimeStampsFilter {

    private ChartConfigWithConfiguration chartConfigWithConfiguration = new ChartConfigWithConfiguration();
    private int cursor = 0;

    public List<TimeStampCoord> process(ChartParameters chartParameters) {

        int textElementWidth = getTextElementWidth(chartParameters);
        int width = chartParameters.getChartBox().getWidth();

        List<DataPointDto> dataPointDtoList = chartParameters.getUniversal().getCurrencyOverviewDto().getDataPoints();

        double step = ((double) dataPointDtoList.size()) / width;

        int cursor = 0;
        List<TimeStampCoord> availableTimeStamps = new ArrayList<>();
        for(int i=0; i<dataPointDtoList.size(); i++) {
            int x = (int) (i * step);
            int availablePosition = cursor + textElementWidth;
            if (x > availablePosition && checkIfSpaceAvailable(x,width,textElementWidth)) {
                availableTimeStamps.add(returnAvailableTimeStamp(i,x,availablePosition, dataPointDtoList.get(i)));
            }
        }
        return availableTimeStamps;
    }

    private TimeStampCoord returnAvailableTimeStamp(int i, int x, int availablePosition, DataPointDto dataPointDto) {
        cursor = availablePosition;
        LocalDateTime timeStamp = dataPointDto.getTimeStamp();
        return new TimeStampCoord(x, timeStamp, i);
    }

    private int getTextElementWidth(ChartParameters chartParameters) {
        int horizontalMarginFromCenter = chartParameters.getText().getHorizontalMarginFromCenter();
        return horizontalMarginFromCenter*2;
    }

    private boolean checkIfSpaceAvailable(int x, int width, int textElementsWidth) {
        return x - textElementsWidth - width > 0;
    }
}
