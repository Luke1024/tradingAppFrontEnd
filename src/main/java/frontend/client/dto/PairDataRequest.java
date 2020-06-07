package frontend.client.dto;

import java.time.LocalDateTime;

public class PairDataRequest {
    private String currencyName;
    private int numberOfDataPoints;
    private PointTimeFrame pointTimeFrame;
    private LocalDateTime adoptedlastPoint;

    public PairDataRequest() {
    }

    public PairDataRequest(String currencyName, int numberOfDataPoints, PointTimeFrame pointTimeFrame) {
        this.currencyName = currencyName;
        this.numberOfDataPoints = numberOfDataPoints;
        this.pointTimeFrame = pointTimeFrame;
    }

    public PairDataRequest(String currencyName, int numberOfDataPoints, PointTimeFrame pointTimeFrame, LocalDateTime adoptedlastPoint) {
        this.currencyName = currencyName;
        this.numberOfDataPoints = numberOfDataPoints;
        this.pointTimeFrame = pointTimeFrame;
        this.adoptedlastPoint = adoptedlastPoint;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public int getNumberOfDataPoints() {
        return numberOfDataPoints;
    }

    public PointTimeFrame getPointTimeFrame() {
        return pointTimeFrame;
    }

    public LocalDateTime getAdoptedlastPoint() {
        return adoptedlastPoint;
    }

    @Override
    public String toString() {
        return "currencyName=" + currencyName +
                "&numberOfDataPoints=" + numberOfDataPoints +
                "&pointTimeFrame=" + pointTimeFrame +
                "&adoptedlastPoint=" + adoptedlastPoint;
    }
}
