package Test;

import Algorithms.Random;
import Constant.GameConstants;
import Game.Tray;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Maxime on 11/10/2015.
 */
public class RandomTest {

    public RandomTest() {
        GameConstants.setVerbose(false);
    }

    @Test
    public void testCanISet2Cells() throws Exception {
        Tray tray = new Tray(2);
        Random random = new Random(tray, null, null);
        tray.setClearCell(0, 0);
        tray.setClearCell(0, 1);
        assertTrue(random.canISet2Cells());
    }
}