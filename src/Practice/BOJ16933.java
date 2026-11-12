package Practice;

import java.util.*;
import java.io.*;

public class BOJ16933 {
	
	static int R, C, K;
	static int grid[][];
	static int[] dr = new int[] {-1, 1, 0, 0};
	static int[] dc = new int[] {0, 0, -1, 1};
	
	public static int bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		
		int[][][][] dist = new int[R][C][K+1][2];
		for(int[][][] rows: dist) {
			for(int[][] state: rows) {
				for(int[] day: state) {
					Arrays.fill(day, -1);
				}
			}
		}
		
		dist[0][0][0][1] = 1; // 1 = ban ngay, 0 = ban dem
		queue.offer(new int[] {0, 0, 0, 1});
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int r = current[0];
			int c = current[1];
			int broken = current[2];
			int day = current[3];
			int distance = dist[r][c][broken][day];
			int reversed = day == 1 ? 0 : 1;
			
			// switch ngay/dem
			if(dist[r][c][broken][reversed] == -1) {
				queue.offer(new int[] {r, c, broken, reversed});
				dist[r][c][broken][reversed] = distance + 1;
			}
			
			if(r == R - 1 || c == C - 1) {
				return distance;
			}
			
			// di chuyen
			for(int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
					continue;
				}
				
				if(dist[nr][nc][broken][day] != -1) continue;
				
				if(grid[nr][nc] == 1) { // pha tuong ban ngay
					if(day == 1 && broken < K) {
						queue.offer(new int[] {nr, nc, broken + 1, reversed});
						dist[nr][nc][broken + 1][reversed] = distance + 1;
						continue;
					}
				}else {
					continue;
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			grid = new int[R][C];
			
			for(int r = 0; r < R; r++) {
				char[] nums = br.readLine().toCharArray();
				int c = 0;
				for(int num : nums) {
					grid[r][c] = num - '0';
					c++;
				}
			}
			System.out.printf("#%d %d\n", tc, bfs());
		}
	}

}
