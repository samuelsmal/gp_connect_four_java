package org.sam.game;

import org.sam.Random.GPRandom;
import org.sam.tree.Terminals.EnemyCanWinInOneRound;
import org.sam.tree.Terminals.EnemyCanWinInTwoRounds;
import org.sam.tree.Terminals.PlayerCanWinInOneRound;
import org.sam.tree.Terminals.PlayerCanWinInTwoRounds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by samuel on 08/08/14.
 */
public abstract class GPPlayer implements Player {

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

    protected long enemyCanWinInOneRound(char playerColour, char enemyColour, Game game) {
        return new EnemyCanWinInOneRound().evaluate(playerColour, enemyColour, game, null);
    }

    protected long playerCanWinInOneRound(char playerColour, char enemyColour, Game game) {
        return new PlayerCanWinInOneRound().evaluate(playerColour, enemyColour, game, null);
    }

    protected long enemyCanWinInTwoRounds(char playerColour, char enemyColour, Game game) {
        return new EnemyCanWinInTwoRounds().evaluate(playerColour, enemyColour, game, null);
    }

    protected long playerCanWinInTwoRounds(char playerColour, char enemyColour, Game game) {
        return new PlayerCanWinInTwoRounds().evaluate(playerColour, enemyColour, game, null);
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
