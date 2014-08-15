package org.sam.tree.Terminals;

import org.sam.game.ColumnFullException;
import org.sam.game.Game;
import org.sam.tree.INode;
import org.sam.tree.Leaf;

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
