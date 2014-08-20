package org.samuelsmal.tree;

import org.samuelsmal.game.GPPlayer;

/**
 * Created by samuel on 14/08/14.
 */
public class TournamentEntry implements Comparable<TournamentEntry> {
    private GPPlayer player;
    private int matchesWon;

    public TournamentEntry(GPPlayer player) {
        this.player = player;
        matchesWon = 0;
    }

    public void newMatchWon() {
        matchesWon++;
    }

    public int matchesWon() {
        return matchesWon;
    }

    public GPPlayer getPlayer() {
        return player;
    }

    @Override
    public int compareTo(TournamentEntry o) {
        return o.matchesWon - matchesWon;
    }

    @Override
    public String toString() {
        return player.getClass().getName();
    }
}
