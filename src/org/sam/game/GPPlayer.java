package org.sam.game;

import org.sam.Random.GPRandom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by samuel on 08/08/14.
 */
abstract class GPPlayer implements Player {

    /**
     * @param game
     * @param ownColour
     * @param enemyColour @return The chosen column [0, 7)
     */
    @Override
    public int play(Game game, char ownColour, char enemyColour) {
        Game copy = new Game();

        List<Decision> decisions = new ArrayList<>();

        for (int i = 0; i < Game.BOARD_WIDTH; i++) {
            if (game.getColourOfStone(i, 0) == Game.EMPTY_STONE_COLOUR) {
                copy.setBoard(game.getBoardCopy());

            try {
                copy.insertStoneInColumn(i, ownColour);
                decisions.add(new Decision(i, evalGame(ownColour, enemyColour, copy)));
            } catch (ColumnFullException e) {
                e.printStackTrace();
            }

            }
        }

        Collections.shuffle(decisions, GPRandom.INSTANCE.getRand());
        Collections.sort(decisions);

        return decisions.get(decisions.size() - 1).column;
    }

    protected abstract long evalGame(char playerColour, char enemyColour, Game game);

    protected class Decision implements Comparable<Decision>{
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
