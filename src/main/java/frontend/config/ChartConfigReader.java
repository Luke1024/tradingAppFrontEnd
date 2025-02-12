package frontend.config;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChartConfigReader {

    private Logger logger = Logger.getLogger(ChartConfigReader.class.getName());

    public ChartConfig readConfigFile(String path) {
        if(path == null){
            logger.log(Level.INFO, "chart config path is null");
            return new ChartConfig();
        }
        ClassLoader classLoader = new ChartConfigReader().getClass().getClassLoader();

        String pathString = classLoader.getResource(path).getFile();
        if(pathString == null){
            return new ChartConfig();
        }

        File file = new File(pathString);


        Properties properties = readFile(file.getPath());
        List<Field> fieldsInChartConfig = Arrays.asList(ChartConfig.class.getDeclaredFields());
        Map<String, String> fieldsAsString = convertFieldsToString(fieldsInChartConfig);

        return initializeChartConfigBasedOnConfigFile(properties, fieldsAsString);
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

        return retrieveAndSetValue(fieldName, fieldType, properties, chartConfig);
    }

    private ChartConfig retrieveAndSetValue(String fieldName, String fieldType, Properties properties, ChartConfig chartConfig) {
        String retrievedProperty = properties.getProperty(fieldName);
        if(retrievedProperty != null) {
            if (fieldType.contains("String") || fieldType.contains("int") || fieldType.contains("double")) {
                return setValue(chartConfig, fieldName, retrievedProperty, fieldType);
            }
        }
        logger.log(Level.WARNING, "Chart configuration file missing fields. Missed field: " + fieldName);
        return chartConfig;
    }

    private ChartConfig setValue(ChartConfig chartConfig, String fieldName, String fieldValue, String fieldType) {

        PropertyDescriptor pd;
        try{
            pd = new PropertyDescriptor(fieldName, chartConfig.getClass());
            pd.getWriteMethod().invoke(chartConfig, filterType(fieldValue, fieldType));
        } catch (Exception e){
            logger.log(Level.WARNING, e.toString());
        }
        return chartConfig;
    }

    private Object filterType(String fieldValue, String fieldType) {
        if(fieldType.contains("String")) return fieldValue;
        if(fieldType.contains("int")) return Integer.parseInt(fieldValue);
        if(fieldType.contains("double")) return Double.parseDouble(fieldValue);
        return null;
    }
}
