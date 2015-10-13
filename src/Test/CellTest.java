package Test;

import Constant.ColorConstants;
import Constant.GameConstants;
import Game.Cell;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Maxime on 11/10/2015.
 */
public class CellTest {

    public CellTest() {
        GameConstants.setVerbose(false);
    }

    @Test
    public void testConstructor() {
        Cell cell = new Cell(0, 0, ColorConstants.DARK);
        assertTrue(cell.getColor() == ColorConstants.DARK);
        assertTrue(cell.getX() == 0);
        assertTrue(cell.getY() == 0);
    }

    @Test
    public void testGetColor() throws Exception {
        Cell cell = new Cell(0, 0, ColorConstants.DARK);
        Cell cell2 = new Cell(0, 0, ColorConstants.CLEAR);

        assertTrue(cell.getColor() == ColorConstants.DARK);
        assertTrue(cell2.getColor() == ColorConstants.CLEAR);
    }

    @Test
    public void testGetX() throws Exception {
        Cell cell = new Cell(0, 0, ColorConstants.DARK);
        assertTrue(cell.getX() == 0);
    }

    @Test
    public void testGetY() throws Exception {
        Cell cell = new Cell(0, 0, ColorConstants.DARK);
        assertTrue(cell.getY() == 0);
    }

    @Test
    public void testIsThisColor() throws Exception {
        Cell cell = new Cell(0, 0, ColorConstants.DARK);
        Cell cell2 = new Cell(0, 0, ColorConstants.CLEAR);

        assertTrue(cell.isThisColor(ColorConstants.DARK));
        assertTrue(cell2.isThisColor(ColorConstants.CLEAR));
    }

    @Test
    public void testIsVisited() throws Exception {
        Cell cell = new Cell(0, 0, ColorConstants.DARK);
        assertTrue(!cell.isVisited());
        cell.setVisited(true);
        assertTrue(cell.isVisited());
    }

    @Test
    public void testSetColor() throws Exception {
        Cell cell = new Cell(0, 0, ColorConstants.DARK);
        cell.setColor(ColorConstants.CLEAR);
        assertTrue(cell.isThisColor(ColorConstants.CLEAR));
    }

    @Test
    public void testSetVisited() throws Exception {
        Cell cell = new Cell(0, 0, ColorConstants.DARK);
        cell.setVisited(true);
        assertTrue(cell.isVisited());
    }

    @Test
    public void testIsBlocked() throws Exception {
        Cell cell = new Cell(0, 0, ColorConstants.DARK);
        cell.setBlocked(true);
        assertTrue(cell.isBlocked());
    }
}