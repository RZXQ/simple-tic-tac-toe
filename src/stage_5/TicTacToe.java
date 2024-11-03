package stage_5;

import java.util.Scanner;

public class TicTacToe {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int BOARD_SIZE = 3;
    private static char[][] board;
    private static boolean flipPlayer;

    public static void main(String[] args) {
        initializeBoard();
        startGame();
    }

    private static void startGame() {
        printBoard();
        while (true) {
            placeSymbol();

            if (isGameOnGoing()) {
                continue;
            }

            printFinalGameStatus();
            break;
        }
    }

    private static void placeSymbol() {
        String userInput = SCANNER.nextLine();
        if (isValidInput(userInput)) {
            char player = togglePlayer();
            String[] tokenArr = userInput.trim().split("\\s+");
            board[Integer.parseInt(tokenArr[0]) - 1][Integer.parseInt(tokenArr[1]) - 1] = player;
            printBoard();
        }
    }

    private static char togglePlayer() {
        flipPlayer = !flipPlayer;

        return flipPlayer ? 'X' : 'O';
    }

    private static boolean isValidInput(String input) {
        try {
            validateInput(input);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static void initializeBoard() {
        board = new char[BOARD_SIZE][BOARD_SIZE];

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = '_';
            }
        }
    }

    private static void validateInput(String input) throws Exception {
        String[] tokenArr = input.trim().split("\\s+");
        if (tokenArr.length != 2) {
            throw new InvalidInputException();
        }

        int row, col;

        try {
            row = Integer.parseInt(tokenArr[0]);
            col = Integer.parseInt(tokenArr[1]);
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }

        if (isOutOfBounds(row) || isOutOfBounds(col)) {
            throw new OutofBoundsException();
        } else if (isCellOccupied(row, col)) {
            throw new OccupiedException();
        }

    }

    private static boolean isOutOfBounds(int index) {
        return index < 1 || index > BOARD_SIZE;
    }

    private static boolean isCellOccupied(int row, int col) {
        return board[row - 1][col - 1] != '_';
    }


    private static void printBoard() {
        System.out.println("---------");
        for (char[] chars : board) {
            System.out.print("| ");
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static void printFinalGameStatus() {
        if (isGameImpossible()) {
            System.out.println("Impossible");
        } else if (hasPlayerWon('X')) {
            System.out.println("X wins");
        } else if (hasPlayerWon('O')) {
            System.out.println("O wins");
        } else if (isGameDraw()) {
            System.out.println("Draw");
        }
    }

    private static boolean isGameImpossible() {
        return hasPlayerWon('X') && hasPlayerWon('O') || (getPlayerMoveDifference() >= 2);
    }

    private static boolean hasPlayerWon(char player) {
        return hasThreeInARowOrColumn(player) || hasThreeInADiagonal(player);
    }

    private static boolean hasThreeInARowOrColumn(char ch) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == ch && board[i][1] == ch && board[i][2] == ch || board[0][i] == ch && board[1][i] == ch && board[2][i] == ch) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasThreeInADiagonal(char player) {
        return board[0][0] == player && board[1][1] == player && board[2][2] == player || board[2][0] == player && board[1][1] == player && board[0][2] == player;
    }

    private static int getPlayerMoveDifference() {
        int countX = 0;
        int countY = 0;

        for (var row : board) {
            for (char cell : row) {
                if (cell == 'X') {
                    countX++;
                }
                if (cell == 'O') {
                    countY++;
                }
            }
        }
        return Math.abs(countX - countY);
    }

    private static boolean isGameOnGoing() {
        return !hasPlayerWon('X') && !hasPlayerWon('O') && hasEmptyCell();
    }

    private static boolean isGameDraw() {
        return !(hasThreeInADiagonal('X') || hasThreeInADiagonal('O') || hasEmptyCell());
    }

    private static boolean hasEmptyCell() {
        boolean hasEmptyCell = false;
        for (var row : board) {
            for (var cell : row) {
                if ('_' == cell) {
                    hasEmptyCell = true;
                    return hasEmptyCell;
                }
            }
        }
        return hasEmptyCell;
    }

}

class OutofBoundsException extends Exception {
    public OutofBoundsException() {
        super("Coordinates should be from 1 to 3!");
    }
}

class OccupiedException extends Exception {
    public OccupiedException() {
        super("This cell is occupied! Choose another one!");
    }
}

class InvalidInputException extends NumberFormatException {
    public InvalidInputException() {
        super("You should enter numbers!");
    }
}
