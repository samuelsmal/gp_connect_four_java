package org.sam.genetics;

import org.sam.tree.INode;
import org.sam.tree.Tree;

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
        /*
        TraverseVisitor lhsVisitor = new TraverseVisitor(lhs);
        TraverseVisitor rhsVisitor = new TraverseVisitor(rhs);

        INode lNode = lhsVisitor.getRandomNode();
        INode rNode = rhsVisitor.getRandomNode();
        INode tmp = rNode;

        rNode = lNode;
        lNode = tmp;*/
    }

    /**
     * Chooses a random node in the tree and creates a new one using a the <code>NodeFactory.getRandomNode()</code>
     * method.
     * @param tree
     */
    public static void mutation(Tree tree) {
        /*
        TraverseVisitor treeVisitor = new TraverseVisitor(tree);

        INode node = treeVisitor.getRandomNode();

        node = NodeFactory.getRandomNode();
        */
    }
}
