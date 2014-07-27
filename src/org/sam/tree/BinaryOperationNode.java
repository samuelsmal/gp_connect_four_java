package org.sam.tree;

/**
 * Created by samuel on 27/07/14.
 */
abstract class BinaryOperationNode implements INode {
    protected INode left;
    protected INode right;

    protected BinaryOperationNode() {}

    protected BinaryOperationNode(INode left, INode right) {
        this.left = left;
        this.right = right;
    }

    public INode getLeft() {
        return left;
    }

    public void setLeft(INode left) {
        this.left = left;
    }

    public INode getRight() {
        return right;
    }

    public void setRight(INode right) {
        this.right = right;
    }
}
