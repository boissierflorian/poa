package drawing;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SelectionHandler implements SelectionObservable {

    private DrawingPane drawingPane;
    private List<IShape> selectedShapes;
    private boolean majSelected;
    private EventHandler<MouseEvent> mouseEventHandler;
    private List<SelectionObserver> selectionObservers;


    public SelectionHandler(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
        this.mouseEventHandler = new MouseListener();
        drawingPane.addEventHandler(MouseEvent.ANY, mouseEventHandler);

        selectedShapes = new ArrayList<>();
        majSelected = false;
        selectionObservers = new ArrayList<>();
    }


    public List<IShape> getSelectedShapes() {
        return selectedShapes;
    }

    public void handle(final KeyEvent keyEvent) {
        if (keyEvent.isShiftDown()) {
            majSelected = true;
        } else {
            clearSelection();
            majSelected = false;
        }
    }

    private synchronized void clearSelection() {
        selectedShapes.forEach(shape -> {
            shape.setSelected(false);
        });

        selectedShapes.clear();
    }

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
        selectionObservers.forEach(c -> c.update(this, shapeCount));
    }

    private class MouseListener implements EventHandler<MouseEvent>  {

        @Override
        public void handle(MouseEvent mouseEvent) {
            // Clic détecté
            if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
                boolean notClickedOnAShape = true;

                for (Iterator<IShape> it = drawingPane.iterator(); it.hasNext();) {
                    final IShape shape = it.next();

                    if (shape.isOn(mouseEvent.getX(), mouseEvent.getY())) {
                        // Si la touche maj est enfoncée
                        if (majSelected) {
                            // Si il est déjà présente on la retire de la liste
                            if (selectedShapes.contains(shape)) {
                                selectedShapes.remove(shape);
                                shape.setSelected(false);
                            } else {
                                // On ajoute la forme à la sélection
                                selectedShapes.add(shape);
                                shape.setSelected(true);
                            }
                        } else { // On vide la liste et on met uniquement la forme sélectionée
                            selectedShapes.clear();
                            selectedShapes.add(shape);
                            shape.setSelected(true);
                        }

                        notClickedOnAShape = false;
                    }
                }

                // Aucun clic -> sélection vidée
                if (notClickedOnAShape) {
                    clearSelection();
                }

                notifySelectionToObservers(selectedShapes.size());
            }

            if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
                if (!majSelected) {
                    clearSelection();
                }
                
                notifySelectionToObservers(selectedShapes.size());
            }
        }
    }
}