package test.tree;

import org.junit.Test;
import org.sam.game.Game;
import org.sam.tree.ConditionalNode;
import org.sam.tree.ConstantNode;
import org.sam.tree.Tree;

import static org.junit.Assert.assertEquals;

public class ConditionalNodeTest {

    @Test
    public void testEvaluate() throws Exception {
        Tree conditionalTree = new Tree(
                new ConditionalNode(
                        new ConstantNode(1),
                        new ConstantNode(2),
                        new ConstantNode(3)));

        assertEquals(conditionalTree.evaluate('x', 'o', new Game()), 2);
    }
}