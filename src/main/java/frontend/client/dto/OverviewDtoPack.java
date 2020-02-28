package frontend.client.dto;

import java.util.List;

public class OverviewDtoPack {
    private List<CurrencyOverviewDto> overviews;

    public OverviewDtoPack(List<CurrencyOverviewDto> overviews) {
        this.overviews = overviews;
    }

    public List<CurrencyOverviewDto> getOverviews() {
        return overviews;
    }
}
