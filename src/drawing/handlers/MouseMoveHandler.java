package drawing.handlers;

import drawing.ui.DrawingPane;
import drawing.shapes.IShape;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.Iterator;

/**
 * Created by lewandowski on 20/12/2017.
 */
public class MouseMoveHandler implements EventHandler<MouseEvent> {

    private DrawingPane drawingPane;

    private double orgSceneX;
    private double orgSceneY;
    private IShape selectedShape;

    public MouseMoveHandler(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
        drawingPane.addEventHandler(MouseEvent.ANY, this);
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
            // Les positions d'origine sur la scène
            orgSceneX = event.getSceneX();
            orgSceneY = event.getSceneY();

            for (Iterator<IShape> it = drawingPane.iterator(); it.hasNext();) {
                final IShape shape = it.next();

                if (shape.isOn(event.getX(), event.getY())) {
                    selectedShape = shape;
                    break;
                }
            }
        }

        if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
            if (selectedShape == null)
                return;

            double offsetX = event.getSceneX() - orgSceneX;
            double offsetY = event.getSceneY() - orgSceneY;

            drawingPane.getSelection().forEach(shape -> {
                shape.offset(offsetX, offsetY);
            });

            orgSceneX += offsetX;
            orgSceneY += offsetY;
        }

        if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
            selectedShape = null;
        }
    }
}
