// Kaiqi Yang: kaiqiy
// Di Mao: dmao1

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class SliderGame {

	//the input size
	int inputSize;

	//the board
	String[][] board;

	//horizontal move sum
	int numH = 0;

	//vertical move sum
	int numV = 0;

	//main function goes here
	public static void main(String[] args) {

		SliderGame sliderGame = new SliderGame();

		sliderGame.inputMatrix();
		sliderGame.caculateMoves();
	}

	//print the board
	public void printMatrix() {
		for (int y = 0; y < inputSize; y++) {
			for (int x = 0; x < inputSize; x++) {

			}
		}
	}

	//use java standard input to load the board
	public void inputMatrix() {

		Scanner scan = new Scanner(System.in);
		//get the size
		inputSize = scan.nextInt();
		//get the next line
		scan.nextLine();

		board = new String[inputSize][inputSize];

		int index = 0;
		while (scan.hasNextLine() && index < inputSize) {

			board[index] = scan.nextLine().split(" ");
			index++;
		}
	}

	//iterate the matrix to check sum possible move
	public void caculateMoves() {
		//caculate the legal moves for vertical and horizontal player
		for (int y = 0; y < inputSize; y++) {
			for (int x = 0; x < inputSize; x++) {
				//caculate the legal moves for horizontal player
				if (board[y][x].equals("H")) {
					numH = numH + countMove(x, y, 'H');
				}

				//caculate the legal moves for vertical player
				if (board[y][x].equals("V")) {
					numV = numV + countMove(x, y, 'V');
				}
			}
		}
		System.out.println(numH);
		System.out.println(numV);
	}

	//count the possible move for each point
	public int countMove(int x, int y, char type) {
		int count = 0;

		if (Character.compare(type, 'H') == 0) {
			if (isLegal(x + 1, y)) { count++; }
			if (isLegal(x, y + 1)) { count++; }
			if (isLegal(x, y - 1)) { count++; }
		}
		if (Character.compare(type, 'V') == 0) {
			if (isLegal(x + 1, y)) { count++; }
			if (isLegal(x - 1, y)) { count++; }
			if (isLegal(x, y - 1)) { count++; }
		}
		return count;
	}

	//check if the next movement is legal
	public boolean isLegal(int x, int y) {
		// the move should not be outside of the board
		if (x < 0 || y < 0 || x >= inputSize || y >= inputSize) {
			return false;
		}

		if (board[y][x].equals("+")) {
			return true;
		} else {
			return false;
		}
	}

}