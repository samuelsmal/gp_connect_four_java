package org.sam.tree.BinaryFunctions;

/**
 * Function Node
 * Created by samuel on 27/07/14.
 */
public class MultiplicationOperationNode extends BinaryOperationNode {
    public MultiplicationOperationNode() {
        operationSign = "*";
    }

    @Override
    protected long doOperation(long leftValue, long rightValue) {
        return leftValue * rightValue;
    }
}
