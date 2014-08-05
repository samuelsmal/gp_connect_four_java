package org.sam.tree;

/**
 * Created by samuel on 05/08/14.
 */
public interface INodeVisitor {
    void visit(AbstractNode node); // Is never called.
    void visit(BinaryOperationNode node);
    void visit(ConditionalNode node);
    void visit(ConstantNode node);
    void visit(ComputationalNode node);
}
