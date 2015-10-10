package Network;

import Constant.ColorConstants;
import Constant.NetworkChar;
import Game.Player;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Maxime on 10/10/2015.
 */
public class Client {

    private static int port;
    private static Socket socket;
    private String addressServer;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    private Player player;

    public Client(String addressServer, int port) {
        this.addressServer = addressServer;
        Client.port = port;
    }

    public void connectionToServer() {
        try {
            socket = new Socket(addressServer, port);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnectionToServer() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {
        String result = "";
        int receive;
        try {
            receive = inputStream.read();
            while (receive > 0 && receive <= 255) {
                result += (char) receive;
                receive = inputStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    public boolean connectionNotClosed(String message) {
        return message != "F";
    }

    public void createPlayer(boolean isFirstPlayer) {
        player = new Player(isFirstPlayer);
    }

    public Player getPlayer() {
        return this.player;
    }

    public void manageMessage() {
        String message = receiveMessage();

        while (connectionNotClosed(message)) {
            switch (message) {
                case NetworkChar.NULL:
                    break;
                case NetworkChar.FIRST_PLAYER:
                    System.out.println("Vous êtes le premier joueur !");
                    createPlayer(true);
                    break;
                case NetworkChar.SECOND_PLAYER:
                    System.out.println("Vous êtes le deuxième joueur !");
                    createPlayer(false);
                    break;
                case NetworkChar.CLEAR_PLAYER:
                    System.out.println("Vous êtes le joueur clair !");
                    getPlayer().setColor(ColorConstants.CLEAR);
                    break;
                case NetworkChar.DARK_PLAYER:
                    System.out.println("Vous êtes le joueur foncé !");
                    getPlayer().setColor(ColorConstants.DARK);
                    break;
                case NetworkChar.PLAYER_STOP:
                    System.out.println("L'autre joueur ne peut plus jouer !");
                    break;
                case NetworkChar.GAME_STOP:
                    System.out.println("Fin de la partie");
                    closeConnectionToServer();
                    break;
            }
            message = receiveMessage();
        }
    }
}
