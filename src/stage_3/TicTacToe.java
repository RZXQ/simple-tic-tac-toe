package stage_3;

import java.util.Scanner;

public class TicTacToe {
    private static char[][] board;

    public static void main(String[] args) {
        createBoard();
        printBoard();
        checkStatus();
    }


    private static void createBoard() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int index = 0;

        board = new char[3][3];
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

    private static void checkStatus() {
        if (isImpossible()) {

            System.out.println("Impossible");
        } else if (hasWon('X')) {
            System.out.println("X wins");
        } else if (hasWon('O')) {
            System.out.println("O wins");

        } else if (notFinish()) {
            System.out.println("Game not finished");
        } else if (isDraw()) {
            System.out.println("Draw");
        }
    }

    private static boolean hasWon(char ch) {
        return threeCharsOnRowOrColumn(ch) || threeCharsOnDiagnosis(ch);
    }

    private static boolean isImpossible() {
        return hasWon('X') && hasWon('O') || (checkDifferences() >= 2);
    }

    private static int checkDifferences() {
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

    private static boolean notFinish() {
        return !hasWon('X') && !hasWon('O') && hasEmptyCell();
    }

    private static boolean isDraw() {
        return !(threeCharsOnDiagnosis('X') || threeCharsOnDiagnosis('O') || hasEmptyCell());
    }

    private static boolean threeCharsOnRowOrColumn(char ch) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == ch && board[i][1] == ch && board[i][2] == ch || board[0][i] == ch && board[1][i] == ch && board[2][i] == ch) {
                return true;
            }
        }
        return false;
    }

    private static boolean threeCharsOnDiagnosis(char ch) {
        return board[0][0] == ch && board[1][1] == ch && board[2][2] == ch || board[2][0] == ch && board[1][1] == ch && board[0][2] == ch;
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