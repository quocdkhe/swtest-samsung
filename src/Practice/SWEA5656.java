package Practice;

import java.io.*;
import java.util.*;

/*
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRQm6qfL0DFAUo
 */

public class SWEA5656 {
	static int R, C, best;
	static int[][] map;
	
	public static void shootCol(int row, int col, int[][] board) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {row, col});
		boolean[][] shooted = new boolean[R][C];
		shooted[row][col] = true;
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int r = current[0];
			int c = current[1];
			int value = board[r][c];
			if(value == 1 || value == 0) {
				continue;
			}
			List<Integer> dr = new ArrayList<>(4*(value-1));
			List<Integer> dc = new ArrayList<>(4*(value-1));
			
			for(int i = 1; i < value; i++) {
				dr.add(-1 * i);
				dr.add(0);
				dr.add(0);
				dr.add(1 * i);
				
				dc.add(0);
				dc.add(-1 * i);
				dc.add(1 * i);
				dc.add(0);
			}
			
		}
		
	}
	
	public static int shoot(List<Integer> cols) {
		int[][] temp = new int[R][C];
		for(int r = 0; r < R; r++) {
			temp[r] = map[r].clone();
		}
		for(int col : cols) {
			int row = -1;
			for(int i = 0; i < R; i++) {
				if(temp[i][col] != 0) {
					row = i;
				}
			}
			if(row == -1) continue;
			
			
		}
		
		return 0;
	}
	
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/TC/SWEA5656.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	
        }
	}
}
