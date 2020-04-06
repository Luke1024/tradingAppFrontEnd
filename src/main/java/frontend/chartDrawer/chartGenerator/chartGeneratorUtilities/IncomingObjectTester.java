package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.client.dto.DataPointDto;
import frontend.config.ChartConfig;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class IncomingObjectTester {

    private boolean nullNotFound = true;

    public boolean isObjectsCorrect(ChartDataDto chartDataDto) {
        boolean currencyCorrect = currencyOverviewDtoIsCorrect(chartDataDto.getCurrencyOverviewDto());
        boolean viewCorrect = viewTimeFrameIsCorrect(chartDataDto.getViewTimeFrame());
        boolean chartConfig = chartConfigCorrect(chartDataDto.getChartConfig());

        if(currencyCorrect && viewCorrect && chartConfig) return true;
        else return false;
    }

    private boolean currencyOverviewDtoIsCorrect(CurrencyOverviewDto currencyOverviewDto) {
        if(currencyOverviewDto == null) return false;
        else {
            if(currencyOverviewDto.getCurrencyName() == null) return false;
            if(currencyOverviewDto.getLastRetrieved() == null) return false;
            if(currencyOverviewDto.getDataPoints() == null) return false;
            if(dataPointsIsOk(currencyOverviewDto.getDataPoints())) return true;
            else return false;
        }
    }

    private boolean dataPointsIsOk(List<DataPointDto> dataPointDtoList) {
        boolean ok = true;
        for(DataPointDto dataPointDto : dataPointDtoList){
            if(dataPointNotOk(dataPointDto)) ok = false;
        }
        return ok;
    }

    private boolean dataPointNotOk(DataPointDto dataPointDto) {
        if(dataPointDto.getTimeStamp() == null) return true;
        return false;
    }


    private boolean viewTimeFrameIsCorrect(ViewTimeFrame viewTimeFrame){
        if(viewTimeFrame == null) return false;
        return true;
    }

    private boolean chartConfigCorrect(ChartConfig chartConfig) {
        if(chartConfig == null) return false;
        else {
            List<Field> chartConfigFields = Arrays.asList(chartConfig.getClass().getDeclaredFields());
            for (Field field : chartConfigFields) {
                if (field == null) {
                    return false;
                }
            }
        }
        return true;
    }
    /*
        List<Field> extractedFields = Arrays.asList(chartDataDto.getClass().getDeclaredFields());
        extractAllFieldsFromObjectAndNestedObjects(extractedFields);
        return nullNotFound;
    }

    //what type of object field returning ????

    private void extractAllFieldsFromObjectAndNestedObjects(List<Field> fieldsToExtraction) {
        List<Field> fields = new ArrayList<>();
        for (Field field : fieldsToExtraction) {
            fields.addAll(Arrays.asList(field.getType().getClass().getDeclaredFields()));
            System.out.println(fields.toString());
            if (fields.size() > 0) {
                nullNotFound = checkIfAllInitialized(fields);
                if(nullNotFound = false){
                    return;
                }
                extractAllFieldsFromObjectAndNestedObjects(fields);
            } else {
                return;
            }
        }
    }

    private boolean checkIfAllInitialized(List<Field> objectsExtractedFromSingleField) {
        boolean initialized = true;

        for (Field object : objectsExtractedFromSingleField) {
            if (object == null) {
                initialized = false;
            }
        }
        return initialized;
    }
    */
}