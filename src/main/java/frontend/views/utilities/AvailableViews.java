package frontend.views.utilities;

import frontend.chartDrawer.chartGenerator.chartParts.ViewTimeFrame;
import frontend.client.dto.PointTimeFrame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AvailableViews {
    private int defaultViewIndex = 3;

    private List<View> views = new ArrayList<>(Arrays.asList(
            new View("Day",24,PointTimeFrame.H1, ViewTimeFrame.D1),
            new View("Week",168, PointTimeFrame.H1, ViewTimeFrame.W1),
            new View("Month",144, PointTimeFrame.H5, ViewTimeFrame.M1),
            new View("Year", 365, PointTimeFrame.D1, ViewTimeFrame.Y1),
            new View("All", 500, PointTimeFrame.W1, ViewTimeFrame.MAX)));

    public View getDefaultView() {
        return views.get(defaultViewIndex);
    }

    public List<View> getViews() {
        return views;
    }
}
