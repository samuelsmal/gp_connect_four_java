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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComputationalNode that = (ComputationalNode) o;

        if (x != null ? !x.equals(that.x) : that.x != null) return false;
        if (y != null ? !y.equals(that.y) : that.y != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
