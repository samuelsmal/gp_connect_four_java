package test.tree;

import org.junit.Test;
import org.sam.tree.*;
import org.sam.tree.BinaryFunctions.AddOperationNode;
import org.sam.tree.Terminals.ConstantNode;
import org.sam.tree.Terminals.EnemyStoneAtNode;
import org.sam.tree.TernaryFunctions.ConditionalNode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by samuel on 05/08/14.
 */
public class NodeTest {
/*
    @Test
    public void testEquals() throws Exception {
        INode aNode = NodeFactory.getRandomNode(1);
        INode copyNode= aNode.getDeepCopy();

        assertFalse(aNode == copyNode);
        assertEquals(copyNode, aNode);
    }

    @Test
    public void testBinaryNodeEquals() throws Exception {
        INode node = new AddOperationNode(new ConstantNode(1), new ConstantNode(2));
        INode copy = node.getDeepCopy();

        assertFalse(node == copy);
        assertEquals(copy, node);
    }

    @Test
    public void testConditionalNode() throws Exception {
        INode node = new ConditionalNode(new ConstantNode(1), new ConstantNode(2), new ConstantNode(3));
        INode copy = node.getDeepCopy();

        assertFalse(node == copy);
        assertEquals(copy, node);
    }

    @Test
    public void testComputationalNode() throws Exception {
        INode node = new EnemyStoneAtNode(new ConstantNode(1), new ConstantNode(2));
        INode copy = node.getDeepCopy();

        assertFalse(node == copy);
        assertEquals(copy, node);
    }

    @Test
    public void testConstantNode() throws Exception {
        INode node = new ConstantNode(2);
        INode copy = node.getDeepCopy();

        assertFalse(node == copy);
        assertEquals(copy, node);
    }*/
}
