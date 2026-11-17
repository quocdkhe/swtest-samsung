package Samsung;

import java.io.*;
import java.util.*;

public class BoardGame {
	
	static List<Integer> dice;
	static Stack<int[]> checked;
	static int[][] board;
	static boolean[][] visited;
	static int[] dr = {0, 0, 1, -1, 1, -1};
	static int[] dc = {-2, 2, -1, -1, 1, 1};
	static int best;
	
	public static boolean bfs(int startR, int startC) {
		boolean[][] used = new boolean[7][13];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {startR, startC});
		used[startR][startC] = true;
		int val = board[startR][startC];
		while(!queue.isEmpty()) {
			int[] cell = queue.poll();
			int r = cell[0];
			int c = cell[1];
			if(!visited[r][c]) {
				return false;
			}
			for(int k = 0; k < 6; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if(nr < 0 || nr >= 7 || nc < 0 || nc >= 13) {
					continue;
				}
				if(val != board[nr][nc]) {
					continue;
				}
				
				if(used[nr][nc]) continue;
				
				used[nr][nc] = true;
				queue.offer(new int[] {nr, nc});
			}
		}
		return true;
	}
	
	public static int calculateScore () {
		int score = 0;
		for(int[] cell : checked) {
			int r = cell[0];
			int c = cell[1];
			if(bfs(r,c)) {
				score++;
			}
		}
		
		return score;
	}
	
	public static void backtrack(int index) {
	    // Đã sử dụng hết xúc xắc
	    if (index == dice.size()) {
	        int score = calculateScore();
	        best = Math.max(best, score);
	        return;
	    }
	    Set<String> candidates = new HashSet<>();
	    // Xét tất cả các ô đã check
	    for (int[] cell : checked) {
	        int r = cell[0];
	        int c = cell[1];

	        for (int k = 0; k < 6; k++) {
	            int nr = r + dr[k];
	            int nc = c + dc[k];

	            if (nr < 0 || nr >= 7 ||
	                nc < 0 || nc >= 13) {
	                continue;
	            }

	            if (board[nr][nc] != dice.get(index)) continue;
	            if (visited[nr][nc]) continue;

	            candidates.add(nr + "," + nc);
	        }
	    }

	    // Không có candidate: bỏ qua viên xúc xắc
	    if (candidates.isEmpty()) {
	        backtrack(index + 1);
	        return;
	    }

	    // Thử từng candidate
	    for (String candidate : candidates) {

	        String[] pos = candidate.split(",");
	        int nr = Integer.parseInt(pos[0]);
	        int nc = Integer.parseInt(pos[1]);

	        visited[nr][nc] = true;
	        checked.push(new int[]{nr, nc});

	        backtrack(index + 1);

	        checked.pop();
	        visited[nr][nc] = false;
	    }
	}
	
	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("src/TC/BoardGameTC.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	    int T = Integer.parseInt(br.readLine());
	    for(int tc = 1; tc <= T; tc++) {
	    	board = new int[7][13];
	    	for(int[] rows: board) {
	    		Arrays.fill(rows, -1);
	    	}
	    	int diceSize = Integer.parseInt(br.readLine());
	    	dice = new ArrayList<>(diceSize);
	    	visited = new boolean[7][13];
	    	checked = new Stack<>();
	    	best = 0;
	    	st = new StringTokenizer(br.readLine());
	    	for(int i = 0; i < diceSize; i++) {
	    		dice.add(Integer.parseInt(st.nextToken()));
	    	}
	    	
	    	for(int i = 0; i < 7; i++) {
	    		st = new StringTokenizer(br.readLine());
	    		if(i == 0 || i == 6) {
	    			board[i][3] = Integer.parseInt(st.nextToken()); 
	    			board[i][5] = Integer.parseInt(st.nextToken()); 
	    			board[i][7] = Integer.parseInt(st.nextToken()); 
	    			board[i][9] = Integer.parseInt(st.nextToken()); 
	    		}
	    		
	    		if(i == 1 || i == 5) {
	    			board[i][2] = Integer.parseInt(st.nextToken()); 
	    			board[i][4] = Integer.parseInt(st.nextToken()); 
	    			board[i][6] = Integer.parseInt(st.nextToken()); 
	    			board[i][8] = Integer.parseInt(st.nextToken()); 
	    			board[i][10] = Integer.parseInt(st.nextToken()); 
	    		}
	    		
	    		if(i == 2 || i == 4) {
	    			board[i][1] = Integer.parseInt(st.nextToken());
	    			board[i][3] = Integer.parseInt(st.nextToken()); 
	    			board[i][5] = Integer.parseInt(st.nextToken()); 
	    			board[i][7] = Integer.parseInt(st.nextToken()); 
	    			board[i][9] = Integer.parseInt(st.nextToken()); 
	    			board[i][11] = Integer.parseInt(st.nextToken()); 
	    		}
	    		
	    		if(i == 3) {
	    			board[i][0] = Integer.parseInt(st.nextToken());
	    			board[i][2] = Integer.parseInt(st.nextToken()); 
	    			board[i][4] = Integer.parseInt(st.nextToken()); 
	    			board[i][6] = Integer.parseInt(st.nextToken()); 
	    			board[i][8] = Integer.parseInt(st.nextToken()); 
	    			board[i][10] = Integer.parseInt(st.nextToken()); 
	    			board[i][12] = Integer.parseInt(st.nextToken());
	    		}
	    	}
	    	checked.push(new int[] {3, 6});
	    	visited[3][6] = true;
	    	backtrack(0);
	    	
	    	System.out.println(best - 1);
	    }
	}

}
