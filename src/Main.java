import Constant.GameConstants;
import Network.Client;

public class Main {

    private static String addressServer;
    private static int port;
    private static int dimension;

    public static void main(String[] args) {
        init(args);

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
            dimension = 4;
        }
    }

}
