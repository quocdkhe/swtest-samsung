package Practice;

import java.util.*;
import java.io.*;

public class LeetCode1263 {
	int[] dr = new int[] {-1, 0, 0, 1};
	int[] dc = new int[] {0, -1, 1, 0};
	
	public boolean isValid(int r, int c, int R, int C) {
		return !(r < 0 || r >= R || c < 0 || c >= C);
	}
	
	public int encode(int r, int c, int C) {
		return r * C + c;		
	}
	
	public boolean dfs(int sr, int sc, int destR, int destC, int br, int bc, char[][] grid) {
		int R = grid.length, C = grid[0].length;
		if(!isValid(sr, sc, R, C)) return false;
		
		Stack<int[]> stack = new Stack<>();
		stack.push(new int[] {sr, sc});
		boolean[][] used = new boolean[R][C];
		used[sr][sc] = true;
		used[br][bc] = true;
		while(!stack.isEmpty()) {
			int[] cur = stack.pop();
			int r = cur[0];
			int c = cur[1];
			if(r == destR && c == destC) return true;
			
			for(int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if(!isValid(nr, nc, R, C)) continue;
				
				if (used[nr][nc]) continue;
				
				if(grid[nr][nc] == '#') continue;
				
				used[nr][nc] = true;
				stack.push(new int[] {nr, nc});
			}
		}
		
		return false;
	}
	
	
	public int minPushBox(char[][] grid) {
		int sBoxR = 0, sBoxC = 0, sPlayerR = 0, sPlayerC = 0, R = grid.length, C = grid[0].length;
		int destR = 0, destC = 0;
		for(int r = 0; r < R; r ++) {
			for(int c = 0; c < C; c++) {
				if(grid[r][c] == 'S') {
					sPlayerR = r;
					sPlayerC = c;
				}
				if(grid[r][c] == 'B') {
					sBoxR = r;
					sBoxC = c;
				}
				if(grid[r][c] == 'T') {
					destR = r;
					destC = c;
				}
			}
		}
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {sBoxR, sBoxC, sPlayerR, sPlayerC});
		int[][][] dist = new int[R][C][420];
		
		for(int[][] dim1 : dist) 
			for(int[] dim2: dim1)
				Arrays.fill(dim2, -1);
			
		
		dist[sBoxR][sBoxC][encode(sPlayerR, sPlayerC, C)] = 0;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int br = cur[0];
			int bc = cur[1];
			int pr = cur[2];
			int pc = cur[3];
			int d = dist[br][bc][encode(pr, pc, C)];
			if(br == destR && bc == destC) return d;
			for(int k = 0; k < 4; k++) {
				int nbr = br + dr[k];
				int nbc = bc + dc[k];
				
				int npr = br + dr[3-k];
				int npc = bc + dc[3-k];
			
				if(nbr < 0 || nbr >= R || nbc < 0 || nbc >= C) continue;
				
				if(!dfs(pr, pc, npr, npc, br, bc, grid)) continue;
				
				if(grid[nbr][nbc] == '#') continue;
				
				if(dist[nbr][nbc][encode(br, bc, C)] != -1) continue;
				
				dist[nbr][nbc][encode(br, bc, C)] = d + 1;
				
				queue.offer(new int[] {nbr, nbc, br, bc});
			}
		}
		
		
		return -1;
    }

	public static void main(String[] args) {
		LeetCode1263 app = new LeetCode1263();
		char[][] grid = new char[][]{{'#', '#', '#', '#', '#', '#'}, {'#', 'T', '#', '#', '#', '#'}, {'#', '.', '.', 'B', '.', '#'}, {'#', '.', '#', '#', '.', '#'}, {'#', '.', '.', '.', 'S', '#'}, {'#', '#', '#', '#', '#', '#'}};
		System.out.println(app.minPushBox(grid));
	}

}
