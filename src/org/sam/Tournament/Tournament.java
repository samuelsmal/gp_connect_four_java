package org.sam.Tournament;

import org.sam.game.GPTreePlayer;
import org.sam.game.Game;
import org.sam.game.Player;
import org.sam.tree.TreeFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by samuel on 08/08/14.
 */
public class Tournament {
    private List<PlayerEnlist> players;

    public Tournament(List<GPTreePlayer> gpTreePlayers) {
        players = new ArrayList<>();

        for(GPTreePlayer gpTreePlayer : gpTreePlayers) {
            players.add(new PlayerEnlist(gpTreePlayer));
        }
    }

    public Tournament(int numberOfPlayers) {
        players = new ArrayList<>();

        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new PlayerEnlist(new GPTreePlayer(TreeFactory.fullTree(4))));
        }
    }

    public List<GPTreePlayer> runTournament() {
        // check this out:
        // http://stackoverflow.com/questions/12845881/java-splitting-work-to-multiple-threads
        // http://stackoverflow.com/questions/19749136/java-multithreading-one-big-loop
        // http://stackoverflow.com/questions/5686200/parallelizing-a-for-loop
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.size(); j++) {
                if (i != j) {
                    Game game = new Game();
                    game.startGame(players.get(i).player, players.get(j).player);

                    // A draw is still possible
                    if (Game.FIRST_PLAYER_COLOUR == game.colourOfWinner()) {
                        players.get(i).newMatchWon();
                    } else if (Game.SECOND_PLAYER_COLOUR == game.colourOfWinner()) {
                        players.get(j).newMatchWon();
                    }
                }
            }
        }

        System.out.println("run complete");

        Collections.sort(players);

        List<GPTreePlayer> winners = new ArrayList<>();

        // look out here...
        for (int i = 0; i < 64; i++) {
            System.out.println("Player " + i + ": won " + players.get(i).matchesWon + " times.");
            winners.add(players.get(i).player);
        }

        return winners;
    }

    private class PlayerEnlist implements Comparable<PlayerEnlist> {
        public GPTreePlayer player;
        public int matchesWon = 0;

        private PlayerEnlist(GPTreePlayer player) {
            this.player = player;
        }

        // Synchronized not really needed. See Java Spec
        public synchronized void newMatchWon() {
            matchesWon++;
        }

        /**
         * @param o the object to be compared.
         * @return a negative integer, zero, or a positive integer as this object
         * is less than, equal to, or greater than the specified object.
         * @throws NullPointerException if the specified object is null
         * @throws ClassCastException   if the specified object's type prevents it
         *                              from being compared to this object.
         */
        @Override
        public int compareTo(PlayerEnlist o) {
            return o.matchesWon - matchesWon;
        }
    }
}
