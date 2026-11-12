package Samsung;

import java.util.*;
import java.io.*;
/**
4
5
1
3000
4
8000 4000 5000 1000
2
2000 6000
1
2500
2
7000 5500
5
4
1049 8409 8444 6054
3
5983 2726 3627
2
8591 4074
3
4610 7157 3958
2
8562 1304
5
2
7490 5898
3
4970 9393 1788
1
3155
3
5837 7236 5894
3
6293 6630 3662
10
4
8284 2317 7755 3301
3
1040 8514 2311
2
2786 3048
2
2967 9401
3
9123 9614 5931
4
1536 8973 4761 4840
3
5688 2072 7870
1
8669
3
9216 5805 9908
3
8187 5501 9890
 */
public class TreasureHunt {

	static int N;
	static List<Integer>[] zones;
	static boolean[][] used;
	static int best;
	
	public static void solve(int index, int sum) {
		if(index == N + 1) {
			if(sum > best) {
				best = sum;
			}
			return;
		}
		for(int i = index; i <= N; i++) {
			for(int j = 0; j < zones[i].size(); j++) {
				if(used[i][j]) {
					continue;
				}
   				used[i][j] = true;
				solve(index + 1, sum +  zones[i].get(j));
				used[i][j] = false;
			}
		}
		
//		for (int j = 0; j < zones[index].size(); j++) {
//		    solve(index + 1, sum + zones[index].get(j));
//		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			zones = new List[N+1];
			used = new boolean[6001][51];
			best = -1;
			for(int i = 1; i <= N; i++) {
				int size = Integer.parseInt(br.readLine());
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < size; j++) {
					if(zones[i] == null) {
						zones[i] = new ArrayList<>(size);
					}
					zones[i].add(Integer.parseInt(st.nextToken()));
				}
			}
			
			solve(1, 0);
			
			System.out.println(best);
			
			
		}
	}
}


