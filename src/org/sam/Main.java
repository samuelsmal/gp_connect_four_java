package org.sam;

import org.sam.Tournament.Tournament;
import org.sam.game.*;
import org.sam.game.EvolvedPlayers.SunAug10_14_10_66;
import org.sam.genetics.Evolution;
import org.sam.tree.LeafFactory;

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
        /*
         *
         *    ,--.
         *   /   |
         *   `|  |
         *    |  |
         *    `--'
         *
         * Setting the LeafFactory options.
         * The default values are:
         *
         *        onlyConditional :   false
         * noHigherLevelFunctions :   false
         *
         * This MUST be set before using the evolution class.
         *     =====
         *
         * Comment out the lines below for altering the options.
         */

        LeafFactory.noHigherLevelFunctions = true;
        LeafFactory.onlyConditional = true;

        /*
         *
         *
         *    ,---.
         *   '.-.  \
         *    .-' .'
         *   /   '-.
         *   '-----'
         *
         * Setting the evolution options, and building the object.
         * The default values are:
         *
         *          numberOfGenerations : 50
         *              numberOfPlayers : 200
         * winAgainstRandomPlayerWeight : 2
         *     winAgainstGPPlayerWeight : 2
         *                   drawWeight : 1
         *                 depthOfTrees : 4
         *            rampedHalfAndHalf : false
         *                  maxApproach : false
         *                   mutationOn : false
         *
         *
         * Comment out the lines below for altering the options.
         * Bear in mind that using unreasonable values could break the program. (Like setting the population to zero.)
         */
        Evolution evolution = new Evolution.Builder()
                //.numberOfGenerations(100)
                //.numberOfPlayers(4000)
                //.winAgainstGPPlayerWeight(4)
                //.winAgainstRandomPlayerWeight(23)
                //.drawWeight(8)
                //.depthOfTrees(20)
                //.rampedHalfAndHalf(true)
                //.maxApproach(true)
                //.mutationOn(true)
                .build();


        GPTreePlayer winner = evolution.evolve();

        int evolveWonFirst = 0;
        int evolveWonSecond = 0;
        int drawFirst = 0;
        int drawSecond = 0;

        RandomPlayer randomPlayer = new RandomPlayer();
        Game game = new Game();

        int rounds = 100;
        for (int i = 0; i < rounds; i++) {
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
                + "\n\t Draw as second player: " + drawSecond
                + "\n\t Total: " + (evolveWonFirst + evolveWonSecond)
                + "\n\t Matches played: " + (2 * rounds));
        System.out.println("Evolved code:\n" + winner);
    }
}
