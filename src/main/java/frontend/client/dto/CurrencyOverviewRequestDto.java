package frontend.client.dto;

public class CurrencyOverviewRequestDto {
    private String currencyPair;
    private int numberOfDataPoints;

    public CurrencyOverviewRequestDto(String currencyPair, int numberOfDataPoints) {
        this.currencyPair = currencyPair;
        this.numberOfDataPoints = numberOfDataPoints;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public int getNumberOfDataPoints() {
        return numberOfDataPoints;
    }
}
