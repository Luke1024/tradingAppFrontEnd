package frontend.client.dto;

import java.time.LocalDateTime;

public class DataPointDto {
    private LocalDateTime timeStamp;
    private TimeFrame timeFrame;
    private double value;

    public DataPointDto(LocalDateTime timeStamp, TimeFrame timeFrame, double value) {
        this.timeStamp = timeStamp;
        this.timeFrame = timeFrame;
        this.value = value;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public TimeFrame getTimeFrame() {
        return timeFrame;
    }

    public double getValue() {
        return value;
    }
}
