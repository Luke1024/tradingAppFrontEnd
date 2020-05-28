package frontend.views.utilities;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import frontend.chartDrawer.chartGenerator.ChartGenerator;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.CurrencyOverviewDto;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.BackEndClient;
import frontend.client.dto.DataPointDto;
import frontend.client.dto.PairDataRequest;
import frontend.client.dto.PointTimeFrame;
import frontend.config.ChartConfig;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrencyPairChartWindowGenerator {

    private Logger logger = Logger.getLogger(CurrencyPairChartWindowGenerator.class.getName());

    private BackEndClient backEndClient = new BackEndClient();
    private AvailableViews availableViews = new AvailableViews();
    private ChartGenerator chartGenerator = new ChartGenerator();
    private View currentView;

    public CurrencyPairChartWindowGenerator(){
        this.currentView = availableViews.getDefaultView();
        if(this.currentView == null){
            logger.log(Level.WARNING, "Default View not set.");
        }
    }

    public List<VerticalLayout> generate(List<String> availableCurrencyPairs){
        List<VerticalLayout> currencyPairCharts = new ArrayList<>();

        if(availableCurrencyPairs != null){
            for(String currencyPair : availableCurrencyPairs) {
                currencyPairCharts.add(generateChartWindow(currencyPair));
            }
        }
        return currencyPairCharts;
    }

    private VerticalLayout generateChartWindow(String currencyPair){
        PairDataRequest pairDataRequest = generatePairDataRequest(currencyPair);
        List<DataPointDto> dataPointDtos = new ArrayList<>();
        if(pairDataRequest != null){
            dataPointDtos = backEndClient.getCurrencyPairDataPoints(pairDataRequest);
        }
        Image chartImage = null;
        if(dataPointDtos != null){
            chartImage = generateChartImage(dataPointDtos, pairDataRequest);
        }
        if(chartImage == null){
            return null;
        }

        return buildChartWindow(chartImage);
    }

    private PairDataRequest generatePairDataRequest(String currencyPair){
        View defaultView = availableViews.getDefaultView();
        if(defaultView != null){
            return new PairDataRequest(currencyPair, defaultView.getRequiredPointNumber(), defaultView.getRequiredPointTimeFrame());
        } else {
            return null;
        }
    }

    private Image generateChartImage(List<DataPointDto> dataPointDtos, PairDataRequest pairDataRequest) {
        ChartDataDto chartDataDto = buildChartDataDto(dataPointDtos, pairDataRequest);
        return chartGenerator.generateChart(chartDataDto);
    }

    private ChartDataDto buildChartDataDto(List<DataPointDto> dataPointDtos, PairDataRequest pairDataRequest){
        View defaultView = availableViews.getDefaultView();

        CurrencyOverviewDto currencyOverviewDto = null;
        try {
            currencyOverviewDto = new CurrencyOverviewDto(pairDataRequest.getCurrencyName(), LocalDateTime.now(), dataPointDtos);
        } catch (Exception e){}
        if(currencyOverviewDto != null){
            try {
                return new ChartDataDto(currencyOverviewDto, defaultView.getTimeFrameInfoForChartGenerator(), new ChartConfig());
            } catch (Exception e){}
        }
        return null;
    }

    private VerticalLayout buildChartWindow(Image chartImage){
        VerticalLayout chartWindow = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();

        for(View view : availableViews.getViews()){
            buttons.add(view.getButtonName());
            buttons.addClickListener(event -> {this.currentView = view;});
        }
        HorizontalLayout image = new HorizontalLayout();
        image.add(chartImage);

        chartWindow.add(buttons);
        chartWindow.add(image);

        return chartWindow;
    }
}
