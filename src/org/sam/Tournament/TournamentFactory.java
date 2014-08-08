package org.sam.Tournament;

/**
 * Created by samuel on 08/08/14.
 */
public class TournamentFactory {
    public static Tournament miniTournament() {
        return new Tournament(64);
    }

    public static Tournament tournament() {
        return new Tournament(2048);
    }
}