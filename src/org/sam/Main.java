package org.sam;

import org.sam.game.Game;
import org.sam.tree.AddOperationNode;
import org.sam.tree.ConstantNode;
import org.sam.tree.FullTree;
import org.sam.tree.Tree;

public class Main {

    public static void main(String[] args) {
        Tree blub = new FullTree(4);

        System.out.println(blub);


        System.out.println(1 + (2 == 12 ? 3 : 0));
    }
}
