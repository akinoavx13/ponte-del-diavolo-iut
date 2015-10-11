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
    private int turnNumber;

    public Player(boolean firstPlayer) {
        turnNumber = 0;
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
        this.algorithm = new Random(tray, client, this);
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public void initFirstPlayer() {
        algorithm.init2Cells();
    }

    public void initSecondPlayer() {
        algorithm.chooseOneColor();
        if (color == ColorConstants.DARK) {
            algorithm.searchBest2Cells();
        }
    }

    public void play() {
        algorithm.searchBest2Cells();
    }

    public String toString() {
        String result = "";

        result += "Adversaire : (color = " + color + "), (";

        if (firstPlayer) {
            result += "firstPlayer)";
        } else {
            result += "secondPlayer)";
        }

        return result;
    }

}
