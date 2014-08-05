package org.sam.tree;

import org.sam.game.Game;

/**
 * Functional Node
 * Created by samuel on 27/07/14.
 */
public class SubtractionOperationNode extends BinaryOperationNode {
    public SubtractionOperationNode() {
    }

    public SubtractionOperationNode(INode left, INode right) {
        super(left, right);
    }

    public String toString() {
        return "(" + left.toString() + ")-(" + right.toString() + ")";
    }

    @Override
    public long evaluate(char playerColour, char enemyColour, Game game) {
        return left.evaluate(playerColour, enemyColour, game) - right.evaluate(playerColour, enemyColour, game);
    }

    @Override
    public INode getDeepCopy() {
        return new SubtractionOperationNode(left.getDeepCopy(), right.getDeepCopy());
    }
}
