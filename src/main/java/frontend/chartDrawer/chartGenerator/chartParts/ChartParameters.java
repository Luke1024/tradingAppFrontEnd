package frontend.chartDrawer.chartGenerator.chartParts;

import frontend.client.dto.CurrencyOverviewDto;

import java.util.ArrayList;
import java.util.List;

public class ChartParameters {

    private Universal universal;
    private BackGround backGround;
    private ChartBox chartBox;
    private Line line;
    private VerticalGrid verticalGrid;
    private HorizontalGrid horizontalGrid;
    private Text text;

    public ChartParameters(Universal universal, BackGround backGround, ChartBox chartBox,
                           Line line, VerticalGrid verticalGrid, HorizontalGrid horizontalGrid, Text text) {
        this.universal = universal;
        this.backGround = backGround;
        this.chartBox = chartBox;
        this.line = line;
        this.verticalGrid = verticalGrid;
        this.horizontalGrid = horizontalGrid;
        this.text = text;
    }

    public Universal getUniversal() {
        return universal;
    }

    public BackGround getBackGround() {
        return backGround;
    }

    public ChartBox getChartBox() {
        return chartBox;
    }

    public Line getLine() {
        return line;
    }

    public VerticalGrid getVerticalGrid() {
        return verticalGrid;
    }

    public HorizontalGrid getHorizontalGrid() {
        return horizontalGrid;
    }

    public Text getText() {
        return text;
    }

    public static class Universal  {
        private CurrencyOverviewDto currencyOverviewDto;
        private int width;
        private int height;
        private ViewTimeFrame viewTimeFrame;

        public Universal(CurrencyOverviewDto currencyOverviewDto, int width, int height, ViewTimeFrame viewTimeFrame) {
            this.currencyOverviewDto = currencyOverviewDto;
            this.width = width;
            this.height = height;
            this.viewTimeFrame = viewTimeFrame;
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

        public ViewTimeFrame getViewTimeFrame() {
            return viewTimeFrame;
        }
    }

    public static class BackGround {
        private Color color;

        public BackGround(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }

    public static class ChartBox {
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

    public static class Line {
        private Color color;
        private int thickness;
        private int verticalRangePercent;

        public Line(Color color, int thickness, int verticalRangePercent) {
            this.color = color;
            this.thickness = thickness;
            this.verticalRangePercent = verticalRangePercent;
        }

        public Color getColor() {
            return color;
        }

        public int getThickness() {
            return thickness;
        }

        public int getVerticalRangePercent() {
            return verticalRangePercent;
        }
    }

    public static class VerticalGrid {
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

    public static class HorizontalGrid {
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

    public static class Text {
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
