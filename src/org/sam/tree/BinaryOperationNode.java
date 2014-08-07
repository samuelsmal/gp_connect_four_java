package org.sam.tree;

import org.sam.game.Game;

/**
 * Created by samuel on 27/07/14.
 */
abstract class BinaryOperationNode implements INode {
    protected INode left;
    protected INode right;

    protected String operationSign;

    protected BinaryOperationNode() {}

    protected BinaryOperationNode(INode left, INode right) {
        this.left = left;
        this.right = right;
    }

    protected abstract long doOperation(long leftValue, long rightValue);

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
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public long evaluate(char playerColour, char enemyColour, Game game) {
        return doOperation(left.evaluate(playerColour, enemyColour, game), right.evaluate(playerColour, enemyColour, game));
    }

    @Override
    public INode getDeepCopy() {
        BinaryOperationNode copy = null;

        try {
            copy = getClass().newInstance();
            copy.left = left.getDeepCopy();
            copy.right = right.getDeepCopy();
        } catch (InstantiationException | IllegalAccessException pEx) {
            throw new RuntimeException(String.format("Could not instantiate %s. Is the default constructor implemented? (ex=%s)", getClass().getSimpleName(), pEx.getMessage()), pEx);
        }

        return copy;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + operationSign + right.toString() + ")";
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
