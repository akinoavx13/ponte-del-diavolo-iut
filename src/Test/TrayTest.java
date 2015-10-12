package Test;

import Constant.ColorConstants;
import Constant.GameConstants;
import Game.Cell;
import Game.Tray;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Maxime on 11/10/2015.
 */
public class TrayTest {

    public TrayTest() {
        GameConstants.setVerbose(false);
    }

    @Test
    public void constructorTest() {
        Tray tray = new Tray(10);
        assertTrue(tray.getDimension() == 10);
        assertTrue(tray.getMatrice().length == 10);
        assertTrue(tray.getMatrice()[1].length == 10);
    }

    @Test
    public void testSetClearCell() throws Exception {
        Tray tray = new Tray(10);
        tray.setClearCell(1, 3);
        assertTrue(tray.getCellIn(1, 3).getColor() == ColorConstants.CLEAR);
        assertTrue(tray.getCellIn(1, 3).getX() == 1);
        assertTrue(tray.getCellIn(1, 3).getY() == 3);
    }

    @Test
    public void testSetDarkCell() throws Exception {
        Tray tray = new Tray(10);
        tray.setDarkCell(3, 1);
        assertTrue(tray.getCellIn(3, 1).getColor() == ColorConstants.DARK);
        assertTrue(tray.getCellIn(3, 1).getX() == 3);
        assertTrue(tray.getCellIn(3, 1).getY() == 1);
    }

    @Test
    public void testGetDimension() throws Exception {
        Tray tray = new Tray(10);
        assertTrue(tray.getDimension() == 10);
    }

    @Test
    public void testGetMatrice() throws Exception {
        Tray tray = new Tray(10);
        assertTrue(tray.getMatrice() instanceof Cell[][]);
    }

    @Test
    public void testGetCellIn() throws Exception {
        Tray tray = new Tray(10);
        assertTrue(tray.getCellIn(1, 3) instanceof Cell);
    }

    @Test
    public void testIsFree() throws Exception {
        Tray tray = new Tray(10);
        tray.setClearCell(0, 0);

        assertTrue(!tray.isFree(0, 0));

        assertTrue(tray.isFree(4, 4));
    }

    @Test
    public void testSetCellTo0() {
        Tray tray = new Tray(10);
        tray.setClearCell(0, 0);

        assertTrue(tray.getCellIn(0, 0).getColor() == ColorConstants.CLEAR);
        tray.setCellTo0(0, 0);
        assertTrue(tray.getCellIn(0, 0).getColor() == ColorConstants.NULL);
    }

    @Test
    public void testSetMatriceUnvisited() {
        Tray tray = new Tray(10);
        tray.getCellIn(0, 0).setVisited(true);
        tray.getCellIn(1, 0).setVisited(true);
        tray.getCellIn(2, 0).setVisited(true);
        tray.getCellIn(3, 0).setVisited(true);

        assertTrue(tray.getCellIn(0, 0).isVisited());
        assertTrue(tray.getCellIn(1, 0).isVisited());
        assertTrue(tray.getCellIn(2, 0).isVisited());
        assertTrue(tray.getCellIn(3, 0).isVisited());

        tray.setMatriceUnvisited();

        assertTrue(!tray.getCellIn(0, 0).isVisited());
        assertTrue(!tray.getCellIn(1, 0).isVisited());
        assertTrue(!tray.getCellIn(2, 0).isVisited());
        assertTrue(!tray.getCellIn(3, 0).isVisited());

    }

    @Test
    public void testSetMatriceTo0() {
        Tray tray = new Tray(10);
        tray.setClearCell(0, 0);
        tray.setClearCell(1, 0);
        tray.setClearCell(2, 0);
        tray.setClearCell(3, 0);

        assertTrue(tray.getCellIn(0, 0).getColor() == ColorConstants.CLEAR);
        assertTrue(tray.getCellIn(1, 0).getColor() == ColorConstants.CLEAR);
        assertTrue(tray.getCellIn(2, 0).getColor() == ColorConstants.CLEAR);
        assertTrue(tray.getCellIn(3, 0).getColor() == ColorConstants.CLEAR);

        tray.setMatriceTo0();

        assertTrue(tray.getCellIn(0, 0).getColor() == ColorConstants.NULL);
        assertTrue(tray.getCellIn(1, 0).getColor() == ColorConstants.NULL);
        assertTrue(tray.getCellIn(2, 0).getColor() == ColorConstants.NULL);
        assertTrue(tray.getCellIn(3, 0).getColor() == ColorConstants.NULL);

    }

}