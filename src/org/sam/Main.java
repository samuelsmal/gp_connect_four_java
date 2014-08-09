package org.sam;

import org.sam.Tournament.Tournament;
import org.sam.game.*;
import org.sam.genetics.Evolution;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Evolution evolution = new Evolution(100);

        GPTreePlayer winner = evolution.evolve(3);

        int evolveWon = 0;

        for (int i = 0; i < 50; i++) {
            Game game1 = new Game();
            game1.startGame(winner, new RandomPlayer());

            if (game1.colourOfWinner() == Game.FIRST_PLAYER_COLOUR)
                evolveWon++;

            Game game2 = new Game();
            game2.startGame(new RandomPlayer(), winner);

            if (game1.colourOfWinner() == Game.SECOND_PLAYER_COLOUR)
                evolveWon++;
        }

        System.out.println("Evolve won " + evolveWon + " times out of 100 against a random player");
        System.out.println(winner);

    }
}
