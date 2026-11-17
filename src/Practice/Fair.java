package Practice;

import java.io.*;
import java.util.*;

public class Fair {
	
	static int n, m ,k, s;
	static int[] goods;
	static List<Integer>[] adj;
	
	static int[] bfs() {
		int[] res = new int[n+1];
		int[][] dist = new int[n+1][k+1];
		for(int[] nums : dist) Arrays.fill(nums, -1);
		Queue<int[]> queue = new ArrayDeque<>();
		for(int i = 1; i <= n; i++) {
			int good = goods[i];
			dist[i][good] = 0;
			queue.offer(new int[] {i, good});
		}
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int city = cur[0];
			int good = cur[1];
			int d = dist[city][good];
			
			for(int v : adj[city]) { // quang duong ngan nhat thanh pho v, nhan duoc good
				if(dist[v][good] == -1) {
					dist[v][good] = d + 1;
					
					queue.offer(new int[] {v, good});
				}
			}
		}
		
		
		for(int i = 1; i <= n; i++) {
			int[] cost = dist[i].clone();
			Arrays.sort(cost);
			int count = s - 1;
			int sum = 0;
			for(int num : cost) {
				if(num < 1) continue;
				if(count == 0) break;
				sum += num;
				count--;
			}
			res[i] = sum;
		}
		
		return res;
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/TC/FairTC.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		goods = new int[n+1];
		adj = new List[n+1];
		for(int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			goods[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u].add(v);
			adj[v].add(u);
		}
		
		int[] res = bfs();
		for(int i = 1; i <= n; i++)
			System.out.print(res[i] + " ");
	}

}
