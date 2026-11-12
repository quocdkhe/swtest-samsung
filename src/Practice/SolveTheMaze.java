package Practice;

import java.util.*;

/*
 * https://codeforces.com/problemset/problem/1365/D
 */

public class SolveTheMaze{

    static int n, m;
    static char[][] grid;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static List<int[]> Bs;
    static List<int[]> Gs;

    static boolean isValid(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

    static boolean solve(Scanner sc) {
        n = sc.nextInt();
        m = sc.nextInt();
        Bs = new ArrayList<>();
        Gs = new ArrayList<>();

        grid = new char[n][m];

        // 1. Read maze
        for (int i = 0; i < n; i++) {
            grid[i] = sc.next().toCharArray();
        }

        // 2. Find all B, G
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {

                if (grid[r][c] == 'B') {
                    Bs.add(new int[] {r, c});
                }
                
                if(grid[r][c] == 'G') {
                	Gs.add(new int[] {r,c});
                }
            }
        }
        
        // Add # for all Bs
        for(int[] cell : Bs) {
        	int r = cell[0];
        	int c = cell[1];
        	for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (!isValid(nr, nc)) {
                    continue;
                }
                
                if(grid[nr][nc] == 'G') {
                	return false;
                }

                if (grid[nr][nc] == '.') {
                    grid[nr][nc] = '#';
                }
            }
        }
        
        if(grid[n-1][m-1] == '#' && !Gs.isEmpty()) {
        	return false;
        }

        // 3. BFS from exit
        // TODO: Your implementation here
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        queue.offer(new int[] {n-1, m-1});
        visited[n-1][m-1] = true;
        while(!queue.isEmpty()) {
        	int[] current = queue.poll();
        	int r = current[0];
        	int c = current[1];
        	for(int k = 0; k < 4; k++) {
        		int nr = r + dr[k];
        		int nc = c + dc[k];
        		
        		if(!isValid(nr, nc)) {
        			continue;
        		}
        		
        		if(visited[nr][nc]) {
        			continue;
        		}
        		
        		if(grid[nr][nc] == '#') {
        			continue;
        		}
        		
        		visited[nr][nc] = true;
        		queue.offer(new int[] {nr, nc});
        	}
        }

        // 4. Check whether all G can reach the exit
        //    and no B can reach the exit.
        // TODO: Your implementation here
        
        for(int[] cell : Gs) {
        	int r = cell[0];
        	int c = cell[1]; 
        	if(!visited[r][c]) {
        		return false;
        	}
        }
        
        return true;
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {
            System.out.println(solve(sc) ? "Yes" : "No");
        }

        sc.close();
    }
}

