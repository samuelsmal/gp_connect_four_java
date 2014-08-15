package test.tree;

import org.junit.Test;
import org.sam.game.Game;
import org.sam.tree.Terminals.PlayerCanWinInOneRound;

import static org.junit.Assert.assertEquals;

public class PlayerCanWinInOneRoundTest {

    @Test
    public void testEvaluate() throws Exception {
        Game game = new Game(new char[][]{
                {'_', '_', '_', '_', '_', '_', '_'},
                {'o', '_', '_', '_', '_', '_', '_'},
                {'o', '_', '_', 'x', '_', '_', '_'},
                {'o', '_', '_', 'o', '_', '_', '_'},
                {'o', 'x', 'o', 'x', '_', '_', '_'},
                {'x', 'o', 'o', 'x', 'o', 'o', 'o'}
        });

        PlayerCanWinInOneRound node = new PlayerCanWinInOneRound();

        assertEquals(1l, node.evaluate('o', 'x', game, null));
        assertEquals(0l, node.evaluate('x', 'o', game, null));
    }
}