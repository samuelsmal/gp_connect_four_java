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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryOperationNode that = (BinaryOperationNode) o;

        if (left != null ? !left.equals(that.left) : that.left != null) return false;
        if (right != null ? !right.equals(that.right) : that.right != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }
}
