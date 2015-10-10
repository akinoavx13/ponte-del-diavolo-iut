package Game;

import Constant.ColorConstants;

/**
 * Created by Maxime on 10/10/2015.
 */
public class Tray {

    private Cell[][] matrice;
    private int dimension;

    public Tray(int dimension) {
        this.dimension = dimension;
        matrice = new Cell[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                matrice[i][j] = new Cell(i, j, "0");
            }
        }
    }

    public String toString() {
        String result = "";

        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                result += "[" + matrice[i][j].getColor() + "]";
            }
            result += "\n";
        }
        return result;
    }

    public void setClearCell(int x, int y) {
        matrice[x][y].setColor(ColorConstants.CLEAR);
    }

    public void setDarkCell(int x, int y) {
        matrice[x][y].setColor(ColorConstants.DARK);
    }

    public int getDimension() {
        return dimension;
    }
}
