package org.sam.tree;

/**
 * Created by samuel on 05/08/14.
 */
abstract class AbstractNode implements INode {
    protected INode parent;

    public AbstractNode() {
    }

    public INode getParent() {
        return parent;
    }

    public void setParent(INode parent) {
        this.parent = parent;
    }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
