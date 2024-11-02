package stage_4;

import java.util.Scanner;

public class TicTacToe {
    private static final Scanner scanner = new Scanner(System.in);

    private static char[][] board;

    public static void main(String[] args) {
        setupBoard();
        printBoard();
        firstMove();
        printBoard();
    }

    private static void firstMove() {
        String userInput;

        do {
            userInput = scanner.nextLine();
        } while (!checkValidInput(userInput));

        String[] tokenArr = userInput.trim().split("\\s+");
        board[Integer.parseInt(tokenArr[0]) - 1][Integer.parseInt(tokenArr[1]) - 1] = 'X';
    }

    private static boolean checkValidInput(String userInput) {
        boolean flag = false;

        try {
            validInput(userInput);
            flag = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return flag;
    }

    private static void validInput(String userInput) throws Exception {
        String[] tokenArr = userInput.trim().split("\\s+");
        if (tokenArr.length == 1) {
            throw new InvalidInputException();
        }

        int row, col;

        try {
            row = Integer.parseInt(tokenArr[0]);
            col = Integer.parseInt(tokenArr[1]);
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }

        if (row < 1 || row > 3 || col < 1 || col > 3) {
            throw new OutofBoundsException();
        } else if (board[row - 1][col - 1] != '_') {
            throw new OccupiedException();
        }

    }

    private static void setupBoard() {
        String input = scanner.nextLine();
        board = new char[3][3];

        int index = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = input.charAt(index++);
            }
        }
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

    private static void checkGameStatus() {
        if (isGameImpossible()) {
            System.out.println("Impossible");
        } else if (hasPlayerWon('X')) {
            System.out.println("X wins");
        } else if (hasPlayerWon('O')) {
            System.out.println("O wins");
        } else if (isGameNotFinished()) {
            System.out.println("Game not finished");
        } else if (isGameDraw()) {
            System.out.println("Draw");
        }
    }

    private static boolean isGameImpossible() {
        return hasPlayerWon('X') && hasPlayerWon('O') || (getPlayerMoveDifference() >= 2);
    }

    private static boolean hasPlayerWon(char ch) {
        return hasThreeInARowOrColumn(ch) || hasThreeInADiagonal(ch);
    }

    private static boolean hasThreeInARowOrColumn(char ch) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == ch && board[i][1] == ch && board[i][2] == ch || board[0][i] == ch && board[1][i] == ch && board[2][i] == ch) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasThreeInADiagonal(char ch) {
        return board[0][0] == ch && board[1][1] == ch && board[2][2] == ch || board[2][0] == ch && board[1][1] == ch && board[0][2] == ch;
    }

    private static int getPlayerMoveDifference() {
        int countX = 0;
        int countY = 0;

        for (var chars : board) {
            for (char aChar : chars) {
                if (aChar == 'X') {
                    countX++;
                }
                if (aChar == 'O') {
                    countY++;
                }
            }
        }
        return Math.abs(countX - countY);
    }

    private static boolean isGameNotFinished() {
        return !hasPlayerWon('X') && !hasPlayerWon('O') && hasEmptyCell();
    }

    private static boolean isGameDraw() {
        return !(hasThreeInADiagonal('X') || hasThreeInADiagonal('O') || hasEmptyCell());
    }

    private static boolean hasEmptyCell() {
        boolean hasEmptyCell = false;
        for (var chars : board) {
            for (var aChar : chars) {
                if ('_' == aChar) {
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
