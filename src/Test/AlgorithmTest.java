package Test;

import Algorithms.Random;
import Constant.ColorConstants;
import Constant.GameConstants;
import Game.Tray;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Maxime on 11/10/2015.
 */
public class AlgorithmTest {

    public AlgorithmTest() {
        GameConstants.setVerbose(false);
    }

    @Test
    public void testCountIslandIsolated() throws Exception {
        Tray tray = new Tray(5);
        tray.setClearCell(0, 0);
        tray.setClearCell(0, 1);
        tray.setClearCell(1, 0);
        tray.setClearCell(2, 0);

        tray.setClearCell(1, 2);
        tray.setClearCell(2, 2);

        tray.setClearCell(0, 4);
        tray.setClearCell(1, 4);

        tray.setClearCell(3, 3);
        tray.setClearCell(4, 2);
        tray.setClearCell(4, 3);
        tray.setClearCell(4, 4);

        Random random = new Random(tray, null, null);

        assertTrue(random.countIslandIsolated(ColorConstants.CLEAR) == 2);
    }

    @Test
    public void testCanSetOneCell() {
        Tray tray = new Tray(4);

        tray.setClearCell(0, 0);
        tray.setClearCell(0, 1);
        tray.setClearCell(1, 0);
        tray.setClearCell(1, 1);

        Random random = new Random(tray, null, null);

        assertTrue(!random.canSetOneCell(2, 2, ColorConstants.CLEAR));
        assertTrue(random.canSetOneCell(2, 3, ColorConstants.CLEAR));

        Tray tray2 = new Tray(4);

        tray2.setClearCell(0, 1);

        tray2.setClearCell(1, 2);
        tray2.setClearCell(3, 2);
        tray2.setClearCell(2, 3);

        Random random2 = new Random(tray2, null, null);

        assertTrue(!random2.canSetOneCell(2, 2, ColorConstants.CLEAR));

    }

}