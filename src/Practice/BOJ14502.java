package Practice;

import java.io.*;
import java.util.*;

/* https://web.archive.org/web/20260420041310/https://www.acmicpc.net/problem/14502
 * 
 * ============================================================
 * Problem: Laboratory (Virus Containment)
 * ============================================================
 *
 * PROBLEM STATEMENT:
 *
 * A laboratory is researching a deadly virus that is dangerous
 * to humans. Unfortunately, the virus has escaped from the
 * research facility.
 *
 * Fortunately, the virus has not spread yet. To prevent its
 * spread, the researchers want to build walls inside the lab.
 *
 * The laboratory can be represented as an N x M grid.
 * Each cell is a 1 x 1 square.
 *
 * The laboratory consists of:
 *   - Empty cells
 *   - Walls
 *   - Cells containing viruses
 *
 * The virus can spread to adjacent empty cells in all four
 * directions (up, down, left, right).
 *
 * You can build exactly THREE additional walls.
 * Each wall occupies one empty cell.
 *
 * After building the three walls, the virus starts spreading.
 * The virus spreads to all reachable empty cells.
 *
 * Your task is to determine the maximum possible size of the
 * SAFE AREA after building exactly three walls.
 *
 * A safe area consists of empty cells that cannot be reached
 * by the virus.
 *
 * ------------------------------------------------------------
 * EXAMPLE:
 *
 * Initial laboratory:
 *
 * 2 0 0 0 1 1 0
 * 0 0 1 0 1 2 0
 * 0 1 1 0 1 0 0
 * 0 1 0 0 0 0 0
 * 0 0 0 0 0 1 1
 * 0 1 0 0 0 0 0
 * 0 1 0 0 0 0 0
 *
 * Cell representation:
 *   0 = Empty cell
 *   1 = Wall
 *   2 = Virus
 *
 * One possible optimal wall placement is:
 *   - Row 2, Column 1
 *   - Row 1, Column 2
 *   - Row 4, Column 6
 *
 * After building the three walls:
 *
 * 2 1 0 0 1 1 0
 * 1 0 1 0 1 2 0
 * 0 1 1 0 1 0 0
 * 0 1 0 0 0 1 0
 * 0 0 0 0 0 1 1
 * 0 1 0 0 0 0 0
 * 0 1 0 0 0 0 0
 *
 * After the virus spreads:
 *
 * 2 1 0 0 1 1 2
 * 1 0 1 0 1 2 2
 * 0 1 1 0 1 2 2
 * 0 1 0 0 0 1 2
 * 0 0 0 0 0 1 1
 * 0 1 0 0 0 0 0
 * 0 1 0 0 0 0 0
 *
 * The size of the safe area is 27.
 *
 * ------------------------------------------------------------
 * INPUT:
 *
 * The first line contains two integers N and M,
 * representing the number of rows and columns of the laboratory.
 *
 * The next N lines contain M integers each, describing the
 * initial state of the laboratory.
 *
 * Cell representation:
 *   0 = Empty cell
 *   1 = Wall
 *   2 = Virus
 *
 * Constraints:
 *
 *   3 <= N, M <= 8
 *
 *   2 <= Number of virus cells <= 10
 *
 *   Number of empty cells >= 3
 *
 * ------------------------------------------------------------
 * OUTPUT:
 *
 * Print the maximum possible size of the safe area after
 * building exactly three additional walls and allowing
 * the virus to spread.
 *
 * ------------------------------------------------------------
 * SAMPLE INPUT:
 *
 * 7 7
 * 2 0 0 0 1 1 0
 * 0 0 1 0 1 2 0
 * 0 1 1 0 1 0 0
 * 0 1 0 0 0 0 0
 * 0 0 0 0 0 1 1
 * 0 1 0 0 0 0 0
 * 0 1 0 0 0 0 0
 *
 * SAMPLE OUTPUT:
 *
 * 27
 * 
 * OUTPUT:
 * #1 27
 * #2 9
 * #3 3
 *
 * ============================================================
 * ALGORITHM HINT:
 *
 * - Brute Force
 * - Backtracking / Combination Generation
 * - BFS / DFS
 * - Simulation
 *
 * General approach:
 *
 * 1. Generate every possible combination of exactly three
 *    empty cells to place walls.
 *
 * 2. For each combination:
 *    - Place the three walls.
 *    - Copy the laboratory grid.
 *    - Run BFS/DFS from every virus cell.
 *    - Spread the virus to all reachable empty cells.
 *    - Count the remaining empty cells (safe area).
 *
 * 3. Keep track of the maximum safe area across all
 *    possible wall combinations.
 *
 * 4. Restore the original grid or use a copied grid
 *    before evaluating the next combination.
 *
 * 5. Print the maximum safe area.
 *
 * IMPORTANT:
 *
 * - You must build exactly three walls.
 * - The virus spreads only through empty cells.
 * - Existing walls block virus propagation.
 * - Newly built walls also block virus propagation.
 * - Virus spreads in four directions only.
 * - All virus cells must spread simultaneously in each
 *   simulation (multi-source BFS is suitable).
 *
 * ============================================================
 */
public class BOJ14502 {

	static int R, C, best;
	static int[][] map;
	static List<int[]> viruses;
	static List<int[]> canPutWalls;
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
	
	static int countSafe(List<int[]> walls) {
		int[][] temp = new int[R][C];
		for(int r = 0; r < R; r++) {
			temp[r] = map[r].clone();
		}
		for(int[] wall : walls) {
			int r = wall[0];
			int c = wall[1];
			temp[r][c] = 1;
		}
		Queue<int[]> queue = new LinkedList<>();
		for(int[] virus : viruses) {
			int r = virus[0];
			int c = virus[1];
			queue.offer(new int[] {r, c});
		}
		
		while(!queue.isEmpty()) {
			int[] virus = queue.poll();
			int r = virus[0];
			int c = virus[1];
			for(int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
					continue;
				}
				
				if(temp[nr][nc] == 1 || temp[nr][nc] == 2) {
					continue;
				}
				
				temp[nr][nc] = 2;
				queue.offer(new int[] {nr, nc});
			}
		}
		
		int safe = 0;
		for(int[] rows: temp) {
			for(int cell: rows) {
				if(cell == 0) {
					safe++;
				}
			}
		}
		return safe;
	}
	
	static void backtrack(int start, List<int[]> walls) {
		if(walls.size() == 3) {
			int safe = countSafe(walls);
			if(safe > best) {
				best = safe;
			}
			return;
		}
		for(int i = start; i < canPutWalls.size(); i++) {
			int[] wall = canPutWalls.get(i);
			int r = wall[0];
			int c = wall[1];
			walls.add(new int[] {r, c});
			backtrack(i + 1, walls);
			walls.removeLast();
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/TC/BOJ14502TC.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	R = Integer.parseInt(st.nextToken());
        	C = Integer.parseInt(st.nextToken());
        	map = new int[R][C];
        	viruses = new ArrayList<>();
        	canPutWalls = new ArrayList<>();	
        	best = Integer.MIN_VALUE;
        	for(int r = 0; r < R; r++) {
        		st = new StringTokenizer(br.readLine());
        		for(int c = 0; c < C; c++) {
        			map[r][c] = Integer.parseInt(st.nextToken());
        			if(map[r][c] == 2) {
        				viruses.add(new int[] {r, c});
        			}
        			if(map[r][c] == 0) {
        				canPutWalls.add(new int[] {r, c});
        			}
        		}
        	}
        	backtrack(0, new ArrayList<>());
        	System.out.println("#" + tc + " " + best);
        	
        	
        }
	}

}
