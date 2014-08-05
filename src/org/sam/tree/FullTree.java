package org.sam.tree;

/**
 * Created by samuel on 31/07/14.
 */
public class FullTree extends Tree {
    public FullTree() {
        root = initFully(2);
    }

    public FullTree(long depth) {
        root = initFully(depth);
    }

    /**
     * Recursive method to init a tree.
     * The idea behind the if-else-clauses is to provide a custom distribution of functions.
     * @param depth of the tree.
     * @return
     */
    private INode initFully(long depth) {
        if (depth > 0) {
            // Functions
            int functionRandomNumber = rand.nextInt(4); // 4 functions

            if (functionRandomNumber <= 0) {
                return new AddOperationNode(initFully(depth - 1), initFully(depth -1 ));
            } else if (functionRandomNumber <= 1) {
                return new SubtractionOperationNode(initFully(depth - 1), initFully(depth -1 ));
            } else if (functionRandomNumber <= 2) {
                return new MultiplicationOperationNode(initFully(depth - 1), initFully(depth -1 ));
            } else if (functionRandomNumber <= 3) {
                return new SaveDivisionOperationNode(initFully(depth - 1), initFully(depth -1 ));
            } else {
                return new ConditionalNode(initFully(depth - 1), initFully(depth -1 ), initFully(depth - 1));
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
