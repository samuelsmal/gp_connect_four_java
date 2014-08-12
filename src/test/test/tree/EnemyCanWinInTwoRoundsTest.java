package test.tree;

import org.junit.Test;
import org.sam.game.Game;
import org.sam.tree.Terminals.EnemyCanWinInTwoRounds;

import static org.junit.Assert.*;

public class EnemyCanWinInTwoRoundsTest {

    @Test
    public void testEvaluate() throws Exception {
        Game game = new Game(new char[][]{
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'o', '_', '_', 'o', '_', '_', '_'},
                {'o', '_', 'o', 'x', '_', '_', '_'},
                {'x', 'o', 'o', 'x', 'o', 'o', 'o'}
        });

        EnemyCanWinInTwoRounds node = new EnemyCanWinInTwoRounds();

        assertEquals(1l, node.evaluate('x', 'o', game, null));
        assertEquals(0, node.evaluate('o', 'x', game, null));
    }
}