package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.gridAndDescriptionGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.ChartGridAndDescriptionGenerator;
import frontend.chartDrawer.chartGenerator.chartParts.ChartParameters;
import frontend.client.dto.DataPointDto;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VisibleTimeStampsFilter {

    @Autowired
    private ChartConfig chartConfig;

    private final static int numberOfValuesShow = 5;

    public List<ChartGridAndDescriptionGenerator.TimeStampCoord> process(ChartParameters chartParameters) {
        List<ChartGridAndDescriptionGenerator.TimeStampCoord> availableTimeStamps = plotAvailableTimeStamps(chartParameters);
        return availableTimeStamps;
    }

    private List<ChartGridAndDescriptionGenerator.TimeStampCoord> plotAvailableTimeStamps(ChartParameters chartParameters) {
        int verticalMarginFromCenter = chartParameters.getText().getVerticalMarginFromCenter();
        int textElementSize = verticalMarginFromCenter*2;

        int width = chartParameters.getChartBox().getWidth();

        List<DataPointDto> dataPointDtoList = chartParameters.getUniversal().getCurrencyOverviewDto().getDataPoints();

        double step = ((double) dataPointDtoList.size()) / width;

        int cursor = 0;
        List<ChartGridAndDescriptionGenerator.TimeStampCoord> chartGridAndDescriptionGenerators = new ArrayList<>();
        for(int i=0; i<dataPointDtoList.size(); i++) {
            int x = (int) (i * step);
            int availablePosition = cursor + textElementSize;
            if (x > availablePosition && checkIfSpaceAvailable(x,width,textElementSize)) {
                cursor = availablePosition;
                LocalDateTime timeStamp = dataPointDtoList.get(i).getTimeStamp();
                chartGridAndDescriptionGenerators.add(new ChartGridAndDescriptionGenerator.TimeStampCoord(x, timeStamp, i));
            }
        }
        return chartGridAndDescriptionGenerators;
    }

    private boolean checkIfSpaceAvailable(int x, int width, int textElementsSize) {
        return x - textElementsSize - width > 0;
    }
}
