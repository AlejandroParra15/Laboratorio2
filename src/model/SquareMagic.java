package model;

import customExceptions.NotOddException;

public class SquareMagic {

	public static final String UP = "UP";
	public static final String DOWN = "DOWN";
	public static final String LEFT = "LEFT";
	public static final String RIGHT = "RIGHT";
	public static final String NO = "NO";
	public static final String NE = "NE";
	public static final String SE = "SE";
	public static final String SO = "SO";

	public boolean checkOdd(int order) throws NotOddException {

		boolean impar = false;

		if (!(order % 2 == 0 && order != 0)) {
			impar = true;
		} else {
			throw new NotOddException(order);
		}
		if (order < 0)
			throw new NotOddException(order);

		if (order == 0)
			throw new NotOddException(order);

		return impar;
	}

	public int[][] fillSquare(int size, String position, String orientation) {
		int row = 0;
		int column = 0;
		int rowAc = 0;
		int colAc = 0;

		int[][] magicSquare = new int[size][size];
		if (position.equals(UP)) {
			if (orientation.equals(NO)) {
				row = 0;
				column = size / 2;

				for (int i = 1; i <= (size * size); i++) {
					magicSquare[row][column] = i;
					rowAc = row;
					colAc = column;
					row -= 1;
					column -= 1;
					if (row == -1) {
						row = size - 1;
					}
					if (column == -1) {
						column = size - 1;
					}
					if (magicSquare[row][column] != 0) {
						row = rowAc + 1;
						column = colAc;
					}
				}
			} else if (orientation.equals(NE)) {
				row = 0;
				column = size / 2;

				for (int i = 1; i <= (size * size); i++) {
					magicSquare[row][column] = i;
					rowAc = row;
					colAc = column;
					row -= 1;
					column += 1;
					if (row == -1) {
						row = size - 1;
					}
					if (column == size) {
						column = 0;
					}
					if (magicSquare[row][column] != 0) {
						row = rowAc + 1;
						column = colAc;
					}
				}
			}
		} else if (position.equals(DOWN)) {
			if (orientation.equals(SO)) {
				row = size - 1;
				column = size / 2;

				for (int i = 1; i <= (size * size); i++) {
					magicSquare[row][column] = i;
					rowAc = row;
					colAc = column;
					row += 1;
					column -= 1;
					if (row == size) {
						row = 0;
					}
					if (column == -1) {
						column = size - 1;
					}
					if (column == size) {
						column = 0;
					}
					if (magicSquare[row][column] != 0) {
						row = rowAc - 1;
						column = colAc;
					}
				}
			} else if (orientation.equals(SE)) {
				row = size - 1;
				column = size / 2;

				for (int i = 1; i <= (size * size); i++) {
					magicSquare[row][column] = i;
					rowAc = row;
					colAc = column;
					row += 1;
					column += 1;
					if (row == size) {
						row = 0;
					}
					if (column == -1) {
						column = size - 1;
					}
					if (column == size) {
						column = 0;
					}
					if (magicSquare[row][column] != 0) {
						row = rowAc - 1;
						column = colAc;
					}
				}
			}
		} else if (position.equals(LEFT)) {
			if (orientation.equals(NO)) {
				row = size / 2;
				column = 0;

				for (int i = 1; i <= (size * size); i++) {
					magicSquare[row][column] = i;
					rowAc = row;
					colAc = column;
					row -= 1;
					column -= 1;
					if (row == -1) {
						row = size - 1;
					}
					if (column == -1) {
						column = size - 1;
					}
					if (magicSquare[row][column] != 0) {
						row = rowAc;
						column = colAc + 1;
					}
				}
			} else if (orientation.equals(SO)) {
				row = size / 2;
				column = 0;

				for (int i = 1; i <= (size * size); i++) {
					magicSquare[row][column] = i;
					rowAc = row;
					colAc = column;
					row += 1;
					column -= 1;
					if (row == size) {
						row = 0;
					}
					if (column == -1) {
						column = size - 1;
					}
					if (column == size) {
						column = 0;
					}
					if (magicSquare[row][column] != 0) {
						row = rowAc;
						column = colAc + 1;
					}
				}
			}
		} else if (position.equals(RIGHT)) {
			if (orientation.equals(SE)) {
				row = size / 2;
				column = size - 1;

				for (int i = 1; i <= (size * size); i++) {
					magicSquare[row][column] = i;
					rowAc = row;
					colAc = column;
					row += 1;
					column += 1;
					if (row == size) {
						row = 0;
					}
					if (column == -1) {
						column = size - 1;
					}
					if (column == size) {
						column = 0;
					}
					if (magicSquare[row][column] != 0) {
						row = rowAc;
						column = colAc - 1;
					}
				}
			} else if (orientation.equals(NE)) {
				row = size / 2;
				column = size - 1;

				for (int i = 1; i <= (size * size); i++) {
					magicSquare[row][column] = i;
					rowAc = row;
					colAc = column;
					row -= 1;
					column += 1;
					if (row == -1) {
						row = size - 1;
					}
					if (column == size) {
						column = 0;
					}
					if (magicSquare[row][column] != 0) {
						row = rowAc;
						column = colAc - 1;
					}
				}
			}
		}
		return magicSquare;
	}

	public int calculateConstant(int order) {

		int constant = (order * ((order * order) + 1)) / 2;
		return constant;
	}

}
