package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartGridAndDescriptionGenerator.utilities;

import java.time.LocalDateTime;

public class TimeStampCoord {
    private int x;
    private LocalDateTime timeStamp;
    private int index;

    public TimeStampCoord(int x, LocalDateTime timeStamp, int index) {
        this.x = x;
        this.timeStamp = timeStamp;
        this.index = index;
    }

    public int getX() {
        return x;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public int getIndex() {
        return index;
    }
}
