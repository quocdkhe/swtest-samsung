package Practice;

import java.io.*;
import java.util.*;

/*
Có một bản đồ được biểu diễn bằng ma trận N×M. 
Trên bản đồ, 0 biểu thị vị trí có thể đi qua, và 1 biểu thị vị trí có tường không thể đi qua. 
Bạn muốn di chuyển từ (1, 1) đến (N, M) bằng đường đi ngắn nhất. Đường đi ngắn nhất là đường đi qua ít ô nhất trên bản đồ, 
bao gồm cả ô bắt đầu và ô kết thúc.
Nếu việc phá vỡ một bức tường trong khi di chuyển làm cho đường đi ngắn hơn, bạn có thể phá vỡ tối đa một bức tường để di chuyển.
Các ô vuông bạn có thể di chuyển từ một ô vuông này sang ô vuông khác là các ô vuông liền kề phía trên, phía dưới, bên trái và bên phải.
Cho một bản đồ, hãy viết chương trình để tìm đường đi ngắn nhất.
Dòng đầu tiên chứa N (1 ≤ N ≤ 1.000) và M (1 ≤ M ≤ 1.000). 
N dòng tiếp theo chứa một bản đồ gồm M số. Giả sử rằng (1, 1) và (N, M) luôn bằng 0.

In dòng đầu tiên, ghi khoảng cách ngắn nhất. In -1 nếu không thể.

2
6 4
0100
1110
1000
0000
0111
0000
4 4
0111
1111
1111
1110

#1 15
#2 -1
 */
public class BOJ2206 {
	
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
	public static char[][] board;
	public static int best, R, C;
	
	
	public static void bfs() {
		int[][][] dist = new int[R][C][2];
		for(int[][] rows: dist) {
			for(int[] cell: rows) {
				Arrays.fill(cell, -1);
			}
		}
		dist[0][0][0] = 0; // 0 = didn't broke, 1 = broken doors
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {0,0,0});
		boolean reachable = false;
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int r = current[0];
			int c = current[1];
			int state = current[2];
			int d = dist[r][c][state];
			if(r == R-1 && c == C-1 && best > d) {
				best = d;
				reachable = true;
				continue;
			}
			for(int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
					continue;
				}
				if(dist[nr][nc][state] != -1) {
					continue;
				}
				if(board[nr][nc] == '1') {
					if(state == 0) {
						queue.offer(new int[] {nr, nc, 1});
						dist[nr][nc][1] = d + 1;
					}
					continue;
				}
				queue.offer(new int[] {nr, nc, state});
				dist[nr][nc][state] = d + 1;
			}
		}
		best = reachable ? best : -1;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	R = Integer.parseInt(st.nextToken());
        	C = Integer.parseInt(st.nextToken());
        	board = new char[R][C];
        	best = Integer.MAX_VALUE;
        	for(int r = 0; r < R; r++) {
        		char[] chars = br.readLine().toCharArray();
        		board[r] = Arrays.copyOf(chars, chars.length);
        	}
        	bfs();
        	System.out.println(best);
        }
	}
}


