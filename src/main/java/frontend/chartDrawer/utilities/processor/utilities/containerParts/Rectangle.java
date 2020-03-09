package frontend.chartDrawer.utilities.processor.utilities.containerParts;

public class Rectangle extends Element {
    private Color color;
    private Color fillColor;
    private boolean fill;
    private int x;
    private int y;
    private int width;
    private int height;

    public Rectangle(Color color, Color fillColor, boolean fill, int x, int y, int width, int height) {
        this.color = color;
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
