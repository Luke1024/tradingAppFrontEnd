package frontend.views.utilities;

import java.time.LocalDateTime;

public class ChartStatusSaver {
    private View view;
    private LocalDateTime start;
    private LocalDateTime stop;

    public ChartStatusSaver(View view, LocalDateTime start, LocalDateTime stop) {
        this.view = view;
        this.start = start;
        this.stop = stop;
    }

    public View getView() {
        return view;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getStop() {
        return stop;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setStartStop(LocalDateTime start, LocalDateTime stop){
        this.start = start;
        this.stop = stop;
    }
}
