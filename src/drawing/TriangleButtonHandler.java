package drawing;


import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class TriangleButtonHandler extends ShapeButtonHandler {
    public TriangleButtonHandler(DrawingPane drawingPane) {
        super(drawingPane);
    }

    @Override
    protected IShape createShape() {
        double x = Math.min(originX, destinationX);
        double y = Math.min(originY, destinationY);
        double width = Math.abs(destinationX - originX);
        double height = Math.abs(destinationY - originY);

        /*Ellipse ellipse = new Ellipse(x + width / 2, y + height / 2, width / 2, height / 2);
        ellipse.getStyleClass().add("ellipse");*/

        double[] points = new double[]{
                x + width / 2,
                y,
                x,
                y + height,
                x + width,
                y + height
        };
        Polygon p = new Polygon(points);

        p.getStyleClass().add("triangle");

        return new ShapeAdapter(p);
    }
}