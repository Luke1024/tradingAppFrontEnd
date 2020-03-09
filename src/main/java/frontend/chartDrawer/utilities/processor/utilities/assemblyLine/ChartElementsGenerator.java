package frontend.chartDrawer.utilities.processor.utilities.assemblyLine;

import frontend.chartDrawer.utilities.processor.utilities.Processor;
import frontend.chartDrawer.utilities.processor.utilities.containerParts.Color;
import frontend.chartDrawer.utilities.processor.utilities.containerParts.Rectangle;
import frontend.config.ChartConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class ChartElementsGenerator {

    @Autowired
    private ChartConfig chartConfig;



    public Processor generate(Processor processor){
        drawBackground(processor);



        return processor;
    }

    private Processor drawBackground(Processor processor){
        Color color = new Color(chartConfig.getBackGroundColor());

        Rectangle backGround = new Rectangle(color, color, true,0,0,chartConfig.getChartWidth(),
                chartConfig.getChartHeight());
        processor.getBluePrint().add(backGround);
        return processor;
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
