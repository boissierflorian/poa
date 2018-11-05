package drawing;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class ShapeAdapter implements IShape {
    private Shape shape;

    public ShapeAdapter(Shape shape) {
        this.shape = shape;
    }

    public void addShapeToPane(Pane pane) {
        pane.getChildren().add(shape);
    }

    @Override
    public void offset(double x, double y) {
        shape.setTranslateX(shape.getTranslateX() + x);
        shape.setTranslateY(shape.getTranslateY() + y);
    }

    @Override
    public boolean isOn(double x, double y) {
        return shape.getBoundsInParent().contains(x, y);
    }

    @Override
    public double getTranslateY() {
        return shape.getTranslateY();
    }

    @Override
    public double getTranslateX() {
        return shape.getTranslateX();
    }

    @Override
    public void setSelected(boolean selected) {
        shape.getStyleClass().remove("selected");

        if (selected) {
            shape.getStyleClass().add("selected");
        }
    }

    public Shape getShape() { return shape; }
}