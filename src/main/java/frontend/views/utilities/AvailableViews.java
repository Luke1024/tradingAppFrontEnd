package frontend.views.utilities;

import frontend.client.dto.PointTimeFrame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AvailableViews {
    private int defaultViewIndex = 0;

    private List<View> views = new ArrayList<>(Arrays.asList(
            new View("Week",168, PointTimeFrame.H1),
            new View("Month",144, PointTimeFrame.H5),
            new View("Year", 365, PointTimeFrame.D1),
            new View("All", 500, PointTimeFrame.M1)));

    public int getDefaultViewIndex() {
        return defaultViewIndex;
    }

    public List<View> getViews() {
        return views;
    }
}
