package Constant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Maxime on 16/10/2015.
 */
public class Log {

    private final static String fileName = "logs.txt";

    public static void clearLog() {
        String filePath = System.getProperty("user.dir") + "/" + fileName;

        try {

            FileWriter fileWriter = new FileWriter(filePath, false);

            BufferedWriter output = new BufferedWriter(fileWriter);

            output.write("");

            output.flush();

            output.close();
        } catch (IOException ioe) {
            System.out.print("Erreur : ");
            ioe.printStackTrace();
        }
    }

    public static void writeLog(String text) {
        String filePath = System.getProperty("user.dir") + "/" + fileName;

        try {

            FileWriter fileWriter = new FileWriter(filePath, true);

            BufferedWriter output = new BufferedWriter(fileWriter);

            output.write(text + "\n");

            output.flush();

            output.close();
        } catch (IOException ioe) {
            System.out.print("Erreur : ");
            ioe.printStackTrace();
        }

    }

}
