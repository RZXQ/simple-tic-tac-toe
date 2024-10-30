package stage_2;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		var str = scanner.nextLine();

		int pointer = 0;

		char[][] arr = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				arr[i][j] = str.charAt(pointer++);
			}
		}

		printArr(arr);
	}

	public static void printArr(char[][] arr) {
		System.out.println("---------");
		for (int i = 0; i < arr.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.print("|");
			System.out.println();
		}
		System.out.println("---------");
	}

}
