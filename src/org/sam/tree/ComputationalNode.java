package org.sam.tree;

/**
 * Created by samuel on 27/07/14.
 */
abstract class ComputationalNode implements INode {
    /*
     * Could be changed to INode.
     */
    protected ConstantNode x;
    protected ConstantNode y;

    protected ComputationalNode() {}

    protected ComputationalNode(ConstantNode x, ConstantNode y) {
        this.x = x;
        this.y = y;
    }

    public ConstantNode getX() {
        return x;
    }

    public void setX(ConstantNode x) {
        this.x = x;
    }

    public ConstantNode getY() {
        return y;
    }

    public void setY(ConstantNode y) {
        this.y = y;
    }
}
