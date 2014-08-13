package org.sam.tree.Terminals;

import org.sam.game.ColumnFullException;
import org.sam.game.Game;
import org.sam.tree.INode;
import org.sam.tree.Leaf;

import java.util.List;

/**
 * Inserts stones into the game, and checks if the player can win in two rounds (including the current one).
 *
 * If : player can make it : Return 1.
 * Else-If : enemy can make it in his turn : Return -1.
 * Else : Return 0.
 *
 * Created by samuel on 11/08/14.
 */
public class PlayerCanWinInTwoRounds implements INode {
    @Override
    public String print(List<Leaf> children) {
        return " playerCanWinInTwoRounds( playerColour, enemyColour, game) ";
    }

    @Override
    public long evaluate(char playerColour, char enemyColour, Game game, List<Leaf> children) {
        // The method's outline is always the same:
        // First test all possibilities of current turn, then go deeper: Breadth-first.

        if (game.checkBoard() == playerColour) {
            return 1;
        }

        Game copy = new Game();
        Game innerCopy = new Game();

        // First checking if the opponent can win.
        for (int i = 0; i < Game.BOARD_WIDTH; i++) {
            if (game.getColourOfStone(i, 0) == Game.EMPTY_STONE_COLOUR) {
                copy.setBoard(game.getBoardCopy());

                try {
                    copy.checkMove(copy.insertStoneInColumn(i, enemyColour));
                } catch (ColumnFullException e) {
                    e.printStackTrace();
                }

                if (copy.getWinnerColour() == enemyColour) {
                    return -1;
                }
            }
        }

        // If the opponent cannot win in his round, check if the player can win in his next round.
        for (int i = 0; i < Game.BOARD_WIDTH; i++) {
            if (game.getColourOfStone(i, 0) == Game.EMPTY_STONE_COLOUR) {
                copy.setBoard(game.getBoardCopy());

                try {
                    copy.insertStoneInColumn(i, enemyColour);
                } catch (ColumnFullException e) {
                    e.printStackTrace();
                }

                for (int j = 0; j < Game.BOARD_WIDTH; j++) {
                    if (copy.getColourOfStone(j, 0) == Game.EMPTY_STONE_COLOUR) {
                        innerCopy.setBoard(copy.getBoardCopy());

                        try {
                            innerCopy.checkMove(innerCopy.insertStoneInColumn(j, playerColour));
                        } catch (ColumnFullException e) {
                            e.printStackTrace();
                        }

                        if (innerCopy.getWinnerColour() == playerColour) {
                            return 1;
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
