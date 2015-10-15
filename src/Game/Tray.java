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
    private int bridgeNumber;

    private Player player;

    public Tray(int dimension) {
        this.dimension = dimension;
        matrice = new Cell[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                matrice[i][j] = new Cell(i, j, ColorConstants.FREE);
            }
        }

        bridgeNumber = 15;
    }

    /*********
     * METHODS*
     *********/

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

    public boolean canSetOneBridge(Cell cellA, Cell cellB) {
        boolean result = false;

        if (getBridgeNumber() > 0 && player.getColor() == cellA.getColor() && player.getColor() == cellB.getColor()) {
            if (!cellA.isBridge() && !cellB.isBridge()) {
                double distanceBetwwenAandB = distanceBetween2Cells(cellA, cellB);

                int xa = cellA.getX();
                int xb = cellB.getX();

                int ya = cellA.getY();
                int yb = cellB.getY();

                if (distanceBetwwenAandB <= 2 * Math.sqrt(2) && distanceBetwwenAandB > Math.sqrt(2)) {

                    int deltaX = xb - xa;
                    int deltaY = yb - ya;

                    double absDXDY = Math.abs(deltaX) + Math.abs(deltaY);

                    if (absDXDY == 2) {

                        int x = xa + deltaX / 2;
                        int y = ya + deltaY / 2;
                        result = !getCellIn(x, y).isBlocked();

                    } else if (absDXDY == 3) {
                        if (Math.abs(deltaX) == 1) {
                            int x1 = xa;
                            int y1 = ya + deltaY / 2;

                            int x2 = xa + deltaX / Math.abs(deltaX);
                            int y2 = ya + deltaY / 2;
                            result = !getCellIn(x1, y1).isBlocked() && !getCellIn(x2, y2).isBlocked();
                        } else {
                            int x1 = xa + deltaX / 2;
                            int y1 = ya;

                            int x2 = xa + deltaX / 2;
                            int y2 = ya + deltaY / Math.abs(deltaY);
                            result = !getCellIn(x1, y1).isBlocked() && !getCellIn(x2, y2).isBlocked();
                        }
                    } else if (absDXDY == 4) {
                        int x = (xa + xb) / 2;
                        int y = (ya + yb) / 2;
                        result = !getCellIn(x, y).isBlocked();
                    }
                }
            }
        }

        return result;
    }

    public boolean canSetOneCell(int x, int y, String color) {
        boolean result = false;

        setMatriceUnvisited();

        if (isFree(x, y) && !getCellIn(x, y).isBlocked()) {
            if (color == ColorConstants.CLEAR) {
                setClearCell(x, y);
            } else if (color == ColorConstants.DARK) {
                setDarkCell(x, y);
            }

            ArrayList<Cell> totalAdjacent = totalCellsAdjacent(x, y, color);

            if (totalAdjacent.size() < 4) {
                if (!islandInDiagNotVisited(x, y, color)) {
                    result = true;
                }
            } else if (totalAdjacent.size() == 4) {
                result = true;
                for (Cell cell : totalAdjacent) {
                    if (cellInDiagNotVisited(cell.getX(), cell.getY(), color)) {
                        result = false;
                    }
                }
            }
        }

        setCellToFree(x, y);

        return result;
    }

    public int countIslandIsolated(String color) {
        int numberIslandIsolated = 0;

        //init the matrice, cells are not visited
        setMatriceUnvisited();

        for (int i = 0; i < getDimension(); i++) {
            for (int j = 0; j < getDimension(); j++) {

                //count if there are 4 cells adjacent.
                if (totalCellsAdjacent(i, j, color).size() == 4) {
                    numberIslandIsolated++;
                }
            }
        }

        return numberIslandIsolated;
    }

    @Override
    public String toString() {
        String result = "";

        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                result += "[" + matrice[y][x].getColor();
                if (matrice[y][x].isBridge()) {
                    result += " | pont]";
                } else {
                    result += "]";
                }
            }
            result += "\n";
        }
        return result;
    }

    public double distanceBetween2Cells(Cell cellA, Cell cellB) {

        int xb_xa = cellB.getX() - cellA.getX();
        int yb_ya = cellB.getY() - cellA.getY();

        return Math.sqrt(Math.pow(xb_xa, 2) + Math.pow(yb_ya, 2));
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

        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                if (matrice[y][x].isThisColor(ColorConstants.FREE)) {
                    numberCellFree++;
                }
            }
        }

        return numberCellFree;
    }

    public int getBridgeNumber() {
        return bridgeNumber;
    }

    public void setBridgeNumber(int bridgeNumber) {
        this.bridgeNumber = bridgeNumber;
    }

    public int getNumberCellColor(String color) {
        int numberCells = 0;

        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                if (matrice[y][x].isThisColor(color)) {
                    numberCells++;
                }
            }
        }

        return numberCells;
    }

    /*********
     * SETTERS*
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
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                matrice[y][x].setVisited(false);
            }
        }
    }

    public void setMatriceNotTested() {
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                matrice[y][x].setTested(false);
            }
        }
    }

    public void setMatriceToFree() {
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                setCellToFree(x, y);
            }
        }
    }

    public void setBridgeIn(Cell cellA, Cell cellB) {
        if (GameConstants.isVerbose()) {
            System.err.println("Pont posée en [" + cellA.getX() + "][" + cellA.getY() + "] et [" + cellB.getX() + "][" + cellB.getX() + "]");
        }

        int xa = cellA.getX();
        int xb = cellB.getX();

        int ya = cellA.getY();
        int yb = cellB.getY();

        int deltaX = xb - xa;
        int deltaY = yb - ya;

        double absDXDY = Math.abs(deltaX) + Math.abs(deltaY);

        if (absDXDY == 2) {

            int x = xa + deltaX / 2;
            int y = ya + deltaY / 2;
            getCellIn(x, y).setBlocked(true);

        } else if (absDXDY == 3) {

            if (Math.abs(deltaX) == 1) {
                int x1 = xa;
                int y1 = ya + deltaY / 2;

                int x2 = xa + deltaX / Math.abs(deltaX);
                int y2 = ya + deltaY / 2;
                getCellIn(x1, y1).setBlocked(true);
                getCellIn(x2, y2).setBlocked(true);
            } else {
                int x1 = xa + deltaX / 2;
                int y1 = ya;

                int x2 = xa + deltaX / 2;
                int y2 = ya + deltaY / Math.abs(deltaY);
                getCellIn(x1, y1).setBlocked(true);
                getCellIn(x2, y2).setBlocked(true);
            }

        } else if (absDXDY == 4) {
            int x = (xa + xb) / 2;
            int y = (ya + yb) / 2;
            getCellIn(x, y).setBlocked(true);
        }

        new Bridge(cellA, cellB);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
