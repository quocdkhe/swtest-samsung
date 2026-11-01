package Samsung;

import java.io.*;
import java.util.*;

/**
 * Cho điểm bắt đầu là R và K, gán vào startX và startY, không được đi qua núi -
 * kí hiệu là X. Nhặt 3 vật phẩm A B và C để có excalibur, sau đó có thể đi qua
 * núi dễ dàng. Tìm đường ngắn nhất đi qua A, B, C và đến S. Lưu ý, nếu chưa có
 * excalibur, S có thể đi qua bình thường
 *
 * Excalibur
 */
public class Excalibur {

    static int N, M, R, K;
    static int[] dr;
    static int[] dc;
    static char[][] matrix;
    static int[][][] dist;

    static int bit(char ch) {
        if (ch == 'A') { // 001
            return 1;
        }
        if (ch == 'B') { // 010
            return 2;
        }
        if (ch == 'C') { // 100
            return 4;
        }
        return 0;
    }

    static BufferedReader bReader;

    static String readNonEmptyLine() throws IOException {
        String line = bReader.readLine();
        while (line != null && line.trim().isEmpty()) {
            line = bReader.readLine();
        }
        return line;
    }

    public static void main(String[] args) throws Exception {
        bReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        int T = Integer.parseInt(readNonEmptyLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            stringTokenizer = new StringTokenizer(readNonEmptyLine());
            N = Integer.parseInt(stringTokenizer.nextToken());
            M = Integer.parseInt(stringTokenizer.nextToken());
            R = Integer.parseInt(stringTokenizer.nextToken());
            K = Integer.parseInt(stringTokenizer.nextToken());
            dr = new int[]{-1, 0, 0, 1};
            dc = new int[]{0, -1, 1, 0};
            matrix = new char[N][M];
            dist = new int[N][M][8];
            int startX = R - 1;
            int startY = K - 1;

            for (int[][] pos : dist) {
                for (int[] state : pos) {
                    Arrays.fill(state, -1);
                }
            }

            for (int i = 0; i < N; i++) {
                char[] chars = readNonEmptyLine().trim().toCharArray();
                for (int j = 0; j < M; j++) {
                    matrix[i][j] = chars[j];
                }
            }

            Queue<int[]> queue = new LinkedList<>();
            int startMask = bit(matrix[startX][startY]);
            dist[startX][startY][startMask] = 0;
            queue.add(new int[]{startX, startY, startMask});

            int ans = -1;
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int r = cur[0], c = cur[1], mask = cur[2];
                int d = dist[r][c][mask];

                if (matrix[r][c] == 'S' && mask == 7) {
                    ans = d;
                    break;
                }

                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k], nc = c + dc[k];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                        continue;
                    }
                    if (matrix[nr][nc] == 'X' && mask != 7) {
                        continue;
                    }
                    int nextMask = mask | bit(matrix[nr][nc]); // so bit,
                    if (dist[nr][nc][nextMask] != -1) {
                        continue;
                    }
                    dist[nr][nc][nextMask] = d + 1;
                    queue.offer(new int[]{nr, nc, nextMask});
                }
            }
            System.out.println("#" + tc + " " + ans);
        }
    }
}
