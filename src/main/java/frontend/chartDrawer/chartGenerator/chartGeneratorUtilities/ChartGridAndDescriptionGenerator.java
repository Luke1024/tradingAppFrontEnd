package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.gridAndDescriptionGeneratorUtilities.TimeStampDescriptionGenerator;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.gridAndDescriptionGeneratorUtilities.VisibleTimeStampsFilter;
import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.gridAndDescriptionGeneratorUtilities.VerticalLinesGenerator;
import frontend.chartDrawer.chartGenerator.chartParts.*;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChartGridAndDescriptionGenerator {

    @Autowired
    private ChartConfig chartConfig;

    private VisibleTimeStampsFilter visibleTimeStampsFilter;
    private TimeStampDescriptionGenerator timeStampDescriptionGenerator;
    private VerticalLinesGenerator verticalLinesGenerator;


    public List<ChartPart> generate(ChartParameters chartParameters) {
        List<ChartPart> chartParts = new ArrayList<>();
        chartParts.addAll(generateVerticalLinesWithTextDescription(chartParameters));
        chartParts.addAll(generateHorizontalLinesWithTextDescription(chartParameters));
        return chartParts;
    }

    private List<ChartPart> generateVerticalLinesWithTextDescription(ChartParameters chartParameters) {

        List<ChartPart> chartParts = new ArrayList<>();
        List<TimeStampCoord> timeStampCoords = visibleTimeStampsFilter.process(chartParameters);
        chartParts.addAll(timeStampDescriptionGenerator.process(chartParameters, timeStampCoords));
        chartParts.addAll(verticalLinesGenerator.process(chartParameters, timeStampCoords));

        return chartParts;
    }

    private List<ChartPart> generateHorizontalLinesWithTextDescription(ChartParameters chartParameters) {
        List<ChartPart> chartParts = new ArrayList<>();
        List<TimeStampCoord> priceCoords = visiblePriceFilter.process(chartParameters);
    }

    public static class TimeStampCoord {
        private int x;
        private LocalDateTime timeStamp;
        private int index;

        public TimeStampCoord(int x, LocalDateTime timeStamp, int index) {
            this.x = x;
            this.timeStamp = timeStamp;
            this.index = index;
        }

        public int getX() {
            return x;
        }

        public LocalDateTime getTimeStamp() {
            return timeStamp;
        }

        public int getIndex() {
            return index;
        }
    }
}
