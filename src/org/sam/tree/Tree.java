package org.sam.tree;

import org.sam.game.Game;

import java.util.ArrayList;
import java.util.List;

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

    public List<Leaf> flatten() {
        List<Leaf> leafs = new ArrayList<>();
        leafs.add(root);

        leafs.addAll(root.flatten());

        return leafs;
    }
}
