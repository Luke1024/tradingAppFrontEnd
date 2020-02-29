package frontend.chartDrawer.utilities.processor.utilities;

import java.awt.*;
import java.util.List;

public class ChartLayout {
    private List<Line> lines;
    private List<Text> texts;
    private List<Rectangle> rectangles;

    public ChartLayout(List<Line> lines, List<Text> texts, List<Rectangle> rectangles) {
        this.lines = lines;
        this.texts = texts;
        this.rectangles = rectangles;
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
}
