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
        assertTrue(tray.isFree(4, 4));
    }
}