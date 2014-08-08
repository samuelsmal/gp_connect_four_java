package org.sam.Tournament;

import org.sam.game.GPTreePlayer;
import org.sam.game.Game;
import org.sam.game.Player;
import org.sam.tree.TreeFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by samuel on 08/08/14.
 */
public class Tournament {
    private List<PlayerEnlist> players;
    private int numberOfPlayersToReturn;

    public Tournament(List<GPTreePlayer> gpTreePlayers, int numberOfPlayersToReturn) {
        this.numberOfPlayersToReturn = numberOfPlayersToReturn;

        players = new ArrayList<>();

        for(GPTreePlayer gpTreePlayer : gpTreePlayers) {
            players.add(new PlayerEnlist(gpTreePlayer));
        }
    }

    /**
     * Runs the tournament. Everybody against everybody else, two times.
     * @return the best 64 players
     */
    public List<GPTreePlayer> runTournament() {
        System.out.print("Tournament has started! " + new Date(System.currentTimeMillis()));

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

        Collections.sort(players);

        List<GPTreePlayer> winners = new ArrayList<>();

        // look out here...
        for (int i = 0; i < numberOfPlayersToReturn; i++) {
            winners.add(players.get(i).player);
        }

        System.out.println("Tournament has ended! The winner won "
                + players.get(0).matchesWon + " times. Second placed "
                + players.get(1).matchesWon + " times.");

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
