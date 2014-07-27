package org.sam.tree;

import org.sam.game.Game;

/**
 * Created by samuel on 27/07/14.
 */
public class AddOperationNode extends BinaryOperationNode {
    public AddOperationNode() {
    }

    public AddOperationNode(INode left, INode right) {
        super(left, right);
    }

    @Override
    public long evaluate(char playerColour, char enemyColour, Game game) {
        return left.evaluate(playerColour, enemyColour, game) + right.evaluate(playerColour, enemyColour, game);
    }
}
