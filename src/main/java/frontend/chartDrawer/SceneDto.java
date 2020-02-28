package frontend.chartDrawer;

public class SceneDto {
    int sceneWidth = 0;
    int sceneHeight = 0;
    int boxWidth = 0;
    int boxHeight = 0;
    int boxLeftUpperCornerX = 0;
    int boxLeftUpperCornerY = 0;

    public SceneDto() {
    }

    public SceneDto(int sceneWidth, int sceneHeight, int boxWidth,
                    int boxHeight, int boxLeftUpperCornerX, int boxLeftUpperCornerY) {
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        this.boxWidth = boxWidth;
        this.boxHeight = boxHeight;
        this.boxLeftUpperCornerX = boxLeftUpperCornerX;
        this.boxLeftUpperCornerY = boxLeftUpperCornerY;
    }

    public int getSceneWidth() {
        return sceneWidth;
    }

    public int getSceneHeight() {
        return sceneHeight;
    }

    public int getBoxWidth() {
        return boxWidth;
    }

    public int getBoxHeight() {
        return boxHeight;
    }

    public int getBoxLeftUpperCornerX() {
        return boxLeftUpperCornerX;
    }

    public int getBoxLeftUpperCornerY() {
        return boxLeftUpperCornerY;
    }
}
