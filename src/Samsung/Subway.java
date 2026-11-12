package Samsung;

import java.util.*;
import java.io.*;
/**
2
18 5 2 17
7 4 5 4 4
2 3 4 7 10 9 8
1 3 6 9
5 6 7 13 14
12 13 15 16
11 16 17 18
5 3 1 5
3 2 3
1 2 3
2 4
5 4 3
 */
public class Subway {
	
	static int N, M, S, E, startLine, endLine;
	static Set<Integer>[] subway;
	static boolean[] visited;
	static int[] dist;
	
	
	public static int solve() {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(startLine);
		dist[startLine] = 0;
		boolean[] visitedStation = new boolean[N+1];
		while(!queue.isEmpty()) {
			int current = queue.poll();
			int d = dist[current];
			for(int station: subway[current]) {
				if(visitedStation[station]) {
					continue;
				}
				visitedStation[station] = true;
				for(int i = 1; i <= M; i++) {
					if(dist[i] != -1) {
						continue;
					}
					if(subway[i].contains(station)) {
						if(i == endLine) {
							return d + 1;
						}
						queue.offer(i);
						dist[i] = d + 1;
					}
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	S = Integer.parseInt(st.nextToken());
        	E = Integer.parseInt(st.nextToken());
        	subway = new Set[M+1];
        	dist = new int[M+1];
        	Arrays.fill(dist, -1);
        	br.readLine();
        	for(int i = 1; i <= M; i++) {
        		if(subway[i] == null) {
        			subway[i] = new HashSet<>();
        		}
        		for(String station: br.readLine().split(" ")) {
        			subway[i].add(Integer.parseInt(station));
        		}
        	}
        	
        	for(int i = 1; i <= M; i++) {
        		if(subway[i].contains(S)) {
        			startLine = i;
        		}
        		if(subway[i].contains(E)) {
        			endLine = i;
        		}
        	}
        	
        	System.out.println(solve());
        }
	}
}
