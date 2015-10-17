import Constant.GameConstants;
import Constant.Log;
import Network.Client;

import java.util.Date;

public class Main {

    private static String addressServer;
    private static int port;
    private static int dimension;

    public static void main(String[] args) {
        init(args);

        Log.clearLog();
        Log.writeLog(new Date().toString() + " : PARAMETRES : adresse : " + addressServer + ", port : " + port + ", dimension : " + dimension + "\n");

        Client client = new Client(addressServer, port, dimension);
        client.connectionToServer();

        client.manageMessages();
    }

    public static void init(String[] args) {
        if (args.length == 3) {
            GameConstants.setInProd(true);
            addressServer = args[0];
            port = Integer.parseInt(args[1]);
            dimension = Integer.parseInt(args[2]);

            if (GameConstants.isVerbose()) {
                System.out.println("Connexion au serveur avec les paramètres : ");
                System.out.println("\tAdresse du serveur : " + addressServer);
                System.out.println("\tPort : " + port);
                System.out.println("\tDimension : " + dimension);
            }

        } else {
            if (GameConstants.isVerbose()) {
                System.out.println("Connexion local");
            }

            addressServer = "127.0.0.1";
            port = 8080;
            dimension = 10;
        }
    }

}
