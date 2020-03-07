package frontend.chartDrawer.utilities.processor.utilities;

import java.util.List;

public class ChartLayout {
    private int width;
    private int height;
    private List<Line> lines;
    private List<Text> texts;
    private List<Rectangle> rectangles;

    public ChartLayout() {
    }

    public ChartLayout(int width, int height, List<Line> lines,
                       List<Text> texts, List<Rectangle> rectangles) {
        this.width = width;
        this.height = height;
        this.lines = lines;
        this.texts = texts;
        this.rectangles = rectangles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Line> getLines() {
        return lines;
    }

    public List<Text> getTexts() {
        return texts;
    }

    public List<Rectangle> getRectangles() {
        return rectangles;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public void setTexts(List<Text> texts) {
        this.texts = texts;
    }

    public void setRectangles(List<Rectangle> rectangles) {
        this.rectangles = rectangles;
    }
}
