package org.sam.genetics;

import org.sam.Tournament.Tournament;
import org.sam.game.GPTreePlayer;
import org.sam.tree.Tree;
import org.sam.tree.TreeFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Runs the tournaments, does the genetics... does ALL OF IT!
 * Created by samuel on 08/08/14.
 */
public class Evolution {
    private List<GPTreePlayer> players;

    public Evolution(int numberOfPlayers) {
        players = new ArrayList<>();

        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new GPTreePlayer(TreeFactory.fullTree(5)));
        }
    }

    public GPTreePlayer evolve(int numberOfGenerations) {
        for (int i = 0; i < numberOfGenerations; i++) {
            System.out.println("Generation " + (i + 1) + " / " + numberOfGenerations);

            Tournament tournament = new Tournament(players, 64);
            players = tournament.runTournament();

            toTheNextGeneration();
        }

        return players.get(0);
    }

    private void toTheNextGeneration() {
        int originalGenerationSize = players.size();

        for (int i = 0; i < originalGenerationSize; i++) {
            for (int j = i+1; j < originalGenerationSize; j++) {
                Tree t1 = new Tree(players.get(i).getTree());
                Tree t2 = new Tree(players.get(j).getTree());

                Genetics.crossOver(t1, t2);

                players.add(new GPTreePlayer(t1));
                players.add(new GPTreePlayer(t2));
            }

            boolean mutationOn = false;
            if (mutationOn) {

                Tree t3 = new Tree(players.get(i).getTree());
                Genetics.mutate(t3);

                players.add(new GPTreePlayer(t3));
            }
        }
    }
}
