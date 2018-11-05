package drawing.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOError;
import java.io.IOException;
import java.net.URL;

public class ButtonFactory {
    public static final String CLEAR_BUTTON = "clear";
    public static final String RECTANGLE_BUTTON = "rectangle";
    public static final String TRIANGLE_BUTTON = "triangle";
    public static final String CIRCLE_BUTTON = "circle";
    public static final String DELETE_BUTTON = "delete";

    private static ImageView getImageView(String buttonName) {
        Image image = null;

        try {
            image = new Image(ButtonFactory.class.getClassLoader().getResourceAsStream(String.format("%s" +
                    ".png", buttonName)));
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return new ImageView(image);
    }

    public static Button createButton(String buttonName) {
        Button b = new Button(buttonName, getImageView(buttonName));
        b.setTooltip(new Tooltip(buttonName));
        return b;
    }
}