package frontend.chartDrawer.utilities.processor.utilities.containerParts;

public class Text extends Element {
    private Color color;
    private int x;
    private int y;
    private int fontSize;
    private String content;

    public Text(Color color, int x, int y, int fontSize, String content) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
        this.content = content;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getFontSize() {
        return fontSize;
    }

    public String getContent() {
        return content;
    }
}
