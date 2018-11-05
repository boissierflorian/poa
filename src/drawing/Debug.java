package drawing;

public class Debug {
    public static void debug(final String msg) {
        if (Constants.DEBUG) {
            System.out.println("[debug] - " + msg);
        }
    }

    private Debug() {}
}