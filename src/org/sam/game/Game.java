package org.sam.game;

import java.util.Arrays;

/**
 * Created by samuel on 27/07/14.
 */
public class Game {
    private static int BOARD_WIDTH = 7;
    private static int BOARD_HEIGHT = 6;

    private char[][] board;

    public Game() {
        board = new char[][]{
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'}
        };
    }

    public Game(char[][] board) {
        this.board = board;
    }

    /**
     * Returns colour of stone at the given coordinates.
     * Can be either
     *      '_' for nothing yet,
     *      'o' or,
     *      'x'
     * @param x 0 <= x < 7
     * @param y 0 <= y < 6
     * @return char of the color at the given coordinates.
     */
    public char getColourOfStone(int x, int y) {
        return board[y][x];
    }

    public void insertStoneInColumn(int col, char colour) throws ColumnFullException {
        for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {
            if (board[i][col] == '_') {
                board[i][col] = colour;
                return ;
            }
        }

        throw new ColumnFullException("Given parameters: " + col + "(column), " + colour + "colour");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game oGame = (Game)o;

        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                if (oGame.getColourOfStone(i, j) != getColourOfStone(i, j)) return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                stringBuilder.append(board[i][j]);
            }

            stringBuilder.append('\n');
        }

        for (int i = 0; i < BOARD_WIDTH; i++) {
            stringBuilder.append(i);
        }

        return stringBuilder.toString();
    }
}
