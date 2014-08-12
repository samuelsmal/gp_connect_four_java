package org.sam.tree.Terminals;

import org.sam.game.ColumnFullException;
import org.sam.game.Game;
import org.sam.tree.INode;
import org.sam.tree.Leaf;

import java.util.List;

/**
 * Created by samuel on 11/08/14.
 */
public class PlayerCanWinInTwoRounds implements INode {
    @Override
    public String print(List<Leaf> children) {
        return " playerCanWinInTwoRounds( playerColour, enemyColour, game) ";
    }

    @Override
    public long evaluate(char playerColour, char enemyColour, Game game, List<Leaf> children) {
        if (game.colourOfWinner() == playerColour) {
            return 1;
        }

        Game copy = new Game();
        Game innerCopy = new Game();

        for (int i = 0; i < Game.BOARD_WIDTH; i++) {

            if (game.getColourOfStone(i, 0) == Game.EMPTY_STONE_COLOUR) {
                copy.setBoard(game.getBoardCopy());

                try {
                    copy.insertStoneInColumn(i, enemyColour);
                } catch (ColumnFullException e) {
                    e.printStackTrace();
                }

                if (copy.colourOfWinner() == enemyColour) {
                    return -1;
                } else {
                    for (int j = 0; j < Game.BOARD_WIDTH; j++) {
                        if (copy.getColourOfStone(i, 0) == Game.EMPTY_STONE_COLOUR) {
                            innerCopy.setBoard(copy.getBoardCopy());

                            try {
                                innerCopy.insertStoneInColumn(i, playerColour);
                            } catch (ColumnFullException e) {
                                e.printStackTrace();
                            }

                            if (innerCopy.colourOfWinner() == playerColour) {
                                return 1;
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }

    @Override
    public INode getCopy() {
        return new PlayerCanWinInTwoRounds();
    }
}
