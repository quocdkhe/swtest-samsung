
package Practice;

import java.util.*;
import java.io.*;
// https://codeforces.com/problemset/problem/1063/B
public class Labyrinth {

    // ===== Static variables =====

    static int n, m;
    static int startR, startC;
    static int maxLeft, maxRight;

    static char[][] grid;

    static int[][] left;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static final int INF = Integer.MAX_VALUE;

    // ===== Core logic =====

    static boolean isValid(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

    static int solve() {

        left = new int[n][m];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            Arrays.fill(left[i], INF);
        }

        Deque<int[]> deque = new ArrayDeque<>();
        deque.offerFirst(new int[] {startR, startC});
        left[startR][startC] = 0;
        while(!deque.isEmpty()) {
        	int[] cell = deque.poll();
        	int r = cell[0];
        	int c = cell[1];
        	for(int k = 0; k < 4; k++) {
        		int nr = r + dr[k];
        		int nc = c + dc[k];
        		if(!isValid(nr, nc) || grid[nr][nc] == '*') continue;
        		
        		int cost = k == 2 ? 1 : 0;
				int nextLeft = left[r][c] + cost;
				
				if (nextLeft >= left[nr][nc]) { // chỉ cập nhật đường đi nếu tìm được đường ít bước trái 
				    continue;
				}
				
				left[nr][nc] = nextLeft;
        		if(cost == 0) {
        			deque.offerFirst(new int[] {nr, nc});
        		}else {
        			deque.offerLast(new int[] {nr, nc});
        		}
        		
        	}
        }
        
        for(int r = 0; r < n; r++) {
        	for(int c = 0; c < m; c++) {
        		if(left[r][c] == INF) continue;
        		int leftCount = left[r][c];
        		int rightCount = leftCount + (c-startC);
        		if(leftCount <= maxLeft && rightCount <= maxRight) {
        			ans++;
        		}
        	}
        }
        return ans;
    }

    // ===== Main =====

    public static void main(String[] args) throws IOException {

        // System.setIn(new FileInputStream("src/TC/LabyrinthTC.txt"));

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        startR = Integer.parseInt(st.nextToken()) - 1;
        startC = Integer.parseInt(st.nextToken()) - 1;
        
        st = new StringTokenizer(br.readLine());
        
        maxLeft = Integer.parseInt(st.nextToken());
        maxRight = Integer.parseInt(st.nextToken());

        grid = new char[n][m];

        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        System.out.println(solve());
    }
}
