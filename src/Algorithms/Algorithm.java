package Algorithms;

import Constant.ColorConstants;
import Constant.NetworkConstants;
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

    public abstract void init2Cells();

    public abstract void searchBest2Cells();

    public abstract void chooseOneColor();
}
