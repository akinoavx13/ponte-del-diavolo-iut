package Algorithms;

import Constant.ColorConstants;
import Constant.GameConstants;
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

    protected Player player;

    protected int bestX1, bestY1, bestX2, bestY2;

    public Algorithm(Tray tray, Client client, Player player) {
        this.tray = tray;
        this.client = client;
        this.player = player;
    }

    /**********
     * METHODS*
     **********/

    public void send2BestCells() {
        client.sendCellPos(bestX1, bestY1, bestX2, bestY2);
    }

    public void sendBridge() {
        tray.setBridgeNumber(tray.getBridgeNumber() - 1);
        client.sendBridgePos(bestX1, bestY1, bestX2, bestY2);
    }

    public void sendPlayerColor() {
        if (player.getColor() == ColorConstants.CLEAR) {
            client.sendMessage(Character.toString(NetworkConstants.CLEAR_PLAYER));
        } else {
            client.sendMessage(Character.toString(NetworkConstants.DARK_PLAYER));
        }
    }

    public void sendWantToStopGame() {
        client.sendMessage("a");
    }

    public boolean canSetTwoCells() {
        int numberCaseFree = tray.getNumberCellFree();
        return numberCaseFree >= 2;
    }

    /*******************
     * ABSTRACT METHODES*
     *******************/

    public abstract void init2Cells();

    public abstract void searchBest2Cells();

    public abstract void chooseOneColor();

    public abstract boolean searchBestCell(boolean forCell1);

    public abstract boolean searchBestBridge();


    /*********
     * SETTERS*
     *********/

    protected void setBestCell1() {
        if (player.getColor() == ColorConstants.CLEAR) {
            if (GameConstants.isVerbose()) {
                System.err.println("Posé en tant que bestCell");
            }
            tray.setClearCell(bestX1, bestY1);
        } else if (player.getColor() == ColorConstants.DARK) {
            if (GameConstants.isVerbose()) {
                System.err.println("Posé en tant que bestCell");
            }
            tray.setDarkCell(bestX1, bestY1);
        }
    }

    protected void setBestCell2() {
        if (player.getColor() == ColorConstants.CLEAR) {
            if (GameConstants.isVerbose()) {
                System.err.println("Posé en tant que bestCell");
            }
            tray.setClearCell(bestX2, bestY2);
        } else if (player.getColor() == ColorConstants.DARK) {
            if (GameConstants.isVerbose()) {
                System.err.println("Posé en tant que bestCell");
            }
            tray.setDarkCell(bestX2, bestY2);
        }
    }

}
