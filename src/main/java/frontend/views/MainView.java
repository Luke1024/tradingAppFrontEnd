package frontend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import frontend.chartDrawer.chartGenerator.ChartGenerator;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.CurrencyOverviewDto;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.BackEndClient;
import frontend.client.dto.DataPointDto;
import frontend.client.dto.PairDataRequest;
import frontend.config.ChartConfig;
import frontend.views.utilities.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Route
public class MainView extends VerticalLayout {
    private Logger logger = Logger.getLogger(MainView.class.getName());
    private AvailableViews availableViews = new AvailableViews();

    private BackEndClient backEndClient = new BackEndClient();
    private int zoom = 0;
    private ViewTimeFrame defaultViewTimeFrame = ViewTimeFrame.D1;
    private ChartConsoleGenerator chartConsoleGenerator = new ChartConsoleGenerator();
    private ChartGenerator chartGenerator = new ChartGenerator();
    private ChartImageController chartImageController = new ChartImageController();
    private ChartStatusSaver chartStatusSaver = null;

    public MainView() throws IOException {
        Button logIn = new Button("Log In");
        Button signIn = new Button("Sign In");

        HorizontalLayout toolbar = new HorizontalLayout(logIn, signIn);

        List<String> availableCurrencyPairs = getAvailableCurrencyPairs();
        HorizontalLayout chartConsole = chartConsoleGenerator.generateConsole(this,availableCurrencyPairs);

        add(toolbar);
        add(chartConsole);
        Image image = generateChartImage(zoom, defaultViewTimeFrame, availableCurrencyPairs.get(0));
        add(image);
    }

    private List<String> getAvailableCurrencyPairs(){
        List<String> availableCurrencyPairs = null;
        try {
            availableCurrencyPairs = backEndClient.getAvailableCurrencyPairs();
            if (availableCurrencyPairs.size() == 0) {
                logger.log(Level.WARNING, "There is no Currency Pairs available.");
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, e.toString());
        }
        return availableCurrencyPairs;
    }

    private Image generateChartImage(int zoom, ViewTimeFrame viewTimeFrame, String currencyPairName){
        PairDataRequest pairDataRequest = generatePairDataRequest(currencyPairName);
        List<DataPointDto> dataPointDtos = new ArrayList<>();
        if(pairDataRequest != null){
            dataPointDtos = backEndClient.getCurrencyPairDataPoints(pairDataRequest);
        }
        Image chartImage = null;
        if(dataPointDtos != null){
            chartImage = getChartImage(dataPointDtos, pairDataRequest);
        }
        if(chartImage == null){
            return null;
        } else return chartImage;
    }

    private PairDataRequest generatePairDataRequest(String currencyPair){
        View defaultView = availableViews.getDefaultView();
        if(defaultView != null){
            return new PairDataRequest(currencyPair, defaultView.getRequiredPointNumber(),
                    defaultView.getRequiredPointTimeFrame());
        } else {
            return null;
        }
    }

    private Image getChartImage(List<DataPointDto> dataPointDtos, PairDataRequest pairDataRequest){
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

    public void switchTimeFrame(View view){
        System.out.println(view.getButtonName());
    }

    public void zoomPlus(){
        System.out.println("+");
    }

    public void zoomMinus(){
        System.out.println("-");
    }

    public void moveLeft(){
        System.out.println("<-");
    }

    public void moveRight(){
        System.out.println("->");
    }

        //HorizontalLayout imageHolder = new HorizontalLayout();
        //HorizontalLayout horizontalLayout = new HorizontalLayout(text, imageHolder);

        //solve requesting currencyPair one by one with CurrencyOverviewDto, control zoom with number of dataPoints
        //for(CurrencyOverviewDto currencyOverviewDto : overviewDtoPack.getOverviews()){
          //  backEndClient.getCurrenciesOverview();
        //}
        //Image image = chartGenerator.drawBasicChart(zoom ,overviewDtoPack.getOverviews().get(0));
        //imageHolder.add(image);

        //HorizontalLayout buttons = new HorizontalLayout(logIn, signIn);
        //add(buttons);

        /*
        Image image = universalChartDrawer.getBlank();
        imageHolder.add(image);
        add(imageHolder,toolbar);
        left.addClickListener(event -> {
            try {
                imageHolder.removeAll();
                imageHolder.add(sendLeftSignal());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        right.addClickListener(event -> {
            try {
                imageHolder.removeAll();
                imageHolder.add(sendRightSignal());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        up.addClickListener(event -> {
            try {
                imageHolder.removeAll();
                imageHolder.add(sendUpSignal());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        down.addClickListener(event -> {
            try {
                imageHolder.removeAll();
                imageHolder.add(sendDownSignal());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Image sendLeftSignal() throws IOException {
        return sendAndReceive(new ActuationDto(true, false, false, false));
    }

    private Image sendRightSignal() throws IOException {
        return sendAndReceive(new ActuationDto(false,true,false,false));
    }

    private Image sendUpSignal() throws IOException {
        return sendAndReceive(new ActuationDto(false, false, true, false));
    }

    private Image sendDownSignal() throws IOException {
        return sendAndReceive(new ActuationDto(false,false,false,true));
    }

    private Image sendAndReceive(ActuationDto actuationDto) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        URI url = UriComponentsBuilder.fromHttpUrl("https://backtest2131431.herokuapp.com/joystick/actuate").build().encode().toUri();
        SceneDto sceneDto = restTemplate.postForObject(url, actuationDto, SceneDto.class);

        return universalChartDrawer.getStream(sceneDto);

    */
}
