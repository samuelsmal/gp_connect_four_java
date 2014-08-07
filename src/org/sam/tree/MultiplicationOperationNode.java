package org.sam.tree;

import org.sam.game.Game;

/**
 * Function Node
 * Created by samuel on 27/07/14.
 */
public class MultiplicationOperationNode extends BinaryOperationNode {
    public MultiplicationOperationNode() {
        operationSign = "*";
    }

    public MultiplicationOperationNode(INode left, INode right) {
        super(left, right);
        operationSign = "*";
    }

    @Override
    protected long doOperation(long leftValue, long rightValue) {
        return leftValue * rightValue;
    }
}
