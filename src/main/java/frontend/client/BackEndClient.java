package frontend.client;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class BackEndClient {
    private RestTemplate restTemplate = new RestTemplate();

    public OverviewDtoPack getCurrenciesOverview(){
        URI url = UriComponentsBuilder.fromHttpUrl("currencies_url").build().encode().toUri();
        OverviewDtoPack currencies = restTemplate.getForObject(url, OverviewDtoPack.class);
        return currencies;
    }
}
