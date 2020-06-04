package frontend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import frontend.chartDrawer.chartGenerator.ChartGenerator;
import frontend.client.BackEndClient;
import frontend.views.utilities.*;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Route
public class MainView extends VerticalLayout {
    private Logger logger = Logger.getLogger(MainView.class.getName());
    private AvailableViews availableViews = new AvailableViews();

    private BackEndClient backEndClient = new BackEndClient();
    private ChartConsoleGenerator chartConsoleGenerator = new ChartConsoleGenerator();
    private ChartGenerator chartGenerator = new ChartGenerator();
    private ChartImageController chartImageController =
            new ChartImageController(chartGenerator, backEndClient, availableViews);

    public MainView() throws IOException {
        Button logIn = new Button("Log In");
        Button signIn = new Button("Sign In");

        HorizontalLayout toolbar = new HorizontalLayout(logIn, signIn);

        List<String> availableCurrencyPairs = getAvailableCurrencyPairs();
        HorizontalLayout chartConsole = chartConsoleGenerator.generateConsole(this,availableCurrencyPairs);

        add(toolbar);
        add(chartConsole);
        Image image = chartImageController.setCurrencyPair(availableCurrencyPairs.get(0));
        if(image != null) {
            add(image);
        }
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

    public Image switchCurrencyPair(String currencyPair){
        return chartImageController.setCurrencyPair(currencyPair);
    }

    public Image switchTimeFrame(View view){
        return chartImageController.setTimeFrame(view);
    }

    public Image moveLeft(){
        return chartImageController.moveLeft();
    }

    public Image moveRight(){
        return chartImageController.moveRight();
    }

    public Image moveMoreLeft(){
        return chartImageController.moveMoreLeft();
    }

    public Image moveMoreRight(){
        return chartImageController.moveMoreRight();
    }

    public Image resetView(){
        return chartImageController.resetView();
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
