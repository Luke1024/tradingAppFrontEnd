package frontend.chartDrawer.chartGenerator.chartParts;

import frontend.client.dto.DataPointDto;

import java.time.LocalDateTime;
import java.util.List;

public class CurrencyOverviewDto {
    private String currencyName;
    private LocalDateTime lastRetrieved;
    private List<DataPointDto> dataPoints;

    public CurrencyOverviewDto() {
    }

    public CurrencyOverviewDto(String currencyName, LocalDateTime lastRetrieved, List<DataPointDto> dataPoints) {
        this.currencyName = currencyName;
        this.lastRetrieved = lastRetrieved;
        this.dataPoints = dataPoints;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public LocalDateTime getLastRetrieved() {
        return lastRetrieved;
    }

    public List<DataPointDto> getDataPoints() {
        return dataPoints;
    }
}
