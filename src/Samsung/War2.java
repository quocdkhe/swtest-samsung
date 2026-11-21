package Samsung;

import java.io.*;
import java.util.*;

public class War2 {

    static char[][] grid;
    static int R, C;
    static int[] dr = new int[]{-1, 0, 0, 1};
    static int[] dc = new int[]{0, -1, 1, 0};
    static List<int[]> As;
    static List<int[]> Bs;

    public static void getAll() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 'A') {
                    As.add(new int[]{r, c});
                }
                if (grid[r][c] == 'B') {
                    Bs.add(new int[]{r, c});
                }
            }
        }
    }

    public static int countIslandA(int startR, int startC, boolean[][] visited, Set<Integer> occupied) {
        Stack<int[]> stack = new Stack<>();
        int count = 1;
        stack.push(new int[]{startR, startC});
        visited[startR][startC] = true;
        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            int r = cur[0];
            int c = cur[1];
            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    continue;
                }
                if (visited[nr][nc]) {
                    continue;
                }
                if (grid[nr][nc] == 'A' || occupied.contains(encode(nr, nc))) {
                    stack.push(new int[]{nr, nc});
                    visited[nr][nc] = true;
                    count++;
                }

            }
        }
        return count;
    }

    public static int countLargestA(Set<Integer> occupied) {
        int best = -1;
        boolean[][] visited = new boolean[R][C];
        for (int[] cell : As) {
            int r = cell[0];
            int c = cell[1];
            if (visited[r][c]) {
                continue;
            }
            int count = countIslandA(r, c, visited, occupied);
            if (count > best) {
                best = count;
            }

        }
        return best;
    }

    public static int encode(int r, int c) {
        return r * C + c;
    }

    public static Set<Integer> countIslandB(int startR, int startC, boolean[][] visited) {
        Set<Integer> setB = new HashSet<>();
        setB.add(encode(startR, startC));
        visited[startR][startC] = true;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    continue;
                }
                if (visited[nr][nc]) {
                    continue;
                }
                if (grid[nr][nc] == 'A') {
                    continue;
                }
                queue.offer(new int[]{nr, nc});
                visited[nr][nc] = true;
                setB.add(encode(nr, nc));
            }
        }

        return setB;
    }

    public static void solve() {
        // countB
        boolean[][] visited = new boolean[R][C];
        int best = -1;
        for (int[] cell : Bs) {
            int r = cell[0];
            int c = cell[1];
            if (visited[r][c]) {
                continue;
            }
            Set<Integer> occupied = countIslandB(r, c, visited);
            int largest = countLargestA(occupied);
            if (largest > best) {
                best = largest;
            }
        }
        System.out.println(best);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("TC/War2TC.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            As = new ArrayList<>();
            Bs = new ArrayList<>();
            grid = new char[R][C];
            for (int r = 0; r < R; r++) {
                grid[r] = br.readLine().toCharArray().clone();
            }
            getAll();
            solve();
        }

    }
}
