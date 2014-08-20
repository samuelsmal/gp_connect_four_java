package org.samuelsmal;

import org.samuelsmal.game.*;
import org.samuelsmal.game.EvolvedPlayers.*;
import org.samuelsmal.genetics.Evolution;
import org.samuelsmal.tree.TournamentEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        evolve();
    }

    private static void play() {
        Game game = new Game();

        game.startGame(new RampedHHOnlyTernaryPlayer(), new HumanPlayer());

        System.out.println(game);

        if (Game.FIRST_PLAYER_COLOUR == game.colourOfWinner()) {
            System.out.println("Player " + Game.FIRST_PLAYER_COLOUR + " won!");
        } else if (Game.SECOND_PLAYER_COLOUR == game.colourOfWinner()) {
            System.out.println("Player " + Game.SECOND_PLAYER_COLOUR + " won!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    private static void superTournament() {
        List<TournamentEntry> bestEvolvedPlayers = new ArrayList<>();

        bestEvolvedPlayers.add(new TournamentEntry(new FullTreePlayer()));
        bestEvolvedPlayers.add(new TournamentEntry(new MaxPlayer()));
        bestEvolvedPlayers.add(new TournamentEntry(new MutationPlayer()));
        bestEvolvedPlayers.add(new TournamentEntry(new NoHigherFunctionsPlayer()));
        bestEvolvedPlayers.add(new TournamentEntry(new NoHigherFunctionsHighPopGenPlayer()));
        bestEvolvedPlayers.add(new TournamentEntry(new RampedHHOnlyTernaryPlayer()));
        bestEvolvedPlayers.add(new TournamentEntry(new RampedHHOnlyTernaryHigherPopAndGenPlayer()));
        bestEvolvedPlayers.add(new TournamentEntry(new RampedHigherPopAndGen()));

        Game game = new Game();

        for (int i = 0; i < bestEvolvedPlayers.size(); i++) {
            for (int j = 0; j < bestEvolvedPlayers.size(); j++) {
                if (i != j) {
                    game.startGame(bestEvolvedPlayers.get(i).getPlayer(), bestEvolvedPlayers.get(j).getPlayer());

                    if (Game.FIRST_PLAYER_COLOUR == game.colourOfWinner()) {
                        bestEvolvedPlayers.get(i).newMatchWon();
                    } else if (Game.SECOND_PLAYER_COLOUR == game.colourOfWinner()) {
                        bestEvolvedPlayers.get(j).newMatchWon();
                    }
                }
            }
        }

        Collections.sort(bestEvolvedPlayers);

        System.out.println("And the overall winner with " + bestEvolvedPlayers.get(0).matchesWon()
            + " wins is " + bestEvolvedPlayers.get(0));

    }

    private static void evolve() {
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
         *      ====
         *
         * Comment out the lines below for altering the options.
         */

        //LeafFactory.noHigherLevelFunctions = true;
        //LeafFactory.onlyConditional = true;

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
         *               positionWeight : 1        The number of times each position is tested, is equal to the group size.
         *            rampedHalfAndHalf : false
         *                  maxApproach : false
         *                   mutationOn : false
         *
         *
         * Comment out the lines below for altering the options.
         * Bear in mind that using unreasonable values could break the program. (Like setting the population to zero.)
         */
        Evolution evolution = new Evolution.Builder()
                //.numberOfGenerations(1000)
                //.numberOfPlayers(400)
                //.winAgainstGPPlayerWeight(4)
                //.winAgainstRandomPlayerWeight(23)
                //.drawWeight(8)
                //.depthOfTrees(20)
                //.positionWeight(2)
                .rampedHalfAndHalf(true)
                //.maxApproach(true)
                .mutationOn(true)
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
