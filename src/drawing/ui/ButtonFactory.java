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
    public static final String GROUP_BUTTON = "group";
    public static final String DESELECT_GROUP_BUTTON = "deselect_group";

    public static final int TEXT_ONLY = 0;
    public static final int ICONS_ONLY = 1;

    private int style;

    public ButtonFactory(int style) {
        this.style = style;
    }


    private ImageView getImageView(String buttonName) {
        Image image = null;

        try {
            image = new Image(ButtonFactory.class.getClassLoader().getResourceAsStream(String.format("%s" +
                    ".png", buttonName)));
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return new ImageView(image);
    }

    public  Button createButton(String buttonName) {
        Button b = new Button(style == ICONS_ONLY ? "" : buttonName, style == ICONS_ONLY ? getImageView(buttonName) : null);
        b.setTooltip(new Tooltip(buttonName));
        return b;
    }
}