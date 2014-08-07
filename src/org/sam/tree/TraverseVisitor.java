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

    public TraverseVisitor(Tree tree) {
        nodes = new ArrayList<INode>();

        tree.getRoot().accept(this);
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
    public void visit(AbstractNode node) {

    }

    @Override
    public void visit(BinaryOperationNode node) {
        nodes.add(node);

        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    @Override
    public void visit(ConditionalNode node) {
        nodes.add(node);

        node.getIfNode().accept(this);
        node.getThenNode().accept(this);
        node.getElseNode().accept(this);
    }

    @Override
    public void visit(ConstantNode node) {
        nodes.add(node);
    }

    @Override
    public void visit(ComputationalNode node) {
        nodes.add(node);

        node.getX().accept(this);
        node.getY().accept(this);
    }
}
