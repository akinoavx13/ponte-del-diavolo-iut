package Test;

import Algorithms.Random;
import Constant.ColorConstants;
import Constant.GameConstants;
import Game.Player;
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
        Player player = new Player(true);

        Random random = new Random(tray, null, player);
        tray.setClearCell(0, 0);
        tray.setClearCell(0, 1);
        assertTrue(random.canSetTwoCells());
    }

    @Test
    public void testSearchFirstBestCell() {
        Tray tray = new Tray(4);
        Player player = new Player(true);

        Random random = new Random(tray, null, player);

        for (int i = 0; i < 1000; i++) {
            tray.setMatriceToFree();

            tray.setClearCell(0, 0);
            tray.setClearCell(1, 0);
            tray.setClearCell(0, 1);
            tray.setClearCell(0, 2);

            random.searchBestCell(true);
            random.searchBestCell(false);

            assertTrue(tray.getCellIn(2, 0).getColor() != ColorConstants.CLEAR);
            assertTrue(tray.getCellIn(1, 1).getColor() != ColorConstants.CLEAR);
            assertTrue(tray.getCellIn(1, 2).getColor() != ColorConstants.CLEAR);
            assertTrue(tray.getCellIn(0, 3).getColor() != ColorConstants.CLEAR);
        }
    }

    @Test
    public void testSearchSecondBestCell() {
        Tray tray = new Tray(4);
        Player player = new Player(false);

        Random random = new Random(tray, null, player);

        for (int i = 0; i < 1000; i++) {
            tray.setMatriceToFree();

            tray.setDarkCell(0, 0);
            tray.setDarkCell(1, 0);
            tray.setDarkCell(0, 1);
            tray.setDarkCell(0, 2);

            random.searchBestCell(true);
            random.searchBestCell(false);

            assertTrue(tray.getCellIn(2, 0).getColor() != ColorConstants.DARK);
            assertTrue(tray.getCellIn(1, 1).getColor() != ColorConstants.DARK);
            assertTrue(tray.getCellIn(1, 2).getColor() != ColorConstants.DARK);
            assertTrue(tray.getCellIn(0, 3).getColor() != ColorConstants.DARK);
        }
    }

}