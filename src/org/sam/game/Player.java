package org.sam.game;

/**
 * Created by samuel on 07/08/14.
 */
public interface Player {
    /**
     *
     * @param game
     * @return The chosen column [0, 7)
     */
    int play(Game game);
}
