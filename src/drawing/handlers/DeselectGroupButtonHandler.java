package drawing.handlers;

import drawing.shapes.Group;
import drawing.shapes.IShape;
import drawing.ui.DrawingPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.events.Event;

import java.util.List;

public class DeselectGroupButtonHandler implements EventHandler<MouseEvent> {

    private DrawingPane drawingPane;

    public DeselectGroupButtonHandler(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        // On récupère la liste des formes sélectionnées
        SelectionHandler selectionHandler = drawingPane.getSelectionHandler();
        List<IShape> shapes = drawingPane.getShapes();
        List<IShape> selectedShapes = selectionHandler.getSelectedShapes();

        // On les supprimes de la liste
        selectedShapes.forEach(selectedShape -> drawingPane.removeShape(selectedShape));

        for (IShape shape : selectedShapes) {
            if (shape instanceof Group) {
                drawingPane.removeShapes(((Group) shape).getShapes());
                ((Group)shape).getShapes().forEach(subShape -> drawingPane.addShape(subShape));
            } else {
                shapes.add(shape);
            }
        }
    }
}
