package drawing.handlers;

import drawing.commands.ICommand;
import drawing.shapes.IShape;
import drawing.ui.DrawingPane;

import java.util.ArrayList;
import java.util.List;

public class ClearCommand implements ICommand {
    private DrawingPane receiver;
    private List<IShape> shapes;

    public ClearCommand(DrawingPane drawingPane) {
        this.receiver = drawingPane;
        this.shapes = new ArrayList<>();
    }

    @Override
    public void execute() {
        shapes.clear();

        for (final IShape shape : receiver.getShapes()) {
            shapes.add(shape);
        }

        receiver.clear();
    }

    @Override
    public void undo() {
        shapes.forEach(shape -> receiver.addShape(shape));
    }
}
