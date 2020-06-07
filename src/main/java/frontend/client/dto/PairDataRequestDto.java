package frontend.client.dto;

public class PairDataRequestDto {
    private String currencyName;
    private int numberOfDataPoints;
    private String pointTimeFrame;
    private String adoptedLastPoint;

    public PairDataRequestDto(String currencyName, int numberOfDataPoints, String pointTimeFrame, String adoptedLastPoint) {
        this.currencyName = currencyName;
        this.numberOfDataPoints = numberOfDataPoints;
        this.pointTimeFrame = pointTimeFrame;
        this.adoptedLastPoint = adoptedLastPoint;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public int getNumberOfDataPoints() {
        return numberOfDataPoints;
    }

    public String getPointTimeFrame() {
        return pointTimeFrame;
    }

    public String getAdoptedLastPoint() {
        return adoptedLastPoint;
    }
}
