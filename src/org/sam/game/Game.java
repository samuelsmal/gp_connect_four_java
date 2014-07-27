package org.sam.game;

/**
 * Created by samuel on 27/07/14.
 */
public class Game {
    private static int BOARD_WIDTH = 6;
    private static int BOARD_HEIGHT = 5;

    private char[][] board = new char[BOARD_WIDTH][BOARD_HEIGHT];

    public Game() {}

    public Game(char[][] board) {
        this.board = board;
    }

    public char getColourOfStone(int x, int y) {
        return board[x][y];
    }
}
