package testApp.imageDrawer;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Drawer  {
    ByteArrayOutputStream imagebuffer = null;
    int reloads = 0;


    public Image getStream(SceneDto sceneDto) {
        BufferedImage image = new BufferedImage (sceneDto.sceneWidth, sceneDto.sceneHeight,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D drawable = image.createGraphics();

        drawable.setColor(Color.green);
        drawable.fill(new Rectangle(
                sceneDto.getBoxLeftUpperCornerX(),
                sceneDto.getBoxLeftUpperCornerY(),
                sceneDto.getBoxWidth(),
                sceneDto.getBoxHeight()));

        try{
            imagebuffer = new ByteArrayOutputStream();
            ImageIO.write(image, "png", imagebuffer);
        } catch (IOException e) {
            return null;
        }
        StreamResource resource = new StreamResource("image",() -> new ByteArrayInputStream(imagebuffer.toByteArray()));
        return  new Image(resource, "scene");
    }

    public Image getBlank(){
        return getStream(new SceneDto(400,400,0,0,0,0));
    }
}
