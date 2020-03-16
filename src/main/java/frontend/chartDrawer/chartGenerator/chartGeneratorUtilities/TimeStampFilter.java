package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.client.dto.TimeFrame;

import java.util.Arrays;
import java.util.List;

public class TimeStampFilter {

    public List<Integer> getVerticalGridDividers(TimeFrame timeFrame) {
        switch(timeFrame){
            case H1:
                return Arrays.asList(3,6,24,7);
            case H5:
                return Arrays.asList();
            case D1:
            case W1:
            case M1:
            case Y1:
        }
    }

}
