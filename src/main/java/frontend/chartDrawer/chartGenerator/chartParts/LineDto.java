package frontend.chartDrawer.chartGenerator.chartParts;

public class LineDto implements ChartPart {
    private Color color = new Color();
    private int thickness;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public LineDto(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public LineDto(Color color, int thickness, int x1, int y1, int x2, int y2) {
        this.color = color;
        this.thickness = thickness;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Color getColor() {
        return color;
    }

    public int getThickness() {
        return thickness;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    @Override
    public String toString() {
        return "Line{" +
                "color=" + color.toString() +
                ", thickness=" + thickness +
                ", x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                '}';
    }
}
