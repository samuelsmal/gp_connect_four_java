package org.sam;

import org.sam.tree.*;
import org.sam.tree.BinaryFunctions.AddOperationNode;
import org.sam.tree.BinaryFunctions.SubtractionOperationNode;
import org.sam.tree.Terminals.ConstantNode;

public class Main {

    public static void main(String[] args) {
        Tree conditionalTree = new Tree();

        Leaf conLeaf = LeafFactory.conditionalLeaf();

        conLeaf.addChild(LeafFactory.constantLeaf(1));
        conLeaf.addChild(LeafFactory.constantLeaf(2));
        conLeaf.addChild(LeafFactory.constantLeaf(3));

        conditionalTree.setRoot(conLeaf);

        System.out.println(conditionalTree);
    }
}
