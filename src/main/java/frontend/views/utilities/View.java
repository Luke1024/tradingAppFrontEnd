package frontend.views.utilities;

import frontend.client.dto.PointTimeFrame;

public class View {
    private String buttonName;
    private int requiredPointNumber;
    private PointTimeFrame requiredPointTimeFrame;

    public View(String buttonName, int requiredPointNumber, PointTimeFrame requiredPointTimeFrame) {
        this.buttonName = buttonName;
        this.requiredPointNumber = requiredPointNumber;
        this.requiredPointTimeFrame = requiredPointTimeFrame;
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
}
