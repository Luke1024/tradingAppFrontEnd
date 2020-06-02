package frontend.views.utilities;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.PointTimeFrame;

import java.time.LocalDateTime;

public class ChartStatusSaver {
    private String currencyPair;
    private PointTimeFrame pointTimeFrame;
    private ViewTimeFrame viewTimeFrame;
    private int pointCount;
    private LocalDateTime stop;
    private Image image;

    public ChartStatusSaver(String currencyPair, PointTimeFrame pointTimeFrame, ViewTimeFrame viewTimeFrame,
                            int pointCount, LocalDateTime stop) {
        this.currencyPair = currencyPair;
        this.pointTimeFrame = pointTimeFrame;
        this.viewTimeFrame = viewTimeFrame;
        this.pointCount = pointCount;
        this.stop = stop;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public PointTimeFrame getPointTimeFrame() {
        return pointTimeFrame;
    }

    public void setPointTimeFrame(PointTimeFrame pointTimeFrame) {
        this.pointTimeFrame = pointTimeFrame;
    }

    public ViewTimeFrame getViewTimeFrame() {
        return viewTimeFrame;
    }

    public void setViewTimeFrame(ViewTimeFrame viewTimeFrame) {
        this.viewTimeFrame = viewTimeFrame;
    }

    public int getPointCount() {
        return pointCount;
    }

    public void setPointCount(int pointCount) {
        this.pointCount = pointCount;
    }

    public LocalDateTime getStop() {
        return stop;
    }

    public void setStop(LocalDateTime stop) {
        this.stop = stop;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
