package Samsung;

import java.io.*;
import java.util.*;

/**
 * COORDINATE COMPRESSION (NÉN TỌA ĐỘ)
 *
 * 1. Mục đích:
 * - Chuyển tọa độ thực có giá trị rất lớn thành chỉ số nhỏ trong mảng.
 * - Giảm bộ nhớ khi cần biểu diễn mặt phẳng hoặc lưới 2D.
 * - Giữ nguyên thứ tự và quan hệ lớn/nhỏ giữa các tọa độ.
 *
 * 2. Cách thực hiện:
 * - Thu thập tất cả tọa độ quan trọng vào một mảng.
 * - Sắp xếp tăng dần.
 * - Loại bỏ các tọa độ trùng nhau.
 * - Mỗi tọa độ được ánh xạ thành chỉ số tương ứng trong mảng mới.
 *
 * Ví dụ:
 * Tọa độ ban đầu: [1, 4, 6, 7, 9, 11]
 * Chỉ số nén:     [0, 1, 2, 3, 4, 5]
 *
 * 3. Ứng dụng với lưới 2D:
 * - Các tọa độ liên tiếp tạo thành một khoảng (một ô).
 * - Ô [i][j] đại diện cho hình chữ nhật:
 *   [X[i], X[i+1]] x [Y[j], Y[j+1]]
 * - Số ô = (X.length - 1) * (Y.length - 1).
 *
 * 4. Cách xác định vùng cần xử lý:
 * - Tìm chỉ số tọa độ biên bằng Binary Search.
 * - Với hình vuông (x, y, s):
 *   xStart = index(x)
 *   xEnd   = index(x + s)
 *   yStart = index(y)
 *   yEnd   = index(y + s)
 *
 * - Duyệt các ô thuộc vùng bằng đoạn nửa mở [start, end):
 *
 *   for (int i = xStart; i < xEnd; i++)
 *       for (int j = yStart; j < yEnd; j++)
 *           // Xử lý ô [i][j]
 *
 * 5. Lưu ý quan trọng:
 * - Mỗi chỉ số đại diện cho một khoảng, KHÔNG phải một tọa độ.
 * - Các ô sau khi nén không nhất thiết có kích thước bằng nhau.
 * - Khi tính diện tích, phải dùng tọa độ thực:
 *
 *   area = (X[i+1] - X[i]) * (Y[j+1] - Y[j]);
 *
 * - Nén tọa độ chỉ thay đổi cách đánh chỉ số, không làm thay đổi
 *   hình dạng, thứ tự hay diện tích thực của các vùng.
 */
public class RobotColoring {
	
	static class Square{
		public int x;
		public int y;
		public int s;
		
		public Square(int x, int y, int s) {
			this.x = x;
			this.y = y;
			this.s = s;
		}
	}
	
	static List<int[]> squares;
	static int[] areas;
	static boolean[][] visited;
	static List<Integer> X;
	static List<Integer> Y;
	static int N;
	
	public static void coloring() {
		for(int i = N - 1; i >= 0; i--) {
			int[] current = squares.get(i);
			int x = current[0];
			int y = current[1];
			int s = current[2];
			
			int startX = Collections.binarySearch(X, x);
			int startY = Collections.binarySearch(Y, y);
			int endX = Collections.binarySearch(X, x + s);
			int endY = Collections.binarySearch(Y, y + s);
			
			for(int j = startX; j < endX; j++) {
				for(int k = startY; k < endY; k++) {
					if(!visited[j][k]) {
						areas[i] += (X.get(j + 1) - X.get(j)) * (Y.get(k + 1) - Y.get(k));
						visited[j][k] = true;
					}
				}
			}
		}
	}
	
	public static void find() {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		List<int[]> smallest = new ArrayList<>();
		List<int[]> largest = new ArrayList<>();
		
		
		for(int i = 0; i < N; i++) {
			int current = areas[i];
			if(current < min && current != 0) {
				min = current;
			}
			if(current > max) {
				max = current;
			}
		}
		
		for(int i = 0; i < N; i++) {
			int current = areas[i];
			if(current == min) {
				smallest.add(new int[] {i + 1, current});
			}
			if(current == max) {
				largest.add(new int[] {i + 1, current});
			}
		}
		
		System.out.print(smallest.size());
		smallest.forEach(v -> {
			int color = v[0];
			int size = v[1];
			System.out.printf(" %d %d", color, size);
		});
		System.out.println();
		System.out.print(largest.size());
		largest.forEach(v -> {
			int color = v[0];
			int size = v[1];
			System.out.printf(" %d %d", color, size);
		});		
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/TC/RobotColoringTC.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	N = Integer.parseInt(br.readLine());
        	areas = new int[N];        	
        	st = new StringTokenizer(br.readLine());
        	squares = new ArrayList<>(N);
        	Set<Integer> setX = new TreeSet<>();
        	Set<Integer> setY = new TreeSet<>();
        	for(int i = 0; i < N; i++) {
        		int x = Integer.parseInt(st.nextToken());
        		int y = Integer.parseInt(st.nextToken());
        		int s = Integer.parseInt(st.nextToken());
        		
        		setX.add(x); setX.add(x + s);
        		setY.add(y); setY.add(y + s);
        		squares.add(new int[] {x, y, s});
        	}
        	X = new ArrayList<>(setX);
        	Y = new ArrayList<>(setY);
        	visited = new boolean[X.size()][Y.size()];
        	coloring();
        	System.out.printf("#%d ", tc);
        	find();
        	
        }
	}

}
