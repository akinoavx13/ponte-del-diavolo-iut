package Test;

import Algorithms.Random;
import Constant.ColorConstants;
import Game.Tray;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Maxime on 11/10/2015.
 */
public class AlgorithmTest {

    @Test
    public void countIslandIsolatedTest() {
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

}
