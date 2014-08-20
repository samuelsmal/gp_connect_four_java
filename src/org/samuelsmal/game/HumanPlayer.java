package org.samuelsmal.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by samuel on 07/08/14.
 */
public class HumanPlayer implements Player {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public HumanPlayer() {}

    /**
     * @param game
     * @return The chosen column [0, 7)
     */
    @Override
    public int play(Game game, char ownColour, char enemyColour) {
        System.out.println(game);
        System.out.println("Enter an integer from (including) 0 to (including) 6. Your colour is " + ownColour);

        int chosenColumn = -1;

        try {
            chosenColumn =  Integer.parseInt(br.readLine());
        } catch(NumberFormatException | IOException e) {
            System.err.println("Invalid Format!" + e.getMessage());
        }

        return chosenColumn;
    }
}
