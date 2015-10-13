package IHM;

import Constant.ColorConstants;
import Game.Tray;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Maxime on 13/10/2015.
 */
public class TrayIHM extends JPanel {

    private Tray tray;

    public TrayIHM(Tray tray) {
        this.tray = tray;
    }

    public void paintComponent(Graphics graphics) {

        int offsetA = 800 / tray.getDimension();
        graphics.setColor(Color.black);

        for (int x = 0; x <= tray.getDimension(); x++) {
            graphics.fillRect(x * offsetA, 0, 5, 800);
        }

        for (int y = 0; y <= tray.getDimension(); y++) {
            graphics.fillRect(0, y * offsetA, 800, 5);
        }

        for (int y = 0; y < tray.getDimension(); y++) {
            for (int x = 0; x < tray.getDimension(); x++) {
                if (tray.getCellIn(x, y).getColor() == ColorConstants.CLEAR) {
                    graphics.setColor(Color.cyan);
                    graphics.fillRect(x * offsetA + 5, y * offsetA + 5, offsetA - 5, offsetA - 5);

                } else if (tray.getCellIn(x, y).getColor() == ColorConstants.DARK) {
                    graphics.setColor(Color.BLUE);
                    graphics.fillRect(x * offsetA + 5, y * offsetA + 5, offsetA - 5, offsetA - 5);
                }
                if (tray.getCellIn(x, y).isBridge()) {
                    graphics.setColor(Color.RED);
                    int xA = tray.getCellIn(x, y).getBridge().getCellStart().getX();
                    int yA = tray.getCellIn(x, y).getBridge().getCellStart().getY();

                    int xB = tray.getCellIn(x, y).getBridge().getCellEnd().getX();
                    int yB = tray.getCellIn(x, y).getBridge().getCellEnd().getY();

                    graphics.drawLine(xA * offsetA, yA * offsetA, xB * offsetA, yB * offsetA);

                }
            }
        }
        repaint();
    }

}
