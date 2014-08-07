package org.sam.tree;

import org.sam.game.Game;

/**
 * Functional Node
 * Created by samuel on 27/07/14.
 */
public class SaveDivisionOperationNode extends BinaryOperationNode {
    public SaveDivisionOperationNode() {
        operationSign = "%";
    }
    public SaveDivisionOperationNode(INode left, INode right) {
        super(left, right);
        operationSign = "%";
    }

    @Override
    public String toString() {
        return "(" + right.toString() + " > 0 ? " + left.toString() + " / " + right.toString() + " : 1)";
    }

    @Override
    protected long doOperation(long leftValue, long rightValue) {
        return (rightValue > 0 ? leftValue / rightValue : 1);
    }
}
