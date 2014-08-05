package org.sam.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by samuel on 05/08/14.
 */
public class TraverseVisitor implements INodeVisitor {
    private ArrayList<INode> nodes;
    private static final Random rand = new Random();

    public TraverseVisitor() {
        nodes = new ArrayList<INode>();
    }

    public TraverseVisitor(ArrayList<INode> nodes) {
        this.nodes = nodes;
    }

    public INode getRandomNode() {
        // 0 (inclusive) and the specified value (exclusive)
        return nodes.get(rand.nextInt(nodes.size()));
    }

    public List<INode> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<INode> nodes) {
        this.nodes = nodes;
    }

    @Override
    public void visit(BinaryOperationNode node) {
        nodes.add(node);

        node.getLeft().visit(this);
        node.getRight().visit(this);
    }

    @Override
    public void visit(ConditionalNode node) {
        nodes.add(node);

        node.getIfNode().visit(this);
        node.getThenNode().visit(this);
        node.getElseNode().visit(this);
    }

    @Override
    public void visit(ConstantNode node) {
        nodes.add(node);
    }

    @Override
    public void visit(ComputationalNode node) {
        nodes.add(node);

        node.getX().visit(this);
        node.getY().visit(this);
    }
}
