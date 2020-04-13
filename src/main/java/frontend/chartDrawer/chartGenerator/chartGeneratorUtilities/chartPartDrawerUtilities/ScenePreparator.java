package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartPartDrawerUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.mappers.ColorMapper;
import frontend.chartDrawer.chartGenerator.chartParts.ChartDataDto;
import frontend.chartDrawer.chartGenerator.chartParts.Color;

import java.awt.*;
import java.awt.image.BufferedImage;


public class ScenePreparator {

    private ColorMapper colorMapper = new ColorMapper();

    public Prepared prepare(ChartDataDto chartDataDto) {
        int imageWidth = chartDataDto.getChartConfig().getChartWidth();
        int imageHeight = chartDataDto.getChartConfig().getChartHeight();
        Color backgroundColor = new Color(chartDataDto.getChartConfig().getBackGroundColor());
        java.awt.Color imageBackgroundColor = colorMapper.mapToAwtColor(backgroundColor);

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D scene = image.createGraphics();
        scene.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        scene.setColor(imageBackgroundColor);

        return new Prepared(scene, image);
    }

    public class Prepared {
        Graphics2D scene;
        BufferedImage image;

        public Prepared(Graphics2D scene, BufferedImage image) {
            this.scene = scene;
            this.image = image;
        }

        public Graphics2D getScene() {
            return scene;
        }

        public BufferedImage getImage() {
            return image;
        }
    }
}
