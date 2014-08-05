package org.sam.tree;

import java.util.Random;

/**
 * Created by samuel on 05/08/14.
 */
public class NodeFactory {
    private static final Random rand = new Random();

    public static INode getRandomNode() {
        return getRandomNode(0);
    }

    public static INode getRandomNode(long depth) {
        if (depth > 0) {
            // Functions
            int functionRandomNumber = rand.nextInt(5); // 4 functions

            if (functionRandomNumber <= 0) {
                return new AddOperationNode(getRandomNode(depth - 1), getRandomNode(depth -1 ));
            } else if (functionRandomNumber <= 1) {
                return new SubtractionOperationNode(getRandomNode(depth - 1), getRandomNode(depth -1 ));
            } else if (functionRandomNumber <= 2) {
                return new MultiplicationOperationNode(getRandomNode(depth - 1), getRandomNode(depth -1 ));
            } else if (functionRandomNumber <= 3) {
                return new SaveDivisionOperationNode(getRandomNode(depth - 1), getRandomNode(depth -1 ));
            } else {
                return new ConditionalNode(getRandomNode(depth - 1), getRandomNode(depth -1 ), getRandomNode(depth - 1));
            }
        } else {
            // Terminals
            int terminalRandomNumber = rand.nextInt(3);

            if (terminalRandomNumber <= 0) {
                return new ConstantNode((long)rand.nextInt(7));
            } else if (terminalRandomNumber <= 1) {
                return new EnemyStoneAtNode(new ConstantNode((long)rand.nextInt(7)), new ConstantNode((long)rand.nextInt(7)));
            } else {
                return new PlayerStoneAtNode(new ConstantNode((long)rand.nextInt(7)), new ConstantNode((long)rand.nextInt(7)));
            }
        }
    }


}
