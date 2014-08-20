package org.sam.genetics;

import org.sam.Random.GPRandom;
import org.sam.tree.BinaryFunctions.AddOperationNode;
import org.sam.tree.BinaryFunctions.MultiplicationOperationNode;
import org.sam.tree.BinaryFunctions.SaveDivisionOperationNode;
import org.sam.tree.BinaryFunctions.SubtractionOperationNode;
import org.sam.tree.Leaf;
import org.sam.tree.LeafFactory;
import org.sam.tree.TernaryFunctions.ConditionalNode;
import org.sam.tree.Tree;

import java.util.List;
import java.util.Random;

/**
 * Provides evolutionary functionality:
 * <ul>
 *     <li>cross-over</li>
 *     <li>mutation</li>
 * </ul>
 * Created by samuel on 05/08/14.
 */
public class Genetics {
    private static final GPRandom rand = GPRandom.INSTANCE;

    /**
     * Traverses each given tree, chooses a random node in each and swaps them
     * with their hole subtree.
     * @param lhs
     * @param rhs
     */
    public static void crossOver(Tree lhs, Tree rhs) {
        List<Leaf> lhsFlattened = lhs.flatten();
        List<Leaf> rhsFlattened = rhs.flatten();

        // Selection of the element-to-switch.
        // The selection process could be improved. See issues.
        /*
        int element = rand.nextInt(Math.min(lhsFlattened.size(), rhsFlattened.size()));

        Leaf lLeaf = lhsFlattened.get(element);
        Leaf rLeaf = rhsFlattened.get(element);
        */

        Leaf lLeaf = lhsFlattened.get(rand.nextInt(lhsFlattened.size()));
        Leaf rLeaf = rhsFlattened.get(rand.nextInt(rhsFlattened.size()));

        Leaf tmpLeaf = new Leaf();

        tmpLeaf.setElement(rLeaf.getElement());
        tmpLeaf.setChildren(rLeaf.getChildren());

        rLeaf.setElement(lLeaf.getElement());
        rLeaf.setChildren(lLeaf.getChildren());

        lLeaf.setElement(tmpLeaf.getElement());
        lLeaf.setChildren(tmpLeaf.getChildren());

        //lhs.setTitle("(c " + lhs.getTitle() + " " + rhs.getTitle() + ")");
        //rhs.setTitle("(c " + rhs.getTitle() + " " + lhs.getTitle() + ")");

        lhs.increaseGeneticCount();
        rhs.increaseGeneticCount();
    }

    /**
     * Chooses a random node in the tree and creates a new one using a the <code>NodeFactory.getRandomNode()</code>
     * method.
     * @param tree
     */
    public static void mutate(Tree tree) {
        tree.increaseGeneticCount();
        List<Leaf> treeFlattened = tree.flatten();

        Leaf toMutate = treeFlattened.get(rand.nextInt(treeFlattened.size()));

        if (rand.nextInt(10) <= 8) {
            //tree.setTitle("(PM " + tree.getTitle() + ")");
            pointMutation(toMutate);
        } else {
            //tree.setTitle("(STM " + tree.getTitle() + ")");
            subTreeMutation(toMutate);
        }
    }

    private static void pointMutation(Leaf leaf) {
        int functionRandomNumber = rand.nextInt(5); // 4 functions

        if (functionRandomNumber <= 3) {
            switch (leaf.getChildren().size()) {
                case 0:
                    leaf.addChild(LeafFactory.randomFullLeaf());
                    leaf.addChild(LeafFactory.randomFullLeaf());
                    break;
                case 1:
                    leaf.addChild(LeafFactory.randomFullLeaf());
                    break;
                case 2:
                    break;
                default:
                    while(leaf.getChildren().size() > 2) {
                        leaf.getChildren().remove(rand.nextInt(leaf.getChildren().size()));
                    }
                    break;
            }
        } else {
            while(leaf.getChildren().size() < 3) {
                if (leaf.getChildren().size() == 0) {
                    leaf.addChild(LeafFactory.randomFullLeaf());
                } else {
                    leaf.getChildren().add(rand.nextInt(leaf.getChildren().size()), LeafFactory.randomFullLeaf());
                }
            }
        }

        if (functionRandomNumber <= 0) {
            leaf.setElement(new AddOperationNode());
        } else if (functionRandomNumber <= 1) {
            leaf.setElement(new SubtractionOperationNode());
        } else if (functionRandomNumber <= 2) {
            leaf.setElement(new MultiplicationOperationNode());
        } else if (functionRandomNumber <= 3) {
            leaf.setElement(new SaveDivisionOperationNode());
        } else {
            leaf.setElement(new ConditionalNode());
        }
    }

    private static void subTreeMutation(Leaf leaf) {
        Leaf newLeaf = LeafFactory.randomFullLeaf();

        leaf.setChildren(newLeaf.getChildren());
        leaf.setElement(newLeaf.getElement());
    }
}
