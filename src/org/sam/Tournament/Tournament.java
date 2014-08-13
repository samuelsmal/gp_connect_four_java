package org.sam.Tournament;

import org.sam.Random.GPRandom;
import org.sam.game.GPTreePlayer;
import org.sam.game.Game;
import org.sam.game.RandomPlayer;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by samuel on 08/08/14.
 */
public class Tournament {
    private List<PlayerEnlist> players;
    private int numberOfPlayersToReturn;
    private int winAgainstRandomPlayerWeight;
    private int winAgainstGPPlayerWeight;
    private int drawWeight;
    private int groupSize;
    private boolean maxApproach;

    public Tournament(List<GPTreePlayer> gpTreePlayers, int numberOfPlayersToReturn, int winAgainstRandomPlayerWeight, int winAgainstGPPlayerWeight, int drawWeight, boolean maxApproach) {
        this.numberOfPlayersToReturn = numberOfPlayersToReturn;
        this.winAgainstRandomPlayerWeight = winAgainstRandomPlayerWeight;
        this.winAgainstGPPlayerWeight = winAgainstGPPlayerWeight;
        this.drawWeight = drawWeight;
        this.maxApproach = maxApproach;

        groupSize = gpTreePlayers.size() / Runtime.getRuntime().availableProcessors();

        if (groupSize == 0) {
            groupSize = gpTreePlayers.size();
        }

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
        System.out.println("Tournament has started! --- " + new Date(System.currentTimeMillis()) + " --- ");

        Collections.shuffle(players, GPRandom.INSTANCE.getRand());

        try {
            playerTournament();
            randomPlayerTournament();
        } catch (InterruptedException  e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<GPTreePlayer> winners = new ArrayList<>(numberOfPlayersToReturn);
        System.out.println("\nThe best evolved players: (wins EP / wins RP / draws EP / draws RP / matches / treetitle_geneticCount)");
        PlayerEnlist p;

        if (!maxApproach) {
            // using a weighted approach, see overall()-method below for more information
            Collections.sort(players);
            for (int i = 0; i < numberOfPlayersToReturn && i < players.size(); i++) {
                p = players.get(i);

                winners.add(p.player);
                System.out.println("\t" + i + " : "
                                + p.matchesWonAgainstPlayer + " / "
                                + p.matchesWonAgainstRandom + " / "
                                + p.drawAgainstPlayer + " / "
                                + p.drawAgainstRandom + " / "
                                + p.matches + " / "
                                + p.player.getTree().getTitle() + "_" + p.player.getTree().getGeneticCount()

                );
            }
        } else {

            // using the max approach

            // Sort by random
            Collections.sort(players, new Comparator<PlayerEnlist>() {
                @Override
                public int compare(PlayerEnlist o1, PlayerEnlist o2) {
                    return o2.matchesWonAgainstRandom - o1.matchesWonAgainstRandom;
                }
            });
            for (int i = 0; i < numberOfPlayersToReturn / 2 && i < players.size(); i++) {
                p = players.get(0);
                winners.add(p.player);
                System.out.println("\t" + i + " : "
                                + p.matchesWonAgainstPlayer + " / "
                                + p.matchesWonAgainstRandom + " / "
                                + p.drawAgainstPlayer + " / "
                                + p.drawAgainstRandom + " / "
                                + p.matches + " / "
                                + p.player.getTree().getTitle() + "_" + p.player.getTree().getGeneticCount()
                );
                players.remove(0);
            }

            // Sort by player
            Collections.sort(players, new Comparator<PlayerEnlist>() {
                @Override
                public int compare(PlayerEnlist o1, PlayerEnlist o2) {
                    return o2.matchesWonAgainstPlayer - o1.matchesWonAgainstPlayer;
                }
            });

            for (int i = 0; i < numberOfPlayersToReturn / 2 && i < players.size(); i++) {
                p = players.get(0);
                winners.add(p.player);
                System.out.println("\t" + i + " : "
                                + p.matchesWonAgainstPlayer + " / "
                                + p.matchesWonAgainstRandom + " / "
                                + p.drawAgainstPlayer + " / "
                                + p.drawAgainstRandom + " / "
                                + p.matches + " / "
                                + p.player.getTree().getTitle() + "_" + p.player.getTree().getGeneticCount()
                );
                players.remove(0);
            }

            // Mostly for the last selection of the overall winner.
            Collections.shuffle(winners, GPRandom.INSTANCE.getRand());
        }

        return winners;
    }

    private void randomPlayerTournament() {
        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(threads);

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

        try {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println("randomPlayerTournament interrupted\n" + e.getMessage());
        }

    }

    private void randomPlayerGroup(int start, int end) {
        Game game = new Game();
        RandomPlayer randomPlayer = new RandomPlayer();

        for (int i = start; i < end && i < players.size(); i++) {
            PlayerEnlist player = players.get(i);

            for (int j = 0; j < groupSize; j++) {
                game.startGame(player.player, randomPlayer);
                player.newMatch();
                if (game.colourOfWinner() == Game.FIRST_PLAYER_COLOUR) {
                    player.newRandomMatchWon();
                } else if (game.colourOfWinner() == Game.EMPTY_STONE_COLOUR) {
                    player.newRandomMatchDraw();
                }

                game.startGame(randomPlayer, player.player);
                player.newMatch();
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

        Collections.shuffle(players, GPRandom.INSTANCE.getRand());

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

        try {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println("playerTournament interrupted\n" + e.getMessage());
        }
    }

    private void groupTournament(int start, int end) {
        Game game = new Game();

        for (int i = start; i < players.size() && i < end; i++) {
            for (int j = start; j < players.size() && j < end; j++) {
                if (i != j) {
                    PlayerEnlist p1 = players.get(i);
                    PlayerEnlist p2 = players.get(j);

                    p1.newMatch();
                    p2.newMatch();

                    game.startGame(p1.player, p2.player);

                    // A draw is still possible
                    if (Game.FIRST_PLAYER_COLOUR == game.colourOfWinner()) {
                        p1.newPlayerMatchWon();
                    } else if (Game.SECOND_PLAYER_COLOUR == game.colourOfWinner()) {
                        p2.newPlayerMatchWon();
                    } else {
                        p1.newPlayerMatchDraw();
                        p2.newPlayerMatchDraw();
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
        public int matches = 0; // 2 * times hundred against the random player

        private PlayerEnlist(GPTreePlayer player) {

            this.player = player;
            matchesWonAgainstRandom = 0;
            matchesWonAgainstPlayer = 0;
            drawAgainstPlayer = 0;
            drawAgainstRandom = 0;
        }

        public void newMatch() {
            synchronized (this) {
                matches++;
            }
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
            if (matches == 0) matches = 1;

            return (matchesWonAgainstRandom * winAgainstRandomPlayerWeight + matchesWonAgainstPlayer * winAgainstGPPlayerWeight + drawWeight * (drawAgainstRandom + drawAgainstPlayer)) / matches;
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
