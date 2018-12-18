package drawing.handlers;

import drawing.commands.ICommand;
import javafx.event.Event;
import javafx.event.EventHandler;

public class ClearButtonHandler implements EventHandler<Event> {
    private ICommand command;

    public ClearButtonHandler(ICommand command) {
        this.command = command;
    }

    @Override
    public void handle(Event event) {
        command.execute();
    }
}
