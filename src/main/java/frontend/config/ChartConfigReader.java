package frontend.config;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public class ChartConfigReader {
    public void readConfigFile(String path) throws IOException {

        Properties properties = readFile(path);
        List<Field> fieldsInChartConfig = Arrays.asList(ChartConfig.class.getDeclaredFields());
        Map<String, String> fieldsAsString = convertFieldsToString(fieldsInChartConfig);

        ChartConfig chartConfig = initializeChartConfigBasedOnConfigFile(properties, fieldsAsString);

        System.out.println(chartConfig.toString());
    }
    private Properties readFile(String path){
        try (InputStream inputFile = new FileInputStream(path)) {
            Properties prop = new Properties();
            prop.load(inputFile);
            return prop;
        }catch (Exception e){
            return new Properties();
        }
    }

    private Map<String,String> convertFieldsToString(List<Field> fields) {
        Map<String,String> typesAndNames = new HashMap<>();
        for(Field field : fields){
            typesAndNames.put(field.getName(),field.getType().toString());
        }
        return typesAndNames;
    }

    private ChartConfig initializeChartConfigBasedOnConfigFile(Properties properties, Map<String,String> fieldsAsString) {
        ChartConfig chartConfig = new ChartConfig();
        for(Map.Entry entry : fieldsAsString.entrySet()) {
            parseField(entry, chartConfig, properties);
        }
        return chartConfig;
    }

    private ChartConfig parseField(Map.Entry<String, String> entry, ChartConfig chartConfig, Properties properties) {
        String fieldName = entry.getKey();
        String fieldType = entry.getValue();

        FieldValueWrapper value = retrieveValue(fieldName, fieldType, properties);
        return setValue(chartConfig, fieldName, value, fieldType);
    }

    private FieldValueWrapper retrieveValue(String fieldName, String fieldType, Properties properties) {
        if(fieldType.contains("String")){
            return new FieldValueWrapper(properties.getProperty(fieldName),0);
        }
        if(fieldType.contains("int")){
            return new FieldValueWrapper("",Integer.parseInt(properties.getProperty(fieldName)));
        }

        return new FieldValueWrapper("",0);
    }

    private ChartConfig setValue(ChartConfig chartConfig, String fieldName, FieldValueWrapper valueWrapper, String fieldType){
        if(fieldType.contains("String")) return setString(chartConfig, fieldName, valueWrapper);
        if(fieldType.contains("int")) return setInt(chartConfig, fieldName, valueWrapper);
        return chartConfig;
    }

    private ChartConfig setString(ChartConfig chartConfig, String fieldName, FieldValueWrapper valueWrapper) {
        PropertyDescriptor pd;
        try{
            pd = new PropertyDescriptor(fieldName, chartConfig.getClass());
            pd.getWriteMethod().invoke(chartConfig, valueWrapper.getStringValue());
        } catch (Exception e){
            e.printStackTrace();
        }
        return chartConfig;
    }

    private ChartConfig setInt(ChartConfig chartConfig, String fieldName, FieldValueWrapper valueWrapper) {
        PropertyDescriptor pd;
        try{
            pd = new PropertyDescriptor(fieldName, chartConfig.getClass());
            pd.getWriteMethod().invoke(chartConfig, valueWrapper.getIntValue());
        } catch (Exception e){
            e.printStackTrace();
        }
        return chartConfig;
    }

    private class FieldValueWrapper {
        private String stringValue;
        private int intValue;

        public FieldValueWrapper(String stringValue, int intValue) {
            this.stringValue = stringValue;
            this.intValue = intValue;
        }

        public String getStringValue() {
            return stringValue;
        }

        public int getIntValue() {
            return intValue;
        }
    }
}
