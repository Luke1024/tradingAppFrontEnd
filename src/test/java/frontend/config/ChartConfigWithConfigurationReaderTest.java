package frontend.config;

import org.junit.Test;


public class ChartConfigWithConfigurationReaderTest {

    private ChartConfigReader chartConfigReader = new ChartConfigReader();

    @Test
    public void readConfigFile() throws Exception {
        chartConfigReader.readConfigFile("/home/luke/test_vaadin_project/vaadin_experimenting/src/main/resources/chart_config.properties");
    }
}