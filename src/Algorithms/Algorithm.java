package Algorithms;

import Constant.ColorConstants;
import Constant.NetworkConstants;
import Game.Cell;
import Game.Player;
import Game.Tray;
import Network.Client;

/**
 * Created by Maxime on 10/10/2015.
 */
public abstract class Algorithm {

    protected Tray tray;
    protected Client client;
    protected int bestX1;
    protected int bestY1;
    protected int bestX2;
    protected int bestY2;
    protected Player player;

    public Algorithm(Tray tray, Client client, Player player) {
        this.tray = tray;
        this.client = client;
        this.player = player;
    }

    public void send2BestCells() {
        client.sendCellPos(bestX1, bestY1, bestX2, bestY2);
    }

    public void sendPlayerColor() {
        if (player.getColor() == ColorConstants.CLEAR) {
            client.sendMessage(NetworkConstants.CLEAR_PLAYER);
        } else {
            client.sendMessage(NetworkConstants.DARK_PLAYER);
        }
    }

    public Tray getTray() {
        return tray;
    }

    protected void setBestCell1(int x, int y) {
        bestX1 = x;
        bestY1 = y;
    }

    protected void setBestCell2(int x, int y) {
        bestX2 = x;
        bestY2 = y;
    }

    public void sendWantToStopGame() {
        client.sendMessage("a");
    }

    public int countIslandIsolated(String color) {
        int numberIslandIsolated = 0;
        Cell[][] matrice = tray.getMatrice();

        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                matrice[i][j].setVisited(false);
            }
        }

        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                if (totalAdjacent(i, j, color) == 4) {
                    numberIslandIsolated++;
                }
            }
        }

        return numberIslandIsolated;
    }

    public int totalAdjacent(int x, int y, String color) {
        int totalAdjacent = 0;
        Cell[][] matrice = tray.getMatrice();

        if (!matrice[x][y].isVisited() && matrice[x][y].getColor() == color) {
            totalAdjacent++;
            matrice[x][y].setVisited(true);
            if (x + 1 < tray.getDimension()) {
                totalAdjacent += totalAdjacent(x + 1, y, color);
            }

            if (y + 1 < tray.getDimension()) {
                totalAdjacent += totalAdjacent(x, y + 1, color);
            }

            if (y - 1 >= 0) {
                totalAdjacent += totalAdjacent(x, y - 1, color);
            }

            if (x - 1 >= 0) {
                totalAdjacent += totalAdjacent(x - 1, y, color);
            }
        }
        return totalAdjacent;
    }

    public abstract void init2Cells();

    public abstract void searchBest2Cells();

    public abstract void chooseOneColor();

    public abstract boolean canISet2Cells();

}
