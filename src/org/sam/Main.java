package org.sam;

import org.sam.game.Game;
import org.sam.tree.*;

public class Main {

    public static void main(String[] args) {
        Tree tree1 = new Tree(new AddOperationNode(new ConstantNode(1), new ConstantNode(2)));

        Tree tree2 = new Tree(new SubtractionOperationNode(new ConstantNode(3), new ConstantNode(4)));

        System.out.print("Trees before:\n");
        System.out.println("\ttree1: \n\t\t" + tree1);
        System.out.println("\ttree2: \n\t\t" + tree2);

        TraverseVisitor lhsVisitor = new TraverseVisitor(tree1);
        TraverseVisitor rhsVisitor = new TraverseVisitor(tree2);

        INode lNode = lhsVisitor.getNodes().get(lhsVisitor.getNodes().size() - 1);
        INode rNode = rhsVisitor.getNodes().get(rhsVisitor.getNodes().size() - 1);
        INode tmp = rNode;

        rNode = lNode;
        lNode = tmp;

        System.out.println("Selected nodes:\n\t" + lNode + "\n\t" + rNode);

        System.out.print("Trees after:\n");
        System.out.println("\ttree1: \n\t\t" + tree1);
        System.out.println("\ttree2: \n\t\t" + tree2);
    }
}
