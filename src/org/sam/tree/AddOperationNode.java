package org.sam.tree;

import org.sam.game.Game;

/**
 * Functional Node
 * Created by samuel on 27/07/14.
 */
public class AddOperationNode extends BinaryOperationNode {
    public AddOperationNode() {
        operationSign = "+";
    }

    public AddOperationNode(INode left, INode right) {
        super(left, right);
        operationSign = "+";
    }

    @Override
    protected long doOperation(long leftValue, long rightValue) {
        return leftValue + rightValue;
    }
}
