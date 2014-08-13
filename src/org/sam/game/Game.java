package org.sam.game;

import java.util.Arrays;

/**
 * Created by samuel on 27/07/14.
 */
public class Game {
    public static final int BOARD_WIDTH = 7;
    public static final int BOARD_HEIGHT = 6;
    public static final char FIRST_PLAYER_COLOUR = 'x';
    public static final char SECOND_PLAYER_COLOUR = 'o';
    public static final char EMPTY_STONE_COLOUR = '_';

    private boolean isRunning;
    private char winnerColour;

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
     * Copy constructor
     * @param other
     */
    public Game(Game other) {
        board = new char[BOARD_HEIGHT][BOARD_WIDTH];

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                board[i][j] = other.board[i][j];
            }
        }
    }

    /**
     *
     * @param firstPlayer
     * @param secondPlayer
     * @return colour of winner 'x' for first, 'o' for second, '_' for a draw
     */
    public char startGame(Player firstPlayer, Player secondPlayer) {
        board = new char[][]{
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_'}
        };

        isRunning = true;

        boolean firstPlayersTurn = true;

        winnerColour = EMPTY_STONE_COLOUR;

        while (isRunning) {
            if (firstPlayersTurn) {
                try {
                    checkMove(insertStoneInColumn(firstPlayer.play(this, FIRST_PLAYER_COLOUR, SECOND_PLAYER_COLOUR), FIRST_PLAYER_COLOUR));
                    winnerColour = FIRST_PLAYER_COLOUR;
                    firstPlayersTurn = false;
                } catch (ColumnFullException e) {
                    System.out.println("Column already full! Or wrong number. Choose another one. " + e.getMessage());
                }
            } else {
                try {
                    checkMove(insertStoneInColumn(secondPlayer.play(this, SECOND_PLAYER_COLOUR, FIRST_PLAYER_COLOUR), SECOND_PLAYER_COLOUR));
                    firstPlayersTurn = true;
                    winnerColour = SECOND_PLAYER_COLOUR;
                } catch (ColumnFullException e) {
                    System.out.println("Column already full! Or wrong number. Choose another one. " + e.getMessage());
                }
            }
        }

        return winnerColour;
    }

    /**
     * @pre The game is in a correct state.
     * @param point
     * @return colour of winner, EMPTY_STONE_COLOUR ("_") otherwise.
     */
    public char checkMove(Point point) {
        char currentStoneColour;

        currentStoneColour = checkEast(point.x, point.y);
        if (currentStoneColour != EMPTY_STONE_COLOUR) {
            isRunning = false;
            winnerColour = currentStoneColour;
        }

        currentStoneColour = checkEastSouth(point.x, point.y);
        if (currentStoneColour != EMPTY_STONE_COLOUR) {
            isRunning = false;
            winnerColour = currentStoneColour;
        }

        currentStoneColour = checkSouth(point.x, point.y);
        if (currentStoneColour != EMPTY_STONE_COLOUR) {
            isRunning = false;
            winnerColour = currentStoneColour;
        }

        currentStoneColour = checkWest(point.x, point.y);
        if (currentStoneColour != EMPTY_STONE_COLOUR) {
            isRunning = false;
            winnerColour = currentStoneColour;
        }

        currentStoneColour = checkWestSouth(point.x, point.y);
        if (currentStoneColour != EMPTY_STONE_COLOUR) {
            isRunning = false;
            winnerColour = currentStoneColour;
        }

        return winnerColour;
    }

    public char checkBoard() {
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                checkMove(new Point(i, j));
            }
        }

        return winnerColour;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    public boolean getRunning() { return isRunning; }


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

    public Point insertStoneInColumn(int col, char colour) throws ColumnFullException {
        if (col < 0 || col > BOARD_WIDTH - 1) {
            throw new ColumnFullException();
        }

        for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {
            if (board[i][col] == EMPTY_STONE_COLOUR) {
                board[i][col] = colour;
                return new Point(col, i);
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

    public boolean hasEnded() {
        return !isRunning;
    }

    public char getWinnerColour() {
        if (isRunning) {
            return EMPTY_STONE_COLOUR;
        }

        return winnerColour;
    }

    private char checkEast(int x, int y) { return checkVector(x, y, 1, 0); }
    private char checkEastSouth(int x, int y) { return checkVector(x, y, 1, 1); }
    private char checkSouth(int x, int y) { return checkVector(x, y, 0, 1); }
    private char checkWestSouth(int x, int y) { return  checkVector(x, y, -1, 1); }
    private char checkWest(int x, int y) { return checkVector(x, y, -1, 0); }
    private char checkNorthWest(int x, int y) { return checkVector(x, y, -1, -1); }
    private char checkNorthEast(int x, int y) { return  checkVector(x, y, 1, -1); }


    private char checkVector(int x, int y, int dx, int dy) {
        char stoneColourToCheckAgainst = getColourOfStone(x, y);

        if (stoneColourToCheckAgainst == EMPTY_STONE_COLOUR) {
            return EMPTY_STONE_COLOUR;
        }

        int correctStones = 1;

        for (int i = 1; (i < 4)
                && (x + i*dx < BOARD_WIDTH)
                && (y + i*dy < BOARD_HEIGHT)
                && (y + i*dy >= 0)
                && (x + i*dx >= 0); i++) {
            if (stoneColourToCheckAgainst == getColourOfStone(x + i * dx, y + i * dy)) {
                ++correctStones;
            }
        }

        return (correctStones < 4 ? EMPTY_STONE_COLOUR : stoneColourToCheckAgainst);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            stringBuilder.append("|");
            for (int j = 0; j < BOARD_WIDTH; j++) {
                stringBuilder.append(board[i][j]);
                stringBuilder.append("|");
            }

            stringBuilder.append("\n");
        }

        stringBuilder.append("=");

        for (int i = 0; i < BOARD_WIDTH; i++) {
            stringBuilder.append("==");
        }

        stringBuilder.append("\n").append("|");

        for (int i = 0; i < BOARD_WIDTH; i++) {
            stringBuilder.append(i).append("|");
        }

        return stringBuilder.toString();
    }

    public char[][] getBoardCopy() {
        char[][] boardCopy = new char[BOARD_HEIGHT][BOARD_WIDTH];

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }

        return boardCopy;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public class Point {
        public int x;
        public int y;

        Point(int x, int y) {
            this.x = x; this.y = y;
        }
    }
}
