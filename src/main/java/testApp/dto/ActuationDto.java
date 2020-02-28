package testApp.dto;

public class ActuationDto {
    boolean left;
    boolean right;
    boolean up;
    boolean down;

    public ActuationDto() {
    }

    public ActuationDto(boolean left, boolean right, boolean up, boolean down) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }
}
