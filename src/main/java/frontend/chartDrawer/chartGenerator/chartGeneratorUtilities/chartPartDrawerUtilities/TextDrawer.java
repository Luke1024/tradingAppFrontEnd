package frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.chartPartDrawerUtilities;

import frontend.chartDrawer.chartGenerator.chartGeneratorUtilities.mappers.ColorMapper;
import frontend.chartDrawer.chartGenerator.chartParts.TextFieldDto;

import java.awt.*;
import java.util.List;

public class TextDrawer {

    private ColorMapper colorMapper = new ColorMapper();

    public Graphics2D draw(Graphics2D scene, List<TextFieldDto> textFieldDtoList){
        for(TextFieldDto text : textFieldDtoList){
            scene = drawText(scene, text);
        }
        return scene;
    }

    private Graphics2D drawText(Graphics2D scene, TextFieldDto textFieldDto) {
        Color textColor = colorMapper.mapToAwtColor(textFieldDto.getColor());
        int xCenter = textFieldDto.getCenterX();
        int yCenter = textFieldDto.getCenterY();
        int fontSize = textFieldDto.getFontSize();
        String content = textFieldDto.getContent();

        scene.setPaint(textColor);
        scene.setFont(new Font("", Font.PLAIN, fontSize));

        Position textPosition = calculateTextPosition(scene, content, fontSize, xCenter, yCenter);

        scene.drawString(content,textPosition.x, textPosition.y);

        return scene;
    }

    private Position calculateTextPosition(Graphics2D scene, String content, int fontSize, int xCenter, int yCenter) {
         int stringWidth = scene.getFontMetrics().stringWidth(content);
         int x = xCenter - stringWidth/2;
         int y = yCenter + fontSize/2;
         return new Position(x,y);
    }

    private class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

