package frontend.views.utilities;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.DataPointDto;
import frontend.client.dto.PointTimeFrame;

import java.time.LocalDateTime;
import java.util.List;

public class ChartStatusSaver {
    private String currencyPair;
    private PointTimeFrame pointTimeFrame;
    private ViewTimeFrame viewTimeFrame;
    private int pointCount;
    private int pointsBeforeLast;
    private Image image;

    public ChartStatusSaver(String currencyPair, PointTimeFrame pointTimeFrame, ViewTimeFrame viewTimeFrame,
                            int pointCount, int pointsBeforeLast, Image image) {
        this.currencyPair = currencyPair;
        this.pointTimeFrame = pointTimeFrame;
        this.viewTimeFrame = viewTimeFrame;
        this.pointCount = pointCount;
        this.pointsBeforeLast = pointsBeforeLast;
        this.image = image;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public PointTimeFrame getPointTimeFrame() {
        return pointTimeFrame;
    }

    public ViewTimeFrame getViewTimeFrame() {
        return viewTimeFrame;
    }

    public int getPointCount() {
        return pointCount;
    }

    public int getPointsBeforeLast() {
        return pointsBeforeLast;
    }

    public Image getImage() {
        return image;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public void setPointTimeFrame(PointTimeFrame pointTimeFrame) {
        this.pointTimeFrame = pointTimeFrame;
    }

    public void setViewTimeFrame(ViewTimeFrame viewTimeFrame) {
        this.viewTimeFrame = viewTimeFrame;
    }

    public void setPointCount(int pointCount) {
        this.pointCount = pointCount;
    }

    public void setPointsBeforeLast(int pointsBeforeLast) {
        this.pointsBeforeLast = pointsBeforeLast;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ChartStatusSaver{" +
                "currencyPair='" + currencyPair + '\'' +
                ", pointTimeFrame=" + pointTimeFrame +
                ", viewTimeFrame=" + viewTimeFrame +
                ", pointCount=" + pointCount +
                ", pointsBeforeLast=" + pointsBeforeLast +
                ", image=" + image +
                '}';
    }
}
