package Samsung;

import java.io.*;
import java.util.*;

public class FactorAndMultiple {
	
	static List<Integer> nums;
	static boolean[] visited;
	static int N, best;
	
	static void solve(int index) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(nums.get(index));
		visited[index] = true;
		int count = 1;
		while(!queue.isEmpty()) {
			int current = queue.poll();
			for(int i = 0; i < N; i++) {
				if(visited[i]) {
					continue;
				}
				if(current % nums.get(i) == 0 || nums.get(i) % current == 0) {
					count++;
					queue.offer(nums.get(i));
					visited[i] = true;
				}
			}
		}
		
		if(count > best) {
			best = count;
		}
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/TC/FactorAndMultipleTC.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	N = Integer.parseInt(br.readLine());
        	nums = new ArrayList<>(N);
        	best = -1;
        	visited = new boolean[N];
        	
        	st = new StringTokenizer(br.readLine());
//        	System.out.println(br.readLine());
        	for(int i = 0; i < N; i++) {
        		nums.add(Integer.parseInt(st.nextToken()));
        	}
        	
        	for(int i = 0; i < N; i++) {
        		if(visited[i]) {
        			continue;
        		}
        		solve(i);
        	}
        	
        	System.out.printf("#%d %d\n",tc, best);
        }
	}

}
