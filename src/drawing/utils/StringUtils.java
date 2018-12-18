package drawing.utils;

public class StringUtils {

    public static String capitalizeFirstLetter(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
