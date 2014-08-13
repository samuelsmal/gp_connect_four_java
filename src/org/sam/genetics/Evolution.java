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
    private int winAgainstGPPlayerWeight;
    private int drawWeight;
    private int numberOfPlayersToReturn;

    public static class Builder {
        private int numberOfGenerations = 50;
        private int numberOfPlayers = 200;
        private boolean mutationOn = false;
        private int winAgainstRandomPlayerWeight = 2;
        private int winAgainstGPPlayerWeight = 2;
        private int drawWeight = 1;
        private int depthOfTrees = 4;

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

        public Builder depthOfTrees(int depthOfTrees) {
            this.depthOfTrees = depthOfTrees;
            return this;
        }

        public Builder winAgainstGPPlayerWeight(int winAgainstGPPlayerWeight) {
            this.winAgainstGPPlayerWeight = winAgainstGPPlayerWeight;
            return this;
        }

        public Evolution build() {
            return new Evolution(numberOfPlayers, depthOfTrees, numberOfGenerations, mutationOn, winAgainstRandomPlayerWeight, winAgainstGPPlayerWeight, drawWeight);
        }
    }

    public Evolution(int numberOfPlayers, int depthOfTrees, int numberOfGenerations, boolean mutationOn, int winAgainstRandomPlayerWeight, int winAgainstGPPlayerWeight, int drawWeight) {
        this.numberOfGenerations = numberOfGenerations;
        this.mutationOn = mutationOn;
        this.winAgainstRandomPlayerWeight = winAgainstRandomPlayerWeight;
        this.winAgainstGPPlayerWeight = winAgainstGPPlayerWeight;
        this.drawWeight = drawWeight;

        if (mutationOn) {
            numberOfPlayersToReturn = (int)Math.sqrt((double)numberOfPlayers);
        } else {
            numberOfPlayersToReturn = (int)(0.5d * (1 + Math.sqrt(4d * (double)numberOfPlayers + 1)));
        }

        System.out.println(
                "Settings:"
                + "\n\tnumberOfPlayers: " + numberOfPlayers
                + "\n\tnumberOfGenerations: " + numberOfGenerations
                + "\n\tdepthOfTrees: " + depthOfTrees
                + "\n\tmutationOn: " + mutationOn
                + "\n\twinAgainstRandomPlayerWeight: " + winAgainstRandomPlayerWeight
                + "\n\twinAgainstGPPlayerWeight: " + winAgainstGPPlayerWeight
                + "\n\tdrawWeight: " + drawWeight
        );

        players = new ArrayList<>(numberOfPlayers);

        for (int i = 0; i < numberOfPlayers; i++) {
            Tree tree = TreeFactory.fullTree(depthOfTrees);
            tree.setTitle(Integer.toString(i));

            players.add(new GPTreePlayer(tree));
            //players.add(new GPTreePlayer(TreeFactory.halfTree(depthOfTrees))); // TODO make this an option
        }
    }

    public GPTreePlayer evolve() {
        for (int i = 0; i < numberOfGenerations; i++) {
            System.out.println("Generation " + (i + 1) + " / " + numberOfGenerations);
            System.out.println("\t" + players.get(0));


            players = new Tournament(players, numberOfPlayersToReturn, winAgainstRandomPlayerWeight, winAgainstGPPlayerWeight, drawWeight).runTournament();
            toTheNextGeneration();

            System.out.println("\tWinner count of nodes: " + players.get(0).getTree().flatten().size());

        }

        return players.get(0);
    }

    private void toTheNextGeneration() {
        int originalGenerationSize = players.size();

        for (int i = 0; i < originalGenerationSize; i++) {
            for (int j = i+1; j < originalGenerationSize; j++) {
                // This has to be done this way.
                // As each cross-over changes the tree, it exchanges the leafs.
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
