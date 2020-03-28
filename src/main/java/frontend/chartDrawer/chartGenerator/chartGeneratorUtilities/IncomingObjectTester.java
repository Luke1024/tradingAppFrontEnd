package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.client.dto.DataPointDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomingObjectTester {

    public boolean isObjectsCorrect(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame) {
        boolean isAnyObjectNull = checkIfAnyObjectIsNull(currencyOverviewDto, viewTimeFrame);
        boolean completeObjectIsCorrect;
        if(isAnyObjectNull) {
            completeObjectIsCorrect = false;
        } else {
            completeObjectIsCorrect = isObjectFullyInitialized(currencyOverviewDto, viewTimeFrame);
        }
        return completeObjectIsCorrect;
    }

    private boolean checkIfAnyObjectIsNull(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame) {
        if(currencyOverviewDto == null || viewTimeFrame == null) return true;
        else return false;
    }

    private boolean isObjectFullyInitialized(CurrencyOverviewDto currencyOverviewDto, ViewTimeFrame viewTimeFrame) {
        boolean isCurrencyOverviewDtoMissingSomething = isCurrencyOverviewDtoMissingSomething(currencyOverviewDto);
        boolean isViewNull = isViewFalse(viewTimeFrame);

        if(isCurrencyOverviewDtoMissingSomething || isViewNull){
            return false;
        } else {
            return true;
        }
    }

    private boolean isCurrencyOverviewDtoMissingSomething(CurrencyOverviewDto currencyOverviewDto) {
        if(currencyOverviewDto.getCurrencyName() == null ||
        currencyOverviewDto.getLastRetrieved() == null ||
        currencyOverviewDto.getDataPoints() == null ||
        currencyOverviewDto.getDataPoints().isEmpty() ||
        isAnyDataPointDtoMissingSomething(currencyOverviewDto)) {
            return true;
        } else return false;
    }

    private boolean isViewFalse(ViewTimeFrame viewTimeFrame){
        if(viewTimeFrame == null) return true;
        else return false;
    }

    private boolean isAnyDataPointDtoMissingSomething(CurrencyOverviewDto currencyOverviewDto) {
        List<DataPointDto> dataPointDtoList = currencyOverviewDto.getDataPoints();
        boolean missing = false;

        if(dataPointDtoList == null){
            missing = true;
        } else {
            for (DataPointDto dataPointDto : dataPointDtoList) {
                if (isDataPointMissingSomething(dataPointDto)) {
                    missing = true;
                }
            }
        }
        return missing;
    }

    private boolean isDataPointMissingSomething(DataPointDto dataPointDto) {
        if(dataPointDto.getTimeStamp() == null) return true;
        else return false;
    }
}
