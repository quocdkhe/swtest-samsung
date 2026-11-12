
package Practice;

import java.util.*;
import java.io.*;

/**
 * https://cses.fi/problemset/task/1194/
 */

public class Monsters {

    // ===== Static variables =====
    static int n, m;
    static char[][] grid;

    static int startR, startC;

    static List<int[]> monsters;

    static int[][] monsterTime;
    static int[][] playerTime;

    static int[][] parentR;
    static int[][] parentC;
    static char[][] direction;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static char[] move = {'U', 'D', 'L', 'R'};

    static final int INF = Integer.MAX_VALUE;

    // ===== Core logic =====

    static boolean isValid(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
    
    static void bfsMonsters() {

        // TODO:
        // Multi-source BFS từ tất cả vị trí M
        // Tính thời gian quái vật đến từng ô
    	
    	Queue<int[]> queue = new ArrayDeque<>();
    	for(int[] monster: monsters) {
    		int r = monster[0];
    		int c = monster[1];
    		queue.offer(new int[] {r,c});
    		monsterTime[r][c] = 0;
    	}
    	
    	while(!queue.isEmpty()) {
    		int[] monster  = queue.poll();
    		int r = monster[0];
    		int c = monster[1];
    		int t = monsterTime[r][c];
    		for(int k = 0; k < 4; k++) {
    			int nr = r + dr[k];
    			int nc = c + dc[k];
    			if(!isValid(nr, nc)) {
    				continue;
    			}
    			if(grid[nr][nc] == '#') {
    				continue;
    			}
    			if(monsterTime[nr][nc] != INF) {
    				continue;
    			}
    			monsterTime[nr][nc] = t + 1;
    			queue.offer(new int[] {nr, nc});
    		}
    	}
    	
    }

    static boolean bfsPlayer() {

        // TODO:
        // BFS từ vị trí A
        // Chỉ đi vào ô an toàn theo monsterTime
        // Dừng khi tìm được một ô nằm ở biên
    	Queue<int[]> queue = new ArrayDeque<>();
    	queue.offer(new int[] {startR, startC});
    	playerTime[startR][startC] = 0;
    	while(!queue.isEmpty()) {
    		int[] cell = queue.poll();
    		int r = cell[0];
    		int c = cell[1];
    		int t = playerTime[r][c];
    		if (r == 0 || r == n - 1 || c == 0 || c == m - 1) {
    		    printPath(r, c);
    		    return true;
    		}
    		for(int k = 0; k < 4; k++) {
    			int nr = r + dr[k];
    			int nc = c + dc[k];
    			if(!isValid(nr, nc)) {
    				continue;
    			}
    			if(playerTime[nr][nc] != INF) {
    				continue;
    			}
    			if(grid[nr][nc] == '#') {
    				continue;
    			}
    			if(t + 1 >= monsterTime[nr][nc]) {
    				continue;
    			}
    			parentR[nr][nc] = r;
    			parentC[nr][nc] = c;
    			direction[nr][nc] = move[k];
    			playerTime[nr][nc] = t + 1;
    			queue.offer(new int[] {nr, nc});
    		}
    	}
        return false;
    }

    static void printPath(int r, int c) {

        StringBuilder path = new StringBuilder();
        while (r != startR || c != startC) {

            path.append(direction[r][c]);

            int pr = parentR[r][c];
            int pc = parentC[r][c];

            r = pr;
            c = pc;
        }

        path.reverse();

        System.out.println("YES");
        System.out.println(path.length());
        System.out.println(path);
    }

    public static void solve() {

        // Khởi tạo các mảng thời gian
        monsterTime = new int[n][m];
        playerTime = new int[n][m];

        parentR = new int[n][m];
        parentC = new int[n][m];
        direction = new char[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(monsterTime[i], INF);
            Arrays.fill(playerTime[i], INF);
            Arrays.fill(parentR[i], -1);
            Arrays.fill(parentC[i], -1);
        }

        // 1. Calculate monster arrival times
        bfsMonsters();

        // 2. Find player's escape path
        boolean escaped = bfsPlayer();

        // 3. Output result
        if (escaped) {
        } else {
            System.out.println("NO");
        }
    }

    // ===== Main =====

    public static void main(String[] args) throws IOException {

        // System.setIn(new FileInputStream("src/TC/MonstersTC.txt"));

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new char[n][m];
        monsters = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();

            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'A') {
                    startR = i;
                    startC = j;
                } else if (grid[i][j] == 'M') {
                    monsters.add(new int[] {i, j});
                }
            }
        }

        solve();
    }
}
