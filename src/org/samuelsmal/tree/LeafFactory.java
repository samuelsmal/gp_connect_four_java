package org.samuelsmal.tree;

import org.samuelsmal.Random.GPRandom;
import org.samuelsmal.tree.BinaryFunctions.AddOperationNode;
import org.samuelsmal.tree.BinaryFunctions.MultiplicationOperationNode;
import org.samuelsmal.tree.BinaryFunctions.SubtractionOperationNode;
import org.samuelsmal.tree.Terminals.*;
import org.samuelsmal.tree.TernaryFunctions.ConditionalNode;

/**
 * Created by samuel on 05/08/14.
 */
public class LeafFactory {
    private static final GPRandom rand = GPRandom.INSTANCE;
    private static boolean called = false; // internal use

    public static boolean onlyConditional = false;
    public static boolean noHigherLevelFunctions = false;

    public static Leaf randomFullLeaf() {
        return randomFullLeaf(2);
    }

    public static Leaf randomFullLeaf(long depth) {
        if (!called) {
            called = true;

            printSettings();
        }

        Leaf leaf = new Leaf();

        if (depth > 0) {
            // Functions

            // Each function node is at least binary.
            leaf.addChild(randomFullLeaf(depth - 1));
            leaf.addChild(randomFullLeaf(depth - 1));

            if (onlyConditional) {
                leaf.setElement(new ConditionalNode());
                leaf.addChild(randomFullLeaf(depth - 1));
            } else {
                int functionRandomNumber = 3; // rand.nextInt(4);

                if (functionRandomNumber <= 0) {
                    leaf.setElement(new AddOperationNode());
                } else if (functionRandomNumber <= 1) {
                    leaf.setElement(new SubtractionOperationNode());
                } else if (functionRandomNumber <= 2) {
                    leaf.setElement(new MultiplicationOperationNode());
                } /*else if (functionRandomNumber <= 3) {
                leaf.setElement(new SaveDivisionOperationNode());
                } */ else {
                    // Ternary element
                    leaf.setElement(new ConditionalNode());
                    leaf.addChild(randomFullLeaf(depth - 1));
                }
            }
        } else {
            // Terminals

            if (noHigherLevelFunctions) {
                int terminalRandomNumber = rand.nextInt(3); // 0 <= rand < n

                if (terminalRandomNumber <= 0) {
                    leaf.setElement(new ConstantNode((long)rand.nextInt(9) - 5)); // Random settings...
                } else if (terminalRandomNumber <= 1) {
                    leaf.setElement(new EnemyStoneAtNode(rand.nextInt(7), rand.nextInt(6)));
                } else {
                    leaf.setElement(new PlayerStoneAtNode(rand.nextInt(7), rand.nextInt(6)));
                }
            } else {
                int terminalRandomNumber = rand.nextInt(3); // 0 <= rand < n

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
        }

        return leaf;
    }

    public static Leaf randomHalfLeaf(long maxDepth) {
        if (!called) {
            called = true;

            printSettings();
        }

        Leaf leaf = new Leaf();

        if (maxDepth > 1) {
            // Functions

            // Each function node is at least binary.
            leaf.addChild(randomHalfLeaf(rand.nextInt((int) maxDepth)));
            leaf.addChild(randomHalfLeaf(rand.nextInt((int) maxDepth)));

            if (onlyConditional) {
                leaf.addChild(randomHalfLeaf(rand.nextInt((int) maxDepth)));
                leaf.setElement(new ConditionalNode());
            } else {
                int functionRandomNumber = 3; // rand.nextInt(4); // 0 <= rand < n // 4 functions

                if (functionRandomNumber <= 0) {
                    leaf.setElement(new AddOperationNode());
                } else if (functionRandomNumber <= 1) {
                    leaf.setElement(new SubtractionOperationNode());
                } else if (functionRandomNumber <= 2) {
                    leaf.setElement(new MultiplicationOperationNode());
                } /*else if (functionRandomNumber <= 3) {
                leaf.setElement(new SaveDivisionOperationNode());
                } */else {
                    // Ternary element
                    leaf.setElement(new ConditionalNode());
                    leaf.addChild(randomHalfLeaf(rand.nextInt((int) maxDepth)));
                }
            }
        } else {
            // Terminals

            if (noHigherLevelFunctions) {
                int terminalRandomNumber = rand.nextInt(3); // 0 <= rand < n

                if (terminalRandomNumber <= 0) {
                    leaf.setElement(new ConstantNode((long)rand.nextInt(9) - 5)); // Random settings...
                } else if (terminalRandomNumber <= 1) {
                    leaf.setElement(new EnemyStoneAtNode(rand.nextInt(7), rand.nextInt(6)));
                } else {
                    leaf.setElement(new PlayerStoneAtNode(rand.nextInt(7), rand.nextInt(6)));
                }
            } else {
                int terminalRandomNumber = rand.nextInt(8); // 0 <= rand < n

                if (terminalRandomNumber <= 0) {
                    leaf.setElement(new ConstantNode((long)rand.nextInt(9) - 5)); // Random settings...
                } else if (terminalRandomNumber <= 1) {
                    leaf.setElement(new EnemyStoneAtNode(rand.nextInt(7), rand.nextInt(6)));
                } else if (terminalRandomNumber <= 2) {
                    leaf.setElement(new PlayerStoneAtNode(rand.nextInt(7), rand.nextInt(6)));
                } else if (terminalRandomNumber <= 4) {
                    leaf.setElement(new EnemyCanWinInOneRound());
                } else if (terminalRandomNumber <= 5) {
                    leaf.setElement(new PlayerCanWinInOneRound());
                } else if (terminalRandomNumber <= 6) {
                    leaf.setElement(new EnemyCanWinInTwoRounds());
                } else {
                    leaf.setElement(new PlayerCanWinInTwoRounds());
                }
            }
        }

        return leaf;
    }

    private static void printSettings() {
        System.out.println(
                "LeafFactory settings:"
                        + "\n\tonlyConditional: " + onlyConditional
                        + "\n\tnoHigherLevelFunctions: " + noHigherLevelFunctions);
    }

}
