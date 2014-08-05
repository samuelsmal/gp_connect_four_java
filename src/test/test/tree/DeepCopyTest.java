package test.tree;

import org.junit.Test;
import org.sam.tree.AddOperationNode;
import org.sam.tree.ConstantNode;
import org.sam.tree.SaveDivisionOperationNode;
import org.sam.tree.Tree;

import static org.junit.Assert.*;

/**
 * Created by samuel on 05/08/14.
 */
public class DeepCopyTest {
    @Test
    public void test() throws Exception {
        Tree a = new Tree(new AddOperationNode(new ConstantNode(1), new ConstantNode(2)));
        Tree c = new Tree(a);

        assertFalse(c.getRoot() == a.getRoot());
        assertEquals(c.getRoot(), a.getRoot());
        assertEquals(a, c);
    }
}
