import Network.Client;

public class Main {

    public static void main(String[] args) {
        String addressServer;
        int port;
        int dimension;

        if (args.length == 3) {
            addressServer = args[0];
            port = Integer.parseInt(args[1]);
            dimension = Integer.parseInt(args[2]);
        } else {
            System.out.println("Vous avez oublié des paramètres !");
            addressServer = "127.0.0.1";
            port = 8080;
            dimension = 10;
        }

        Client client = new Client(addressServer, port);
        client.connectionToServer();

        String res = client.receive();

        while (res != "F" && res != "") {
            System.out.println("Message recu du serveur : \"" + res + "\"");
            res = client.receive();
        }

        client.closeConnectionToServer();

    }
}
