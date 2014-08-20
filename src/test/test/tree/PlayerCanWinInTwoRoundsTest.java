package test.tree;

import org.junit.Test;
import org.samuelsmal.game.Game;
import org.samuelsmal.tree.Terminals.PlayerCanWinInTwoRounds;

import static org.junit.Assert.*;

public class PlayerCanWinInTwoRoundsTest {

    @Test
    public void testEvaluate() throws Exception {
        Game game = new Game(new char[][]{
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'o', '_', '_', 'x', '_', '_', '_'},
                {'o', '_', '_', 'o', '_', '_', '_'},
                {'o', 'x', 'o', 'x', '_', '_', '_'},
                {'x', 'o', 'o', 'x', 'o', 'o', 'o'}
        });

        PlayerCanWinInTwoRounds node = new PlayerCanWinInTwoRounds();

        assertEquals(-1l, node.evaluate('o', 'x', game, null));
        assertEquals(-1l, node.evaluate('x', 'o', game, null));
    }

    @Test
    public void testEvaluate2() throws Exception {
        Game game = new Game(new char[][]{
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'o', '_', '_', '_', '_', '_', '_'},
                {'o', '_', '_', '_', '_', '_', '_'},
                {'o', '_', '_', '_', '_', '_', '_'},
                {'x', '_', '_', '_', '_', '_', '_'}
        });

        PlayerCanWinInTwoRounds node = new PlayerCanWinInTwoRounds();

        assertEquals(1l, node.evaluate('o', 'x', game, null));
    }
}