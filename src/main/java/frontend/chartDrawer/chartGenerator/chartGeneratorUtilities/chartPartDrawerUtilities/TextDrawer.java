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
        int x = textFieldDto.getCenterX();
        int y = textFieldDto.getCenterY();
        int fontSize = textFieldDto.getFontSize();
        String content = textFieldDto.getContent();

        scene.setPaint(textColor);
        scene.setFont(new Font("", Font.PLAIN, fontSize));
        scene.drawString(content,x,y);

        return scene;
    }
}
