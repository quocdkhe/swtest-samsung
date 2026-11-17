package Practice;

import java.util.*;
import java.io.*;

public class NearestOppositeParity {

	static int n;
	static int[] a;
	
	static boolean isValid (int index) {
		return index >= 1 && index <= n;
	}

	static boolean oddEven(int num1, int num2) {
		return Math.abs(num1 - num2) % 2 == 1;
	}
	
	static int bfs(int start) { // bfs 1 nguồn
		int[] dist = new int[n+1];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		Arrays.fill(dist, -1);
		dist[start] = 0;
		while(!queue.isEmpty()) {
			int i = queue.poll();
			int d = dist[i];
			
			int j1 = i - a[i];
			int j2 = i + a[i];
			
			if(isValid(j1) && dist[j1] == -1) {
				if(oddEven(a[start], a[j1])) {
					return d+1;
				}else {
					dist[j1] = d + 1;
					queue.offer(j1);
				}
			}
			
			if(isValid(j2) && dist[j2] == -1) {
				if(oddEven(a[start], a[j2])) {
					return d+1;
				}else {
					dist[j2] = d + 1;
					queue.offer(j2);
				}
			}
			
		}
		
		return -1;
	}
	
	static int[] solve() { // nhiều 
		int[][] dist = new int[n+1][n+1];
		int[] res = new int[n+1];
		Arrays.fill(res, -1);
		for(int[] nums: dist) {
			Arrays.fill(nums, -1);
		}
		Queue<int[]> queue = new ArrayDeque<>();
		for(int i = 1; i <= n; i++) {
			queue.offer(new int[] {i,i});
			dist[i][i] = 0;
		}
	
		queue.offer(new int[] {2,2});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int i = cur[0];
			int next = cur[1];
			int d = dist[i][next];
			
			int j1 = next - a[next];
			int j2 = next + a[next];
			
			if(isValid(j1) && dist[i][j1] == -1) {
				if(oddEven(a[i], a[j1])) {
					res[i] = d + 1;
					continue;
				}else {
					dist[i][j1] = d + 1;
					queue.offer(new int[] {i, j1});
				}
			}
			
			if(isValid(j2) && dist[i][j2] == -1) {
				if(oddEven(a[i], a[j2])) {
					res[i] = d + 1;
					continue;
				}else {
					dist[i][j2] = d + 1;
					queue.offer(new int[] {i, j2});
				}
			}
		}
		
		return res;
		
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		a = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
//		for(int i = 1; i <= n; i++) {
//			System.out.printf("%d ", bfs(i));
//		}
		
		int[] res = solve();
		
		for(int i = 1; i <= n; i++) {
			System.out.printf("%d ", res[i]);
		}
		
	}

}
