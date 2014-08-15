package org.sam.tree.BinaryFunctions;

import org.sam.game.Game;
import org.sam.tree.INode;
import org.sam.tree.Leaf;

import java.util.List;

/**
 * Created by samuel on 27/07/14.
 */
abstract class BinaryOperationNode implements INode {
    protected String operationSign;

    protected BinaryOperationNode() {}

    protected abstract long doOperation(long leftValue, long rightValue);

    @Override
    public long evaluate(char playerColour, char enemyColour, Game game, List<Leaf> children) {
        return doOperation(children.get(0).evaluate(playerColour, enemyColour, game), children.get(1).evaluate(playerColour, enemyColour, game));
    }

    @Override
    public String print(List<Leaf> children) {
        return "(" + children.get(0).toString() + " " + operationSign + " " + children.get(1).toString() + ")";
    }
}
