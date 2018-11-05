package drawing;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.lang.Iterable;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lewandowski on 20/12/2017.
 */
public class DrawingPane extends Pane implements Iterable<IShape>, Observable {

    private MouseMoveHandler mouseMoveHandler;
    private SelectionHandler selectionHandler;

    private ArrayList<IShape> shapes;
    private ArrayList<Observer> observers;

    public DrawingPane() {
        clipChildren();
        shapes = new ArrayList<>();
        mouseMoveHandler = new MouseMoveHandler(this);
        selectionHandler = new SelectionHandler(this);
        observers = new ArrayList<>();
    }


    /**
     * Clips the children of this {@link Region} to its current size.
     * This requires attaching a change listener to the region’s layout bounds,
     * as JavaFX does not currently provide any built-in way to clip children.
     */
    void clipChildren() {
        final Rectangle outputClip = new Rectangle();
        this.setClip(outputClip);

        this.layoutBoundsProperty().addListener((ov, oldValue, newValue) -> {
            outputClip.setWidth(newValue.getWidth());
            outputClip.setHeight(newValue.getHeight());
        });
    }

    public void addShape(Shape shape) {
        addShape(new ShapeAdapter(shape));
    }

    public void clear() {
        this.getChildren().removeAll(shapes);
        this.getChildren().clear();
        shapes.clear();
        notifyObservers(this, 0);
    }

    public void addShape(final IShape shape) {
        shape.addShapeToPane(this);
        shapes.add(shape);
        notifyObservers(this, shapes.size());
    }

    @Override
    public Iterator<IShape> iterator() {
        return shapes.iterator();
    }

    @Override
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers(Observable obs, int shapeCount) {
        for (Observer observer : observers) {
            observer.update(obs, shapeCount);
        }
    }

    public List<IShape> getSelection() {
        return selectionHandler.getSelectedShapes();
    }

    public void handleKeyEvent(final KeyEvent event) {
        selectionHandler.handle(event);
    }

    public List<IShape> getShapes() { return shapes; }
}