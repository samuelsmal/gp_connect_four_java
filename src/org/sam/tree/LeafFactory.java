package org.sam.tree;

import org.sam.Random.GPRandom;
import org.sam.tree.BinaryFunctions.AddOperationNode;
import org.sam.tree.BinaryFunctions.MultiplicationOperationNode;
import org.sam.tree.BinaryFunctions.SaveDivisionOperationNode;
import org.sam.tree.BinaryFunctions.SubtractionOperationNode;
import org.sam.tree.Terminals.*;
import org.sam.tree.TernaryFunctions.ConditionalNode;

/**
 * Created by samuel on 05/08/14.
 */
public class LeafFactory {
    private static final GPRandom rand = GPRandom.INSTANCE;

    public static Leaf randomFullLeaf() {
        return randomFullLeaf(0);
    }

    public static Leaf randomFullLeaf(long depth) {
        Leaf leaf = new Leaf();

        if (depth > 0) {
            // Functions
            int functionRandomNumber = rand.nextInt(5); // 4 functions

            // Each function node is at least binary.
            leaf.addChild(randomFullLeaf(depth - 1));
            leaf.addChild(randomFullLeaf(depth - 1));

            if (functionRandomNumber <= 0) {
                leaf.setElement(new AddOperationNode());
            } else if (functionRandomNumber <= 1) {
                leaf.setElement(new SubtractionOperationNode());
            } else if (functionRandomNumber <= 2) {
                leaf.setElement(new MultiplicationOperationNode());
            } /*else if (functionRandomNumber <= 3) {
                leaf.setElement(new SaveDivisionOperationNode());
            }*/ else {
                // Ternary element
                leaf.setElement(new ConditionalNode());
                leaf.addChild(randomFullLeaf(depth - 1));
            }
        } else {
            // Terminals
            int terminalRandomNumber = rand.nextInt(7); // 0 <= rand < n

            if (terminalRandomNumber <= 0) {
                leaf.setElement(new ConstantNode((long)rand.nextInt(9) - 5)); // Random settings...
            } else if (terminalRandomNumber <= 1) {
                leaf.setElement(new EnemyStoneAtNode(rand.nextInt(7), rand.nextInt(6)));
            } else if (terminalRandomNumber <= 2){
                leaf.setElement(new PlayerStoneAtNode(rand.nextInt(7), rand.nextInt(6)));
            } else if (terminalRandomNumber <= 3) {
                leaf.setElement(new EnemyCanWinInOneRound());
            } else if (terminalRandomNumber <= 4) {
                leaf.setElement(new PlayerCanWinInOneRound());
            } else if (terminalRandomNumber <= 5) {
                leaf.setElement(new EnemyCanWinInTwoRounds());
            } else {
                leaf.setElement(new PlayerCanWinInTwoRounds());
            }
        }

        return leaf;
    }

    public static Leaf randomHalfLeaf(long maxDepth) {
        Leaf leaf = new Leaf();

        if (maxDepth > 1) {
            // Functions
            int functionRandomNumber = rand.nextInt(5); // 0 <= rand < n // 4 functions

            // Each function node is at least binary.
            leaf.addChild(randomHalfLeaf(rand.nextInt((int) maxDepth - 1)));
            leaf.addChild(randomHalfLeaf(rand.nextInt((int) maxDepth - 1)));

            if (functionRandomNumber <= 0) {
                leaf.setElement(new AddOperationNode());
            } else if (functionRandomNumber <= 1) {
                leaf.setElement(new SubtractionOperationNode());
            } else if (functionRandomNumber <= 2) {
                leaf.setElement(new MultiplicationOperationNode());
            } /*else if (functionRandomNumber <= 3) {
                leaf.setElement(new SaveDivisionOperationNode());
            }*/ else {
                // Ternary element
                leaf.setElement(new ConditionalNode());
                leaf.addChild(randomHalfLeaf(rand.nextInt((int) maxDepth - 1)));
            }
        } else {
            // Terminals
            int terminalRandomNumber = rand.nextInt(3); // 0 <= rand < n

            if (terminalRandomNumber <= 0) {
                leaf.setElement(new ConstantNode((long)rand.nextInt(9) - 5)); // Random settings...
            } else if (terminalRandomNumber <= 1) {
                leaf.setElement(new EnemyStoneAtNode(rand.nextInt(7), rand.nextInt(6)));
            } else /*if (terminalRandomNumber <= 2)*/{
                leaf.setElement(new PlayerStoneAtNode(rand.nextInt(7), rand.nextInt(6)));
            }/* else if (terminalRandomNumber <= 4) {
                leaf.setElement(new EnemyCanWinInOneRound());
            } else {
                leaf.setElement(new PlayerCanWinInOneRound());
            }*/
        }

        return leaf;
    }

    /**
     *
     * @return A leaf without any children but with a ConditionalNode as the element.
     */
    public static Leaf conditionalLeaf() {
        return new Leaf(new ConditionalNode());
    }

    public static Leaf addOperationLeaf() {
        return new Leaf(new AddOperationNode());
    }

    public static Leaf constantLeaf(long constant) {
        return new Leaf(new ConstantNode(constant));
    }


}
