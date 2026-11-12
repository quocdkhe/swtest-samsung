package Samsung;

import java.io.*;
import java.util.*;

public class Pixel {
	
	public static int N, M;
	public static int[][] board;
	public static boolean[][] visited;
	static int[] dr = new int[] {-1, 0, 0, 1};
	static int[] dc = new int[] {0, -1, 1, 0};
	
	static class Region implements Comparable<Region>{
		public List<int[]> cells;
		
		@Override
		public int compareTo(Region other) {
			return this.cells.size() - other.cells.size();
		}
	}
	
	public static Region bfs(int startX, int startY) {
		List<int[]> cells = new ArrayList<>();
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {startX, startY});
		cells.add(new int[] {startX, startY});
		visited[startX][startY] = true;
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int r = current[0];
			int c = current[1];
			for(int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}
				if(visited[nr][nc] || board[nr][nc] == 0) {
					continue;
				}
				visited[nr][nc] = true;
				queue.offer(new int [] {nr, nc});
				cells.add(new int[] {nr, nc});
			}
		}
		
		Region region = new Region();
		region.cells = cells;
		return region;
	}
	
	public static void solve() {


		List<Region> regions = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(board[i][j] == 0 || visited[i][j] == true) {
					continue;
				}
				Region region = bfs(i, j);
				regions.add(region);
			}
		}
		Collections.sort(regions);
		int label = 1;
		int prev = -1;

		
		for(int i = 0; i < regions.size(); i++) {
			List<int[]> blocks = regions.get(i).cells;
			if(blocks.size() > prev && prev != -1) {
				label++;
			}
			for(int[] cell: regions.get(i).cells) {
				int r = cell[0]; int c = cell[1];
				board[r][c] = label;
			}
			prev = blocks.size();
		}
		
		List<Integer> vertical = new ArrayList<>(M);
		List<Integer> horizontal = new ArrayList<>(N);
		
		for (int r = 0; r < N; r++) {
			int sum = 0;
			for (int c = 0; c < M; c++) {
				sum += board[r][c];
			}
			horizontal.add(sum);
		}
		
		for (int c = 0; c < M; c++) {
			int sum = 0;
			for (int r = 0; r < N; r++) {
				sum += board[r][c];
			}
			vertical.add(sum);
		}
		
		int maxCol = Collections.max(vertical);
		int maxRow = Collections.max(horizontal);
		
		int firstMaxCol = vertical.indexOf(maxCol) + 1;
		int lastMaxCol = vertical.lastIndexOf(maxCol) + 1;
		
		int firstMaxRow = horizontal.indexOf(maxRow) + 1;
		int lastMaxRow = horizontal.lastIndexOf(maxRow) + 1;
		
		int res1, res2;
		
		res1 = getIndex(firstMaxCol, lastMaxCol);
		res2 = getIndex(firstMaxRow, lastMaxRow);
		
		System.out.printf(" %d %d\n", res1, res2);
		
	}
	
	
	public static int getIndex(int first, int last) {
		if(first == last) return first;
		int sumIndex = first + last;
		if(sumIndex % 2 == 1) {
			return sumIndex/2 + 1;
		}else {
			return sumIndex/2;
		}
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/TC/PixelTC.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	board = new int[N][M];
        	visited = new boolean[N][M];
        	for(int i = 0; i < N; i++) {
        		for(int j = 0; j < M; j++) {
        			 board[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	System.out.print("#" + tc);
        	solve();
        	
        }

	}

}
