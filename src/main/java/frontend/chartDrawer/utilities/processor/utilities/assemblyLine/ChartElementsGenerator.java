package frontend.chartDrawer.utilities.processor.utilities.assemblyLine;

import frontend.chartDrawer.utilities.processor.utilities.Container;
import frontend.chartDrawer.utilities.processor.utilities.containerParts.Color;
import frontend.chartDrawer.utilities.processor.utilities.containerParts.Rectangle;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class ChartElementsGenerator {

    @Autowired
    private ChartConfig chartConfig;



    public Container generate(Container container){
        drawBackground(container);



        return container;
    }

    private Container drawBackground(Container container){
        Color color = new Color(chartConfig.getBackGroundColor());

        Rectangle backGround = new Rectangle(color, color, true,0,0,chartConfig.getChartWidth(),
                chartConfig.getChartHeight());
        container.getBluePrint().add(backGround);
        return container;
    }

    private class ChartBox {
        int x1;
        int y1;
        int x2;
        int y2;

        public ChartBox() {
        }
    }
}
