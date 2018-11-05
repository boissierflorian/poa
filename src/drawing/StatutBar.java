package drawing;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class StatutBar extends HBox implements Observer {
    private Label label;

    public StatutBar() {
        label = new Label("0 Shape(s)");
        this.getChildren().add(label);
    }

    public void setText(final String text) {
        label.setText(text);
    }

    @Override
    public void update(Observable obs, int shapeCount) {
        label.setText(String.format("%d Shape(s)", shapeCount));
    }
}
