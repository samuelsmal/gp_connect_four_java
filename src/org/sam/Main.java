package org.sam;

import org.sam.game.*;
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
        RandomPlayer randomPlayer = new RandomPlayer();

        game.startGame(randomPlayer, gpTreePlayer);


        System.out.println("FirstPlayerColour: " + Game.FIRST_PLAYER_COLOUR);
        System.out.println("Player " + game.colourOfWinner() + " has won!\n" + game);

        //System.out.println(gpTreePlayer);

        Game game2 = new Game();
        game2.startGame(new RandomPlayer(), new GPPlayerRun0());
        System.out.println("Player " + game2.colourOfWinner() + " has won!\n" + game);

        Game game3 = new Game();
        game3.startGame(new RandomPlayer(), new GPTreePlayer(TreeFactory.fullTree(10)));
        System.out.println("Player " + game3.colourOfWinner() + " has won!\n" + game);

        Game gama4 = new Game();
        gama4.startGame(new GPTreePlayer(TreeFactory.fullTree(2)), new GPTreePlayer(TreeFactory.fullTree(12)));
        System.out.println("Player " + gama4.colourOfWinner() + " has won!\n" + game);
    }
}
