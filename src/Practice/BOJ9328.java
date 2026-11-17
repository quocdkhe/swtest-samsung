package Practice;


import java.io.*;
import java.util.*;

 /*
 * BOJ 9328 - 열쇠 (Chìa khóa)
 *
 * ĐỀ BÀI:
 *
 * Sanggeun muốn đột nhập vào một tòa nhà một tầng để lấy các tài liệu
 * quan trọng.
 *
 * Bản đồ tòa nhà cho biết vị trí của các tài liệu. Tuy nhiên, các cửa
 * trong tòa nhà đều bị khóa và cần chìa khóa tương ứng để mở.
 *
 * Sanggeun có thể sở hữu một số chìa khóa ngay từ đầu. Ngoài ra,
 * các chìa khóa khác được đặt rải rác bên trong tòa nhà.
 *
 * Sanggeun bắt đầu ở bên ngoài tòa nhà và có thể đi vào hoặc đi ra
 * thông qua bất kỳ ô nào ở biên tòa nhà, miễn là ô đó không phải tường.
 *
 * Có thể di chuyển theo 4 hướng: lên, xuống, trái, phải.
 *
 * QUY ƯỚC:
 * - '.'     : Ô trống.
 * - '*'     : Tường, không thể đi qua.
 * - '$'     : Tài liệu cần lấy.
 * - 'A'-'Z' : Cửa bị khóa.
 * - 'a'-'z' : Chìa khóa tương ứng.
 *
 * Chìa khóa chữ thường có thể mở tất cả các cửa tương ứng viết hoa.
 * Một chìa khóa có thể được sử dụng nhiều lần.
 *
 * YÊU CẦU:
 * Tính số lượng tài liệu tối đa mà Sanggeun có thể lấy được
 * trong mỗi test case.
 *
 * INPUT:
 * - Dòng đầu tiên chứa số nguyên T: số lượng test case.
 * - Mỗi test case gồm:
 *   + Hai số nguyên h, w: chiều cao và chiều rộng tòa nhà.
 *   + h dòng tiếp theo mô tả bản đồ, mỗi dòng có w ký tự.
 *   + Một dòng chứa các chìa khóa ban đầu.
 *     Nếu không có chìa khóa, dòng này chứa ký tự '0'.
 *
 * Giới hạn:
 * - 1 <= T <= 100
 * - 2 <= h, w <= 100
 *
 * OUTPUT:
 * Với mỗi test case, in ra số lượng tài liệu tối đa có thể lấy được.
 *
 * SAMPLE INPUT:
 * 3
 * 5 17
 * *****************
 * .............**$*
 * *B*A*P*C**X*Y*.X.
 * *y*x*a*p**$*$**$*
 * *****************
 * cz
 * 5 11
 * *.*********
 * *...*...*x*
 * *X*.*.*.*.*
 * *$*...*...*
 * ***********
 * 0
 * 7 7
 * *ABCDE*
 * X.....F
 * W.$$$.G
 * V.$$$.H
 * U.$$$.J
 * T.....K
 * *SQPML*
 * 0
 *
 * SAMPLE OUTPUT:
 * 3
 * 1
 * 0
 */

public class BOJ9328 {

    static BufferedReader br;
    static StringTokenizer st;

    static int H, W;
    static char[][] map;

    static boolean[] keys;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int documentCount;
    
    static int getIndex(char value) {
    	if(value >= 'a' && value <='z') return value - 'a';
    	return value  - 'A';
    }
    
    static void bfs() {
    	Queue<int[]> queue = new ArrayDeque<>();
    	boolean[][] visited = new boolean[H+2][W+2];
    	List<int[]>[] waitingDoors = new List[26];
    	for(int i = 0; i < 26; i++) {
    		waitingDoors[i] = new ArrayList<>();
    	}
    	visited[0][0] = true;
    	queue.offer(new int[] {0,0});
    	
    	while(!queue.isEmpty()) {
    		int[] cur = queue.poll();
    		int r = cur[0];
    		int c = cur[1];
//    		int key = getIndex(map[r][c]);
    		if(map[r][c] == '$') {
//    			System.out.println("Found at:" + r + " " + c);
    			documentCount++;
    		
    		}
    		for(int k = 0; k < 4; k++) {
    			int nr = r + dr[k];
    			int nc = c + dc[k];
    			if (nr < 0 || nr >= H+2 || nc < 0 || nc >= W+2) {
        			continue;
        		}
    			char next = map[nr][nc];
    			if(visited[nr][nc]) continue;
    			if(next == '*') continue;
    			
    			// doesn't have key
    			if(next >= 'A' && next <= 'Z' && !keys[getIndex(next)]) {
    				visited[nr][nc] = true;
    				waitingDoors[getIndex(next)].add(new int[] {nr, nc});
    				continue;
    			}
    			
				if(next >= 'a' && next <= 'z') {
					int idx = getIndex(next);
				    keys[idx] = true;
					keys[idx] = true;
    				for(int[] cell : waitingDoors[idx]) {
						queue.offer(new int[] {cell[0], cell[1]});
					}
    				waitingDoors[idx].clear(); // clear after processing
				}
				
				// the rest - can pass easily
    			visited[nr][nc] = true;
				queue.offer(new int[] {nr, nc});
    			
    		}
    		
    	}
    	
    }

    public static void main(String[] args) throws IOException {
    	System.setIn(new FileInputStream("src/TC/BOJ9328TC.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {

            // Đọc kích thước bản đồ
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            // Khởi tạo bản đồ
            map = new char[H + 2][W + 2];
            documentCount = 0;
            keys = new boolean[26];
            for(int i = 0; i <= H+1; i++) {
            	for(int j = 0; j <= W+1; j++) {
            		if(j == 0 || j == W+1 || i == 0 || i == H+1) {
            			map[i][j] = '.';
            		}
            	}
            }
            for(int i = 1; i <= H; i++) {
            	char[] lines = br.readLine().toCharArray();
            	for(int j = 1; j <= W; j++) {
            		map[i][j] = lines[j-1];
            	}
            }
            
            
            char[] keysInput = br.readLine().toCharArray();
            for(char key : keysInput) {
            	if(getIndex(key) < 0) continue;
            	keys[getIndex(key)] = true;
            }
            
            bfs();
            // Ghi kết quả
            System.out.println("#" + tc + " " + documentCount);
        }
        br.close();
    }


}

