package drawing.ui;

import drawing.handlers.EllipseButtonHandler;
import drawing.handlers.RectangleButtonHandler;
import drawing.handlers.TriangleButtonHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class ToolBar {
    private Button clearButton;
    private Button rectangleButton;
    private Button circleButton;
    private Button triangleButton;
    private Button delButton;

    public ToolBar(DrawingPane drawingPane) {
        clearButton = ButtonFactory.createButton(ButtonFactory.CLEAR_BUTTON);
        clearButton.setOnAction(event -> drawingPane.clear());
        rectangleButton = ButtonFactory.createButton(ButtonFactory.RECTANGLE_BUTTON);
        rectangleButton.addEventFilter(ActionEvent.ACTION, new RectangleButtonHandler(drawingPane));
        circleButton = ButtonFactory.createButton(ButtonFactory.CIRCLE_BUTTON);
        circleButton.addEventFilter(ActionEvent.ACTION, new EllipseButtonHandler(drawingPane));
        triangleButton = ButtonFactory.createButton(ButtonFactory.TRIANGLE_BUTTON);
        triangleButton.addEventFilter(ActionEvent.ACTION, new TriangleButtonHandler(drawingPane));
        delButton = ButtonFactory.createButton(ButtonFactory.DELETE_BUTTON);
        delButton.setOnMouseClicked(mouseEvent -> drawingPane.removeSelectedShapes());
    }

    public HBox build() {
        HBox hBox = new HBox();
        hBox.getChildren().addAll(clearButton, rectangleButton, circleButton, triangleButton, delButton);
        hBox.setPadding(new Insets(5));
        hBox.setSpacing(5.0);
        hBox.getStyleClass().add("toolbar");
        return hBox;
    }
}
