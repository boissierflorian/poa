package drawing.ui;

import drawing.handlers.MouseMoveHandler;
import drawing.handlers.SelectionHandler;
import drawing.observable.Observable;
import drawing.observable.Observer;
import drawing.observable.SelectionObservable;
import drawing.observable.SelectionObserver;
import drawing.shapes.IShape;
import drawing.shapes.ShapeAdapter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.lang.Iterable;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lewandowski on 20/12/2017.
 */
public class DrawingPane extends Pane implements Iterable<IShape>, Observable, SelectionObservable {

    private MouseMoveHandler mouseMoveHandler;
    private SelectionHandler selectionHandler;

    private ArrayList<IShape> shapes;
    private ArrayList<Observer> observers;
    private ArrayList<SelectionObserver> selectionObservers;

    public DrawingPane() {
        clipChildren();
        shapes = new ArrayList<>();
        mouseMoveHandler = new MouseMoveHandler(this);
        selectionHandler = new SelectionHandler(this);
        observers = new ArrayList<>();
        selectionObservers = new ArrayList<>();
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

    public  void removeSelectedShapes() {
        shapes.removeAll(selectionHandler.getSelectedShapes());

        this.getChildren().removeAll(selectionHandler.getSelectedShapes()
                .stream().map(s -> s.getShape()).collect(Collectors.toList()));

        selectionHandler.getSelectedShapes().clear();

        notifyObservers(this, shapes.size());
        notifySelectionToObservers(0);
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

    public SelectionHandler getSelectionHandler() { return selectionHandler; }

    @Override
    public void addSelectionObserver(SelectionObserver obs) {
        selectionObservers.add(obs);
    }

    @Override
    public void removeSelectionObservers() {
        selectionObservers.clear();
    }

    @Override
    public void notifySelectionToObservers(int shapeCount) {
        selectionObservers.forEach(s -> s.update(this, shapeCount));
    }
}