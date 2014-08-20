package org.samuelsmal.tree.Terminals;

import org.samuelsmal.game.Game;
import org.samuelsmal.tree.INode;
import org.samuelsmal.tree.Leaf;

import java.util.List;

/**
 * Created by samuel on 11/08/14.
 */
public class PlayerCanWinInOneRound implements INode {
    @Override
    public long evaluate(char playerColour, char enemyColour, Game game, List<Leaf> children) {
        return (game.colourOfWinner() == playerColour ? 1 : 0);
    }

    @Override
    public String print(List<Leaf> children) {
        return " (game.colourOfWinner() == playerColour ? 1 : 0) ";
    }

    @Override
    public INode getCopy() {
        return new PlayerStoneAtNode();
    }
}
