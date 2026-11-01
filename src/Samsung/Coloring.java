package Samsung;

import java.io.*;
import java.util.*;

/**
 * Đề bài: Tô màu vào những ô nào mà 4 ô xung quanh nó, cộng trừ theo bất kì
 * cách nào, ra được giá trị ô ở giữa
 */
public class Coloring {

    static boolean isValid(List<Integer> neighbors, int target) {
        int a = 0, b = 0, c = 0, d = 0;
        if (neighbors.size() == 2) {
            a = neighbors.get(0);
            b = neighbors.get(1);
        }
        if (neighbors.size() == 3) {
            a = neighbors.get(0);
            b = neighbors.get(1);
            c = neighbors.get(2);
        }
        if (neighbors.size() == 4) {
            a = neighbors.get(0);
            b = neighbors.get(1);
            c = neighbors.get(2);
            d = neighbors.get(3);
        }

        return (a + b + c + d == target
                || // 1 dau tru
                a - b + c + d == target
                || a + b - c + d == target
                || a + b + c - d == target
                || -a + b + c + d == target
                || // 2 dau tru
                a - b - c + d == target
                || a - b + c - d == target
                || a + b - c - d == target
                || -a - b + c + d == target
                || -a + b - c + d == target
                || -a + b + c - d == target
                || // 3 dau tru
                a - b - c - d == target
                || -a - b + c - d == target
                || -a + b - c - d == target
                || -a - b - c + d == target
                || // 4 dau tru
                -a - b - c - d == target);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] matrix = new int[N][M];
            int[][] colored = new int[N][M];

            // enter the matrix
            for (int i = 0; i < N; i++) {
                StringTokenizer st1 = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    matrix[i][j] = Integer.parseInt(st1.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    List<Integer> neighbors = new ArrayList<>();
                    if (i > 0) {
                        neighbors.add(matrix[i - 1][j]);
                    }
                    if (i + 1 < N) {
                        neighbors.add(matrix[i + 1][j]);
                    }
                    if (j > 0) {
                        neighbors.add(matrix[i][j - 1]);
                    }
                    if (j + 1 < M) {
                        neighbors.add(matrix[i][j + 1]);
                    }

                    if (isValid(neighbors, matrix[i][j])) {
                        colored[i][j] = 1;
                    } else {
                        colored[i][j] = 0;
                    }
                }
            }

            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (colored[i][j] == 1) {
                        count++;
                    }
                }
            }
            bw.write("Case #" + t + ": " + count + "\n");
        }
        bw.flush();
    }
}
