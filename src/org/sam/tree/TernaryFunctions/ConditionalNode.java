package org.sam.tree.TernaryFunctions;

import org.sam.game.Game;
import org.sam.tree.INode;
import org.sam.tree.Leaf;

import java.util.List;

/**
 * Created by samuel on 27/07/14.
 */
public class ConditionalNode implements INode {
    public ConditionalNode() {}

    @Override
    public long evaluate(char playerColour, char enemyColour, Game game, List<Leaf> children) {
        return children.get(0).evaluate(playerColour, enemyColour, game) >= 0
                ? children.get(1).evaluate(playerColour, enemyColour, game)
                : children.get(2).evaluate(playerColour, enemyColour, game);
    }

    @Override
    public String print(List<Leaf> children) {
        return "(" + children.get(0).toString() + " >= 0 ? "
                + children.get(1).toString() + " : "
                + children.get(2).toString() + ")";
    }
}
