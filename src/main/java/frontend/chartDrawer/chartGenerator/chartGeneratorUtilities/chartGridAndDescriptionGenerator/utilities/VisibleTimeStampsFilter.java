package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.DataPointDto;
import frontend.client.dto.PointTimeFrame;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class VisibleTimeStampsFilter {

    public List<TimeStampCoord> process(ChartDataDto chartDataDto) {

        int textElementWidth = chartDataDto.getChartConfig().getTextElementWidth();
        int chartBoxWidth = chartDataDto.getChartConfig().getChartBoxWidth();

        List<DataPointDto> dataPointDtoList = chartDataDto.getCurrencyOverviewDto()
                .getDataPoints();
        int stepCount = dataPointDtoList.size()-1;

        double step = chartBoxWidth / stepCount;

        int cursor = 0;
        List<TimeStampCoord> availableTimeStamps = new ArrayList<>();
        for(int i=0; i<stepCount; i++) {
            int x = (int) (i * step);
            if(isThereSpaceToPlaceElement(cursor, x, textElementWidth) &&
                    isThereSpaceLeftInTheChartBox(x, textElementWidth, chartBoxWidth)) {
                availableTimeStamps.add(returnAvailableTimeStamp(i,x,dataPointDtoList.get(i)));
                cursor = moveCursor(x, textElementWidth);
            }
        }
        return availableTimeStamps;
    }

    //timestamp autofill

    private boolean isThereSpaceToPlaceElement(int cursor, int x, int textElementWidth) {
        return cursor + textElementWidth/2 < x;
    }

    private boolean isThereSpaceLeftInTheChartBox(int x, int textElementWidth, int chartBoxWidth) {
        return chartBoxWidth - textElementWidth/2 - x > 0;
    }

    private TimeStampCoord returnAvailableTimeStamp(int i, int x, DataPointDto dataPointDto) {
        LocalDateTime timeStamp = dataPointDto.getTimeStamp();
        return new TimeStampCoord(x, timeStamp, i);
    }

    private int moveCursor(int x, int textElementWidth) {
        return x + textElementWidth/2;
    }
}
