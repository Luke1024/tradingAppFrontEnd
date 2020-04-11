package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities.visibleValueFilterUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;

public class RangeAnalyzer {

    public Range minMaxTextFieldPosition(ChartDataDto chartDataDto) {
        int margin = computeMargin(chartDataDto);
        int halfOfTextFieldHeight = chartDataDto.getChartConfig().getTextElementHeight()/2;

        int marginForTextField = computeMinimalDistanceOfTextFieldCenter(margin, halfOfTextFieldHeight);

        int minTextField = computeMinHeightOfCenterOfTextField(chartDataDto, marginForTextField);
        int maxTextField = computeMaxHeightOfCenterOfTextField(chartDataDto, marginForTextField);

        return new Range(minTextField, maxTextField);
    }

    private int computeMargin(ChartDataDto chartDataDto) {
        int chartBoxHeight = chartDataDto.getChartConfig().getChartBoxHeight();
        int chartLineHeightRangeInPercent = chartDataDto.getChartConfig().getLineChartBoxHeightRangeInPercentage();
        double heightCoefficient = ((double) chartLineHeightRangeInPercent)/100;

        System.out.println((int) ((chartBoxHeight - chartBoxHeight * heightCoefficient)/2));
        return (int) ((chartBoxHeight - chartBoxHeight * heightCoefficient)/2);
    }

    private int computeMinimalDistanceOfTextFieldCenter(int margin, int halfOfTextFieldHeight) {
        if(margin > halfOfTextFieldHeight) {
            return margin;
        } else {
            return halfOfTextFieldHeight;
        }
    }

    private int computeMinHeightOfCenterOfTextField(ChartDataDto chartDataDto, int marginForTextField) {
        int chartBoxBottomY = chartDataDto.getChartConfig().getChartBoxLeftBottomCornerY();
        return chartBoxBottomY + marginForTextField;
    }

    private int computeMaxHeightOfCenterOfTextField(ChartDataDto chartDataDto, int marginForTextField) {
        int chartBoxHeight = chartDataDto.getChartConfig().getChartBoxHeight();
        int chartBoxBottomY = chartDataDto.getChartConfig().getChartBoxLeftBottomCornerY();

        int chartBoxTopY = chartBoxBottomY + chartBoxHeight;
        return chartBoxTopY - marginForTextField;
    }

    public static class Range{
        int min;
        int max;

        public Range(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return "Range{" +
                    "min=" + min +
                    ", max=" + max +
                    '}';
        }
    }
}
