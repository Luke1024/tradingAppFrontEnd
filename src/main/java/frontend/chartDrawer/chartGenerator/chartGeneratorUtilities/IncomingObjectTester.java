package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.client.dto.DataPointDto;

import java.util.List;

public class IncomingObjectTester {
    public boolean test(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame) {
        boolean objectsNull = isObjectsNull(currencyOverviewDto, viewTimeFrame);
        boolean subobjectsIsNull;
        if( ! objectsNull) {
            subobjectsIsNull = isObjectCorrect(currencyOverviewDto, viewTimeFrame);
        }
    }

    private boolean isObjectsNull(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame) {
        if(currencyOverviewDto == null || viewTimeFrame == null) return true;
        else return false;
    }

    private boolean isObjectCorrect(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame) {
        boolean currencySubObjNull = isDataPointDtosCorrect(currencyOverviewDto);
        boolean viewNull = isViewCorrect(viewTimeFrame);
    }

    private boolean isDataPointDtosCorrect(CurrencyOverviewDto currencyOverviewDto) {
        if(currencyOverviewDto.getCurrencyName() == null ||
        currencyOverviewDto.getLastRetrieved() == null ||
        currencyOverviewDto.getDataPoints() == null ||
        currencyOverviewDto.getDataPoints().isEmpty() ||
        isDataPointDtosCorrect(currencyOverviewDto)) {
            return true;
        } else return false;
    }

    private boolean isViewCorrect(ViewTimeFrame viewTimeFrame){
        if(viewTimeFrame == null) return true;
        else return false;
    }

    private boolean isAnyDataPointDtoEmptyOrNull(CurrencyOverviewDto currencyOverviewDto) {
        List<DataPointDto> dataPointDtoList = currencyOverviewDto.getDataPoints();
        boolean isCorrect = true;

        for(DataPointDto dataPointDto : dataPointDtoList) {
            isCorrect = isSingleDataPointDtoCorrect(dataPointDto);
        }
        return isCorrect;
    }

    private boolean isSingleDataPointDtoCorrect(DataPointDto dataPointDto) {
        if(dataPointDto.getTimeStamp() != null) return true;
        else return false;
    }
}
