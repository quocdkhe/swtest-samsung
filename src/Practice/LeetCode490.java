package Practice;

import java.util.*;
import java.io.*;

public class LeetCode490 {
	
	
	public class State{
		public int r;
		public int c;
		public int dist;
		
		public State(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	
	public int[] move(int r, int c, int[][] maze, int k) {
		int R = maze.length;
		int C = maze[0].length;
		if(k == 0) { // down
			for(int nr = r; nr <= R; nr++) {
				if(nr == R || maze[nr][c] == 1) {
					return new int[] {nr-1,c};
				}
			}
		}
		
		if(k==1) { // left
			for(int nc = c; nc >= 0; nc--) {
				if(maze[r][nc] == 1) {
					return new int[] {r, nc + 1};
				}
				if(nc == 0) {
					return new int[] {r, 0};
				}
			}
		}
		
		if(k==2) { // right
			for(int nc = c; nc <= C; nc++) {
				if(nc == C || maze[r][nc] == 1)
					return new int[] {r, nc-1};
			}
		}
		
		if(k==3) { // up
			for(int nr = r; nr >= 0; nr--) {
				if(maze[nr][c] == 1) {
					return new int[] {nr+1, c};
				}
				if(nr == 0)
					return new int[] {0, c};
			}
		}
		
		return null;
	}
	
	public int solve(int[][] maze, int startR, int startC, int destR, int destC) {
		int R = maze.length;
		int C = maze[0].length;
		Queue<State> queue = new ArrayDeque<>();
		queue.offer(new State(startR, startC, 0));
		boolean[][] visited = new boolean[R][C];
		while(!queue.isEmpty()) {
			State cur = queue.poll();
			int r = cur.r;
			int c = cur.c;
			int d = cur.dist;
			if(r == destR && c == destC) return d;
			
			for(int k = 0; k < 4; k++) {
				int[] next = move(r,c,maze,k);
				int nr = next[0];
				int nc = next[1];
				if(visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				queue.offer(new State(nr, nc, d + 1));
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		LeetCode490 app = new LeetCode490();
		int[][] maze = new int[][] {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
//		int[][] maze = new int[][] {{0,0,0,0,0},{1,1,0,0,1},{0,0,0,0,0},{0,1,0,0,1},{0,1,0,0,0}};
		System.out.println(app.solve(maze, 0, 4, 3, 2));
	}

}
