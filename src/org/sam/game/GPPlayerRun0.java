package org.sam.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The finished player
 * Created by samuel on 08/08/14.
 */
public class GPPlayerRun0 extends GPPlayer {

    @Override
    protected long evalGame(char playerColour, char enemyColour, Game game) {
        return (((((3+(playerColour == game.getColourOfStone((int)3, (int)5) ? 1 : 0)
        )*((playerColour == game.getColourOfStone((int)1, (int)5) ? 1 : 0)
                *(enemyColour == game.getColourOfStone((int)0, (int)4) ? 1 : 0)
        )) >= 0 ? ((2 > 0 ? 8 / 2 : 1) >= 0 ? ((enemyColour == game.getColourOfStone((int)1, (int)5) ? 1 : 0)
                >= 0 ? (playerColour == game.getColourOfStone((int)3, (int)2) ? 1 : 0)
                : 0) : ((enemyColour == game.getColourOfStone((int)0, (int)5) ? 1 : 0)
                +(playerColour == game.getColourOfStone((int)2, (int)0) ? 1 : 0)
        )) : (((enemyColour == game.getColourOfStone((int)0, (int)5) ? 1 : 0)
                >= 0 ? 4 : (enemyColour == game.getColourOfStone((int)1, (int)3) ? 1 : 0)
        )*((enemyColour == game.getColourOfStone((int)5, (int)2) ? 1 : 0)
                >= 0 ? 4 : (playerColour == game.getColourOfStone((int)0, (int)1) ? 1 : 0)
        )))*((((enemyColour == game.getColourOfStone((int)1, (int)3) ? 1 : 0)
                +(playerColour == game.getColourOfStone((int)0, (int)4) ? 1 : 0)
        ) >= 0 ? (0 > 0 ? (playerColour == game.getColourOfStone((int)6, (int)0) ? 1 : 0)
                / 0 : 1) : (5+(playerColour == game.getColourOfStone((int)1, (int)0) ? 1 : 0)
        ))+(((playerColour == game.getColourOfStone((int)0, (int)5) ? 1 : 0)
                >= 0 ? (enemyColour == game.getColourOfStone((int)2, (int)3) ? 1 : 0)
                : (enemyColour == game.getColourOfStone((int)2, (int)4) ? 1 : 0)
        )*((enemyColour == game.getColourOfStone((int)4, (int)4) ? 1 : 0)
                > 0 ? (playerColour == game.getColourOfStone((int)4, (int)1) ? 1 : 0)
                / (enemyColour == game.getColourOfStone((int)4, (int)4) ? 1 : 0)
                : 1)))) > 0 ? (((((playerColour == game.getColourOfStone((int)3, (int)0) ? 1 : 0)
                +9)*(7+3)) >= 0 ? ((0-(playerColour == game.getColourOfStone((int)3, (int)4) ? 1 : 0)
        ) > 0 ? (5 >= 0 ? (playerColour == game.getColourOfStone((int)4, (int)4) ? 1 : 0)
                : (playerColour == game.getColourOfStone((int)6, (int)3) ? 1 : 0)
        ) / (0-(playerColour == game.getColourOfStone((int)3, (int)4) ? 1 : 0)
        ) : 1) : (((enemyColour == game.getColourOfStone((int)5, (int)4) ? 1 : 0)
                > 0 ? (enemyColour == game.getColourOfStone((int)6, (int)1) ? 1 : 0)
                / (enemyColour == game.getColourOfStone((int)5, (int)4) ? 1 : 0)
                : 1)-(8-2))) >= 0 ? ((((playerColour == game.getColourOfStone((int)5, (int)1) ? 1 : 0)
                +4)*((playerColour == game.getColourOfStone((int)4, (int)5) ? 1 : 0)
                >= 0 ? 9 : (playerColour == game.getColourOfStone((int)2, (int)0) ? 1 : 0)
        ))-(((playerColour == game.getColourOfStone((int)0, (int)1) ? 1 : 0)
                +(enemyColour == game.getColourOfStone((int)6, (int)1) ? 1 : 0)
        )*(1 > 0 ? 2 / 1 : 1))) : ((((playerColour == game.getColourOfStone((int)1, (int)0) ? 1 : 0)
                >= 0 ? (enemyColour == game.getColourOfStone((int)6, (int)3) ? 1 : 0)
                : (playerColour == game.getColourOfStone((int)6, (int)3) ? 1 : 0)
        )+((enemyColour == game.getColourOfStone((int)1, (int)3) ? 1 : 0)
                >= 0 ? 3 : (playerColour == game.getColourOfStone((int)4, (int)0) ? 1 : 0)
        ))*((9-(playerColour == game.getColourOfStone((int)2, (int)1) ? 1 : 0)
        ) >= 0 ? ((playerColour == game.getColourOfStone((int)2, (int)4) ? 1 : 0)
                -(enemyColour == game.getColourOfStone((int)3, (int)2) ? 1 : 0)
        ) : (1*(playerColour == game.getColourOfStone((int)5, (int)3) ? 1 : 0)
        )))) / ((((3+(playerColour == game.getColourOfStone((int)3, (int)5) ? 1 : 0)
        )*((playerColour == game.getColourOfStone((int)1, (int)5) ? 1 : 0)
                *(enemyColour == game.getColourOfStone((int)0, (int)4) ? 1 : 0)
        )) >= 0 ? ((2 > 0 ? 8 / 2 : 1) >= 0 ? ((enemyColour == game.getColourOfStone((int)1, (int)5) ? 1 : 0)
                >= 0 ? (playerColour == game.getColourOfStone((int)3, (int)2) ? 1 : 0)
                : 0) : ((enemyColour == game.getColourOfStone((int)0, (int)5) ? 1 : 0)
                +(playerColour == game.getColourOfStone((int)2, (int)0) ? 1 : 0)
        )) : (((enemyColour == game.getColourOfStone((int)0, (int)5) ? 1 : 0)
                >= 0 ? 4 : (enemyColour == game.getColourOfStone((int)1, (int)3) ? 1 : 0)
        )*((enemyColour == game.getColourOfStone((int)5, (int)2) ? 1 : 0)
                >= 0 ? 4 : (playerColour == game.getColourOfStone((int)0, (int)1) ? 1 : 0)
        )))*((((enemyColour == game.getColourOfStone((int)1, (int)3) ? 1 : 0)
                +(playerColour == game.getColourOfStone((int)0, (int)4) ? 1 : 0)
        ) >= 0 ? (0 > 0 ? (playerColour == game.getColourOfStone((int)6, (int)0) ? 1 : 0)
                / 0 : 1) : (5+(playerColour == game.getColourOfStone((int)1, (int)0) ? 1 : 0)
        ))+(((playerColour == game.getColourOfStone((int)0, (int)5) ? 1 : 0)
                >= 0 ? (enemyColour == game.getColourOfStone((int)2, (int)3) ? 1 : 0)
                : (enemyColour == game.getColourOfStone((int)2, (int)4) ? 1 : 0)
        )*((enemyColour == game.getColourOfStone((int)4, (int)4) ? 1 : 0)
                > 0 ? (playerColour == game.getColourOfStone((int)4, (int)1) ? 1 : 0)
                / (enemyColour == game.getColourOfStone((int)4, (int)4) ? 1 : 0)
                : 1)))) : 1);
    }
}
