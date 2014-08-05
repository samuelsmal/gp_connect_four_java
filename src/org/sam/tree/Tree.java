package org.sam.tree;

import org.sam.game.Game;

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

    public Tree(Tree source) { this.root = source.root.getDeepCopy(); }

    public String toString() {
        return root.toString();
    }

    public long evaluate(char playerColour, char enemyColour, Game game) {
        return root.evaluate(playerColour, enemyColour, game);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tree tree = (Tree) o;

        if (root != null ? !root.equals(tree.root) : tree.root != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return root != null ? root.hashCode() : 0;
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
