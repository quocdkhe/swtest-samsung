package Samsung;

import java.io.*;
import java.util.*;

/**
2
5
2 5 3 1 4
1 2 3 4 5
5 4 3 2 1
1 3 2 4 5
4 2 5 3 1
4
1 2 3 4
3 1 2 4
2 3 1 4
2 1 3 4
 */

public class Pair {
	
	static int N;
	static int[][] ranking;
	static int min, max;
	static boolean[] used;
	
	public static void solve(int i, int sum) {
		if(i > N) {
			if(sum > max) {
				max = sum;
			}
			if(sum < min) {
				min = sum;
			}
		}
		
		for (int j = 1; j <= N; j++) {
	        if (used[j]) continue;
	        used[j] = true;
	        int rank = ranking[i][j];
	        solve(i + 1, sum + rank);
	        used[j] = false;   // backtrack
	    }
	}
	

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	N = Integer.parseInt(br.readLine());
        	min = Integer.MAX_VALUE;
        	max = Integer.MIN_VALUE;
        	ranking = new int[N+1][N+1];
        	used = new boolean[N+1];
        	for(int i = 1; i <= N; i++) {
        		st = new StringTokenizer(br.readLine());
        		for(int j = 1; j <= N; j++) {
        			int person = Integer.parseInt(st.nextToken());
        			ranking[i][person] = j;
        		}
        	}
        	
        	solve(1, 0);
        	
        	System.out.printf("#%d %d %d\n",tc, min, max);
        }
	}
}
