package org.sam;

import org.sam.Tournament.Tournament;
import org.sam.game.*;
import org.sam.game.EvolvedPlayers.SunAug10_14_10_66;
import org.sam.genetics.Evolution;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        evol();
    }

    private static void myself() {
        Game game = new Game();

        game.startGame(new SunAug10_14_10_66(), new HumanPlayer());
    }

    private static void evol() {
        Evolution evolution = new Evolution.Builder().numberOfPlayers(200).numberOfGenerations(10).depthOfTrees(8).winAgainstRandomPlayerWeight(3).build();

        GPTreePlayer winner = evolution.evolve();

        int evolveWonFirst = 0;
        int evolveWonSecond = 0;
        int drawFirst = 0;
        int drawSecond = 0;

        RandomPlayer randomPlayer = new RandomPlayer();
        Game game = new Game();

        for (int i = 0; i < 50; i++) {
            game.startGame(winner, randomPlayer);

            if (game.colourOfWinner() == Game.FIRST_PLAYER_COLOUR) {
                evolveWonFirst++;
            } else if (game.colourOfWinner() == Game.EMPTY_STONE_COLOUR) {
                drawFirst++;
            }

            game.startGame(randomPlayer, winner);

            if (game.colourOfWinner() == Game.SECOND_PLAYER_COLOUR) {
                evolveWonSecond++;
            } else if (game.colourOfWinner() == Game.EMPTY_STONE_COLOUR) {
                drawSecond++;
            }
        }

        System.out.println("Evolved player won "
                + "\n\t As first player: " + evolveWonFirst
                + "\n\t As second player: " + evolveWonSecond
                + "\n\t Draw as first player: " + drawFirst
                + "\n\t Draw as second player: " + drawSecond);
        System.out.println("Evolved code:\n" + winner);
    }
}
