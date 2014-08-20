package test.tree;

import org.junit.Test;
import org.samuelsmal.game.Game;
import org.samuelsmal.tree.Leaf;
import org.samuelsmal.tree.TernaryFunctions.ConditionalNode;
import org.samuelsmal.tree.Terminals.ConstantNode;
import org.samuelsmal.tree.Tree;

import static org.junit.Assert.assertEquals;

public class ConditionalNodeTest {

    @Test
    public void testEvaluate() throws Exception {
        Tree conditionalTree = new Tree();

        Leaf conLeaf =  new Leaf(new ConditionalNode());

        conLeaf.addChild(new Leaf(new ConstantNode(1)));
        conLeaf.addChild(new Leaf(new ConstantNode(2)));
        conLeaf.addChild(new Leaf(new ConstantNode(3)));

        conditionalTree.setRoot(conLeaf);

        assertEquals(conditionalTree.evaluate('x', 'o', new Game()), 2);
    }
}