package stage_3;

import java.util.Scanner;

public class TicTacToe {
    private static char[][] board;

    public static void main(String[] args) {
        createBoard();
        printBoard();
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

    public static void printBoard() {
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
}