package org.sam;

import org.sam.Tournament.Tournament;
import org.sam.Tournament.TournamentFactory;
import org.sam.game.*;
import org.sam.genetics.Evolution;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Evolution evolution = new Evolution(272);

        GPTreePlayer winner = evolution.evolve(5);

        Game game = new Game();

        game.startGame(new HumanPlayer(), winner);

        System.out.println(game.colourOfWinner() + " has won!\n" + game);
    }
}
