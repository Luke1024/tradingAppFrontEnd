package frontend.config;

import org.junit.Test;


public class ChartConfigWithConfigurationReaderTest {

    private ChartConfigReader chartConfigReader = new ChartConfigReader();

    @Test
    public void readConfigFile() throws Exception {
        chartConfigReader.readConfigFile("chart_config.properties");
    }
}