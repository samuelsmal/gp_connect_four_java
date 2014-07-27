package org.sam.tree;

import org.sam.game.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by samuel on 27/07/14.
 */
public class Tree {
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

    private ArrayList<>

    public void initRandom(long maxDepth) {
        List<Class> nodes = new ArrayList<Class>() {{
            add(Class.forName("AddOperationNode"));

        }}

        INode[] nodes = new INode[]{
                new AddOperationNode(),
                new SubtractionOperationNode(),
                new MultiplicationOperationNode(),
                new SaveDivisionOperationNode(),
                new ConditionalNode(),
                new ConstantNode()};

        // Poor choice of random seed.
        Random random = new Random(System.currentTimeMillis());

        AddOperationNode add = new AddOperationNode();

        Class<INode> nodes2 = new Class<INode>(){AddOperationNode.class};


    }
}
