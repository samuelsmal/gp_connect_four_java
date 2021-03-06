package org.samuelsmal.tree.BinaryFunctions;

import org.samuelsmal.tree.INode;

/**
 * Function Node
 * Created by samuel on 27/07/14.
 */
public class MultiplicationOperationNode extends BinaryOperationNode {
    public MultiplicationOperationNode() {
        operationSign = "*";
    }

    @Override
    public INode getCopy() {
        return new MultiplicationOperationNode();
    }

    @Override
    protected long doOperation(long leftValue, long rightValue) {
        return leftValue * rightValue;
    }
}
