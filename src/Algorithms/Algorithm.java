package Algorithms;

import Constant.ColorConstants;
import Constant.GameConstants;
import Constant.NetworkConstants;
import Game.Cell;
import Game.Player;
import Game.Tray;
import Network.Client;

import java.util.ArrayList;

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

    public int countIslandIsolated(String color) {
        int numberIslandIsolated = 0;
        Cell[][] matrice = tray.getMatrice();

        //init the matrice, cells are not visited
        tray.setMatriceUnvisited();

        for (int i = 0; i < tray.getDimension(); i++) {
            for (int j = 0; j < tray.getDimension(); j++) {

                //count if there are 4 cells adjacent.
                if (tray.totalCellsAdjacent(i, j, color).size() == 4) {
                    numberIslandIsolated++;
                }
            }
        }

        return numberIslandIsolated;
    }

    public void send2BestCells() {
        player.setCellsRemaining(player.getCellsRemaining() - 2);
        client.sendCellPos(bestX1, bestY1, bestX2, bestY2);
    }

    public void sendBridge() {
        tray.setBridgeNumber(tray.getBridgeNumber() - 1);
        client.sendBridgePos(bestX1, bestY1, bestX2, bestY2);
    }

    public void sendPlayerColor() {
        if (player.getColor() == ColorConstants.CLEAR) {
            client.sendMessage(NetworkConstants.CLEAR_PLAYER);
        } else {
            client.sendMessage(NetworkConstants.DARK_PLAYER);
        }
    }

    public void set2BestCells() {
        if (player.getColor() == ColorConstants.CLEAR) {
            tray.setClearCell(bestX1, bestY1);
            tray.setClearCell(bestX2, bestY2);
        } else if (player.getColor() == ColorConstants.DARK) {
            tray.setDarkCell(bestX1, bestY1);
            tray.setDarkCell(bestX2, bestY2);
        }
    }

    public void sendWantToStopGame() {
        client.sendMessage("a");
    }

    public boolean canSetTwoCells() {
        return tray.getNumberCellFree() >= 2 && player.getCellsRemaining() >= 2;
    }

    public boolean canSetOneCell(int x, int y, String color) {
        boolean result = false;

        tray.setMatriceUnvisited();

        if (tray.isFree(x, y)) {
            if (color == ColorConstants.CLEAR) {
                tray.setClearCell(x, y);
            } else if (color == ColorConstants.DARK) {
                tray.setDarkCell(x, y);
            }

            ArrayList<Cell> totalAdjacent = tray.totalCellsAdjacent(x, y, color);

            if (totalAdjacent.size() < 4) {
                if (!tray.islandInDiagNotVisited(x, y, color)) {
                    result = true;
                }
            } else if (totalAdjacent.size() == 4) {
                result = true;
                for (Cell cell : totalAdjacent) {
                    if (tray.cellInDiagNotVisited(cell.getX(), cell.getY(), color)) {
                        result = false;
                    }
                }
            }
        }

        tray.setCellToFree(x, y);

        return result;
    }

    public boolean canSetOneBridge(Cell cellA, Cell cellB) {
        boolean result = false;

        if (cellA.getColor() == cellB.getColor()) {
            if (!cellA.isBridge() && !cellB.isBridge()) {
                double distanceBetwwenAandB = tray.distanceBetween2Cells(cellA, cellB);

                int xa = cellA.getX();
                int xb = cellB.getX();

                int ya = cellA.getY();
                int yb = cellB.getY();

                if (distanceBetwwenAandB <= 2 * Math.sqrt(2) && distanceBetwwenAandB > Math.sqrt(2)) {

                    int deltaX = xb - xa;
                    int deltaY = yb - ya;

                    double absDXDY = Math.abs(deltaX) + Math.abs(deltaY);

                    if (absDXDY == 2) {

                        int x = xa + deltaX / 2;
                        int y = ya + deltaY / 2;
                        result = !tray.getCellIn(x, y).isBlocked();

                    } else if (absDXDY == 3) {

                        int x1 = (xa + xb) / 2;
                        int y1 = (ya + yb) / 2;

                        int x2 = xa + deltaX / 2;
                        int y2 = ya + deltaY / 2;
                        result = !tray.getCellIn(x1, y1).isBlocked() && !tray.getCellIn(x2, y2).isBlocked();

                    } else if (absDXDY == 4) {
                        int x = (xa + xb) / 2;
                        int y = (ya + yb) / 2;
                        result = !tray.getCellIn(x, y).isBlocked();
                    }
                }
            }
        }

        return result;
    }

    /*******************
     * ABSTRACT METHODES*
     *******************/

    public abstract void init2Cells();

    public abstract void searchBest2Cells();

    public abstract void chooseOneColor();

    public abstract boolean searchBestCell(boolean forCell1);


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
