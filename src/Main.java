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
            addressServer = args[0];
            port = Integer.parseInt(args[1]);
            dimension = Integer.parseInt(args[2]);
        } else {
            addressServer = "127.0.0.1";
            port = 8080;
            dimension = 3;
        }
    }

}
