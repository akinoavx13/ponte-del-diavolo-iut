package Test;

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

}
