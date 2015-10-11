package Network;

import Constant.ColorConstants;
import Constant.GameConstants;
import Constant.NetworkConstants;
import Game.Player;
import Game.Tray;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
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
        }

    }

    public void connectionToServer() {
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
        if (GameConstants.IN_PROD) {
            try {
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String receiveMessage() {
        String result = "";
        if (GameConstants.IN_PROD) {
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
        } else {
            result = scanner.nextLine();
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
            System.out.println("Envoi du message : \"" + message + "\"");
        }
    }

    public void sendCellPos(int x1, int y1, int x2, int y2) {
        String message = "";
        message += String.valueOf(y1) + String.valueOf(x1) + "+" + String.valueOf(y2) + String.valueOf(x2);
        sendMessage(message);
        player.setTurnNumber(player.getTurnNumber() + 1);
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

    public void manageMessages() {
        String message = receiveMessage();

        while (connectionNotClosed(message)) {
            switch (message) {
                case NetworkConstants.NULL:
                    break;
                case NetworkConstants.FIRST_PLAYER:
                    System.out.println("Vous êtes le premier joueur !");
                    createPlayer(true);
                    player.setRandomAlgorithm(tray, this);
                    player.initFirstPlayer();
                    break;
                case NetworkConstants.SECOND_PLAYER:
                    System.out.println("Vous êtes le deuxième joueur !");
                    createPlayer(false);
                    player.setRandomAlgorithm(tray, this);
                    break;
                case NetworkConstants.CLEAR_PLAYER:
                    System.out.println("Vous êtes le joueur clair !");
                    player.setColor(ColorConstants.CLEAR);
                    break;
                case NetworkConstants.DARK_PLAYER:
                    System.out.println("Vous êtes le joueur foncé !");
                    player.setColor(ColorConstants.DARK);
                    break;
                case NetworkConstants.PLAYER_STOP:
                    System.out.println("L'autre joueur ne peut plus jouer !");
                    break;
                case NetworkConstants.GAME_STOP:
                    System.out.println("Fin de la partie");
                    closeConnectionToServer();
                    System.exit(0);
                    break;
                case "matrice":
                    System.out.println(tray);
                    break;
                case "tour":
                    System.out.println("Tour n° " + player.getTurnNumber());
                    break;
                case "player":
                    System.out.println(player);
                    break;
            }

            if (message.length() == 5) {
                System.out.print("Tour n° " + player.getTurnNumber() + "\t");
                player.setTurnNumber(player.getTurnNumber() + 1);
                for (int i = 0; i < message.length(); i++) {
                    if (message.charAt(i) == '+') {
                        System.out.println("L'autre joueur a joué 2 cases : [" + message.charAt(0) + "][" + message.charAt(1) + "] et [" + message.charAt(3) + "][" + message.charAt(4) + "]");

                        if (player.getColor() == ColorConstants.CLEAR) {
                            tray.setDarkCell(Integer.parseInt(String.valueOf(message.charAt(0))), Integer.parseInt(String.valueOf(message.charAt(1))));
                            tray.setDarkCell(Integer.parseInt(String.valueOf(message.charAt(3))), Integer.parseInt(String.valueOf(message.charAt(4))));
                        } else if (player.getColor() == ColorConstants.DARK) {
                            tray.setClearCell(Integer.parseInt(String.valueOf(message.charAt(0))), Integer.parseInt(String.valueOf(message.charAt(1))));
                            tray.setClearCell(Integer.parseInt(String.valueOf(message.charAt(3))), Integer.parseInt(String.valueOf(message.charAt(4))));
                        }

                        if (player.getTurnNumber() == 1) {
                            player.initSecondPlayer();
                        } else if (player.getTurnNumber() > 1) {
                            player.play();
                        }

                    } else if (message.charAt(i) == '-') {
                        System.out.println("L'autre joueur a posé un pont sur la case : [" + message.charAt(0) + "][" + message.charAt(1) + "] et [" + message.charAt(3) + "][" + message.charAt(4) + "]");
                    }
                }
            }

            System.out.println("A vous de jouer !");

            message = receiveMessage();
        }
    }
}
