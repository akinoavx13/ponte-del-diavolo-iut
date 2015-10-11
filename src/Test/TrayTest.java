package Test;

import Constant.ColorConstants;
import Game.Tray;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Maxime on 11/10/2015.
 */
public class TrayTest {

    @Test
    public void constructorTest() {
        Tray tray = new Tray(10);
        assertTrue(tray.getDimension() == 10);
        assertTrue(tray.getMatrice().length == 10);
        assertTrue(tray.getMatrice()[1].length == 10);
    }

    @Test
    public void setClearCellTest() {
        Tray tray = new Tray(10);
        tray.setClearCell(0, 0);
        assertTrue(tray.getCellIn(0, 0).getColor() == ColorConstants.CLEAR);
    }

    @Test
    public void setDarkCellTest() {
        Tray tray = new Tray(10);
        tray.setDarkCell(0, 0);
        assertTrue(tray.getCellIn(0, 0).getColor() == ColorConstants.DARK);
    }

}