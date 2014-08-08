package org.sam.tree.BinaryFunctions;

import org.sam.tree.INode;

/**
 * Functional Node
 * Created by samuel on 27/07/14.
 */
public class SubtractionOperationNode extends BinaryOperationNode {
    public SubtractionOperationNode() {
        operationSign = "-";
    }

    @Override
    public INode getCopy() {
        return new SubtractionOperationNode();
    }

    @Override
    protected long doOperation(long leftValue, long rightValue) {
        return leftValue - rightValue;
    }
}
