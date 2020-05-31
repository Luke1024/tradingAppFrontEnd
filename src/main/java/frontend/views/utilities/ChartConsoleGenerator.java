package frontend.views.utilities;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import frontend.views.MainView;

import java.util.ArrayList;
import java.util.List;

public class ChartConsoleGenerator {
    private AvailableViews availableViews = new AvailableViews();
    private MainView mainView;

    public HorizontalLayout generateConsole(MainView mainView, List<String> currencyPairs){
        this.mainView = mainView;
        HorizontalLayout chartConsole = new HorizontalLayout();

        ComboBox<String> currencyPairBox = new ComboBox<>();

        if(currencyPairs != null && currencyPairs.size()>0){
            currencyPairBox.setItems(currencyPairs);
            currencyPairBox.setPlaceholder(currencyPairs.get(0));
        }

        currencyPairBox.addValueChangeListener(e -> {this.mainView.switchCurrencyPair(e.getValue());});
        chartConsole.add(currencyPairBox);

        List<Button> buttons = generateChartControlButtons();
        for(Button button : buttons){
            chartConsole.add(button);
        }
        return chartConsole;
    }

    private List<Button> generateChartControlButtons(){
        List<Button> chartControlButtons = new ArrayList<>();

        for(View view : availableViews.getViews()){
            Button button = new Button(view.getButtonName());
            button.addClickListener(e -> {
                this.mainView.switchTimeFrame(view);
            });
            chartControlButtons.add(button);
        }

        Button buttonZoomPlus = new Button("+");
        Button buttonZoomMinus = new Button("-");
        Button moveLeft = new Button("<-");
        Button moveRight = new Button("->");
        Button moveMoreLeft = new Button("<<");
        Button moveMoreRight = new Button(">>");
        Button resetView = new Button("RESET");


        buttonZoomPlus.addClickListener(e -> {
            this.mainView.zoomPlus();
        });
        buttonZoomMinus.addClickListener(e -> {
            this.mainView.zoomMinus();
        });
        moveLeft.addClickListener(e -> {
            System.out.println("<-");
        });
        moveRight.addClickListener(e -> {
            System.out.println("->");
        });
        moveMoreLeft.addClickListener(e -> {
            System.out.println("<<");
        });
        moveMoreRight.addClickListener(e -> {
            System.out.println(">>");
        });
        resetView.addClickListener(e -> {
            System.out.println("RESET");
        });

        chartControlButtons.add(buttonZoomPlus);
        chartControlButtons.add(buttonZoomMinus);
        chartControlButtons.add(moveLeft);
        chartControlButtons.add(moveRight);
        chartControlButtons.add(moveMoreLeft);
        chartControlButtons.add(moveMoreRight);
        chartControlButtons.add(resetView);

        return chartControlButtons;
    }
}
