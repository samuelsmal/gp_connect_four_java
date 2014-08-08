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

    public Tournament(int numberOfPlayers) {
        players = new ArrayList<>();

        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new PlayerEnlist(new GPTreePlayer(TreeFactory.fullTree(4))));
        }
    }

    public List<Player> runTournament() {
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.size(); j++) {
                if (i != j) {
                    Game game = new Game();
                    game.startGame(players.get(i).player, players.get(j).player);

                    // A draw is still possible
                    if (Game.FIRST_PLAYER_COLOUR == game.colourOfWinner()) {
                        players.get(i).matchesWon++;
                    } else if (Game.SECOND_PLAYER_COLOUR == game.colourOfWinner()) {
                        players.get(j).matchesWon++;
                    }
                }
            }
        }

        Collections.sort(players);

        List<Player> winners = new ArrayList<>();

        // look out here...
        for (int i = 0; i < 64; i++) {
            System.out.println("Player " + i + ": won " + players.get(i).matchesWon + " times.");
            winners.add(players.get(i).player);
        }

        return winners;
    }

    private class PlayerEnlist implements Comparable<PlayerEnlist> {
        public Player player;
        public int matchesWon = 0;

        private PlayerEnlist(Player player) {
            this.player = player;
        }

        @Override
        public int compareTo(PlayerEnlist o) {
            return matchesWon - o.matchesWon;
        }
    }
}
