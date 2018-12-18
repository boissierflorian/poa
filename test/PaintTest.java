import drawing.shapes.IShape;
import drawing.ui.PaintApplication;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Iterator;

import static org.junit.Assert.*;

public class PaintTest extends ApplicationTest {

    PaintApplication app;

    @Override
    public void start(Stage stage) {
        try {
            app = new PaintApplication();
            app.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_draw_circle_programmatically() {
        interact(() -> {
                    app.getDrawingPane().addShape(new Ellipse(20, 20, 30, 30));
                });
        Iterator it = app.getDrawingPane().iterator();
        assertTrue(it.next() instanceof IShape);
        assertFalse(it.hasNext());
    }

    @Test
    public void should_draw_circle() {
        // given:
        clickOn("Circle");
        moveBy(60,60);

        // when:
        drag().dropBy(30,30);
        //press(MouseButton.PRIMARY); moveBy(30,30); release(MouseButton.PRIMARY);

        // then:
        Iterator it = app.getDrawingPane().iterator();
        assertTrue(it.next() instanceof IShape);
        assertFalse(it.hasNext());
    }

    @Test
    public void should_draw_rectangle() {
        // given:
        clickOn("Rectangle");
        moveBy(0,60);

        // when:
        drag().dropBy(70,40);

        // then:
        Iterator it = app.getDrawingPane().iterator();
        assertTrue(it.next() instanceof IShape);
        assertFalse(it.hasNext());
    }

    @Test
    public void should_draw_triangle() {
        // given:
        clickOn("Triangle");
        moveBy(0, 60);

        // when:
        drag().dropBy(70, 40);

        // then:
        Iterator it = app.getDrawingPane().iterator();
        assertTrue(it.next() instanceof IShape);
        assertFalse(it.hasNext());
    }

    @Test
    public void should_clear() {
        // given:
        clickOn("Rectangle");
        moveBy(30,60).drag().dropBy(70,40);
        clickOn("Circle");
        moveBy(-30,160).drag().dropBy(70,40);
        clickOn("Triangle");
        moveBy(60, 160).drag().dropBy(80, 180);

        // when:
        clickOn("Clear");

        // then:
        assertFalse(app.getDrawingPane().iterator().hasNext());
    }

    @Test
    public void should_be_valid_selected_shape() {
        // given:
        assertEquals("0 Shape(s), 0 selected", app.getStatutBar().getText());

        // when:
        clickOn("Rectangle");
        moveBy(30, 60).clickOn(MouseButton.PRIMARY).drag(MouseButton.PRIMARY);

        // then:
        assertEquals("1 Shape(s), 1 selected", app.getStatutBar().getText());
        dropBy(50, 0);
    }

    @Test
    public void should_be_grouped() {
        // when:
        clickOn("Rectangle");
        moveBy(30, 60).clickOn(MouseButton.PRIMARY).drag(MouseButton.PRIMARY);
        push(KeyCode.SHIFT);
        dropBy(50, 0);
        clickOn("Circle");
        moveBy(30, 60).clickOn(MouseButton.PRIMARY).drag(MouseButton.PRIMARY);
    }
}