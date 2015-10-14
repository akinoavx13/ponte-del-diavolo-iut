package Network;

import Constant.GameConstants;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Maxime on 14/10/2015.
 */
public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int port;

    private InputStream inputStream;
    private OutputStream outputStream;

    public Server() {
        port = 8080;
        try {
            System.out.println("En attente du client ...");
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            System.out.println("Ajout d'un client : " + clientSocket.getInetAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();

        server.sendMessage("S");

        server.sendMessage("0");
        server.sendMessage("0");
        server.sendMessage("+");
        server.sendMessage("0");
        server.sendMessage("1");

        server.sendMessage("F");


        /*int message = server.receiveMessage();

        while (true) {
            if (message != -1) {
                System.out.println("Message : \"" + (char) message + "\"");
            }
            message = server.receiveMessage();
        }*/

    }

    public int receiveMessage() {
        int result = -1;
        if (GameConstants.IN_PROD) {
            try {
                result = inputStream.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void sendMessage(String message) {
        for (int i = 0; i < message.length(); i++) {
            try {
                outputStream.write((int) message.charAt(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
