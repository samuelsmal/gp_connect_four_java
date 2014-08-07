package org.sam;

import org.sam.game.GPTreePlayer;
import org.sam.game.Game;
import org.sam.game.HumanPlayer;
import org.sam.genetics.Evolution;
import org.sam.tree.*;
import org.sam.tree.BinaryFunctions.AddOperationNode;
import org.sam.tree.BinaryFunctions.SubtractionOperationNode;
import org.sam.tree.Terminals.ConstantNode;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        HumanPlayer p1 = new HumanPlayer();
        HumanPlayer p2 = new HumanPlayer();

        GPTreePlayer gpTreePlayer = new GPTreePlayer(TreeFactory.fullTree(5));

        game.startGame(p1, gpTreePlayer);



        System.out.println("Player " + game.colourOfWinner() + " has won!\n" + game);
    }
}
