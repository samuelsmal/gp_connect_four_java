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
    private int numberOfGenerations;
    private boolean mutationOn = false;
    private int winAgainstRandomPlayerWeight;
    private int numberOfPlayersToReturn;

    public static class Builder {
        private int numberOfGenerations;
        private int numberOfPlayers;
        private boolean mutationOn = false;
        private int winAgainstRandomPlayerWeight = 1;

        public Builder () {}

        public Builder numberOfGenerations(int numberOfGenerations) {
            this.numberOfGenerations = numberOfGenerations;
            return this;
        }

        public Builder numberOfPlayers(int numberOfPlayers) {
            this.numberOfPlayers = numberOfPlayers;
            return this;
        }

        public Builder mutationOn(boolean mutationOn) {
            this.mutationOn = mutationOn;
            return this;
        }

        public Builder winAgainstRandomPlayerWeight(int winAgainstRandomPlayerWeight) {
            this.winAgainstRandomPlayerWeight = winAgainstRandomPlayerWeight;
            return this;
        }

        public Evolution build() {
            return new Evolution(numberOfPlayers, numberOfGenerations, mutationOn, winAgainstRandomPlayerWeight);
        }
    }

    public Evolution(int numberOfPlayers, int numberOfGenerations, boolean mutationOn, int winAgainstRandomPlayerWeight) {
        this.numberOfGenerations = numberOfGenerations;
        this.mutationOn = mutationOn;
        this.winAgainstRandomPlayerWeight = winAgainstRandomPlayerWeight;

        if (mutationOn) {
            numberOfPlayersToReturn = (int)Math.sqrt((double)numberOfPlayers);
        } else {
            numberOfPlayersToReturn = (int)(0.5d * (1 + Math.sqrt(4d * (double)numberOfPlayers + 1)));
        }


        players = new ArrayList<>(numberOfPlayers);
        System.out.println("players : " + players.size() + " (" + numberOfPlayers + ")" );


        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new GPTreePlayer(TreeFactory.fullTree(8)));
        }
    }

    public GPTreePlayer evolve() {
        for (int i = 0; i < numberOfGenerations; i++) {
            System.out.println("Generation " + (i + 1) + " / " + numberOfGenerations);

            players = new Tournament(players, numberOfPlayersToReturn, winAgainstRandomPlayerWeight).runTournament();

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

            if (mutationOn) {
                Tree t3 = new Tree(players.get(i).getTree());
                Genetics.mutate(t3);

                players.add(new GPTreePlayer(t3));
            }
        }
    }
}
