package Samsung.Test;

import java.io.*;
import java.util.*;

/**
 * Trò chơi con ếch: Có 2 con ếch. Người chơi sẽ ấn phím trên bàn phím để điều
 * khiển một con ếch theo 4 hướng lên xuống trái phải. Con ếch còn lại sẽ di
 * chuyển theo hướng ngược lại. Nếu 2 con ếch gặp nhau thì sẽ bị va chạm và
 * không thể di chuyển.
 *
 * Hãy tính số lần nhấn phím tối thiểu để cả 2 con ếch đến được đích. Nếu không
 * thể thì in ra -1.
 */
public class FrogAndGreenFrog {

    static int startR1, startC1, startR2, startC2;
    static char[][] grid;
    static int destR, destC;
    static int R, C;
    static int[] dr = new int[]{-1, 0, 0, 1}; // U, D, L, R
    static int[] dc = new int[]{0, -1, 1, 0};

    public static boolean isInvalid(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C || grid[r][c] == '#';
    }

    public static boolean reachedDest(int r, int c) {
        return r == destR && c == destC;
    }

    public static int bfs() {
        int[][][][] dist = new int[R][C][R][C];
        Queue<int[]> queue = new ArrayDeque<>();
        for (int[][][] dim1 : dist) {
            for (int[][] dim2 : dim1) {
                for (int[] dim3 : dim2) {
                    Arrays.fill(dim3, -1);
                }
            }
        }
        dist[startR1][startC1][startR2][startC2] = 0;
        queue.offer(new int[]{startR1, startC1, startR2, startC2});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r1 = current[0];
            int c1 = current[1];
            int r2 = current[2];
            int c2 = current[3];
            int d = dist[r1][c1][r2][c2];
            if (reachedDest(r1, c1) && reachedDest(r2, c2)) {
                return d;
            }

            for (int k = 0; k < 4; k++) {
                int nr1 = r1 + dr[k];
                int nc1 = c1 + dc[k];
                int nr2 = r2 + dr[3 - k];
                int nc2 = c2 + dc[3 - k];

                // invalid
                if (isInvalid(nr1, nc1) && isInvalid(nr2, nc2)) {
                    continue;
                }

                //staionary
                if ((isInvalid(nr1, nc1) && !isInvalid(nr2, nc2))
                        || reachedDest(r1, c1) //						|| grid[nr1][nc1] == '#'
                        ) {
                    nr1 = r1;
                    nc1 = c1;
                }

                if ((!isInvalid(nr1, nc1) && isInvalid(nr2, nc2))
                        || reachedDest(r2, c2) //						|| grid[nr2][nc2] == '#'
                        ) {
                    nr2 = r2;
                    nc2 = c2;
                }

                // collision
                if (nr1 == nr2 && nc1 == nc2 && !reachedDest(nr1, nc1)) {
                    continue;
                }

                if (dist[nr1][nc1][nr2][nc2] != -1) {
                    continue;
                }

                dist[nr1][nc1][nr2][nc2] = d + 1;
                queue.offer(new int[]{nr1, nc1, nr2, nc2});
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/TC/FrogAndGreenFrogTC.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            grid = new char[R][C];
            st = new StringTokenizer(br.readLine());
            startR1 = Integer.parseInt(st.nextToken()) - 1;
            startC1 = Integer.parseInt(st.nextToken()) - 1;
            startR2 = Integer.parseInt(st.nextToken()) - 1;
            startC2 = Integer.parseInt(st.nextToken()) - 1;
            destR = Integer.parseInt(st.nextToken()) - 1;
            destC = Integer.parseInt(st.nextToken()) - 1;
            for (int i = 0; i < R; i++) {
                grid[i] = br.readLine().toCharArray().clone();
            }

            System.out.printf("#%d %d", tc, bfs());

        }
    }

}
