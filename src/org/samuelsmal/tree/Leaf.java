package org.samuelsmal.tree;

import org.samuelsmal.game.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 07/08/14.
 */
public class Leaf {
    private INode element;
    private List<Leaf> children;

    public Leaf() {
        children = new ArrayList<>();
    }

    public Leaf(INode element) {
        children = new ArrayList<>();
        this.element = element;
    }

    public Leaf(Leaf other) {
        element = other.getElement().getCopy();
        children = new ArrayList<>(other.getChildren().size());

        for (Leaf l : other.getChildren()) {
            children.add(new Leaf(l));
        }
    }

    @Override
    public String toString() {
        return element.print(children);
    }

    public long evaluate(char playerColour, char enemyColour, Game game) {
        return element.evaluate(playerColour, enemyColour, game, children);
    }

    public INode getElement() {
        return element;
    }

    public void setElement(INode element) {
        this.element = element;
    }

    public List<Leaf> getChildren() {
        return children;
    }

    public void setChildren(List<Leaf> children) {
        this.children = children;
    }

    public void addChild(Leaf child) { this.children.add(child); }

    public List<Leaf> flatten() {
        List<Leaf> leafs = new ArrayList<>();
        leafs.add(this);

        for(Leaf leaf : children) {
            leafs.addAll(leaf.flatten());
        }

        return leafs;
    }
}
