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
        if (canSetTwoCells() && searchFirstBestCell() && searchSecondBestCell()) {
            set2BestCells();

            send2BestCells();
        } else {
            sendWantToStopGame();
        }
    }

    @Override
    public boolean searchFirstBestCell() {
        boolean result = false;

        int x = (int) (Math.random() * tray.getDimension());
        int y = (int) (Math.random() * tray.getDimension());

        if (player.getColor() == ColorConstants.CLEAR) {
            while (!tray.isFree(x, y)) {
                x = (int) (Math.random() * tray.getDimension());
                y = (int) (Math.random() * tray.getDimension());
            }
            result = canSetOneCell(x, y, ColorConstants.CLEAR);
            if (result) {
                bestX1 = x;
                bestY1 = y;
            }
        } else if (player.getColor() == ColorConstants.DARK) {
            while (!tray.isFree(x, y)) {
                x = (int) (Math.random() * tray.getDimension());
                y = (int) (Math.random() * tray.getDimension());
            }
            result = canSetOneCell(x, y, ColorConstants.DARK);
            if (result) {
                bestX1 = x;
                bestY1 = y;
            }
        }

        return result;
    }

    @Override
    public boolean searchSecondBestCell() {
        boolean result = false;

        int x = (int) (Math.random() * tray.getDimension());
        int y = (int) (Math.random() * tray.getDimension());

        if (player.getColor() == ColorConstants.CLEAR) {
            while (!tray.isFree(x, y)) {
                x = (int) (Math.random() * tray.getDimension());
                y = (int) (Math.random() * tray.getDimension());
            }
            result = canSetOneCell(x, y, ColorConstants.CLEAR);
            if (result) {
                bestX2 = x;
                bestY2 = y;
            }
        } else if (player.getColor() == ColorConstants.DARK) {
            while (!tray.isFree(x, y)) {
                x = (int) (Math.random() * tray.getDimension());
                y = (int) (Math.random() * tray.getDimension());
            }
            result = canSetOneCell(x, y, ColorConstants.DARK);
            if (result) {
                bestX2 = x;
                bestY2 = y;
            }
        }

        return result;
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
    public boolean canSetTwoCells() {
        return tray.getNumberCellFree() >= 2 && player.getCellsRemaining() >= 2;
    }

}
