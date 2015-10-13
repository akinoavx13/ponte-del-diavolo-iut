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

    @Test
    public void testCanSetOneBridge() {
        Tray tray = new Tray(5);

        tray.setClearCell(2, 2);

        tray.setClearCell(2, 0);
        tray.setClearCell(0, 2);
        tray.setClearCell(4, 2);
        tray.setClearCell(2, 4);

        tray.setClearCell(3, 3);
        tray.setClearCell(2, 3);
        tray.setClearCell(1, 3);
        tray.setClearCell(1, 2);
        tray.setClearCell(1, 1);
        tray.setClearCell(2, 1);
        tray.setClearCell(3, 1);
        tray.setClearCell(3, 2);

        Random random = new Random(tray, null, null);

        assertTrue(random.canSetOneBridge(tray.getCellIn(2, 2), tray.getCellIn(2, 0)));
        assertTrue(random.canSetOneBridge(tray.getCellIn(2, 2), tray.getCellIn(4, 2)));
        assertTrue(random.canSetOneBridge(tray.getCellIn(2, 2), tray.getCellIn(2, 4)));
        assertTrue(random.canSetOneBridge(tray.getCellIn(2, 2), tray.getCellIn(0, 2)));

        assertTrue(!random.canSetOneBridge(tray.getCellIn(2, 2), tray.getCellIn(3, 3)));
        assertTrue(!random.canSetOneBridge(tray.getCellIn(2, 2), tray.getCellIn(2, 3)));
        assertTrue(!random.canSetOneBridge(tray.getCellIn(2, 2), tray.getCellIn(1, 3)));
        assertTrue(!random.canSetOneBridge(tray.getCellIn(2, 2), tray.getCellIn(1, 2)));
        assertTrue(!random.canSetOneBridge(tray.getCellIn(2, 2), tray.getCellIn(1, 1)));
        assertTrue(!random.canSetOneBridge(tray.getCellIn(2, 2), tray.getCellIn(2, 1)));
        assertTrue(!random.canSetOneBridge(tray.getCellIn(2, 2), tray.getCellIn(3, 1)));
        assertTrue(!random.canSetOneBridge(tray.getCellIn(2, 2), tray.getCellIn(3, 2)));

        Tray tray2 = new Tray(8);

        tray2.setClearCell(2, 2);

        tray2.setClearCell(0, 0);
        tray2.setClearCell(4, 0);
        tray2.setClearCell(4, 4);
        tray2.setClearCell(0, 4);

        tray2.setClearCell(7, 0);
        tray2.setClearCell(7, 7);
        tray2.setClearCell(0, 7);
        tray2.setClearCell(5, 2);
        tray2.setClearCell(2, 5);

        Random random2 = new Random(tray2, null, null);

        assertTrue(random2.canSetOneBridge(tray2.getCellIn(2, 2), tray2.getCellIn(0, 0)));
        assertTrue(random2.canSetOneBridge(tray2.getCellIn(2, 2), tray2.getCellIn(4, 0)));
        assertTrue(random2.canSetOneBridge(tray2.getCellIn(2, 2), tray2.getCellIn(4, 4)));
        assertTrue(random2.canSetOneBridge(tray2.getCellIn(2, 2), tray2.getCellIn(0, 4)));

        assertTrue(!random2.canSetOneBridge(tray2.getCellIn(2, 2), tray2.getCellIn(7, 0)));
        assertTrue(!random2.canSetOneBridge(tray2.getCellIn(2, 2), tray2.getCellIn(7, 7)));
        assertTrue(!random2.canSetOneBridge(tray2.getCellIn(2, 2), tray2.getCellIn(0, 7)));
        assertTrue(!random2.canSetOneBridge(tray2.getCellIn(2, 2), tray2.getCellIn(5, 2)));
        assertTrue(!random2.canSetOneBridge(tray2.getCellIn(2, 2), tray2.getCellIn(2, 5)));

        Tray tray3 = new Tray(5);

        tray3.setClearCell(2, 2);

        tray3.setClearCell(3, 0);
        tray3.setClearCell(4, 1);
        tray3.setClearCell(4, 3);
        tray3.setClearCell(3, 4);
        tray3.setClearCell(1, 4);
        tray3.setClearCell(0, 3);
        tray3.setClearCell(0, 1);
        tray3.setClearCell(1, 0);

        Random random3 = new Random(tray3, null, null);


        assertTrue(random3.canSetOneBridge(tray3.getCellIn(2, 2), tray3.getCellIn(3, 0)));
        assertTrue(random3.canSetOneBridge(tray3.getCellIn(2, 2), tray3.getCellIn(4, 1)));
        assertTrue(random3.canSetOneBridge(tray3.getCellIn(2, 2), tray3.getCellIn(4, 3)));
        assertTrue(random3.canSetOneBridge(tray3.getCellIn(2, 2), tray3.getCellIn(3, 4)));
        assertTrue(random3.canSetOneBridge(tray3.getCellIn(2, 2), tray3.getCellIn(1, 4)));
        assertTrue(random3.canSetOneBridge(tray3.getCellIn(2, 2), tray3.getCellIn(0, 3)));
        assertTrue(random3.canSetOneBridge(tray3.getCellIn(2, 2), tray3.getCellIn(0, 1)));
        assertTrue(random3.canSetOneBridge(tray3.getCellIn(2, 2), tray3.getCellIn(1, 0)));

        Tray tray4 = new Tray(5);

        tray4.setClearCell(0, 2);
        tray4.setClearCell(1, 1);
        tray4.setClearCell(1, 3);
        tray4.setClearCell(2, 2);

        Random random4 = new Random(tray4, null, null);

        assertTrue(random4.canSetOneBridge(tray4.getCellIn(0, 2), tray4.getCellIn(2, 2)));

        tray4.setBridgeIn(tray4.getCellIn(0, 2), tray4.getCellIn(2, 2));

        assertTrue(!random4.canSetOneBridge(tray4.getCellIn(1, 1), tray4.getCellIn(1, 3)));


        Tray tray5 = new Tray(5);

        tray5.setClearCell(2, 2);
        tray5.setClearCell(0, 3);
        tray5.setClearCell(2, 3);
        tray5.setClearCell(0, 4);

        Random random5 = new Random(tray5, null, null);

        assertTrue(random5.canSetOneBridge(tray5.getCellIn(0, 4), tray5.getCellIn(2, 2)));

        tray5.setBridgeIn(tray5.getCellIn(0, 4), tray5.getCellIn(2, 2));

        assertTrue(!random5.canSetOneBridge(tray5.getCellIn(0, 3), tray5.getCellIn(2, 3)));


        Tray tray6 = new Tray(5);

        tray6.setClearCell(0, 3);
        tray6.setClearCell(2, 3);
        tray6.setClearCell(0, 4);
        tray6.setClearCell(2, 4);

        Random random6 = new Random(tray6, null, null);

        assertTrue(random6.canSetOneBridge(tray6.getCellIn(0, 4), tray6.getCellIn(2, 3)));

        tray6.setBridgeIn(tray6.getCellIn(0, 4), tray6.getCellIn(2, 3));

        assertTrue(!random6.canSetOneBridge(tray6.getCellIn(0, 3), tray6.getCellIn(2, 4)));

    }

}