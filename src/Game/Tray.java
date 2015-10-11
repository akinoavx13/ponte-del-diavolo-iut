package Game;

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

    @Override
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
        System.err.println("Case clair posée en [" + x + "][" + y + "]");
        matrice[x][y].setColor("c");
    }

    public void setDarkCell(int x, int y) {
        System.err.println("Case foncé posée en [" + x + "][" + y + "]");
        matrice[x][y].setColor("d");
    }

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
        return matrice[x][y].getColor() == "0";
    }

}
