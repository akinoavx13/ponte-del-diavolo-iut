package Test;

import Constant.ColorConstants;
import Game.Cell;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Maxime on 11/10/2015.
 */
public class CellTest {

    @Test
    public void constructorTest() {
        Cell cell = new Cell(0, 0, ColorConstants.DARK);
        assertTrue(cell.getColor() == ColorConstants.DARK);
        assertTrue(cell.getX() == 0);
        assertTrue(cell.getY() == 0);
    }

}