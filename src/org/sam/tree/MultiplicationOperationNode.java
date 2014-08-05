package org.sam.tree;

import org.sam.game.Game;

/**
 * Function Node
 * Created by samuel on 27/07/14.
 */
public class MultiplicationOperationNode extends BinaryOperationNode {
    public MultiplicationOperationNode() {
    }

    public MultiplicationOperationNode(INode left, INode right) {
        super(left, right);
    }

    public String toString() {
        return "(" + left.toString() + ")*(" + right.toString() + ")";
    }

    @Override
    public long evaluate(char playerColour, char enemyColour, Game game) {
        return left.evaluate(playerColour, enemyColour, game) * right.evaluate(playerColour, enemyColour, game);
    }

    @Override
    public INode getDeepCopy() {
        return new MultiplicationOperationNode(left.getDeepCopy(), right.getDeepCopy());
    }
}
