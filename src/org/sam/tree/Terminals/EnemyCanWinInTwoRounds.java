package org.sam.tree.Terminals;

import org.sam.game.ColumnFullException;
import org.sam.game.Game;
import org.sam.tree.INode;
import org.sam.tree.Leaf;

import java.util.ArrayList;
import java.util.List;

/**
 * Inserts stones into the game, and checks if the enemy can win in two rounds.
 *
 * If : player can make it : Return -1
 * Else-If : enemy can make it: Return 1
 * Else-If : player can make it in his turn : Return -1
 * Else-If : enemy can make it in his next turn : Return 1
 * Else : Return 0
 *
 * Created by samuel on 11/08/14.
 */
public class EnemyCanWinInTwoRounds implements INode {
    @Override
    public long evaluate(char playerColour, char enemyColour, Game game, List<Leaf> children) {
        // The method's outline is always the same:
        // First test all possibilities of current turn, then go deeper: Breadth-first.

        // The players turn has already happened.
        // Ergo the first turn is the enemy's.

        if (game.checkBoard() == playerColour) {
            return -1;
        }

        Game copy = new Game();

        for (int i = 0; i < Game.BOARD_WIDTH; i++) {
            if (game.getColourOfStone(i, 0) == Game.EMPTY_STONE_COLOUR) {
                copy.setBoard(game.getBoardCopy());

                try {
                    copy.checkMove(copy.insertStoneInColumn(i, enemyColour));
                } catch (ColumnFullException e) {
                    e.printStackTrace();
                }

                if (copy.getWinnerColour() == enemyColour) {
                    return 1;
                }
            }
        }

        Game innerCopy = new Game();
        Game innerInnerCopy = new Game();

        for (int i = 0; i < Game.BOARD_WIDTH; i++) {
            if (game.getColourOfStone(i, 0) == Game.EMPTY_STONE_COLOUR) {
                copy.setBoard(game.getBoardCopy());

                try {
                    copy.checkMove(copy.insertStoneInColumn(i, enemyColour));
                } catch (ColumnFullException e) {
                    e.printStackTrace();
                }

                if (copy.getWinnerColour() != enemyColour) {
                    // Check players possibilities
                    for (int j = 0; j < Game.BOARD_WIDTH; j++) {
                        if (copy.getColourOfStone(j, 0) == Game.EMPTY_STONE_COLOUR) {
                            innerCopy.setBoard(copy.getBoardCopy());

                            try {
                                innerCopy.checkMove(innerCopy.insertStoneInColumn(j, playerColour));
                            } catch (ColumnFullException e) {
                                e.printStackTrace();
                            }

                            // Setting an else-clause here is faulty:
                            // It will turn into a depth-first instead of a breadth-first search.
                            if (innerCopy.getWinnerColour() == playerColour) {
                                return -1;
                            }
                        }
                    }

                    // Move on: Enemy's second turn.
                    for (int j = 0; j < Game.BOARD_WIDTH; j++) {
                        if (copy.getColourOfStone(j, 0) == Game.EMPTY_STONE_COLOUR) {
                            innerCopy.setBoard(copy.getBoardCopy());

                            try {
                                innerCopy.checkMove(innerCopy.insertStoneInColumn(j, playerColour));
                            } catch (ColumnFullException e) {
                                e.printStackTrace();
                            }

                            if (innerCopy.getWinnerColour() != playerColour) {
                                for (int k = 0; k < Game.BOARD_WIDTH; k++) {
                                    if (innerCopy.getColourOfStone(k, 0) == Game.EMPTY_STONE_COLOUR) {
                                        innerInnerCopy.setBoard(innerCopy.getBoardCopy());

                                        try {
                                            innerInnerCopy.checkMove(
                                                    innerInnerCopy.insertStoneInColumn(k, enemyColour));
                                        } catch (ColumnFullException e) {
                                            e.printStackTrace();
                                        }

                                        if (innerInnerCopy.getWinnerColour() == enemyColour) {
                                            return 1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }

    @Override
    public String print(List<Leaf> children) {
        return " enemyCanWinInTwoRounds( playerColour, enemyColour, game) ";
    }

    @Override
    public INode getCopy() {
        return new EnemyCanWinInTwoRounds();
    }
}
