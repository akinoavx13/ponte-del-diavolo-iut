package Network;

import Constant.ColorConstants;
import Constant.GameConstants;
import Constant.Log;
import Constant.NetworkConstants;
import Game.Player;
import Game.Tray;
import IHM.Window;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

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
    private Tray tray;
    private Scanner scanner;

    public Client(String addressServer, int port, int dimension) {
        this.addressServer = addressServer;
        Client.port = port;
        tray = new Tray(dimension);

        if (!GameConstants.IN_PROD) {
            scanner = new Scanner(System.in);
            new Window(tray);
        }
    }

    public void connectionToServer() {
        Log.writeLog(new Date().toString() + " : début de la connexion avec le serveur");
        if (GameConstants.IN_PROD) {
            try {
                socket = new Socket(addressServer, port);
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnectionToServer() {
        Log.writeLog(new Date().toString() + " : fin de la connexion avec le serveur");
        if (GameConstants.IN_PROD) {
            try {
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (GameConstants.isVerbose()) {
            System.out.print("Déconnexion du serveur");
        }
    }

    public int receiveMessage() {
        int result = -1;
        if (GameConstants.IN_PROD) {
            try {
                result = inputStream.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            result = scanner.nextLine().charAt(0);
        }
        return result;
    }

    public void sendMessage(String message) {
        if (GameConstants.IN_PROD) {
            for (int i = 0; i < message.length(); i++) {
                try {
                    outputStream.write((int) message.charAt(i));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (GameConstants.isVerbose()) {
                System.out.println("Envoi du message : \"" + message + "\"");
            }
        }
    }

    public void sendCellPos(int x1, int y1, int x2, int y2) {
        String message = "";
        message += String.valueOf(y1) + String.valueOf(x1) + "+" + String.valueOf(y2) + String.valueOf(x2);
        sendMessage(message);
        player.setTurnNumber(player.getTurnNumber() + 1);
        Log.writeLog(new Date().toString() + " : message envoyé au serveur : \"" + message + "\"");
    }

    public void sendBridgePos(int x1, int y1, int x2, int y2) {
        String message = "";
        message += String.valueOf(y1) + String.valueOf(x1) + "-" + String.valueOf(y2) + String.valueOf(x2);
        sendMessage(message);
        player.setTurnNumber(player.getTurnNumber() + 1);
        Log.writeLog(new Date().toString() + " : message envoyé au serveur : \"" + message + "\"");
    }

    public void createPlayer(boolean isFirstPlayer) {
        player = new Player(isFirstPlayer);
        tray.setPlayer(player);
    }

    public void manageMessages() {
        int message;
        while (true) {
            message = receiveMessage();
            switch (message) {
                case (int) NetworkConstants.FIRST_PLAYER:
                    if (GameConstants.isVerbose()) {
                        System.out.println("Vous êtes le premier joueur !");
                    }
                    createPlayer(true);
                    player.setRandomAlgorithm(tray, this);
                    player.initFirstPlayer();
                    Log.writeLog(new Date().toString() + " : message reçu du serveur : \"" + (char) message + "\"");
                    break;
                case (int) NetworkConstants.SECOND_PLAYER:
                    if (GameConstants.isVerbose()) {
                        System.out.println("Vous êtes le deuxième joueur !");
                    }
                    createPlayer(false);
                    player.setRandomAlgorithm(tray, this);
                    Log.writeLog(new Date().toString() + " : message reçu du serveur : \"" + (char) message + "\"");
                    break;
                case (int) NetworkConstants.CLEAR_PLAYER:
                    if (GameConstants.isVerbose()) {
                        System.out.println("Vous êtes le joueur foncé !");
                    }
                    player.setColor(ColorConstants.DARK);
                    player.play();
                    Log.writeLog(new Date().toString() + " : message reçu du serveur : \"" + (char) message + "\"");
                    break;
                case (int) NetworkConstants.DARK_PLAYER:
                    if (GameConstants.isVerbose()) {
                        System.out.println("Vous êtes le joueur clair !");
                    }
                    player.setColor(ColorConstants.CLEAR);
                    Log.writeLog(new Date().toString() + " : message reçu du serveur : \"" + (char) message + "\"");
                    break;
                case (int) NetworkConstants.PLAYER_STOP:
                    if (GameConstants.isVerbose()) {
                        System.out.println("L'autre joueur ne peut plus jouer !");
                    }
                    if (player.getColor() == ColorConstants.DARK) {
                        player.play();
                    }
                    Log.writeLog(new Date().toString() + " : message reçu du serveur : \"" + (char) message + "\"");
                    break;
                case (int) NetworkConstants.GAME_STOP:
                    if (GameConstants.isVerbose()) {
                        System.out.println("Fin de la partie");
                    }
                    Log.writeLog(new Date().toString() + " : message reçu du serveur : \"" + (char) message + "\"");
                    closeConnectionToServer();
                    System.exit(0);
                    break;
            }

            if (message >= 48 && message <= 57) {

                System.out.print("Tour n° " + player.getTurnNumber() + "\t");
                player.setTurnNumber(player.getTurnNumber() + 1);

                int rowCell1 = Integer.parseInt(String.valueOf((char) message));
                int columnCell1 = Integer.parseInt(String.valueOf((char) receiveMessage()));

                int operator = receiveMessage();

                if (operator == (int) '+') {
                    int rowCell2 = Integer.parseInt(String.valueOf((char) receiveMessage()));
                    int columnCell2 = Integer.parseInt(String.valueOf((char) receiveMessage()));

                    System.out.println("L'autre joueur a joué 2 cases : [" + rowCell1 + "][" + columnCell1 + "] et [" + rowCell2 + "][" + columnCell2 + "]");
                    Log.writeLog(new Date().toString() + " : message reçu du serveur : \"" + rowCell1 + columnCell1 + "+" + rowCell2 + columnCell2 + "\"");

                    if (player.getColor() == ColorConstants.CLEAR) {
                        tray.setDarkCell(columnCell1, rowCell1);
                        tray.setDarkCell(columnCell2, rowCell2);
                    } else if (player.getColor() == ColorConstants.DARK) {
                        tray.setClearCell(columnCell1, rowCell1);
                        tray.setClearCell(columnCell2, rowCell2);
                    }

                    if (player.getTurnNumber() == 1) {
                        player.initSecondPlayer();
                    } else if (player.getTurnNumber() > 1) {
                        player.play();
                    }

                } else if (operator == (int) '-') {

                    int rowCell2 = Integer.parseInt(String.valueOf((char) receiveMessage()));
                    int columnCell2 = Integer.parseInt(String.valueOf((char) receiveMessage()));

                    System.out.println("L'autre joueur a posé un pont sur la case : [" + rowCell1 + "][" + columnCell1 + "] et [" + rowCell2 + "][" + columnCell2 + "]");
                    Log.writeLog(new Date().toString() + " : message reçu du serveur : \"" + rowCell1 + columnCell1 + "-" + rowCell2 + columnCell2 + "\"");
                    tray.setBridgeIn(tray.getCellIn(columnCell1, rowCell1), tray.getCellIn(columnCell2, rowCell2));
                    tray.setBridgeNumber(tray.getBridgeNumber() - 1);

                    if (player.getTurnNumber() == 1) {
                        player.initSecondPlayer();
                    } else if (player.getTurnNumber() > 1) {
                        player.play();
                    }
                }
            }
        }
    }
}
