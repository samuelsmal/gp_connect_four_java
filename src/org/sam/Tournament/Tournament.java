package org.sam.Tournament;

import org.sam.Random.GPRandom;
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
    private int winAgainstGPPlayerWeight;
    private int drawWeight;

    public Tournament(List<GPTreePlayer> gpTreePlayers, int numberOfPlayersToReturn, int winAgainstRandomPlayerWeight, int winAgainstGPPlayerWeight, int drawWeight) {
        this.numberOfPlayersToReturn = numberOfPlayersToReturn;
        this.winAgainstRandomPlayerWeight = winAgainstRandomPlayerWeight;
        this.winAgainstGPPlayerWeight = winAgainstGPPlayerWeight;
        this.drawWeight = drawWeight;

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

        Collections.shuffle(players, GPRandom.INSTANCE.getRand());

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
        System.out.println("\nThe best evolved players: (against other evolved players / against random player)");
        for (int i = 0; i < numberOfPlayersToReturn && i < players.size(); i++) {
            winners.add(players.get(i).player);
            System.out.println("\t" + i + " : " + players.get(i).matchesWonAgainstPlayer + " / " + players.get(i).matchesWonAgainstRandom);
        }

        return winners;
    }

    private void randomPlayerTournament() {
        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(threads);

        final int groupSize = players.size() / threads;

        for (int i = 0; i < threads; i++) {
            final int start = i * groupSize;
            final int end = (i + 1) * groupSize;

            service.execute(new Runnable() {
                @Override
                public void run() {
                    randomPlayerGroup(start, end);
                }
            });
        }

        service.shutdown();

    }

    private void randomPlayerGroup(int start, int end) {
        Game game = new Game();
        RandomPlayer randomPlayer = new RandomPlayer();

        for (int i = start; i < end && i < players.size(); i++) {
            PlayerEnlist player = players.get(i);

            for (int j = 0; j < 100; j++) {
                game.startGame(player.player, randomPlayer);
                if (game.colourOfWinner() == Game.FIRST_PLAYER_COLOUR) {
                    player.newRandomMatchWon();
                } else if (game.colourOfWinner() == Game.EMPTY_STONE_COLOUR) {
                    player.newRandomMatchDraw();
                }

                game.startGame(randomPlayer, player.player);
                if (game.colourOfWinner() == Game.SECOND_PLAYER_COLOUR) {
                    player.newRandomMatchWon();
                } else if (game.colourOfWinner() == Game.EMPTY_STONE_COLOUR) {
                    player.newRandomMatchDraw();
                }
            }
        }
    }

    private void playerTournament() throws InterruptedException, ExecutionException {

        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(threads);

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
                        players.get(i).newPlayerMatchWon();
                    } else if (Game.SECOND_PLAYER_COLOUR == game.colourOfWinner()) {
                        players.get(j).newPlayerMatchWon();
                    } else {
                        players.get(i).newPlayerMatchDraw();
                        players.get(j).newPlayerMatchDraw();
                    }
                }
            }
        }
    }

    private class PlayerEnlist implements Comparable<PlayerEnlist> {
        public GPTreePlayer player;
        public int matchesWonAgainstPlayer = 0;
        public int matchesWonAgainstRandom = 0;
        public int drawAgainstPlayer = 0;
        public int drawAgainstRandom = 0;

        private PlayerEnlist(GPTreePlayer player) {
            this.player = player;
        }

        public void newPlayerMatchWon() {
            synchronized (this) {
                matchesWonAgainstPlayer++;
            }
        }

        public void newRandomMatchWon(){
            synchronized (this) {
                matchesWonAgainstRandom++;
            }
        }

        public void newPlayerMatchDraw() {
            synchronized (this) {
                drawAgainstPlayer++;
            }
        }

        public void newRandomMatchDraw() {
            synchronized (this) {
                drawAgainstRandom++;
            }
        }


        public int overall() {
            return matchesWonAgainstRandom * winAgainstRandomPlayerWeight + matchesWonAgainstPlayer * winAgainstGPPlayerWeight + drawWeight * (drawAgainstRandom + drawAgainstPlayer);
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
            return o.overall() - overall();
        }
    }
}
