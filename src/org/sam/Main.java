package org.sam;

import org.sam.Tournament.Tournament;
import org.sam.Tournament.TournamentFactory;
import org.sam.game.*;
import org.sam.genetics.Evolution;
import org.sam.tree.*;
import org.sam.tree.BinaryFunctions.AddOperationNode;
import org.sam.tree.BinaryFunctions.SubtractionOperationNode;
import org.sam.tree.Terminals.ConstantNode;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Tournament tournament = TournamentFactory.tournament();

        List<Player> winners = tournament.runTournament();

        Game game = new Game();

        game.startGame(new HumanPlayer(), winners.get(0));
    }
}
