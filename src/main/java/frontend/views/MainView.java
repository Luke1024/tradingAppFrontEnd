package frontend.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import frontend.chartDrawer.chartGenerator.ChartGenerator;
import frontend.client.BackEndClient;
import frontend.config.ChartConfig;
import frontend.config.ChartConfigReader;
import frontend.views.utilities.*;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Route
public class MainView extends VerticalLayout {
    private static final Logger logger = Logger.getLogger(MainView.class.getName());
    private static final AvailableViews availableViews = new AvailableViews();

    private ChartConfigReader chartConfigReader = new ChartConfigReader();

    private BackEndClient backEndClient = new BackEndClient();
    private ChartConsoleGenerator chartConsoleGenerator = new ChartConsoleGenerator();
    private ChartGenerator chartGenerator = new ChartGenerator();
    private ChartImageController chartImageController =
            new ChartImageController(chartGenerator, backEndClient, availableViews);
    private HorizontalLayout imageHolder = new HorizontalLayout();

    public MainView() throws IOException {
        //fix config reader
        ChartConfig chartConfig = new ChartConfig();
        chartImageController.setChartConfig(chartConfig);

        Notification workInProgress = new Notification();
        workInProgress.setText("Work in progress.");
        workInProgress.setDuration(2000);
        workInProgress.setPosition(Notification.Position.TOP_START);

        Button logIn = new Button("Log In");
        Button signIn = new Button("Sign In");
        logIn.addClickListener(e -> workInProgress.open());
        signIn.addClickListener(e -> workInProgress.open());

        HorizontalLayout toolbar = new HorizontalLayout(logIn, signIn);

        List<String> availableCurrencyPairs = getAvailableCurrencyPairs();
        HorizontalLayout chartConsole = chartConsoleGenerator.generateConsole(this, availableCurrencyPairs);

        add(toolbar);
        add(chartConsole);
        add(imageHolder);
        Image image = chartImageController.setCurrencyPair(availableCurrencyPairs.get(0));
        if(image != null) {
            imageHolder.add(image);
        } else {
            logger.log(Level.WARNING, "Image is null.");
        }
    }

    private List<String> getAvailableCurrencyPairs(){
        List<String> availableCurrencyPairs = null;
        try {
            availableCurrencyPairs = backEndClient.getAvailableCurrencyPairs();
            if (availableCurrencyPairs.isEmpty()) {
                logger.log(Level.WARNING, "There is no Currency Pairs available.");
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, e.toString());
        }
        return availableCurrencyPairs;
    }

    public void switchCurrencyPair(String currencyPair){
        imageHolder.removeAll();
        Image image =chartImageController.setCurrencyPair(currencyPair);
        if(image != null) {
            imageHolder.add(image);
        } else {
            logger.log(Level.WARNING, "Image is null.");
        }
    }

    public void switchTimeFrame(View view) {
        imageHolder.removeAll();
        Image image = chartImageController.setTimeFrame(view);
        if(image != null){
            imageHolder.add(image);
        }else {
            logger.log(Level.WARNING, "Image is null.");
        }
    }

    public void moveLeft(){
        imageHolder.removeAll();
        Image image = chartImageController.moveLeft();
        if(image != null){
            imageHolder.add(image);
        } else {
            logger.log(Level.WARNING, "Image is null.");
        }
    }

    public void moveRight(){
        imageHolder.removeAll();
        Image image = chartImageController.moveRight();
        if(image != null){
            imageHolder.add(image);
        } else {
            logger.log(Level.WARNING, "Image is null.");
        }
    }

    public void moveMoreLeft(){
        imageHolder.removeAll();
        Image image = chartImageController.moveMoreLeft();
        if(image != null){
            imageHolder.add(image);
        } else {
            logger.log(Level.WARNING, "Image is null.");
        }
    }

    public void moveMoreRight(){
        imageHolder.removeAll();
        Image image = chartImageController.moveMoreRight();
        if(image != null){
            imageHolder.add(image);
        } else {
            logger.log(Level.WARNING, "Image is null.");
        }
    }

    public void resetView(){
        imageHolder.removeAll();
        Image image = chartImageController.resetView();
        if(image != null){
            imageHolder.add(image);
        } else {
            logger.log(Level.WARNING, "Image is null.");
        }
    }
}
