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
    public void testTotalAdjacent() throws Exception {
        Tray tray = new Tray(5);

        tray.setClearCell(0, 0);
        tray.setClearCell(0, 1);
        tray.setClearCell(1, 0);
        tray.setClearCell(2, 0);

        Random random = new Random(tray, null, null);

        //init the matrice, cells are not visited
        for (int i = 0; i < tray.getMatrice().length; i++) {
            for (int j = 0; j < tray.getMatrice()[i].length; j++) {
                tray.getMatrice()[i][j].setVisited(false);
            }
        }

        assertTrue(random.totalAdjacent(0, 0, ColorConstants.CLEAR) == 4);
    }
}