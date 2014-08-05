package test.tree;

import org.junit.Test;
import org.sam.tree.INode;
import org.sam.tree.TraverseVisitor;
import org.sam.tree.Tree;
import org.sam.tree.TreeFactory;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by samuel on 05/08/14.
 */
public class CrossoverTest {

    @Test
    public void coTest() {
        Tree a = TreeFactory.fullTree();
        Tree b = TreeFactory.fullTree();

        TraverseVisitor crossOverVisitorA = new TraverseVisitor();
        TraverseVisitor crossOverVisitorB = new TraverseVisitor();

        a.getRoot().visit(crossOverVisitorA);
        b.getRoot().visit(crossOverVisitorB);

        INode aNode = crossOverVisitorA.getRandomNode();
        INode aaNode = aNode;
        INode bNode = crossOverVisitorB.getRandomNode();
        INode bbNode = bNode;
        INode tmpNode = bNode;

        bNode = aNode;
        aNode = tmpNode;

        assertFalse(aNode == aaNode);
        assertTrue(bNode == aaNode);
        assertFalse(bNode == bbNode);
        assertTrue(aNode == bbNode);

        crossOverVisitorA.setNodes(new ArrayList<INode>());
        crossOverVisitorB.setNodes(new ArrayList<INode>());

        a.getRoot().visit(crossOverVisitorA);
        b.getRoot().visit(crossOverVisitorB);

        assertFalse(crossOverVisitorA.getNodes().contains(aNode));
        assertTrue(crossOverVisitorA.getNodes().contains(bNode));
        assertFalse(crossOverVisitorB.getNodes().contains(bNode));
        assertTrue(crossOverVisitorB.getNodes().contains(aNode));
    }
}
