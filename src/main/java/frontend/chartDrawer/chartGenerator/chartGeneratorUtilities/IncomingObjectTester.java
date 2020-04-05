package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IncomingObjectTester {

    private boolean nullNotFound = true;

    public boolean isObjectsCorrect(ChartDataDto chartDataDto) {
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
}