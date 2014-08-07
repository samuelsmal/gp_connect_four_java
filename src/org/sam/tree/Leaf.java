package org.sam.tree;

import org.sam.game.Game;

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
}
