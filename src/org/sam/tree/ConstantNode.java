package org.sam.tree;

import org.sam.game.Game;

/**
 * Terminal Node
 * Created by samuel on 27/07/14.
 */
public class ConstantNode implements INode {
    /*
     * Should be in [0,6].
     */
    private long constant;

    public ConstantNode() {
    }

    public ConstantNode(long constant) {
        this.constant = constant;
    }

    public String toString() {
        return Long.toString(constant);
    }

    public long getConstant() {
        return constant;
    }

    public void setConstant(long constant) {
        this.constant = constant;
    }

    @Override
    public long evaluate(char playerColour, char enemyColour, Game game) {
        return constant;
    }

    @Override
    public INode getDeepCopy() {
        return new ConstantNode(constant);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConstantNode that = (ConstantNode) o;

        if (constant != that.constant) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (constant ^ (constant >>> 32));
    }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
