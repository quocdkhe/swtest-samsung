package Samsung;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * Bài toán lucky ball, quả bóng chỉ có thể lăn xuống ô dưới, 3 vị trí: dưới
 * chéo trái, phải 1 đơn vị, hoặc ngay dưới
 *
 * Tìm tổ hợp để tổng số được chọn là lớn nhất
 *
 *
 * RollABall
 */
public class RollABall {

    static int solve(int[][] a, int i, int j) {
        int n = a.length;
        int m = a[0].length;

        if (j < 0 || j >= m) {
            return a[i][j];
        }

        int down = solve(a, i + 1, j);
        int left = solve(a, i + 1, j - 1);
        int right = solve(a, i + 1, j + 1);

        return a[i][j] + Math.max(down, Math.max(left, right));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] a = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < a[0].length; i++) {
            ans = Math.min(ans, solve(a, 0, i));
        }

        bw.write(ans);
        bw.flush();
    }
}
