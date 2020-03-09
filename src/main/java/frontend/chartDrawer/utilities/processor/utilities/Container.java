package frontend.chartDrawer.utilities.processor.utilities;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.utilities.dataObjects.TpSlLineData;
import frontend.chartDrawer.utilities.processor.utilities.containerParts.Element;
import frontend.client.dto.CurrencyOverviewDto;

import java.util.LinkedList;
import java.util.Queue;

public class Container {
    private TpSlLineData tpSlLineData;
    private CurrencyOverviewDto currencyOverviewDto;
    private Queue<Element> bluePrint = new LinkedList<>();
    private Image image;

    public void setTpSlLineData(TpSlLineData tpSlLineData) {
        this.tpSlLineData = tpSlLineData;
    }

    public void setCurrencyOverviewDto(CurrencyOverviewDto currencyOverviewDto) {
        this.currencyOverviewDto = currencyOverviewDto;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public TpSlLineData getTpSlLineData() {
        return tpSlLineData;
    }

    public CurrencyOverviewDto getCurrencyOverviewDto() {
        return currencyOverviewDto;
    }

    public Queue<Element> getBluePrint() {
        return bluePrint;
    }

    public Image getImage() {
        return image;
    }
}
