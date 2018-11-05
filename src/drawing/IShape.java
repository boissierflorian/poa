package drawing;

import javafx.scene.layout.Pane;

public interface IShape {
    void addShapeToPane(Pane pane);

    void offset(double x, double y);
    boolean isOn(double x, double y);

    double getTranslateY();
    double getTranslateX();

    void setSelected(boolean selected);
}