package frontend.chartDrawer.chartGenerator.chartParts;

public class TextField extends ChartPart {
    private Color color;
    private int centerX;
    private int centerY;
    private int fontSize;
    private String content;

    public TextField(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public TextField(Color color, int centerX, int centerY, int fontSize, String content) {
        this.color = color;
        this.centerX = centerX;
        this.centerY = centerY;
        this.fontSize = fontSize;
        this.content = content;
    }


    public void setColor(Color color) {
        this.color = color;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
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

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getFontSize() {
        return fontSize;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "TextField{" +
                "color=" + color +
                ", centerX=" + centerX +
                ", centerY=" + centerY +
                ", fontSize=" + fontSize +
                ", content='" + content + '\'' +
                '}';
    }
}
