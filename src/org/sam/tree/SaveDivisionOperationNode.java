package org.sam.tree;

import org.sam.game.Game;

/**
 * Functional Node
 * Created by samuel on 27/07/14.
 */
public class SaveDivisionOperationNode extends BinaryOperationNode {
    public SaveDivisionOperationNode() {
    }
    public SaveDivisionOperationNode(INode left, INode right) {
        super(left, right);
    }

    public String toString() {
        return "((" + left.toString() + ")%(" + right.toString() + "))";
    }

    @Override
    public long evaluate(char playerColour, char enemyColour, Game game) {
        long right_evaluation = right.evaluate(playerColour, enemyColour, game);

        if (right_evaluation != 0) {
            return left.evaluate(playerColour, enemyColour, game) / right_evaluation;
        } else {
            return 1;
        }
    }
}
