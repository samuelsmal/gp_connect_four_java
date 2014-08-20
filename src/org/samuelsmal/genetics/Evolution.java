package org.samuelsmal.genetics;

import org.samuelsmal.Tournament.Tournament;
import org.samuelsmal.game.GPTreePlayer;
import org.samuelsmal.tree.Tree;
import org.samuelsmal.tree.TreeFactory;

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
    private int positionWeight; // if -1, then the positions are given the same weight as there are group members.
    private boolean maxApproach;

    public static class Builder {
        private int numberOfGenerations = 50;
        private int numberOfPlayers = 200;
        private boolean mutationOn = false;
        private int winAgainstRandomPlayerWeight = 2;
        private int winAgainstGPPlayerWeight = 2;
        private int drawWeight = 1;
        private int depthOfTrees = 4;
        private int positionWeight = 1;
        private boolean rampedHalfAndHalf = false;
        private boolean maxApproach = false;

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

        public Builder drawWeight(int drawWeight) {
            this.drawWeight = drawWeight;
            return this;
        }

        public Builder rampedHalfAndHalf(boolean rampedHalfAndHalf) {
            this.rampedHalfAndHalf = rampedHalfAndHalf;
            return this;
        }

        public Builder maxApproach(boolean maxApproach) {
            this.maxApproach = maxApproach;
            return this;
        }

        public Builder positionWeight(int positionWeight) {
            this.positionWeight = positionWeight;
            return this;
        }

        public Evolution build() {
            return new Evolution(numberOfPlayers,
                    depthOfTrees,
                    numberOfGenerations,
                    mutationOn,
                    winAgainstRandomPlayerWeight,
                    winAgainstGPPlayerWeight,
                    drawWeight,
                    rampedHalfAndHalf,
                    maxApproach,
                    positionWeight);
        }
    }

    public Evolution(int numberOfPlayers,
                     int depthOfTrees,
                     int numberOfGenerations,
                     boolean mutationOn,
                     int winAgainstRandomPlayerWeight,
                     int winAgainstGPPlayerWeight,
                     int drawWeight,
                     boolean rampedHalfAndHalf,
                     boolean maxApproach,
                     int positionWeight) {
        this.numberOfGenerations = numberOfGenerations;
        this.mutationOn = mutationOn;
        this.winAgainstRandomPlayerWeight = winAgainstRandomPlayerWeight;
        this.winAgainstGPPlayerWeight = winAgainstGPPlayerWeight;
        this.drawWeight = drawWeight;
        this.maxApproach = maxApproach;
        this.positionWeight = positionWeight;

        if (mutationOn) {
            numberOfPlayersToReturn = (int)Math.sqrt((double)numberOfPlayers);
        } else {
            numberOfPlayersToReturn = (int)(0.5d * (1 + Math.sqrt(4d * (double)numberOfPlayers + 1)));
        }

        System.out.println(
                "Evolution settings:"
                + "\n\tnumberOfPlayers: " + numberOfPlayers
                + "\n\tnumberOfGenerations: " + numberOfGenerations
                + "\n\tdepthOfTrees: " + depthOfTrees
                + "\n\tmutationOn: " + mutationOn
                + "\n\twinAgainstRandomPlayerWeight: " + winAgainstRandomPlayerWeight
                + "\n\twinAgainstGPPlayerWeight: " + winAgainstGPPlayerWeight
                + "\n\tdrawWeight: " + drawWeight
                + "\n\tpositionWeight:" + positionWeight
                + "\n\trampedHalfAndHalf: " + rampedHalfAndHalf
                + "\n\tmaxApproach: " + maxApproach
        );

        players = new ArrayList<>(numberOfPlayers);

        if (rampedHalfAndHalf) {
            for (int i = 0; i < numberOfPlayers / 2; i++) {
                Tree fullTree = TreeFactory.fullTree(depthOfTrees);
                fullTree.setTitle("FT" + i);

                Tree halfTree = TreeFactory.halfTree(depthOfTrees);
                halfTree.setTitle("HT" + i);

                players.add(new GPTreePlayer(fullTree));
                players.add(new GPTreePlayer(halfTree));
            }
        } else {
            for (int i = 0; i < numberOfPlayers; i++) {
                Tree tree = TreeFactory.fullTree(depthOfTrees);
                tree.setTitle(Integer.toString(i));

                players.add(new GPTreePlayer(tree));
            }
        }
    }

    public GPTreePlayer evolve() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();

        System.out.println("The group size is " + (players.size() /  + availableProcessors) + ". The number of available processors is " + availableProcessors);

        for (int i = 0; i < numberOfGenerations; i++) {
            System.out.println("Generation " + (i + 1) + " / " + numberOfGenerations);
            System.out.println("\t" + players.get(0));

            players = new Tournament(players, numberOfPlayersToReturn, winAgainstRandomPlayerWeight, winAgainstGPPlayerWeight, drawWeight, maxApproach, positionWeight).runTournament();
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
