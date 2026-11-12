package Samsung;

import java.io.*;
import java.util.*;
/**
3
13 12 2
1 6
1 9
7 1
5 10
7 5
11 8
7 11
4 7
3 4
2 12
13 2
4 13
1
13
13 12 1
1 6
1 9
7 1
5 10
7 5
11 8
7 11
4 7
3 4
2 12
13 2
4 13
7
13 12 1
1 6
1 9
7 1
5 10
7 5
11 8
7 11
4 7
3 4
2 12
13 2
4 13
4
 */
public class Excel {
	
	static int N, M, C;
	static List<Integer>[] graph;
	static boolean[] visited;
	static Queue<Integer> queue;
	static int count;
	
	public static void bfs() {
		while(!queue.isEmpty()) {
			int u = queue.poll();
			for(int v : graph[u]) {
				System.out.println("Đang xét:" + v);
				if(visited[v]) {
					continue;
				}
				count++;
				queue.offer(v);
			}
		}
	}
	
	
	
	public static void main(String[] args) throws Exception{
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	C = Integer.parseInt(st.nextToken());
        	graph = new List[N+1];
        	visited = new boolean[N+1];
        	count = C;
        	queue = new LinkedList<>();
        	
        	for(int i = 1; i <= N; i++) {
        		graph[i] = new ArrayList<>();
        	}
        	for(int i = 0; i < M; i++) {
        		st = new StringTokenizer(br.readLine());
        		int u = Integer.parseInt(st.nextToken());
        		int v = Integer.parseInt(st.nextToken());
        		
        		graph[u].add(v);
        	}
        	
        	for(int i = 0; i < C; i++) {
        		int cell = Integer.parseInt(br.readLine());
        		queue.offer(cell);
        		visited[cell] = true;
        	}
        	
        	bfs();
        	
        	System.out.println(count);
        	
        }
	}
}
