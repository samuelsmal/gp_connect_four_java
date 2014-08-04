package test.game;

import org.junit.Before;
import org.junit.Test;
import org.sam.game.ColumnFullException;
import org.sam.game.Game;

import static org.junit.Assert.*;

public class GameTest {
    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game(new char[][]{
                {'x', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', 'o'}
        });
    }

    @Test
    public void testGetColourOfStone() throws Exception {
        assertEquals(game.getColourOfStone(0, 0), 'x');
        assertEquals(game.getColourOfStone(6, 5), 'o');
    }

    @Test
    public void testInsertStoneIntoColumn() throws Exception {
        try {
            game.insertStoneInColumn(6, 'x');
        } catch (ColumnFullException e) {
            e.printStackTrace();
        }

        assertEquals(game.getColourOfStone(0, 0), 'x');
        assertEquals(game.getColourOfStone(6, 5), 'o');
        assertEquals(game.getColourOfStone(6, 4), 'x');
        assertEquals(game.getColourOfStone(6, 3), '_');
    }
}