package drawing.handlers;

import drawing.shapes.Group;
import drawing.shapes.IShape;
import drawing.ui.DrawingPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class GroupButtonHandler implements EventHandler<MouseEvent> {

    private DrawingPane drawingPane;

    public GroupButtonHandler(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        // On récupère la liste des formes sélectionnées
        SelectionHandler selectionHandler = drawingPane.getSelectionHandler();
        List<IShape> selectedShapes = selectionHandler.getSelectedShapes();

        // On les supprimes de la liste
        selectedShapes.forEach(selectedShape -> drawingPane.removeShape(selectedShape));

        // On ajoute le groupe à la liste
        IShape group = new Group(selectedShapes);
        drawingPane.addShape(group);
    }
}