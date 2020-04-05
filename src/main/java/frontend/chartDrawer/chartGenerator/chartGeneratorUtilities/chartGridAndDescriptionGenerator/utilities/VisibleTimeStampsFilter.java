package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.client.dto.DataPointDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VisibleTimeStampsFilter {

    private int cursor = 0;

    public List<TimeStampCoord> process(ChartDataDto chartDataDto) {

        int textElementWidth = getTextElementWidth(chartDataDto);
        int width = chartDataDto.getChartConfig().getChartBoxWidth();

        List<DataPointDto> dataPointDtoList = chartDataDto.getCurrencyOverviewDto().getDataPoints();

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

    private int getTextElementWidth(ChartDataDto chartDataDto) {
        int textElementWidth = chartDataDto.getChartConfig().getTextElementWidth();
        return textElementWidth*2;
    }

    private boolean checkIfSpaceAvailable(int x, int width, int textElementWidth) {
        return x - textElementWidth - width > 0;
    }
}
