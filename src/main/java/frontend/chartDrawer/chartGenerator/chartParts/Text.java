package frontend.chartDrawer.chartGenerator.chartParts;

public class Text extends ChartPart {
    private Color color;
    private int x;
    private int y;
    private int fontSize;
    private String content;

    public Text(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Text(Color color, int x, int y, int fontSize, String content) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
        this.content = content;
    }


    public void setColor(Color color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setContent(String content) {
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

    @Override
    public String toString() {
        return "Text{" +
                "color=" + color +
                ", x=" + x +
                ", y=" + y +
                ", fontSize=" + fontSize +
                ", content='" + content + '\'' +
                '}';
    }
}
