package org.sam.tree.BinaryFunctions;

/**
 * Functional Node
 * Created by samuel on 27/07/14.
 */
public class SubtractionOperationNode extends BinaryOperationNode {
    public SubtractionOperationNode() {
        operationSign = "-";
    }

    @Override
    protected long doOperation(long leftValue, long rightValue) {
        return leftValue - rightValue;
    }
}
