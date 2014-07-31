package org.sam.tree;

import org.sam.game.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by samuel on 27/07/14.
 */
public class Tree {
    protected static Random rand = new Random(System.currentTimeMillis());

    protected INode root;

    public Tree() {}

    public Tree(INode root) {
        this.root = root;
    }

    public String toString() {
        return root.toString();
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

    public void initWithGrow(long maxDepth) {
        // TODO: finish
    }
}
