package Practice;

import java.io.*;
import java.util.*;

public class ThirdAvenue {

	static int R, C;
	static char[][] map;
	static int[] S, G;
	static int[] dr = new int[] {-1, 0, 0, 1};
	static int[] dc = new int[] {0, -1, 1, 0};
	static Map<Character, List<int[]>> teleport;

	
	public static int bfs() {
//		boolean visited[][] = new boolean[R][C];
		int[][] dist = new int[R][C];
		for(int[] rows : dist) {
			Arrays.fill(rows, -1);
		}
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(S);
//		visited[S[0]][S[1]] = true;
		dist[S[0]][S[1]] = 0;
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int r = current[0];
			int c = current[1];
			int d = dist[r][c];
			char ch = map[r][c];
			// di chuyen 4 xung quanh
			
			if(r == G[0] && c == G[1]) {
				return d;
			}
			
			for(int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
					continue;
				}
				if(map[nr][nc] == '#') {
					continue;
				}
				if(dist[nr][nc] != -1) {
					continue;
				}
				queue.offer(new int[] {nr, nc});
				dist[nr][nc] = d + 1;
			}
			// dich chuyen
			if(ch >= 'a' && ch <= 'z') {
				List<int[]> cells = teleport.get(ch);
				for(int[] cell : cells) {
					int nr = cell[0];
					int nc = cell[1];
					if(dist[nr][nc] != -1) {
						continue;
					}
					
					queue.offer(new int[] {nr, nc});
					dist[nr][nc] = d + 1;
					
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/TC/ThirdAvenueTC.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
    	st = new StringTokenizer(br.readLine());
    	R = Integer.parseInt(st.nextToken());
    	C = Integer.parseInt(st.nextToken());
    	map = new char[R][C];
    	teleport = new HashMap<>();
    	for(int r = 0; r < R; r++) {
    		map[r] = br.readLine().toCharArray().clone();
    	}
    	
    	for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				char current = map[r][c];
				if(current == 'G') {
					G = new int[] {r, c};
				}
				
				if(current == 'S') {
					S = new int[] {r, c};
				}
				
				if(current >= 'a' && current <= 'z') {
					if(teleport.get(current) == null) {
						teleport.put(current, new ArrayList<>());
					}
					List<int[]> cells = teleport.get(current);
					cells.add(new int[] {r, c});
				}
			}
		}
        System.out.println(bfs());
	}

}
