package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.mappers;

import java.awt.*;

public class ColorMapper {
    public Color mapToAwtColor(frontend.chartDrawer.chartGenerator.chartParts.Color color) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue());
    }
}
