package frontend.views.utilities;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import frontend.chartDrawer.chartGenerator.ChartGenerator;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.client.BackEndClient;
import frontend.client.dto.DataPointDto;
import frontend.client.dto.PairDataRequest;
import frontend.client.dto.PointTimeFrame;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CurrencyPairChartWindowGenerator {

    private Logger logger = Logger.getLogger(CurrencyPairChartWindowGenerator.class.getName());

    private BackEndClient backEndClient = new BackEndClient();
    private AvailableViews availableViews = new AvailableViews();
    private ChartGenerator chartGenerator = new ChartGenerator();

    //default settings
    private String defaultPointTimeFrame = "Week";


    public List<HorizontalLayout> generate(List<String> availableCurrencyPairs){
        List<HorizontalLayout> currencyPairCharts = new ArrayList<>();

        if(availableCurrencyPairs != null){
            for(String currencyPair : availableCurrencyPairs) {
                currencyPairCharts.add(generateChartWindow(currencyPair));
            }
        }
        return currencyPairCharts;
    }

    private HorizontalLayout generateChartWindow(String currencyPair){
        PairDataRequest pairDataRequest = generatePairDataRequest(currencyPair);
        List<DataPointDto> dataPointDtos = new ArrayList<>();
        if(pairDataRequest != null){
            dataPointDtos = backEndClient.getCurrencyPairDataPoints(pairDataRequest);
        }
        Image chartImage;
        if(dataPointDtos != null){
            chartImage = generateChartImage(dataPointDtos);
        }

        return buildChartWindow();
    }

    private PairDataRequest generatePairDataRequest(String currencyPair){
        int defaultViewIndex = availableViews.getDefaultViewIndex();
        View defaultView = availableViews.getViews().get(defaultViewIndex);
        if(defaultView != null){
            return new PairDataRequest(currencyPair, defaultView.getRequiredPointNumber(), defaultView.getRequiredPointTimeFrame());
        } else {
            return null;
        }
    }

    private Image generateChartImage(List<DataPointDto> dataPointDtos){
        ChartDataDto chartDataDto = new ChartDataDto();
        return Image();
    }

    private
}
