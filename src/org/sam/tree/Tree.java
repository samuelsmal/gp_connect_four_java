package org.sam.tree;

import org.sam.game.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by samuel on 27/07/14.
 */
public class Tree {
    private static Random rand = new Random(System.currentTimeMillis());

    private INode root;

    public Tree(INode root) {
        this.root = root;
    }

    public long evaluate(char playerColour, char enemyColour, Game game) {
        return root.evaluate(playerColour, enemyColour, game);
    }

    public INode getRoot() {
        return root;
    }

    public void setRoot(INode root) {
        this.root = root;
    }

    private INode initFully(long depth) {
        if (depth > 0) {
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

    public void initWithGrow(long maxDepth) {
        // TODO: finish
    }
}
