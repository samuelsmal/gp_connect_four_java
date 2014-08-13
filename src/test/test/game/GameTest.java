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

    @Test
    public void testColourOfWinner() throws Exception {
        assertEquals(game.getWinnerColour(), Game.EMPTY_STONE_COLOUR);

        game = new Game(new char[][]{
            {'x', '_', '_', '_', '_', '_', '_'},
            {'_', '_', '_', '_', '_', '_', '_'},
            {'_', '_', '_', '_', '_', '_', '_'},
            {'_', '_', '_', '_', '_', '_', '_'},
            {'_', '_', '_', '_', '_', '_', '_'},
            {'_', '_', '_', '_', 'x', 'x', 'x'}
        });

        game.setRunning(true);
        game.checkMove(game.insertStoneInColumn(3, 'x'));

        assertFalse(game.getRunning());

        game = new Game(new char[][]{
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', 'x', '_', '_', '_'},
                {'_', '_', '_', 'x', '_', '_', '_'},
                {'_', '_', '_', 'x', 'o', 'o', 'o'}
        });

        game.setRunning(true);

        game.checkMove(game.insertStoneInColumn(3, 'x'));

        assertFalse(game.getRunning());

        game = new Game(new char[][]{
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', 'x', 'o', '_', '_', '_'},
                {'_', 'x', 'o', 'x', '_', '_', '_'},
                {'x', 'o', '_', 'x', 'o', 'o', 'o'}
        });
        game.setRunning(true);
        game.checkMove(game.insertStoneInColumn(3, 'x'));
        assertFalse(game.getRunning());

        game = new Game(new char[][]{
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', 'x', '_', '_', '_'},
                {'_', '_', '_', 'o', '_', '_', '_'},
                {'_', 'x', 'o', 'x', '_', '_', '_'},
                {'x', 'o', 'o', '_', 'o', 'o', 'o'}
        });

        game.setRunning(true);
        game.checkMove(game.insertStoneInColumn(2, 'x'));
        assertFalse(game.getRunning());

        game = new Game(new char[][]{
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', 'x', '_', '_', '_'},
                {'_', '_', '_', 'o', '_', '_', '_'},
                {'_', '_', 'o', 'x', '_', '_', '_'},
                {'_', '_', 'o', '_', 'o', 'o', 'o'}
        });

        game.setRunning(true);
        game.checkMove(game.insertStoneInColumn(1, 'o'));
        assertFalse(game.getRunning());
    }
}