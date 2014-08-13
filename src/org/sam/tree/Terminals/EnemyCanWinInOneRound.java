package org.sam.tree.Terminals;

import org.sam.game.ColumnFullException;
import org.sam.game.Game;
import org.sam.tree.INode;
import org.sam.tree.Leaf;

import java.util.List;

/**
 *  Inserts stones into the game, and checks if the enemy can win in two rounds.
 *
 *  If : player can make it : Return -1
 *  Else-If : enemy can make it : Return 1
 *  Else : Return 0
 *
 * Created by samuel on 11/08/14.
 */
public class EnemyCanWinInOneRound implements INode {
    /**
     *
     * @param playerColour
     * @param enemyColour
     * @param game
     * @param children
     * @return 0 if not, 1 if true
     */
    @Override
    public long evaluate(char playerColour, char enemyColour, Game game, List<Leaf> children) {
        // The method's outline is always the same:
        // First test all possibilities of current turn, then go deeper: Breadth-first.

        // The players turn has already happened.
        // Ergo the first turn is the enemy's.

        if (game.colourOfWinner() == playerColour) {
            return -1;
        }

        Game copy = new Game();

        for (int i = 0; i < Game.BOARD_WIDTH; i++) {
            if (game.getColourOfStone(i, 0) == Game.EMPTY_STONE_COLOUR) {
                copy.setBoard(game.getBoardCopy());

                try {
                    copy.insertStoneInColumn(i, enemyColour);
                } catch (ColumnFullException e) {
                    e.printStackTrace();
                }

                if (copy.colourOfWinner() == enemyColour) {
                    return 1;
                }
            }
        }

        return 0;
    }

    @Override
    public String print(List<Leaf> children) {
        return " enemyCanWinInOneRound(enemyColour, game) ";
    }

    @Override
    public INode getCopy() {
        return new EnemyStoneAtNode();
    }
}
