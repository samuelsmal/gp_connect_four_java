package org.sam.tree;

/**
 * Created by samuel on 31/07/14.
 */
public class FullTree extends Tree {
    public FullTree() {
        root = initFully(2);
    }

    public FullTree(long depth) {
        root = initFully(depth);
    }

    /**
     * Recursive method to init a tree.
     * The idea behind the if-else-clauses is to provide a custom distribution of functions.
     * @param depth of the tree.
     * @return
     */
    private INode initFully(long depth) {
        return NodeFactory.getRandomNode(depth);
    }
}
