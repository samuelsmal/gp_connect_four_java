package org.sam.tree;

import org.sam.game.Game;

/**
 * Created by samuel on 27/07/14.
 */
public class ConditionalNode implements INode {
    private INode ifNode;
    private INode thenNode;
    private INode elseNode;

    public ConditionalNode() {
    }

    public ConditionalNode(INode ifNode, INode thenNode, INode elseNode) {
        this.ifNode = ifNode;
        this.thenNode = thenNode;
        this.elseNode = elseNode;
    }

    public String toString() {
        return " (" + ifNode.toString() + " >= 0 ? " + thenNode.toString() + " : " + thenNode.toString() + ")";
    }

    public INode getIfNode() {
        return ifNode;
    }

    public void setIfNode(INode ifNode) {
        this.ifNode = ifNode;
    }

    public INode getThenNode() {
        return thenNode;
    }

    public void setThenNode(INode thenNode) {
        this.thenNode = thenNode;
    }

    public INode getElseNode() {
        return elseNode;
    }

    public void setElseNode(INode elseNode) {
        this.elseNode = elseNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConditionalNode that = (ConditionalNode) o;

        if (elseNode != null ? !elseNode.equals(that.elseNode) : that.elseNode != null) return false;
        if (ifNode != null ? !ifNode.equals(that.ifNode) : that.ifNode != null) return false;
        if (thenNode != null ? !thenNode.equals(that.thenNode) : that.thenNode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ifNode != null ? ifNode.hashCode() : 0;
        result = 31 * result + (thenNode != null ? thenNode.hashCode() : 0);
        result = 31 * result + (elseNode != null ? elseNode.hashCode() : 0);
        return result;
    }

    @Override
    public long evaluate(char playerColour, char enemyColour, Game game) {
        return ifNode.evaluate(playerColour, enemyColour, game) >= 0 ? thenNode.evaluate(playerColour, enemyColour, game) : elseNode.evaluate(playerColour, enemyColour, game);
    }

    @Override
    public INode getDeepCopy() {
        return new ConditionalNode(ifNode.getDeepCopy(), thenNode.getDeepCopy(), elseNode.getDeepCopy());
    }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
