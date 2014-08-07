package org.sam.genetics;

import org.sam.tree.INode;
import org.sam.tree.Leaf;
import org.sam.tree.Tree;

import java.util.List;
import java.util.Random;

/**
 * Provides evolutionary functionality:
 * <ul>
 *     <li>cross-over</li>
 *     <li>mutation</li>
 * </ul>
 * Created by samuel on 05/08/14.
 */
public class Evolution {
    private static final Random rand = new Random();

    /**
     * Traverses each given tree, chooses a random node in each and swaps them
     * with their hole subtree.
     * @param lhs
     * @param rhs
     */
    public static void crossOver(Tree lhs, Tree rhs) {
        List<Leaf> lhsFlattened = lhs.flatten();
        List<Leaf> rhsFlattened = lhs.flatten();

        Leaf lLeaf = lhsFlattened.get(rand.nextInt(lhsFlattened.size()));
        Leaf rLeaf = rhsFlattened.get(rand.nextInt(rhsFlattened.size()));

        Leaf tmpLeaf = new Leaf();

        tmpLeaf.setElement(rLeaf.getElement());
        tmpLeaf.setChildren(rLeaf.getChildren());

        rLeaf.setElement(lLeaf.getElement());
        rLeaf.setChildren(lLeaf.getChildren());

        lLeaf.setElement(tmpLeaf.getElement());
        lLeaf.setChildren(tmpLeaf.getChildren());
    }

    /**
     * Chooses a random node in the tree and creates a new one using a the <code>NodeFactory.getRandomNode()</code>
     * method.
     * @param tree
     */
    public static void mutate(Tree tree) {
        List<Leaf> treeFlattened = tree.flatten();

        pointMutation(treeFlattened.get(rand.nextInt(treeFlattened.size())));

    }

    private static void pointMutation(Leaf leaf) {
        // TODO: fill stub
    }

    private static void subTreeMutation(Leaf leaf) {
        // TODO: fill stub
    }
}
