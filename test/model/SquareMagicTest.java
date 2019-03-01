package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import customExceptions.NotOddException;

class SquareMagicTest {

	private SquareMagic squareM;
	private int[][] matrix = new int[3][3];
	
	@Test
	public void setupScenary1() {
		squareM = new SquareMagic();
	}
	
	@Test
	public void setupScenary2() {
		squareM = new SquareMagic();
		//DOWN - SO
		matrix[0][0] = 2; matrix[0][1] = 9; matrix[0][2] = 4;
		matrix[1][0] = 7; matrix[1][1] = 5; matrix[1][2] = 3;
		matrix[2][0] = 6; matrix[2][1] = 1; matrix[2][2] = 8;
	}
	
	@Test
	public void setupScenary3() {
		squareM = new SquareMagic();
		//LEFT - NO
		matrix[0][0] = 6; matrix[0][1] = 7; matrix[0][2] = 2;
		matrix[1][0] = 1; matrix[1][1] = 5; matrix[1][2] = 9;
		matrix[2][0] = 8; matrix[2][1] = 3; matrix[2][2] = 4;
	}
	
	@Test
	public void setupScenary4() {
		squareM = new SquareMagic();
		//RIGHT - SE
		matrix[0][0] = 4; matrix[0][1] = 3; matrix[0][2] = 8;
		matrix[1][0] = 9; matrix[1][1] = 5; matrix[1][2] = 1;
		matrix[2][0] = 2; matrix[2][1] = 7; matrix[2][2] = 6;
	}
	
	@Test
	public void testMethod1() {
		setupScenary1();
		try {
			squareM.checkOdd(0);
			fail("Se esperaba excepcion NotOddException");
		} catch (NotOddException e) {}
		
	}
	
	@Test
	public void testMethod2() {
		setupScenary1();
		try {
			squareM.checkOdd(-3);
			fail("Se esperaba excepcion NotOddException");
		} catch (NotOddException e) {}
	}
	
	@Test
	public void testMethod3() {
		setupScenary1();
		try {
			squareM.checkOdd(2);
			fail("Se esperaba excepcion NotOddException");
		} catch (NotOddException e) {}
	}
	
	@Test
	public void testMethod4() {
		setupScenary2();
		int[][] mTest = squareM.fillSquare(3, "DOWN", "SO");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				assertEquals(matrix[i][j], mTest[i][j]);
			}
		}
	}
	
	@Test
	public void testMethod5() {
		setupScenary3();
		int[][] mTest = squareM.fillSquare(3, "LEFT", "NO");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				assertEquals(matrix[i][j], mTest[i][j]);
			}
		}
	}
	
	@Test
	public void testMethod6() {
		setupScenary4();
		int[][] mTest = squareM.fillSquare(3, "RIGHT", "SE");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				assertEquals(matrix[i][j], mTest[i][j]);
			}
		}
	}
	
	@Test
	public void testMethod7() {
		setupScenary1();
		assertEquals(15, squareM.calculateConstant(3));
	}
	
	@Test
	public void testMethod8() {
		setupScenary1();
		assertEquals(65, squareM.calculateConstant(5));
	}

}
