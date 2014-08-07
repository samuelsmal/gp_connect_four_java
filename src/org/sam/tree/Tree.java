package org.sam.tree;

import org.sam.game.Game;

import java.util.List;
import java.util.Random;

/**
 * Created by samuel on 27/07/14.
 */
public class Tree {
    private Leaf root;

    public Tree() {}

    public Tree(Leaf root) { this.root = root; }

    @Override
    public String toString() {
        return root.toString();
    }

    public long evaluate(char playerColour, char enemyColour, Game game) {
        return root.evaluate(playerColour, enemyColour, game);
    }

    public Leaf getRoot() {
        return root;
    }

    public void setRoot(Leaf root) {
        this.root = root;
    }
}
