package org.samuelsmal.tree.Terminals;

import org.samuelsmal.game.Game;
import org.samuelsmal.tree.INode;
import org.samuelsmal.tree.Leaf;

import java.util.List;

/**
 * Terminal Node
 * Created by samuel on 27/07/14.
 */
public class PlayerStoneAtNode extends ComputationalNode {
    public PlayerStoneAtNode() { super(); }

    public PlayerStoneAtNode(int x, int y) {super(x, y);}

    @Override
    public INode getCopy() {
        return new PlayerStoneAtNode(x, y);
    }

    @Override
    public String print(List<Leaf> children) {
        return "(playerColour == game.getColourOfStone(" + x + ", " + y + ") ? 1 : 0)";
    }

    @Override
    public long evaluate(char playerColour, char enemyColour, Game game, List<Leaf> children) {
        return playerColour == game.getColourOfStone(x, y) ? 1 : 0;
    }
}
