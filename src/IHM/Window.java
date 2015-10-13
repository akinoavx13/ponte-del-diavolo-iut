package IHM;

import Game.Tray;

import javax.swing.*;

/**
 * Created by Maxime on 13/10/2015.
 */
public class Window extends JFrame {

    private TrayIHM trayIHM;

    public Window(Tray tray) {
        this.setSize(827, 827);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.trayIHM = new TrayIHM(tray);
        this.setContentPane(trayIHM);

        this.setVisible(true);

        trayIHM.repaint();

    }

}
