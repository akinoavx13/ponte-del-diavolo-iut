package Game;

/**
 * Created by Maxime on 12/10/2015.
 */
public class Bridge {

    private Cell cellStart;
    private Cell cellEnd;

    public Bridge(Cell cellStart, Cell cellEnd) {
        this.cellStart = cellStart;
        this.cellStart.setBridge(this);

        this.cellEnd = cellEnd;
        this.cellEnd.setBridge(this);
    }

    public Cell getCellStart() {
        return cellStart;
    }

    public Cell getCellEnd() {
        return cellEnd;
    }

}
