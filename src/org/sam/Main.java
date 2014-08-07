package org.sam;

import org.sam.genetics.Evolution;
import org.sam.tree.*;
import org.sam.tree.BinaryFunctions.AddOperationNode;
import org.sam.tree.BinaryFunctions.SubtractionOperationNode;
import org.sam.tree.Terminals.ConstantNode;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Tree a = new Tree();

        Leaf aRoot = LeafFactory.addOperationLeaf();
        aRoot.addChild(LeafFactory.constantLeaf(1));
        aRoot.addChild(LeafFactory.constantLeaf(2));

        a.setRoot(aRoot);

        Tree b = new Tree();

        Leaf bRoot = LeafFactory.addOperationLeaf();
        bRoot.addChild(LeafFactory.constantLeaf(3));
        bRoot.addChild(LeafFactory.constantLeaf(4));

        b.setRoot(bRoot);

        System.out.println("Trees before:\n\t a:\n\t\t" + a + "\n\t b:\n\t\t" + b);

        List<Leaf> lhsFlattened = a.flatten();
        List<Leaf> rhsFlattened = b.flatten();

        Leaf lLeaf = lhsFlattened.get(0);
        Leaf rLeaf = rhsFlattened.get(0);

        Leaf tmpLeaf = new Leaf();

        tmpLeaf.setElement(rLeaf.getElement());
        tmpLeaf.setChildren(rLeaf.getChildren());

        rLeaf.setElement(lLeaf.getElement());
        rLeaf.setChildren(lLeaf.getChildren());

        lLeaf.setElement(tmpLeaf.getElement());
        lLeaf.setChildren(tmpLeaf.getChildren());

        System.out.println("Trees after:\n\t a:\n\t\t" + a + "\n\t b:\n\t\t" + b);



    }
}
