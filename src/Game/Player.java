package Game;

import Algorithms.Algorithm;
import Algorithms.Random;
import Constant.ColorConstants;
import Network.Client;

/**
 * Created by Maxime on 10/10/2015.
 */
public class Player {

    private boolean firstPlayer;
    private String color;
    private Algorithm algorithm = null;

    public Player(boolean firstPlayer) {
        this.firstPlayer = firstPlayer;
        if (firstPlayer) {
            color = ColorConstants.CLEAR;
        } else {
            color = ColorConstants.DARK;
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    public void setRandomAlgorithm(Tray tray, Client client) {
        this.algorithm = new Random(tray, client);
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }
}
