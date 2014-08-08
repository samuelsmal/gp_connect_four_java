package org.sam.game;

import org.sam.tree.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by samuel on 07/08/14.
 */
public class GPTreePlayer implements Player {
    private Tree tree;


    public GPTreePlayer() {
        tree = new Tree();
    }

    public GPTreePlayer(Tree tree) {
        this.tree = tree;
    }

    /**
     * @param game
     * @return The chosen column [0, 7)
     */
    @Override
    public int play(Game game, char ownColour, char enemyColour) {
        Game ggggGame = new Game();

        List<Decision> decisions = new ArrayList<>();

        for (int i = 0; i < Game.BOARD_WIDTH; i++) {
            if (game.getColourOfStone(i, 0) == Game.EMPTY_STONE_COLOUR) {
                ggggGame.setBoard(game.getBoardCopy());

                try {
                    ggggGame.insertStoneInColumn(i, ownColour);
                    decisions.add(new Decision(i, tree.evaluate(ownColour, enemyColour, ggggGame)));
                } catch (ColumnFullException e) {
                    e.printStackTrace();
                }

            }
        }

        Collections.shuffle(decisions);
        Collections.sort(decisions);

        return decisions.get(decisions.size() - 1).column;
    }

    @Override
    public String toString() {
        return tree.toString();
    }

    private class Decision implements Comparable<Decision>{
        public int column;
        public long measure;

        private Decision(int column, long measure) {
            this.column = column;
            this.measure = measure;
        }

        @Override
        public int compareTo(Decision o) {
            return measure - o.measure < 0 ? -1 : measure - o.measure == 0 ? 0 : 1;
        }
    }
}
