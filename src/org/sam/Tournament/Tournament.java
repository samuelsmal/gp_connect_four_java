package org.sam.Tournament;

import org.sam.game.GPTreePlayer;
import org.sam.game.Game;
import org.sam.game.Player;
import org.sam.game.RandomPlayer;
import org.sam.tree.TreeFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by samuel on 08/08/14.
 */
public class Tournament {
    private List<PlayerEnlist> players;
    private int numberOfPlayersToReturn;
    private int winAgainstRandomPlayerWeight;

    public Tournament(List<GPTreePlayer> gpTreePlayers, int numberOfPlayersToReturn, int winAgainstRandomPlayerWeight) {
        this.numberOfPlayersToReturn = numberOfPlayersToReturn;
        this.winAgainstRandomPlayerWeight = winAgainstRandomPlayerWeight;

        players = new ArrayList<>(gpTreePlayers.size());

        for(GPTreePlayer gpTreePlayer : gpTreePlayers) {
            players.add(new PlayerEnlist(gpTreePlayer));
        }
    }

    /**
     * Runs the tournament. Everybody against everybody else, two times.
     * @return the best 64 players
     */
    public List<GPTreePlayer> runTournament() {
        System.out.print("Tournament has started! --- " + new Date(System.currentTimeMillis()) + " --- ");

        try {
            playerTournament();
        } catch (InterruptedException  e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        randomPlayerTournament();

        Collections.sort(players);

        List<GPTreePlayer> winners = new ArrayList<>(numberOfPlayersToReturn);

        // look out here...
        System.out.println("The best evolved players: ");
        for (int i = 0; i < numberOfPlayersToReturn && i < players.size(); i++) {
            winners.add(players.get(i).player);
            System.out.println("\t" + i + " : " + players.get(i).matchesWon);
        }

        return winners;
    }

    private void randomPlayerTournament() {
        Game game = new Game();
        RandomPlayer randomPlayer = new RandomPlayer();

        for(PlayerEnlist player : players) {
            game.startGame(player.player, randomPlayer);
            if (game.colourOfWinner() == Game.FIRST_PLAYER_COLOUR) {
                player.newMatchWon(winAgainstRandomPlayerWeight);
            }

            game.startGame(randomPlayer, player.player);
            if (game.colourOfWinner() == Game.SECOND_PLAYER_COLOUR) {
                player.newMatchWon(winAgainstRandomPlayerWeight);
            }
        }
    }

    private void playerTournament() throws InterruptedException, ExecutionException {

        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(threads);

        // check this out:
        // http://stackoverflow.com/questions/12845881/java-splitting-work-to-multiple-threads
        // http://stackoverflow.com/questions/19749136/java-multithreading-one-big-loop
        // http://stackoverflow.com/questions/5686200/parallelizing-a-for-loop

        final int groupSize = players.size() / threads;

        Collections.shuffle(players);

        for (int i = 0; i < threads; i++) {
            final int start = i * groupSize;
            final int end = (i + 1) * groupSize;

            service.execute(new Runnable() {
                @Override
                public void run() {
                    groupTournament(start, end);
                }
            });
        }


        Game game = new Game();
        // TODO: Make groups out of x players. This takes too long. And doesn't produce anything.
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.size(); j++) {
                if (i != j) {
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

        service.shutdown();
    }

    private void groupTournament(int start, int end) {
        Game game = new Game();

        for (int i = start; i < players.size() && i < end; i++) {
            for (int j = start; j < players.size() && j < end; j++) {
                if (i != j) {
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

        public void newMatchWon(int weight) {
            synchronized (this) {
                matchesWon += weight;
            }
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
