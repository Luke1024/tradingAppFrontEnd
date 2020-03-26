package frontend.chartDrawer.chartGenerator.chartParts;

public class Rectangle extends ChartPart {
    private Color color;
    private double thickness;
    private Color fillColor;
    private boolean fill;
    private int x;
    private int y;
    private int width;
    private int height;

    public Rectangle(Color color, double thickness, Color fillColor,
                     boolean fill, int x, int y, int width, int height) {
        this.color = color;
        this.thickness = thickness;
        this.fillColor = fillColor;
        this.fill = fill;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public double getThickness() {
        return thickness;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public boolean isFill() {
        return fill;
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
}
