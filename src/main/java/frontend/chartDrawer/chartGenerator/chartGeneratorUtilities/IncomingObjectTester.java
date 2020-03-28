package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.client.dto.DataPointDto;

import java.util.List;

public class IncomingObjectTester {
    public boolean test(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame) {
        boolean isAnyObjectNull = checkIfAnyObjectIsNull(currencyOverviewDto, viewTimeFrame);
        boolean completeObjectIsCorrect;
        if(isAnyObjectNull) {
            return false;
        } else {
            completeObjectIsCorrect = isObjectCorrect(currencyOverviewDto, viewTimeFrame);
        }
        return completeObjectIsCorrect;
    }

    private boolean checkIfAnyObjectIsNull(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame) {
        if(currencyOverviewDto == null || viewTimeFrame == null) return true;
        else return false;
    }

    private boolean isObjectCorrect(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame) {
        boolean isOverviewFalse = isCurrencyOverviewDtoFalse(currencyOverviewDto);
        boolean isViewFalse = isViewFalse(viewTimeFrame);

        if(isOverviewFalse || isViewFalse){
            return false;
        } else {
            return true;
        }
    }

    private boolean isCurrencyOverviewDtoFalse(CurrencyOverviewDto currencyOverviewDto) {
        if(currencyOverviewDto.getCurrencyName() == null ||
        currencyOverviewDto.getLastRetrieved() == null ||
        currencyOverviewDto.getDataPoints() == null ||
        currencyOverviewDto.getDataPoints().isEmpty() ||
        isAnyDataPointDtoFalse(currencyOverviewDto)) {
            return true;
        } else return false;
    }

    private boolean isViewFalse(ViewTimeFrame viewTimeFrame){
        if(viewTimeFrame == null) return true;
        else return false;
    }

    private boolean isAnyDataPointDtoFalse(CurrencyOverviewDto currencyOverviewDto) {
        List<DataPointDto> dataPointDtoList = currencyOverviewDto.getDataPoints();
        boolean isFalse = false;

        for(DataPointDto dataPointDto : dataPointDtoList) {
            isFalse = isSingleDataPointDtoFalse(dataPointDto);
        }
        return isFalse;
    }

    private boolean isSingleDataPointDtoFalse(DataPointDto dataPointDto) {
        if(dataPointDto.getTimeStamp() != null) return true;
        else return false;
    }
}
