package Practice;

import java.util.*;
import java.io.*;

public class EscapeFromMars {
	
	static int N, M, K;
	static List<Integer>[] adj;
	static int[] dist;
	static boolean[] visited;
	

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/TC/EscapeFromMarsTC.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			adj = new List[N+1];
			dist = new int[N+1];
			visited = new boolean[N+1];
			Queue<Integer> queue = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine());
			Arrays.fill(dist, -1);
			for(int i = 0; i < K; i++) {
				int toxic = Integer.parseInt(st.nextToken());
				dist[toxic] = 0;
				queue.offer(toxic);
				visited[toxic] = true;
			}
			for(int i = 1; i <= N; i++) {
				adj[i] = new ArrayList<>();
			}
			// nhap do thi
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				adj[u].add(v);
				adj[v].add(u);
			}
			
			// start BFS
			while(!queue.isEmpty()) {
				int u = queue.poll();
				int d = dist[u];
				for(int v : adj[u]) {
					if(visited[v]) continue;
					visited[v] = true;
					dist[v] = d + 1;
					queue.offer(v);
				}
			}
			
			
			int best = Integer.MIN_VALUE;
			int pos = -1;
//			for(int num : dist) {
//				if(num > best) {
//					best = num;
//				}
//			}
			
			// tim vi tri 
			for(int i = 1; i <= N; i++) {
				if(dist[i] > best) {
					pos = i;
					best = dist[i];
				}
			}
			
			for(int i = 1; i <= N; i++) {
				System.out.printf("Dinh: %d dist: %d\n", i, dist[i]);
			}
			
			System.out.printf("#%d %d %d\n", tc, pos, best);
		}
	}

}
