package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartPartDrawerUtilities;

import frontend.chartDrawer.chartGenerator.chartParts.ChartPart;
import frontend.chartDrawer.chartGenerator.chartParts.Line;
import frontend.chartDrawer.chartGenerator.chartParts.Rectangle;
import frontend.chartDrawer.chartGenerator.chartParts.TextField;

import java.util.ArrayList;
import java.util.List;

public class ObjectSorter {

    public Parts sort(List<ChartPart> chartParts) {
        List<Rectangle> rectangles = new ArrayList<>();
        List<Line> lines = new ArrayList<>();
        List<TextField> textFields = new ArrayList<>();

        for(ChartPart chartPart : chartParts) {
            if(chartPart instanceof Rectangle) rectangles.add((Rectangle) chartPart);
            if(chartPart instanceof Line) lines.add((Line) chartPart);
            if(chartPart instanceof TextField) textFields.add((TextField) chartPart);
        }
        return new Parts(rectangles, lines, textFields);
    }

    public class Parts {
        private List<Rectangle> rectangles;
        private List<Line> lines;
        private List<TextField> textFields;

        public Parts(List<Rectangle> rectangles, List<Line> lines, List<TextField> textFields) {
            this.rectangles = rectangles;
            this.lines = lines;
            this.textFields = textFields;
        }

        public List<Rectangle> getRectangles() {
            return rectangles;
        }

        public List<Line> getLines() {
            return lines;
        }

        public List<TextField> getTextFields() {
            return textFields;
        }
    }
}
