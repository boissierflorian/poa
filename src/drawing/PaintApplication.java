package drawing;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.tools.Tool;

/**
 * Created by lewandowski on 20/12/2017.
 */
public class PaintApplication extends Application {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Scene scene;
    private BorderPane root;
    private DrawingPane drawingPane;

    private Button clearButton;
    private Button rectangleButton;
    private Button circleButton;
    private Button triangleButton;
    private Button delButton;

    private ToolBar toolBar;
    private StatutBar statutBar;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();
        scene = new Scene(root, WIDTH, HEIGHT);
        scene.addEventHandler(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                drawingPane.handleKeyEvent(keyEvent);
            }
        });

        root.getStylesheets().add(
                PaintApplication.class.getResource("./Paint.css").toExternalForm());

        drawingPane = new DrawingPane();

        drawingPane.getStyleClass().add("drawingPane");
        root.setCenter(drawingPane);
        toolBar = new ToolBar(drawingPane);
        root.setTop(toolBar.build());

        statutBar = new StatutBar();
        statutBar.setPadding(new Insets(10));
        statutBar.setSpacing(5.0);
        drawingPane.addObserver((Observer) statutBar);
        drawingPane.addSelectionObserver((SelectionObserver) statutBar);
        drawingPane.getSelectionHandler().addSelectionObserver(statutBar);
        statutBar.getStyleClass().add("statutbar");
        root.setBottom(statutBar);

        primaryStage.setTitle("Drawing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public DrawingPane getDrawingPane() {
        return drawingPane;
    }

    public StatutBar getStatutBar() { return statutBar; }

    public static void main(String[] args) {
        launch(args);
    }
}
