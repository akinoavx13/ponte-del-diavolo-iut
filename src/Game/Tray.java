package Game;

import Constant.ColorConstants;
import Constant.GameConstants;

import java.util.ArrayList;

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
                matrice[i][j] = new Cell(i, j, ColorConstants.FREE);
            }
        }
    }

    /*********
     * METHODS*
     *********/

    public void setClearCell(int x, int y) {
        if (GameConstants.isVerbose()) {
            System.err.println("Case clair posée en [" + x + "][" + y + "]");
        }
        matrice[x][y].setColor(ColorConstants.CLEAR);
    }

    public void setDarkCell(int x, int y) {
        if (GameConstants.isVerbose()) {
            System.err.println("Case foncé posée en [" + x + "][" + y + "]");
        }
        matrice[x][y].setColor(ColorConstants.DARK);
    }

    public void setCellToFree(int x, int y) {
        if (GameConstants.isVerbose()) {
            System.err.println("Case libre posée en [" + x + "][" + y + "]");
        }
        matrice[x][y].setColor(ColorConstants.FREE);
    }

    public void setMatriceUnvisited() {
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                matrice[x][y].setVisited(false);
            }
        }
    }

    public void setMatriceToFree() {
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                setCellToFree(x, y);
            }
        }
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                result += "[" + matrice[j][i].getColor() + "]";
            }
            result += "\n";
        }
        return result;
    }

    /*********
     * GETTERS*
     *********/

    public int getDimension() {
        return dimension;
    }

    public Cell[][] getMatrice() {
        return matrice;
    }

    public Cell getCellIn(int x, int y) {
        return matrice[x][y];
    }

    public boolean isFree(int x, int y) {
        return matrice[x][y].getColor() == ColorConstants.FREE;
    }

    public int getNumberCellFree() {
        int numberCellFree = 0;

        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                if (matrice[x][y].isThisColor(ColorConstants.FREE)) {
                    numberCellFree++;
                }
            }
        }

        return numberCellFree;
    }

    public boolean cellInDiagNotVisited(int x, int y, String color) {
        boolean result = false;

        //above left
        if (x - 1 >= 0 && y - 1 >= 0 && !getCellIn(x - 1, y - 1).isVisited() && getCellIn(x - 1, y - 1).getColor() == color) {
            result = true;
        }

        //above right
        else if (x + 1 < dimension && y - 1 >= 0 && !getCellIn(x + 1, y - 1).isVisited() && getCellIn(x + 1, y - 1).getColor() == color) {
            result = true;
        }

        //below right
        else if (x + 1 < dimension && y + 1 < dimension && !getCellIn(x + 1, y + 1).isVisited() && getCellIn(x + 1, y + 1).getColor() == color) {
            result = true;
        }

        //below right
        else if (x - 1 >= 0 && y + 1 < dimension && !getCellIn(x - 1, y + 1).isVisited() && getCellIn(x - 1, y + 1).getColor() == color) {
            result = true;
        }

        return result;
    }

    public boolean islandInDiagNotVisited(int x, int y, String color) {
        boolean result = false;

        //above left
        if (x - 1 >= 0 && y - 1 >= 0 && !getCellIn(x - 1, y - 1).isVisited() && getCellIn(x - 1, y - 1).getColor() == color) {
            int totalAdjacent = totalCellsAdjacent(x - 1, y - 1, color).size();
            if (totalAdjacent == 4) {
                result = true;
            }
        }

        //above right
        else if (x + 1 < dimension && y - 1 >= 0 && !getCellIn(x + 1, y - 1).isVisited() && getCellIn(x + 1, y - 1).getColor() == color) {
            int totalAdjacent = totalCellsAdjacent(x + 1, y - 1, color).size();
            if (totalAdjacent == 4) {
                result = true;
            }
        }

        //below right
        else if (x + 1 < dimension && y + 1 < dimension && !getCellIn(x + 1, y + 1).isVisited() && getCellIn(x + 1, y + 1).getColor() == color) {
            int totalAdjacent = totalCellsAdjacent(x + 1, y + 1, color).size();
            if (totalAdjacent == 4) {
                result = true;
            }
        }

        //below right
        else if (x - 1 >= 0 && y + 1 < dimension && !getCellIn(x - 1, y + 1).isVisited() && getCellIn(x - 1, y + 1).getColor() == color) {
            int totalAdjacent = totalCellsAdjacent(x - 1, y + 1, color).size();
            if (totalAdjacent == 4) {
                result = true;
            }
        }

        return result;
    }

    /*
    public int totalCellsAdjacent(int x, int y, String color) {
        int totalAdjacent = 0;

        //if there is a cell correspond to the right color
        // and if we didn't check it before
        if (!matrice[x][y].isVisited() && matrice[x][y].isThisColor(color)) {
            totalAdjacent++;
            matrice[x][y].setVisited(true);

            //check above
            if (y - 1 >= 0) {
                totalAdjacent += totalCellsAdjacent(x, y - 1, color);
            }

            //check bellow
            if (y + 1 < dimension) {
                totalAdjacent += totalCellsAdjacent(x, y + 1, color);
            }

            //check left
            if (x - 1 >= 0) {
                totalAdjacent += totalCellsAdjacent(x - 1, y, color);
            }

            //check right
            if (x + 1 < dimension) {
                totalAdjacent += totalCellsAdjacent(x + 1, y, color);
            }
        }
        return totalAdjacent;
    }
    */

    public ArrayList<Cell> totalCellsAdjacent(int x, int y, String color) {
        ArrayList<Cell> totalAdjacent = new ArrayList<>();

        //if there is a cell correspond to the right color
        // and if we didn't check it before
        if (!matrice[x][y].isVisited() && matrice[x][y].isThisColor(color)) {
            matrice[x][y].setVisited(true);
            totalAdjacent.add(matrice[x][y]);

            //check above
            if (y - 1 >= 0) {
                totalAdjacent.addAll(totalCellsAdjacent(x, y - 1, color));
            }

            //check bellow
            if (y + 1 < dimension) {
                totalAdjacent.addAll(totalCellsAdjacent(x, y + 1, color));
            }

            //check left
            if (x - 1 >= 0) {
                totalAdjacent.addAll(totalCellsAdjacent(x - 1, y, color));
            }

            //check right
            if (x + 1 < dimension) {
                totalAdjacent.addAll(totalCellsAdjacent(x + 1, y, color));
            }
        }
        return totalAdjacent;
    }

    /*********
     * SETTERS*
     *********/

}
