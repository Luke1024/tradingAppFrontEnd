package frontend.config;

public class ClientConfig {
    private String serverAdress = "http://localhost:8081/finance/";
    private String getAvailablePairs = "/currency/pairs";
    private String getCurrencyPairDataPoints = "currency/pairs/data";

    public String getServerAdress() {
        return serverAdress;
    }

    public String getGetAvailablePairs() {
        return getAvailablePairs;
    }

    public String getCurrencyPairDataPoints() {
        return getCurrencyPairDataPoints;
    }
}
