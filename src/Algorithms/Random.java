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
            int x1 = (int) (Math.random() * tray.getDimension());
            int y1 = (int) (Math.random() * tray.getDimension());
            int x2 = (int) (Math.random() * tray.getDimension());
            int y2 = (int) (Math.random() * tray.getDimension());

            //if the place is already taken
            while (!tray.isFree(x1, y1)) {
                x1 = (int) (Math.random() * tray.getDimension());
                y1 = (int) (Math.random() * tray.getDimension());
            }
            bestX1 = x1;
            bestY1 = y1;
            if (player.getColor() == ColorConstants.CLEAR) {
                tray.setClearCell(bestX1, bestY1);
            } else if (player.getColor() == ColorConstants.DARK) {
                tray.setDarkCell(bestX2, bestY2);
            }

            //if the place is already taken
            while (!tray.isFree(x2, y2)) {
                x2 = (int) (Math.random() * tray.getDimension());
                y2 = (int) (Math.random() * tray.getDimension());
            }
            bestX2 = x2;
            bestY2 = y2;
            if (player.getColor() == ColorConstants.CLEAR) {
                tray.setClearCell(bestX1, bestY1);
            } else if (player.getColor() == ColorConstants.DARK) {
                tray.setDarkCell(bestX2, bestY2);
            }

            send2BestCells();
        } else {
            sendWantToStopGame();
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

        for (int i = 0; i < tray.getMatrice().length; i++) {
            for (int j = 0; j < tray.getMatrice()[i].length; j++) {
                if (tray.isFree(i, j)) {
                    numberCellFree++;
                }
            }
        }
        return numberCellFree >= 2;
    }
}
