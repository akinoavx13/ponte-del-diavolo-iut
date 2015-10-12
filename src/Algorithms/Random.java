package Algorithms;

import Constant.ColorConstants;
import Game.Player;
import Game.Tray;
import Network.Client;

/**
 * Created by Maxime on 10/10/2015.
 */
public class Random extends Algorithm {

    public Random(Tray tray, Client client, Player player) {
        super(tray, client, player);
    }

    /**
     * set the two first cells during the initialisation
     */
    @Override
    public void init2Cells() {
        int x1 = (int) (Math.random() * tray.getDimension());
        int y1 = (int) (Math.random() * tray.getDimension());
        int x2 = (int) (Math.random() * tray.getDimension());
        int y2 = (int) (Math.random() * tray.getDimension());

        if (player.getColor() == ColorConstants.CLEAR) {
            tray.setClearCell(x1, y1);
            tray.setClearCell(x2, y2);
        } else if (player.getColor() == ColorConstants.DARK) {
            tray.setDarkCell(x1, y1);
            tray.setDarkCell(x2, y2);
        }

        setBestCell1(x1, y1);
        setBestCell2(x2, y2);

        send2BestCells();
    }

    /**
     * set the two best cells configuration
     */
    @Override
    public void searchBest2Cells() {
        if (canISet2Cells()) {

            searchFirstBestCell();
            searchSecondBestCell();

            send2BestCells();
        } else {
            sendWantToStopGame();
        }
    }

    @Override
    public void searchFirstBestCell() {
        int x = (int) (Math.random() * tray.getDimension());
        int y = (int) (Math.random() * tray.getDimension());

        while (!tray.isFree(x, y)) {
            x = (int) (Math.random() * tray.getDimension());
            y = (int) (Math.random() * tray.getDimension());
        }
        bestX1 = x;
        bestY1 = y;

        if (player.getColor() == ColorConstants.CLEAR) {
            tray.setClearCell(bestX1, bestY1);

            tray.setMatriceUnvisited();
            if (totalCellsAdjacent(bestX1, bestY1, player.getColor()) >= 5) {
                tray.setCellTo0(bestX1, bestY1);
                searchFirstBestCell();
            }

        } else if (player.getColor() == ColorConstants.DARK) {
            tray.setDarkCell(bestX1, bestY1);

            tray.setMatriceUnvisited();
            if (totalCellsAdjacent(bestX1, bestY1, player.getColor()) >= 5) {
                tray.setCellTo0(bestX1, bestY1);
                searchFirstBestCell();
            }
        }
    }

    @Override
    public void searchSecondBestCell() {
        int x = (int) (Math.random() * tray.getDimension());
        int y = (int) (Math.random() * tray.getDimension());

        while (!tray.isFree(x, y)) {
            x = (int) (Math.random() * tray.getDimension());
            y = (int) (Math.random() * tray.getDimension());
        }
        bestX2 = x;
        bestY2 = y;

        if (player.getColor() == ColorConstants.CLEAR) {
            tray.setClearCell(bestX2, bestY2);

            tray.setMatriceUnvisited();
            if (totalCellsAdjacent(bestX2, bestY2, player.getColor()) >= 5) {
                tray.setCellTo0(bestX2, bestY2);
                searchFirstBestCell();
            }

        } else if (player.getColor() == ColorConstants.DARK) {
            tray.setDarkCell(bestX2, bestY2);

            tray.setMatriceUnvisited();
            if (totalCellsAdjacent(bestX2, bestY2, player.getColor()) >= 5) {
                tray.setCellTo0(bestX2, bestY2);
                searchFirstBestCell();
            }
        }
    }

    /**
     * Choose the color of the player during the initialisation
     */
    @Override
    public void chooseOneColor() {
        int color = (int) (Math.random() * 2);
        if (color == 0) {
            player.setColor(ColorConstants.CLEAR);
        } else {
            player.setColor(ColorConstants.DARK);
        }
        sendPlayerColor();
    }

    /**
     * check if there are 2 places to set cells
     *
     * @return
     */
    @Override
    public boolean canISet2Cells() {
        int numberCellFree = 0;

        for (int i = 0; i < tray.getDimension(); i++) {
            for (int j = 0; j < tray.getDimension(); j++) {
                if (tray.isFree(i, j)) {
                    numberCellFree++;
                }
            }
        }
        return numberCellFree >= 2 && player.getCellsRemaining() >= 2;
    }

}
