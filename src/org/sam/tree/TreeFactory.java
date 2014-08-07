package org.sam.tree;

/**
 * Created by samuel on 05/08/14.
 */
public class TreeFactory {
    public static Tree fullTree() {
        return new Tree(LeafFactory.randomFullLeaf());
    }

    public static Tree fullTree(long depth) {
        return new Tree(LeafFactory.randomFullLeaf(depth));
    }
}
