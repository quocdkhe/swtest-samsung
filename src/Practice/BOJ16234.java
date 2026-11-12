package Practice;

/*
 * https://web.archive.org/web/20260419134041/https://www.acmicpc.net/problem/16234
 * 
 * ============================================================
 * Problem: Population Movement
 * ============================================================
 *
 * PROBLEM STATEMENT:
 *
 * There is an N x N grid of countries. Each country occupies
 * a 1 x 1 cell and has a population.
 *
 * Two countries are considered neighbors if they share a
 * common edge (up, down, left, or right).
 *
 * Today is the first day of population movement.
 *
 * Population movement proceeds as follows and continues
 * until no more movement occurs:
 *
 * 1. For every pair of neighboring countries, if the absolute
 *    difference between their populations is at least L
 *    and at most R, the border between them is opened.
 *
 * 2. If one or more borders are opened, population movement
 *    begins.
 *
 * 3. Countries connected through opened borders form a UNION.
 *
 * 4. All countries belonging to the same union redistribute
 *    their population equally.
 *
 *    The new population of each country is:
 *
 *       total population of the union / number of countries
 *
 *    Integer division is used (fractional parts are discarded).
 *
 * 5. All opened borders are closed.
 *
 * 6. Repeat the process on the next day.
 *
 * Given the initial population of every country, determine
 * how many days population movement will occur.
 *
 * ------------------------------------------------------------
 * INPUT:
 *
 * The first line contains three integers N, L, and R.
 *
 * The next N lines contain N integers each, representing
 * the population of each country at position A[r][c].
 *
 * Constraints:
 *
 *    1 <= N <= 50
 *    1 <= L <= R <= 100
 *    0 <= A[r][c] <= 100
 *
 * The input guarantees that population movement occurs
 * for no more than 2,000 days.
 *
 * ------------------------------------------------------------
 * OUTPUT:
 *
 * Print the number of days during which population movement
 * occurs.
 *
 * ============================================================
 * TEST CASES:
 * ============================================================
 *
 * Example 1:
 *
 * Input:
 * 2 20 50
 * 50 30
 * 20 40
 *
 * Output:
 * 1
 *
 * Explanation:
 * All four countries form one union.
 * Total population = 50 + 30 + 20 + 40 = 140.
 * Each country receives 140 / 4 = 35 people.
 * No further population movement occurs.
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 *
 * Input:
 * 2 40 50
 * 50 30
 * 20 40
 *
 * Output:
 * 0
 *
 * Explanation:
 * No neighboring countries have a population difference
 * within the range [40, 50].
 * Therefore, no population movement occurs.
 *
 * ------------------------------------------------------------
 *
 * Example 3:
 *
 * Input:
 * 2 20 50
 * 50 30
 * 30 40
 *
 * Output:
 * 1
 *
 * Explanation:
 * The first three countries form one union.
 * Total population = 50 + 30 + 30 = 110.
 * Each country receives 110 / 3 = 36 people.
 * The fourth country remains unchanged with 40 people.
 * No further population movement occurs.
 *
 * ------------------------------------------------------------
 *
 * Example 4:
 *
 * Input:
 * 3 5 10
 * 10 15 20
 * 20 30 25
 * 40 22 10
 *
 * Output:
 * 2
 *
 * ------------------------------------------------------------
 *
 * Example 5:
 *
 * Input:
 * 4 10 50
 * 10 100 20 90
 * 80 100 60 70
 * 70 20 30 40
 * 50 20 100 10
 *
 * Output:
 * 3
 */
public class BOJ16234 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
