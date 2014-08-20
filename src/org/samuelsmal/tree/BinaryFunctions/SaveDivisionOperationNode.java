package org.samuelsmal.tree.BinaryFunctions;

import org.samuelsmal.tree.INode;
import org.samuelsmal.tree.Leaf;

import java.util.List;

/**
 * Functional Node
 * Created by samuel on 27/07/14.
 */
public class SaveDivisionOperationNode extends BinaryOperationNode {
    public SaveDivisionOperationNode() {
        operationSign = "%";
    }

    @Override
    public INode getCopy() {
        return new SaveDivisionOperationNode();
    }

    @Override
    public String print(List<Leaf> children) {
        String rhs = children.get(1).toString();
        return "(" + rhs + " != 0 ? "
                + children.get(0).toString() + " / " + rhs + " : 1)";
    }

    @Override
    protected long doOperation(long leftValue, long rightValue) {
        return (rightValue != 0 ? leftValue / rightValue : 1);
    }
}
