package test.tree;

import org.junit.Test;
import org.sam.game.Game;
import org.sam.tree.AddOperationNode;
import org.sam.tree.ConstantNode;
import org.sam.tree.Tree;

import static org.junit.Assert.assertEquals;

public class TreeTest {

    @Test
    public void testEvaluate() throws Exception {
        Tree addTree = new Tree(
                new AddOperationNode(
                        new ConstantNode(2),
                        new ConstantNode(1)));

        assertEquals("Add eval wrong", addTree.evaluate('x', 'o', new Game()), 3);
    }
}