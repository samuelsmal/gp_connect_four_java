package org.sam.tree;

/**
 * Created by samuel on 05/08/14.
 */
public class TreeFactory {
    public static Tree fullTree() {
        return new Tree(NodeFactory.getRandomNode());
    }

    public static Tree fullTree(long depth) {
        return new Tree(NodeFactory.getRandomNode(depth));
    }
}
