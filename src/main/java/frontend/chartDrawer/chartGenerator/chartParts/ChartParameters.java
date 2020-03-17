package frontend.chartDrawer.chartGenerator.chartParts;

import frontend.client.dto.CurrencyOverviewDto;

import java.util.ArrayList;
import java.util.List;

public class ChartParameters {

    private List<ParametersDto> parametersDtoList = new ArrayList<>();

    public ChartParameters(List<ParametersDto> parametersDtoList) {
        this.parametersDtoList = parametersDtoList;
    }

    public List<ParametersDto> getParameters() {
        return parametersDtoList;
    }

    public interface ParametersDto {}

    public static class Universal implements ParametersDto {
        private CurrencyOverviewDto currencyOverviewDto;
        private int width;
        private int height;

        public Universal(CurrencyOverviewDto currencyOverviewDto, int width, int height) {
            this.currencyOverviewDto = currencyOverviewDto;
            this.width = width;
            this.height = height;
        }

        public CurrencyOverviewDto getCurrencyOverviewDto() {
            return currencyOverviewDto;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    public static class BackGround implements ParametersDto {
        private Color color;

        public BackGround(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }

    public static class ChartBox implements ParametersDto {
        private int x;
        private int y;
        private int width;
        private int height;
        private double stepPix;
        private Color color;
        private int thickness;

        public ChartBox(int x, int y, int width, int height, double stepPix, Color color, int thickness) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.stepPix = stepPix;
            this.color = color;
            this.thickness = thickness;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public double getStepPix() {
            return stepPix;
        }

        public Color getColor() {
            return color;
        }

        public int getThickness() {
            return thickness;
        }
    }

    public static class Line implements ParametersDto {
        private Color color;
        private int thickness;

        public Line(Color color, int thickness) {
            this.color = color;
            this.thickness = thickness;
        }

        public Color getColor() {
            return color;
        }

        public int getThickness() {
            return thickness;
        }
    }

    public static class VerticalGrid implements ParametersDto {
        private Color color;
        private int thickness;

        public VerticalGrid(Color color, int thickness) {
            this.color = color;
            this.thickness = thickness;
        }

        public Color getColor() {
            return color;
        }

        public int getThickness() {
            return thickness;
        }
    }

    public static class HorizontalGrid implements ParametersDto {
        private Color color;
        private int thickness;

        public HorizontalGrid(Color color, int thickness) {
            this.color = color;
            this.thickness = thickness;
        }

        public Color getColor() {
            return color;
        }

        public int getThickness() {
            return thickness;
        }
    }

    public static class Text implements ParametersDto {
        private Color color;
        private int fontSize;

        public Text(Color color, int fontSize) {
            this.color = color;
            this.fontSize = fontSize;
        }

        public Color getColor() {
            return color;
        }

        public int getFontSize() {
            return fontSize;
        }
    }
}
