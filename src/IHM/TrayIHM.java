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

        graphics.drawString("Ponts : ", 850, 50);
        graphics.drawString(String.valueOf(tray.getBridgeNumber()), 930, 50);

        graphics.drawString("Case vide : ", 850, 70);
        graphics.drawString(String.valueOf(tray.getNumberCellFree()), 930, 70);

        graphics.drawString("Case clair : ", 850, 90);
        graphics.drawString(String.valueOf(tray.getNumberCellColor(ColorConstants.CLEAR)), 930, 90);

        graphics.drawString("Case foncé : ", 850, 110);
        graphics.drawString(String.valueOf(tray.getNumberCellColor(ColorConstants.DARK)), 930, 110);

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
                if (tray.getCellIn(x, y).isBlocked()) {
                    graphics.setColor(Color.YELLOW);
                    graphics.fillRect(x * offsetA + 10, y * offsetA + 10, offsetA - 15, offsetA - 15);
                }
                if (tray.getCellIn(x, y).isTested()) {
                    graphics.setColor(Color.GREEN);
                    graphics.fillRect(x * offsetA + 10, y * offsetA + 10, offsetA - 15, offsetA - 15);
                }
                if (tray.getCellIn(x, y).isVisited()) {
                    graphics.setColor(Color.GRAY);
                    graphics.fillRect(x * offsetA + 10, y * offsetA + 10, offsetA - 15, offsetA - 15);
                }
            }
        }
        repaint();
    }

}
