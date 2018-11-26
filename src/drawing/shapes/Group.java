package drawing.shapes;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class Group implements IShape {

    private List<IShape> shapes;

    public Group(List<IShape> shapes) {
        this.shapes = new ArrayList<IShape>();
        this.shapes.addAll(shapes);
    }

    @Override
    public void addShapeToPane(Pane pane) {
        shapes.forEach(shape -> shape.addShapeToPane(pane));
    }

    @Override
    public void offset(double x, double y) {
        shapes.forEach(shape -> shape.offset(x, y));
    }

    @Override
    public boolean isOn(double x, double y) {
        return shapes.stream().filter(shape -> shape.isOn(x, y)).count() > 0;
    }

    @Override
    public double getTranslateY() {
        return 0;
    }

    @Override
    public double getTranslateX() {
        return 0;
    }

    @Override
    public void setSelected(boolean selected) {
        shapes.forEach(shape -> shape.setSelected(selected));
    }

    @Override
    public Shape getShape() {
        return null;
    }


    public List<IShape> getShapes() {
        return shapes;
    }
}