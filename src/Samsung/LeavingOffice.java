package Samsung;

import java.io.*;
import java.util.*;

/**
 * Tìm đường ngắn nhất để đi đến exit, kí hiệu là 'O'. Cửa là 'A', tường là 'X'
 * - luôn luôn không đi qua được khoá là 'a'. Nhặt khoá lên thì đi được qua cửa
 *
 */
public class LeavingOffice {

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    // BFS tu (sx, sy); canPassDoor = true thi duoc di qua cua 'A'
    static int[][] bfs(char[][] map, int sx, int sy, boolean canPassDoor) {
        int N = map.length;
        int M = map[0].length;
        int[][] dist = new int[N][M];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy});
        dist[sx][sy] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nx = cur[0] + dx[k];
                int ny = cur[1] + dy[k];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (map[nx][ny] == 'X' || (map[nx][ny] == 'A' && !canPassDoor)) {
                    continue;
                }
                if (dist[nx][ny] != -1) {
                    continue;
                }

                dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                queue.offer(new int[]{nx, ny});
            }
        }
        return dist;
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

            char[][] map = new char[N][];
            int exitX = 0, exitY = 0;
            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().trim().toCharArray();
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 'O') {
                        exitX = i;
                        exitY = j;
                    }
                }
            }

            int[][] distS = bfs(map, startX, startY, false); // tu start, chua co key
            int[][] distE = bfs(map, exitX, exitY, true);    // tu exit, co key

            // cach 1: khong can key
            int result = distS[exitX][exitY];

            // cach 2: start -> key -> exit
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 'a' && distS[i][j] != -1 && distE[i][j] != -1) {
                        int total = distS[i][j] + distE[i][j];
                        if (result == -1 || total < result) {
                            result = total;
                        }
                    }
                }
            }

            System.out.println("#" + t + " " + result);
        }
    }
}
