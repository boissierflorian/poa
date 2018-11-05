
package drawing;

public class CSSFactory {

    private static String CSS_DIR = "./";

    public static String getStyleSheet(String filename) {
        return CSSFactory.class.getResource(CSS_DIR + filename).toExternalForm();
    }

}

