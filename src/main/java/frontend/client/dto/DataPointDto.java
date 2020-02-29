package frontend.client.dto;

import java.time.LocalDateTime;

public class DataPointDto {
    private LocalDateTime timeStamp;
    private double value;

    public DataPointDto(LocalDateTime timeStamp, double value) {
        this.timeStamp = timeStamp;
        this.value = value;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public double getValue() {
        return value;
    }
}
