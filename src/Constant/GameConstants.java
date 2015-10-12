package Constant;

/**
 * Created by Maxime on 11/10/2015.
 */
public class GameConstants {

    public static boolean IN_PROD = false;
    private static boolean VERBOSE = true;

    public static boolean isInProd() {
        return IN_PROD;
    }

    public static void setInProd(boolean inProd) {
        IN_PROD = inProd;
    }

    public static boolean isVerbose() {
        return VERBOSE;
    }

    public static void setVerbose(boolean verbose) {
        VERBOSE = verbose;
    }
}
