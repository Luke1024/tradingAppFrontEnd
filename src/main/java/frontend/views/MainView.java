package frontend.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import frontend.client.BackEndClient;
import frontend.chartDrawer.ChartGenerator;
import frontend.client.dto.CurrencyOverviewDto;

import java.io.IOException;

@Route
public class MainView extends VerticalLayout {
    private Button logIn = new Button("Log In");
    private Button signIn = new Button("Sign In");
    private ChartGenerator chartGenerator = new ChartGenerator();
    private BackEndClient backEndClient = new BackEndClient();
    private int zoom = 0;

    public MainView() throws IOException {
        HorizontalLayout toolbar = new HorizontalLayout(logIn, signIn);
        Text text = new Text("EUR/USD 1.09");

        HorizontalLayout imageHolder = new HorizontalLayout();
        //HorizontalLayout horizontalLayout = new HorizontalLayout(text, imageHolder);


        //solve requesting currencyPair one by one with CurrencyOverviewDto, control zoom with number of dataPoints
        //for(CurrencyOverviewDto currencyOverviewDto : overviewDtoPack.getOverviews()){
          //  backEndClient.getCurrenciesOverview();
        //}
        //Image image = chartGenerator.drawBasicChart(zoom ,overviewDtoPack.getOverviews().get(0));
        //imageHolder.add(image);

        HorizontalLayout buttons = new HorizontalLayout(logIn, signIn);
        add(buttons);

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
}
