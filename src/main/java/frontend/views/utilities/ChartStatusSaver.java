package frontend.views.utilities;

import java.time.LocalDateTime;

public class ChartStatusSaver {
    private String currencyPair;
    private View view;
    private boolean viewIgnore;
    private int pointCount;
    private LocalDateTime stop;

    public ChartStatusSaver(String currencyPair, View view, boolean viewIgnore, int pointCount) {
        this.currencyPair = currencyPair;
        this.view = view;
        this.viewIgnore = viewIgnore;
        this.pointCount = pointCount;
    }

    public ChartStatusSaver(String currencyPair, View view, boolean viewIgnore, int pointCount, LocalDateTime stop) {
        this.currencyPair = currencyPair;
        this.view = view;
        this.viewIgnore = viewIgnore;
        this.pointCount = pointCount;
        this.stop = stop;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public boolean isViewIgnore() {
        return viewIgnore;
    }

    public void setViewIgnore(boolean viewIgnore) {
        this.viewIgnore = viewIgnore;
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
}
