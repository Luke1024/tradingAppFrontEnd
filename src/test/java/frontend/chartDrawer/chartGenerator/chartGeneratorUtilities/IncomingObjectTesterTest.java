package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.client.dto.DataPointDto;
import frontend.config.ChartConfig;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class IncomingObjectTesterTest {

    private IncomingObjectTester incomingObjectTester = new IncomingObjectTester();

    private DataPointDto dataPointDtoCorrect = new DataPointDto(LocalDateTime.now(), 0.23);
    private DataPointDto dataPointDtoMissingFirstValue = new DataPointDto(null, 0.1253);

    private List<DataPointDto> dataPointDtoListCorrect = Arrays.asList(dataPointDtoCorrect, dataPointDtoCorrect);
    private List<DataPointDto> dataPointDtoListNotCorrect = Arrays.asList(dataPointDtoCorrect, dataPointDtoMissingFirstValue);

    private CurrencyOverviewDto currencyOverviewDtoCorrect =
            new CurrencyOverviewDto("EURUSD", LocalDateTime.now(), dataPointDtoListCorrect);
    private CurrencyOverviewDto currencyOverviewDtoMissingFirstField = new CurrencyOverviewDto(null, LocalDateTime.now(), dataPointDtoListCorrect);
    private CurrencyOverviewDto currencyOverviewDtoMissingSecondField = new CurrencyOverviewDto("EURUSD", null, dataPointDtoListCorrect);
    private CurrencyOverviewDto currencyOverviewDtoMissingThirdField = new CurrencyOverviewDto("EURUSD", LocalDateTime.now(),null);

    private CurrencyOverviewDto currencyOverviewDtoMissingParameterInDataPointDto = new CurrencyOverviewDto("EURUSD", LocalDateTime.now(), dataPointDtoListNotCorrect);
    private ChartConfig chartConfig = new ChartConfig();


    @Test
    public void testCompleteObjects() {
        ChartDataDto chartDataDto = new ChartDataDto(currencyOverviewDtoCorrect, ViewTimeFrame.D1, chartConfig);
        Assert.assertEquals(true, incomingObjectTester.isObjectCorrect(chartDataDto));
    }

    @Test
    public void testCurrencyOverviewIsNullViewTimeFrameIsNotNull() {
        ChartDataDto chartDataDto = new ChartDataDto(null, ViewTimeFrame.D1, chartConfig);
        Assert.assertEquals(false, incomingObjectTester.isObjectCorrect(chartDataDto));
    }

    @Test
    public void testCurrencyOverviewIsNotNullViewTimeFrameIsNull() {
        ChartDataDto chartDataDto = new ChartDataDto(currencyOverviewDtoCorrect, null, chartConfig);
        Assert.assertEquals(false, incomingObjectTester.isObjectCorrect(chartDataDto));
    }

    @Test
    public void testCurrencyOverviewMissingFirstField() {
        ChartDataDto chartDataDto = new ChartDataDto(currencyOverviewDtoMissingFirstField, ViewTimeFrame.D1, chartConfig);
        Assert.assertEquals(false, incomingObjectTester.isObjectCorrect(chartDataDto));
    }

    @Test
    public void testCurrencyOverviewMissingSecondField() {
        ChartDataDto chartDataDto = new ChartDataDto(currencyOverviewDtoMissingSecondField, ViewTimeFrame.D1, chartConfig);
        Assert.assertEquals(false, incomingObjectTester.isObjectCorrect(chartDataDto));
    }

    @Test
    public void testCurrencyOverviewMissingThirdField() {
        ChartDataDto chartDataDto = new ChartDataDto(currencyOverviewDtoMissingThirdField, ViewTimeFrame.D1, chartConfig);
        Assert.assertEquals(false, incomingObjectTester.isObjectCorrect(chartDataDto));
    }

    @Test
    public void testCurrencyOverviewMissingValueInDataPointDto() {
        ChartDataDto chartDataDto = new ChartDataDto(currencyOverviewDtoMissingParameterInDataPointDto, ViewTimeFrame.D1, chartConfig);
        Assert.assertEquals(false, incomingObjectTester.isObjectCorrect(chartDataDto));
    }

    @Test
    public void testOnlyChartConfigIsNull() {
        ChartDataDto chartDataDto = new ChartDataDto(currencyOverviewDtoCorrect, ViewTimeFrame.D1, null);
        Assert.assertEquals(false, incomingObjectTester.isObjectCorrect(chartDataDto));
    }
}