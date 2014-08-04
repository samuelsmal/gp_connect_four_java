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
        return " (if(" + ifNode.toString() + " >= 0){" + thenNode.toString() + "} else {" + thenNode.toString() + "})";
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
    public long evaluate(char playerColour, char enemyColour, Game game) {
        return ifNode.evaluate(playerColour, enemyColour, game) >= 0 ? thenNode.evaluate(playerColour, enemyColour, game) : elseNode.evaluate(playerColour, enemyColour, game);
    }

    @Override
    public INode getDeepCopy() {
        return new ConditionalNode(ifNode.getDeepCopy(), thenNode.getDeepCopy(), elseNode.getDeepCopy());
    }
}
