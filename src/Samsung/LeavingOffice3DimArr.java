package Samsung;

import java.io.*;
import java.util.*;

/**
 * Tìm đường ngắn nhất để đi đến exit, kí hiệu là 'O'. Cửa là 'A', tường là 'X'
 * - luôn luôn không đi qua được khoá là 'a'. Nhặt khoá lên thì đi được qua cửa
 * LeavingOffice3DimArr
 */

public class LeavingOffice3DimArr {

    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};

    static class State {

        public int x;
        public int y;
        public int hasKey;

        public State(int x, int y, int hasKey) {
            this.x = x;
            this.y = y;
            this.hasKey = hasKey;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken()) - 1; // toa do bat dau tu 1
            int startY = Integer.parseInt(st.nextToken()) - 1;

            char[][] map = new char[N][M];
            int[][][] dist = new int[N][M][2];

            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().trim().toCharArray();
            }

            Queue<State> queue = new LinkedList<>();
            queue.offer(new State(startX, startY, 0));

            for (int[][] e : dist) {
                for (int[] f : e) {
                    Arrays.fill(f, -1);
                }
            }
            dist[startX][startY][0] = 0;
            int best = Integer.MAX_VALUE;
            while (!queue.isEmpty()) {
                State current = queue.poll();
                int r = current.x;
                int c = current.y;
                int hasKey = current.hasKey;
                int d = dist[r][c][hasKey];
                if (map[r][c] == 'O') {
                    best = Math.min(best, d);
                }
                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                        continue;
                    }
                    if (hasKey != 1 && map[nr][nc] == 'A') {
                        continue;
                    }

                    if (map[nr][nc] == 'X') {
                        continue;
                    }
                    int nextKey = (hasKey == 1 || map[nr][nc] == 'a') ? 1 : 0;
                    if (dist[nr][nc][nextKey] != -1) {
                        continue;
                    }

                    dist[nr][nc][nextKey] = d + 1;
                    queue.offer(new State(nr, nc, nextKey));

                }
            }
            System.out.println("Case #" + t + ": " + (best == Integer.MAX_VALUE ? -1 : best));
        }
    }
}
