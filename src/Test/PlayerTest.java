package Test;

import Algorithms.Random;
import Game.Player;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Maxime on 10/10/2015.
 */
public class PlayerTest {

    @Test
    public void constructorTest() {
        Player player = new Player(true);
        assertTrue(player.isFirstPlayer());
    }

    @Test
    public void setRandomAlgorithme() {
        Player player = new Player(true);
        player.setRandomAlgorithm(null, null);
        assertTrue(player.getAlgorithm() instanceof Random);
    }

}
