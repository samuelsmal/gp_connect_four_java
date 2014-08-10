package org.sam;

import org.sam.Tournament.Tournament;
import org.sam.game.*;
import org.sam.genetics.Evolution;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Evolution evolution = new Evolution.Builder().numberOfPlayers(20).numberOfGenerations(3).build();

        GPTreePlayer winner = evolution.evolve();

        int evolveWonFirst = 0;
        int evolveWonSecond = 0;

        RandomPlayer randomPlayer = new RandomPlayer();
        Game game = new Game();

        for (int i = 0; i < 50; i++) {
            game.startGame(winner, randomPlayer);

            if (game.colourOfWinner() == Game.FIRST_PLAYER_COLOUR)
                evolveWonFirst++;

            game.startGame(randomPlayer, winner);

            if (game.colourOfWinner() == Game.SECOND_PLAYER_COLOUR)
                evolveWonSecond++;
        }

        System.out.println("Evolved player won "
                + (evolveWonFirst + evolveWonSecond)
                + " times out of 100 against a random player."
                + "\n\t As first player: " + evolveWonFirst
                + "\n\t As second player: " + evolveWonSecond);
        System.out.println("Evolved code:\n" + winner);

    }
}
