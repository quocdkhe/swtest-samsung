package Samsung;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 3rd dimensions: the first is plant type, next is remain hour to grow
 */
public class Greening {
	
	
	static int R, C, K, H;
	static boolean[][] blocked;
	static int[] dr = new int[] {-1, 1, 0, 0};
	static int[] dc = new int[] {0, 0, -1, 1};
	static int[][] best;	
	static List<int[]>[] groups;
	
	
	public static void bfs(List<int[]> group, int t) {
	    int[][] dist = new int[R][C];
	    for (int[] row : dist) Arrays.fill(row, -1);

	    Queue<int[]> queue = new LinkedList<>();
	    for (int[] s : group) {
	        dist[s[0]][s[1]] = 0;
	        queue.offer(new int[]{s[0], s[1]});
	    }

	    while (!queue.isEmpty()) {
	        int[] cur = queue.poll();
	        int r = cur[0], c = cur[1];
	        best[r][c] = Math.min(best[r][c], dist[r][c] * t);

	        for (int k = 0; k < 4; k++) {
	            int nr = r + dr[k], nc = c + dc[k];
	            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
	            if (blocked[nr][nc] || dist[nr][nc] != -1) continue;
	            dist[nr][nc] = dist[r][c] + 1;
	            queue.offer(new int[]{nr, nc});
	        }
	    }
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	R = Integer.parseInt(st.nextToken());
        	C = Integer.parseInt(st.nextToken());
        	K = Integer.parseInt(st.nextToken());
        	H = Integer.parseInt(st.nextToken());
        	best = new int[R][C];
        	blocked = new boolean[R][C];
        	groups = new List[6];
        	for(int[] rows: best) {
        		Arrays.fill(rows, Integer.MAX_VALUE);
        	}
        	
        	
        	for(int i = 0; i < K; i++) {
        		st = new StringTokenizer(br.readLine());
        		int r = Integer.parseInt(st.nextToken());
        		int c = Integer.parseInt(st.nextToken());
        		int t = Integer.parseInt(st.nextToken());
        		if(groups[t] == null) {
        			groups[t] = new ArrayList<>();
        		}
        		groups[t].add(new int[] {r-1, c-1, t});
        	}
        	
        	for(int i = 0; i < H; i++) {
        		st = new StringTokenizer(br.readLine());
        		int r = Integer.parseInt(st.nextToken());
        		int c = Integer.parseInt(st.nextToken());
        		blocked[r-1][c-1] = true;
        	}
        	
        	for (int d = 1; d <= 5; d++) {
        	    if (groups[d] == null) continue;
        	    bfs(groups[d], d);
        	}
        	
        	int ans = 0;
        	for(int r = 0; r < R; r++) {
        		for(int c = 0; c < C; c++) {
        			if(!blocked[r][c] && best[r][c] != Integer.MAX_VALUE) {
        				ans = Math.max(ans, best[r][c]);     					
        			}
        		}
        	}
        	
        	for(int r = 0; r < R; r++) {
        		for(int c = 0; c < C; c++) {
        			if(!blocked[r][c] && best[r][c] == Integer.MAX_VALUE) {
        				ans = -1;
        				break;
        			}
        			if(ans == -1) {
        				break;
        			}
        		}
        	}
        	
        	
        	System.out.printf("#%d %d\n", tc, ans);
        	
        	
        }
	}
}
