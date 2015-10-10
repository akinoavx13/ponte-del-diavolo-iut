package Algorithms;

import Game.Tray;
import Network.Client;

/**
 * Created by Maxime on 10/10/2015.
 */
public abstract class Algorithm {

    protected Tray tray;
    protected Client client;

    public Tray getTray() {
        return tray;
    }

    public abstract void init2Cells();
}
