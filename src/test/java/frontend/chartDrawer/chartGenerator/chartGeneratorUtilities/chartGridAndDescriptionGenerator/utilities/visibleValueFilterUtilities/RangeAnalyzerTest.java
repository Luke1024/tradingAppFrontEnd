package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.visibleValueFilterUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RangeAnalyzerTest {

    private RangeAnalyzer rangeAnalyzer = new RangeAnalyzer();

    @Test
    public void testComputingRangeCase1() {
        ChartDataDto chartDataDto = mock(ChartDataDto.class, Mockito.RETURNS_DEEP_STUBS);

        when(chartDataDto.getChartConfig().getChartHeight()).thenReturn(200);
        when(chartDataDto.getChartConfig().getTextElementHeight()).thenReturn(30);
        when(chartDataDto.getChartConfig().getChartBoxHeight()).thenReturn(150);
        when(chartDataDto.getChartConfig().getLineChartBoxHeightRangeInPercentage()).thenReturn(80);
        when(chartDataDto.getChartConfig().getChartBoxLeftBottomCornerY()).thenReturn(40);

        RangeAnalyzer.Range expectedRange = new RangeAnalyzer.Range(55, 175);

        Assert.assertEquals(expectedRange.toString(), rangeAnalyzer.minMaxTextFieldPosition(chartDataDto).toString());
    }

    @Test
    public void testComputingRangeCase2() {
        ChartDataDto chartDataDto = mock(ChartDataDto.class, Mockito.RETURNS_DEEP_STUBS);

        when(chartDataDto.getChartConfig().getChartHeight()).thenReturn(200);
        when(chartDataDto.getChartConfig().getTextElementHeight()).thenReturn(30);
        when(chartDataDto.getChartConfig().getChartBoxHeight()).thenReturn(160);
        when(chartDataDto.getChartConfig().getLineChartBoxHeightRangeInPercentage()).thenReturn(50);
        when(chartDataDto.getChartConfig().getChartBoxLeftBottomCornerY()).thenReturn(30);

        RangeAnalyzer.Range expectedRange = new RangeAnalyzer.Range(70, 150);

        Assert.assertEquals(expectedRange.toString(), rangeAnalyzer.minMaxTextFieldPosition(chartDataDto).toString());
    }

    @Test
    public void testComputingRangeCase3() {
        ChartDataDto chartDataDto = mock(ChartDataDto.class, Mockito.RETURNS_DEEP_STUBS);

        when(chartDataDto.getChartConfig().getChartHeight()).thenReturn(200);
        when(chartDataDto.getChartConfig().getTextElementHeight()).thenReturn(20);
        when(chartDataDto.getChartConfig().getChartBoxHeight()).thenReturn(160);
        when(chartDataDto.getChartConfig().getLineChartBoxHeightRangeInPercentage()).thenReturn(100);
        when(chartDataDto.getChartConfig().getChartBoxLeftBottomCornerY()).thenReturn(30);

        RangeAnalyzer.Range expectedRange = new RangeAnalyzer.Range(40, 180);

        Assert.assertEquals(expectedRange.toString(), rangeAnalyzer.minMaxTextFieldPosition(chartDataDto).toString());
    }
}