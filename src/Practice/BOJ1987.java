package Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
Có một bảng kích thước R hàng, C cột. Mỗi ô ghi một chữ cái in hoa, và con ngựa (말) 
đang đứng ở ô trên cùng bên trái (hàng 1, cột 1).
Ngựa có thể đi sang 1 trong 4 ô kề (trên, dưới, trái, phải). 
Nhưng chữ cái ở ô mới phải khác với tất cả chữ cái ở các ô đã đi qua. 
Nói cách khác, không được đi qua 2 ô có cùng chữ cái.

Bắt đầu từ ô trên cùng bên trái, tính xem ngựa đi qua được tối đa bao nhiêu ô. Ô xuất phát cũng được tính.

Input: Dòng 1 có R và C cách nhau bởi dấu cách (1 ≤ R, C ≤ 20). 
Từ dòng 2, R dòng, mỗi dòng gồm C chữ cái in hoa viết liền nhau, không có khoảng trắng.

Output: In ra 1 dòng: số ô tối đa mà ngựa đi qua được.


2 4
CAAB
ADCB
3 6
HFDFFB
AJHGDH
DGAGEH
5 5
IEFCJ
FHFKC
FFALF
HFGCF
HMCHH

#1 3
#2 6
#3 10
 */
public class BOJ1987 {
	
	public static char[][] board;
	public static int best, R, C;
	public static Set<Character> used;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    
    
	public static void solve(int count, int r, int c) {
		if(count > best) {
			best = count;
		}
		for(int k = 0; k < 4; k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];
			if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
				continue;
			}
			if(used.contains(board[nr][nc])) {
				continue;
			}
			used.add(board[nr][nc]);
			solve(count + 1, nr, nc);
			used.remove(board[nr][nc]);
		}
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
        	used = new HashSet<>();
        	for(int r = 0; r < R; r++) {
        		char[] chars = br.readLine().toCharArray();
        		board[r] = Arrays.copyOf(chars, chars.length);
        	}
        	used.add(board[0][0]);
        	solve(1, 0, 0);
        	System.out.printf("#%d %d\n", tc, best);
        }
	}
}
