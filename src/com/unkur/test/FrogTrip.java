package com.unkur.test;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * <h1>Trip of frog</h1> 
 * The program counts least number of jumps, that frog
 * needs to reach end position from the start.
 * 
 * @author Konyk
 * @version 1.0
 * @since 2015-09
 */

public class FrogTrip {

	public static void main(String[] args) {
		FrogTrip frogTrip = new FrogTrip();
		frogTrip.frogJumps();
	}

	/**
	 * Nested class for saving x and y parameters
	 * 
	 * @author Konyk
	 */
	class Point {
		int x;
		int y;

		Point() {
			this.x = 0;
			this.y = 0;
		}

		Point(int X, int Y) {
			this.x = X;
			this.y = Y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
		
		
	}

	/**
	 * The method checks frog`s jump, tree location and array borders
	 *
	 * @param x
	 * @param y
	 * @return false if frog would jump into the tree or out of array, else
	 *         return true
	 */
	boolean border(int x, int y) {
		if ((x == 14 & y == 9) | (x == 5 & y == 8))
			return false;
		if (y < 0 | y >= 10)
			return false;
		return true;
	}

	/**
	 * The method counts the least number of jumps
	 */
	void frogJumps() {

		// all possible jumps
		final int moveX[] = { 1, 1, 2, 2, 3 };
		final int moveY[] = { 2, -2, 1, -1, 0 };
		// start position of frog
		Point begin = new Point(11, 7);
		// end position of frog
		Point end = new Point(9, 10);
		// initialization field (16x10)
		int[][] area = new int[16][10];

		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 10; j++) {
				area[i][j] = -1;
			}
		}

		area[begin.x][begin.y] = 0;
		// saving first point in queue
		ArrayDeque<Point> jumps = new ArrayDeque<Point>();
		jumps.add(begin);

		while (!jumps.isEmpty()) {

			Point current = jumps.pollFirst();

			for (int i = 0; i < 5; i++) {
				// checking all possible moves
				int x = current.x + moveX[i];
				int y = current.y + moveY[i];
				// if current sector is more then 16 , then frog starts from
				// first sector
				if (x >= 16)
					x = x - 16;

				if (border(x, y)) {

					// area[x][y] is a location of possible frog jump
					area[x][y] = area[current.x][current.y] + 1;

					// if current frog jump equals to the end position
					if (x == (end.x - 1) && y == (end.y - 1)) {
						System.out.println("Total jumps used to reach the end position: " + area[x][y]);
						System.out.println("All possible jumps " + jumps.size() + ":");
						Arrays.asList(jumps).forEach(jump -> System.out.println(jump + ";"));
						System.exit(0);

					}
					// if it`s not, saving current frog jump to queue
					jumps.add(new Point(x, y));
				}
			}
		}

	}

}
