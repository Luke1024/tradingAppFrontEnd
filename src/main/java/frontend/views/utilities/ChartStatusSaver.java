package frontend.views.utilities;

import java.time.LocalDateTime;

public class ChartStatusSaver {
    private String currencyPair;
    private View view;
    private int pointCount;
    private LocalDateTime stop;

    public ChartStatusSaver(String currencyPair, View view, int pointCount, LocalDateTime stop) {
        this.currencyPair = currencyPair;
        this.view = view;
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
