package testApp.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import testApp.dto.ActuationDto;
import testApp.imageDrawer.Drawer;
import testApp.imageDrawer.SceneDto;
import java.io.IOException;
import java.net.URI;

@Route
public class MainView extends VerticalLayout {

    private Button left = new Button("Left");
    private Button right = new Button("Right");
    private Button up = new Button("Up");
    private Button down = new Button("Down");
    private Drawer drawer = new Drawer();

    public MainView() throws IOException {
        HorizontalLayout toolbar = new HorizontalLayout(left, right, up, down);
        HorizontalLayout imageHolder = new HorizontalLayout();

        Image image = drawer.getBlank();
        imageHolder.add(image);
        add(imageHolder,toolbar);
        left.addClickListener(event -> {
            try {
                imageHolder.removeAll();
                imageHolder.add(sendLeftSignal());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        right.addClickListener(event -> {
            try {
                imageHolder.removeAll();
                imageHolder.add(sendRightSignal());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        up.addClickListener(event -> {
            try {
                imageHolder.removeAll();
                imageHolder.add(sendUpSignal());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        down.addClickListener(event -> {
            try {
                imageHolder.removeAll();
                imageHolder.add(sendDownSignal());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Image sendLeftSignal() throws IOException {
        return sendAndReceive(new ActuationDto(true, false, false, false));
    }

    private Image sendRightSignal() throws IOException {
        return sendAndReceive(new ActuationDto(false,true,false,false));
    }

    private Image sendUpSignal() throws IOException {
        return sendAndReceive(new ActuationDto(false, false, true, false));
    }

    private Image sendDownSignal() throws IOException {
        return sendAndReceive(new ActuationDto(false,false,false,true));
    }

    private Image sendAndReceive(ActuationDto actuationDto) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/joystick/actuate").build().encode().toUri();
        SceneDto sceneDto = restTemplate.postForObject(url, actuationDto, SceneDto.class);

        return drawer.getStream(sceneDto);
    }
}
