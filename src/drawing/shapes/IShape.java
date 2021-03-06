package drawing.shapes;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public interface IShape {
    void addShapeToPane(Pane pane);

    void offset(double x, double y);
    boolean isOn(double x, double y);

    double getTranslateY();
    double getTranslateX();

    void setSelected(boolean selected);
    Shape getShape();
}