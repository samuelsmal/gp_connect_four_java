package test.tree;

import org.junit.Test;
import org.sam.game.Game;
import org.sam.tree.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TreeTest {

    @Test
    public void testEvaluate() throws Exception {
        Tree addTree = new Tree(
                new AddOperationNode(
                        new ConstantNode(2),
                        new ConstantNode(1)));

        assertEquals("Add eval wrong", addTree.evaluate('x', 'o', new Game()), 3);
    }

    @Test
    public void testEvaluate2() throws Exception {
        Game game = new Game();

        game.insertStoneInColumn(0, 'x');

        Tree enemyStoneAtNodeTree = new Tree(
            new EnemyStoneAtNode(
                new ConstantNode(0),
                new ConstantNode(5)
            )
        );

        assertEquals(enemyStoneAtNodeTree.evaluate('o', 'x', game), 1l);
    }

    @Test
    public void testEvaluate3() throws Exception {
        Game game = new Game();

        game.insertStoneInColumn(0, 'x');

        Tree tttttree = new Tree(
                new ConditionalNode(
                        new EnemyStoneAtNode(new ConstantNode(0), new ConstantNode(5)),
                        new ConstantNode(-1),
                        new ConstantNode((5))
                )
        );

        assertEquals(tttttree.evaluate('o', 'x', game), -1l);
    }

    @Test
    public void testEquals() throws Exception {
        Tree aTree = TreeFactory.fullTree();
        Tree copy = new Tree(aTree);

        assertFalse(aTree == copy);

        assertFalse(aTree.getRoot() == copy.getRoot());
        assertEquals(aTree.getRoot(), copy.getRoot());

        assertEquals(copy, aTree);
        assertEquals(aTree, copy);
    }
}