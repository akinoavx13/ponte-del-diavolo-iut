package Test;

import Algorithms.Random;
import Constant.ColorConstants;
import Constant.GameConstants;
import Game.Player;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Maxime on 10/10/2015.
 */
public class PlayerTest {

    public PlayerTest() {
        GameConstants.setVerbose(false);
    }

    @Test
    public void testConstructor() {
        Player player = new Player(true);
        assertTrue(player.isFirstPlayer());
    }

    @Test
    public void testGetColor() throws Exception {
        Player player = new Player(true);

        assertTrue(player.getColor() == ColorConstants.CLEAR);
    }

    @Test
    public void testIsFirstPlayer() throws Exception {
        Player player = new Player(true);

        assertTrue(player.isFirstPlayer());
    }

    @Test
    public void testGetAlgorithm() throws Exception {
        Player player = new Player(true);
        player.setRandomAlgorithm(null, null);
        assertTrue(player.getAlgorithm() instanceof Random);
    }

    @Test
    public void testGetTurnNumber() throws Exception {
        Player player = new Player(true);
        player.setTurnNumber(1);
        assertTrue(player.getTurnNumber() == 1);
    }

    @Test
    public void testSetColor() throws Exception {
        Player player = new Player(true);
        player.setColor(ColorConstants.DARK);
        assertTrue(player.getColor() == ColorConstants.DARK);

        player.setColor(ColorConstants.CLEAR);
        assertTrue(player.getColor() == ColorConstants.CLEAR);
    }

    @Test
    public void testSetRandomAlgorithm() throws Exception {
        Player player = new Player(true);
        player.setRandomAlgorithm(null, null);
        assertTrue(player.getAlgorithm() instanceof Random);
    }

    @Test
    public void testSetTurnNumber() throws Exception {
        Player player = new Player(true);
        player.setTurnNumber(1);
        assertTrue(player.getTurnNumber() == 1);
    }

}
