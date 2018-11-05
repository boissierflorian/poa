package drawing.ui;

import drawing.observable.Observable;
import drawing.observable.Observer;
import drawing.observable.SelectionObservable;
import drawing.observable.SelectionObserver;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class StatutBar extends HBox implements Observer, SelectionObserver {
    private Label label;
    private int shapeCount;
    private int selectedShapeCount;

    public StatutBar() {
        label = new Label("0 Shape(s), 0 selected");
        this.getChildren().add(label);
    }

    public void setText(final String text) {
        label.setText(text);
    }

    public String getText() {
        return label.getText();
    }

    @Override
    public void update(Observable obs, int shapeCount) {
        this.shapeCount = shapeCount;
        updateText();
    }

    @Override
    public void update(SelectionObservable obs, int shapeCount) {
        this.selectedShapeCount = shapeCount;
        updateText();
    }

    public void updateText() {
        label.setText(String.format("%d Shape(s), %d selected", shapeCount, selectedShapeCount));
    }
}
