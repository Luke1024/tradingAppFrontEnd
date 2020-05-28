package frontend.views.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.PointTimeFrame;

public class View {
    private String buttonName;
    private int requiredPointNumber;
    private PointTimeFrame requiredPointTimeFrame;
    private ViewTimeFrame timeFrameInfoForChartGenerator;

    public View(String buttonName, int requiredPointNumber, PointTimeFrame requiredPointTimeFrame,
                ViewTimeFrame timeFrameInfoForChartGenerator) {
        this.buttonName = buttonName;
        this.requiredPointNumber = requiredPointNumber;
        this.requiredPointTimeFrame = requiredPointTimeFrame;
        this.timeFrameInfoForChartGenerator = timeFrameInfoForChartGenerator;
    }

    public String getButtonName() {
        return buttonName;
    }

    public int getRequiredPointNumber() {
        return requiredPointNumber;
    }

    public PointTimeFrame getRequiredPointTimeFrame() {
        return requiredPointTimeFrame;
    }

    public ViewTimeFrame getTimeFrameInfoForChartGenerator() {
        return timeFrameInfoForChartGenerator;
    }
}
