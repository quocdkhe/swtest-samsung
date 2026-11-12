package Practice;

import java.io.*;
import java.util.*;

public class ShortestPath {
	
	static int n, m ,k;
	static List<Integer>[] adj;
	static Set<String> forbidden;
	
	static boolean checkForbidden(int u, int v, int w) {
		return forbidden.contains(String.join(",", "" + u, "" + v, "" + w));
	}
	
	static List<Integer> bfs() {

        Queue<int[]> queue = new ArrayDeque<>();
        int[][] parent = new int[n + 1][n + 1];
        boolean[][] visited = new boolean[n + 1][n + 1];
        queue.offer(new int[]{0, 1});
        visited[0][1] = true;

        int endPrev = -1;

        while (!queue.isEmpty()) {
        	int[] state = queue.poll();
        	int u = state[0];
        	int v = state[1];
        	if(v == n) {
    			endPrev = u;
    			break;
    		}
        	for(int w : adj[v]) {
        		if(visited[v][w]) {
        			continue;
        		}
        		if(checkForbidden(u, v, w)) {
        			continue;
        		}
        		
        		parent[v][w] = u;
        		visited[v][w] = true;
        		queue.offer(new int[] {v,w});
        	}
        }

        if (endPrev == -1) {
            return new ArrayList<>();
        }
        
        List<Integer> path = new ArrayList<>();
        int u = endPrev;
        int v = n;
        path.add(n);
        while (v != 1) {
            path.add(u);
            int p = parent[u][v];
            v = u;
            u = p;
        }
        Collections.reverse(path);
        return path;
    }
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/TC/ShortestPathTC.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        forbidden = new HashSet<>(k);
        adj = new List[n+1];
        for(int i = 1; i <= n; i++) {
        	adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int u = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	adj[u].add(v);
        	adj[v].add(u);
        }
        
        for(int i = 0; i < k; i++) {
        	String currentPath = String.join(",", br.readLine().split(" "));
        	forbidden.add(currentPath);
        }
        List<Integer> path = bfs();
        System.out.println(path.size() - 1);
        for(int num: path) {
        	System.out.print(num + " ");
        }
        
        
	}

}
