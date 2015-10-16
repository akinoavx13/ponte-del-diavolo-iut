package Algorithms;

import Constant.ColorConstants;
import Constant.Log;
import Game.Player;
import Game.Tray;
import Network.Client;

import java.util.Date;

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

        Log.writeLog(new Date().toString() + " : DEBUT d'initialisation des deux premières cellules, si on est le premier joueur. FONCTION : ini2Cells : CLASSE : Algorithms/Random");

        int x1 = (int) (Math.random() * tray.getDimension());
        int y1 = (int) (Math.random() * tray.getDimension());

        int x2 = (int) (Math.random() * tray.getDimension());
        int y2 = (int) (Math.random() * tray.getDimension());

        while (x1 == x2 && y1 == y2) {
            x2 = (int) (Math.random() * tray.getDimension());
            y2 = (int) (Math.random() * tray.getDimension());
        }

        if (player.getColor() == ColorConstants.CLEAR) {
            tray.setClearCell(x1, y1);
            tray.setClearCell(x2, y2);
        } else if (player.getColor() == ColorConstants.DARK) {
            tray.setDarkCell(x1, y1);
            tray.setDarkCell(x2, y2);
        }

        bestX1 = x1;
        bestY1 = y1;

        bestX2 = x2;
        bestY2 = y2;
        Log.writeLog(new Date().toString() + " : FIN d'initialisation des deux premières cellules, si on est le premier joueur. FONCTION : ini2Cells : CLASSE : Algorithms/Random");

        send2BestCells();
    }

    /**
     * set the two best cells configuration
     */
    @Override
    public void searchBest2Cells() {
        Log.writeLog(new Date().toString() + " : DEBUT de la recherche des 2 meilleurs cellules. FONCTION : searchBest2Cells : CLASSE : Algorithms/Random");
        if (canSetTwoCells()) {
            boolean bestCell1Found = searchBestCell(true);
            if (bestCell1Found) {
                setBestCell1();
                boolean bestCell2Found = searchBestCell(false);
                if (bestCell2Found) {
                    setBestCell2();
                    send2BestCells();
                } else {
                    tray.setCellToFree(bestX1, bestY1);
                    sendWantToStopGame();
                }
            } else {
                sendWantToStopGame();
            }
        } else {

            sendWantToStopGame();
        }
        Log.writeLog(new Date().toString() + " : FIN de la recherche des 2 meilleurs cellules. FONCTION : searchBest2Cells : CLASSE : Algorithms/Random");
    }

    @Override
    public boolean searchBestCell(boolean forCell1) {
        Log.writeLog(new Date().toString() + " : DEBUT cherche la meilleur cellule . FONCTION : searchBestCell : CLASSE : Algorithms/Algorithm");

        boolean result = false;

        for (int y = 0; y < tray.getDimension(); y++) {
            for (int x = 0; x < tray.getDimension(); x++) {
                if (tray.isFree(x, y) && !tray.getCellIn(x, y).isTested()) {
                    if (tray.canSetOneCell(x, y, player.getColor())) {
                        if (forCell1) {
                            bestX1 = x;
                            bestY1 = y;
                        } else {
                            bestX2 = x;
                            bestY2 = y;
                        }
                        return true;
                    }
                    tray.getCellIn(x, y).setTested(true);
                }
            }
        }
        Log.writeLog(new Date().toString() + " : DEBUT cherche la meilleur cellule . FONCTION : searchBestCell : CLASSE : Algorithms/Algorithm");

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

    @Override
    public boolean searchBestBridge() {
        return false;
    }

}
