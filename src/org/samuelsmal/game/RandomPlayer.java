package org.samuelsmal.game;

import org.samuelsmal.Random.GPRandom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 07/08/14.
 */
public class RandomPlayer implements Player {
    private static final GPRandom rand = GPRandom.INSTANCE;

    /**
     * @param game
     * @param ownColour
     * @param enemyColour @return The chosen column [0, 7)
     */
    @Override
    public int play(Game game, char ownColour, char enemyColour) {
        List<Integer> possibleMoves = new ArrayList<>();

        for (int i = 0; i < Game.BOARD_WIDTH; i++) {
            if (game.getColourOfStone(i, 0) == Game.EMPTY_STONE_COLOUR) {
                possibleMoves.add(new Integer(i));
            }
        }

        return possibleMoves.get(rand.nextInt(possibleMoves.size()));
    }
}
