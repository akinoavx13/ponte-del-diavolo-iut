package Constant;

/**
 * Created by Maxime on 11/10/2015.
 */
public class GameConstants {

    public static final boolean IN_PROD = false;
    private static boolean verbose = true;

    public static boolean isVerbose() {
        return verbose;
    }

    public static void setVerbose(boolean verbose1) {
        verbose = verbose1;
    }
}
