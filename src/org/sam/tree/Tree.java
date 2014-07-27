package org.sam.tree;

import org.sam.game.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by samuel on 27/07/14.
 */
public class Tree {
    private Random rand = new Random(System.currentTimeMillis());

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

    private ArrayList<Class> getFunctionSet() {
        ArrayList<Class> functions = null;
        try {
             functions = new ArrayList<Class>() {{
                add(Class.forName("AddOperationNode"));
                add(Class.forName("SubtractionOperationNode"));
                add(Class.forName("MultiplicationOperationNode"));
                add(Class.forName("SaveDivisionOperationNode"));
                add(Class.forName("ConditionalNode"));
                add(Class.forName("PlayerStoneAtNode"));
                add(Class.forName("EnemyStoneAtNode"));
            }};
        } catch (ClassNotFoundException e) {
            System.out.println("Tree::getFunctionSet, Exception thrown  :" + e);
        }

        return functions;
    }

    private ArrayList<Class> getTerminalSet() {
        ArrayList<Class> terminals = null;

        try {
            terminals =  new ArrayList<Class>() {{
                add(Class.forName("ConstantNode"));
            }};
        } catch (ClassNotFoundException e) {
            System.out.println("Tree::getTerminalSet, Exception thrown  :" + e);
        }

        return terminals;
    }

    private INode getRandomNode(long depth) {
        List<Class> functions = getFunctionSet();
        List<Class> terminals = getTerminalSet();

        INode node = null;

        if (depth >= 1) {
            try {
                node = (INode)functions.get(rand.nextInt(functions.size() + 1)).newInstance();
            } catch (InstantiationException e) {
                System.out.println("Exception thrown  :" + e);
            } catch (IllegalAccessException e) {
                System.out.println("Exception thrown  :" + e);
            }

            try {
                if (node.getClass().equals(Class.forName("ConditionalNode"))) {
                    // TODO

                } else if (node.getClass().equals(Class.forName("PlayerStoneAtNode"))
                        || node.getClass().equals(Class.forName("EnemyStoneAtNode"))) {
                    // TODO
                }
            } catch(ClassNotFoundException e) {
                System.out.println("Exception thrown  :" + e);
            }
        } else {
            try {
                node = (INode) terminals.get(rand.nextInt(terminals.size() + 1)).newInstance();
            } catch (InstantiationException e) {
                System.out.println("Exception thrown  :" + e);
            } catch (IllegalAccessException e) {
                System.out.println("Exception thrown  :" + e);
            }

            try {
                if (node.getClass().equals(Class.forName("ConstantNode"))) {
                    // TODO
                }
            } catch(ClassNotFoundException e) {
                System.out.println("Exception thrown  :" + e);
            }
            }
        }

        return node;
    }

    // TODO: finish and test
    public void initWithFull(long depth) {
        List<Class> functions = getFunctionSet();
        List<Class> terminals = getTerminalSet();

        INode currentNode = root;

        try {
            currentNode = (INode)functions.get(rand.nextInt(functions.size() + 1)).newInstance();
        } catch (InstantiationException e) {
            System.out.println("Exception thrown  :" + e);
        } catch (IllegalAccessException e) {
            System.out.println("Exception thrown  :" + e);
        }

        try {
            if (root.getClass().equals(Class.forName("ConditionalNode"))) {

            } else if (root.getClass().equals(Class.forName("PlayerStoneAtNode"))
                    || root.getClass().equals(Class.forName("EnemyStoneAtNode"))) {

            }
        } catch(ClassNotFoundException e) {
            System.out.println("Exception thrown  :" + e);
        }

    }

    // TODO: finish
    public void initWithGrow(long maxDepth) {
        List<Class> functions = getFunctionSet();
        List<Class> terminals = getTerminalSet();

        // Poor choice of random seed.
        Random random = new Random(System.currentTimeMillis());
    }
}
