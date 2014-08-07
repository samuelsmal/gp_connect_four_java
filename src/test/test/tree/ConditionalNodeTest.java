package test.tree;

import org.junit.Test;
import org.sam.game.Game;
import org.sam.tree.Leaf;
import org.sam.tree.LeafFactory;
import org.sam.tree.TernaryFunctions.ConditionalNode;
import org.sam.tree.Terminals.ConstantNode;
import org.sam.tree.Tree;

import static org.junit.Assert.assertEquals;

public class ConditionalNodeTest {

    @Test
    public void testEvaluate() throws Exception {
        Tree conditionalTree = new Tree();

        Leaf conLeaf = LeafFactory.conditionalLeaf();

        conLeaf.addChild(LeafFactory.constantLeaf(1));
        conLeaf.addChild(LeafFactory.constantLeaf(2));
        conLeaf.addChild(LeafFactory.constantLeaf(3));

        conditionalTree.setRoot(conLeaf);

        assertEquals(conditionalTree.evaluate('x', 'o', new Game()), 2);
    }
}