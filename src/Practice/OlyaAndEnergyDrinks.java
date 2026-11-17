
package Practice;

import java.util.*;
import java.io.*;
// https://codeforces.com/problemset/problem/877/D
public class OlyaAndEnergyDrinks {

    // ===== Static variables =====
    static int n, m, k;
    static int startR, startC;
    static int endR, endC;

    static char[][] grid;
    static int[][] dist;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static final int INF = Integer.MAX_VALUE;


    // ===== Core logic =====
    static int solve() {

        dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        // TODO: BFS tìm số bước ít nhất từ start đến end
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {startR, startC});
        dist[startR][startC] = 0;
        while(!queue.isEmpty()) {
        	int[] current = queue.poll();
        	int r = current[0];
        	int c = current[1];
        	int d = dist[r][c];
        	
        	if(r == endR && c == endC) {
        		return d;
        	}
        	for(int i = 0; i < 4; i++) {
        		for(int j = 1; j <= k; j++) {
        			int nr = r + dr[i] * j;
        			int nc = c + dc[i] * j;
        			
        			if(nr < 0 || nr >= n || nc < 0 || nc >= m) break;
        			
        			if(grid[nr][nc] == '#') break;
        			
        			if(dist[nr][nc] < d + 1) break; // da co duong di toi uu hon
        			
        			if(dist[nr][nc] == d + 1) continue; // visited
        			
        			
        			dist[nr][nc] = d + 1;
        			queue.offer(new int[] {nr, nc});
        		}
        		
        	}
        }
        
        
        
        return -1;
    }

    // ===== Main =====
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        st = new StringTokenizer(br.readLine());

        startR = Integer.parseInt(st.nextToken()) - 1;
        startC = Integer.parseInt(st.nextToken()) - 1;

        endR = Integer.parseInt(st.nextToken()) - 1;
        endC = Integer.parseInt(st.nextToken()) - 1;
        
        
        System.out.println(solve());
    }
}
