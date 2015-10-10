package Algorithms;

import Game.Tray;
import Network.Client;

/**
 * Created by Maxime on 10/10/2015.
 */
public class Random extends Algorithm {

    public Random(Tray tray, Client client) {
        super();
        this.tray = tray;
        this.client = client;
    }

    public void init2Cells() {
        int x1 = (int) (Math.random() * tray.getDimension());
        int y1 = (int) (Math.random() * tray.getDimension());
        int x2 = (int) (Math.random() * tray.getDimension());
        int y2 = (int) (Math.random() * tray.getDimension());

        tray.setClearCell(x1, y1);
        tray.setClearCell(x2, y2);

        client.sendCellPos(x1, y1, x2, y2);
    }

}
