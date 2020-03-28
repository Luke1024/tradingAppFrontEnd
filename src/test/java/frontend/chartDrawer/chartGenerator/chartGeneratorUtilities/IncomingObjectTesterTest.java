package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.CurrencyOverviewDto;
import frontend.client.dto.DataPointDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IncomingObjectTesterTest {

    @Autowired
    private IncomingObjectTester incomingObjectTester;



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

    @Test
    public void testCompleteObjects() {
        Assert.assertEquals(true,incomingObjectTester.isObjectsCorrect(currencyOverviewDtoCorrect, ViewTimeFrame.D1));
    }

    @Test
    public void testCurrencyOverviewIsNullViewTimeFrameIsNotNull() {
        Assert.assertEquals(false, incomingObjectTester.isObjectsCorrect(null, ViewTimeFrame.MAX));
    }

    @Test
    public void testCurrencyOverviewIsNotNullViewTimeFrameIsNull() {
        Assert.assertEquals(false, incomingObjectTester.isObjectsCorrect(currencyOverviewDtoCorrect, null));
    }

    @Test
    public void testCurrencyOverviewMissingFirstField() {
        Assert.assertEquals(false, incomingObjectTester.isObjectsCorrect(currencyOverviewDtoMissingFirstField, ViewTimeFrame.D1));
    }

    @Test
    public void testCurrencyOverviewMissingSecondField() {
        Assert.assertEquals(false, incomingObjectTester.isObjectsCorrect(currencyOverviewDtoMissingSecondField, ViewTimeFrame.D1));
    }

    @Test
    public void testCurrencyOverviewMissingThirdField() {
        Assert.assertEquals(false, incomingObjectTester.isObjectsCorrect(currencyOverviewDtoMissingThirdField, ViewTimeFrame.D1));
    }

    @Test
    public void testCurrencyOverviewMissingValueInDataPointDto() {
        Assert.assertEquals(false, incomingObjectTester.isObjectsCorrect(currencyOverviewDtoMissingParameterInDataPointDto, ViewTimeFrame.D1));
    }
}