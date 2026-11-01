package Samsung;

import java.io.*;

public class PickLargestSum {

    /**
     *
     * Đề bài: Cho ma trận NxM, ở mỗi hàng, chọn ra một số để cuối cùng tổng của
     * chúng là lớn nhất. Rule: cột nào đã được chọn rồi thì không chọn lại
     *
     * Bản chất của bài toán backtrack: khi việc đưa ra một quyết định ảnh hưởng
     * đến các quyết định khác
     *
     * Ví dụ ở đây luôn tham chiếu đến mảng đánh dấu
     */
    static int N;
    static int M;
    static int result;
    static boolean[] picked;
    static int board[][];

    static void solve(int row, int sum) {
        if (row >= N) {
            result = Math.max(result, sum);
            return;
        }

        for (int col = 0; col < M; col++) {
            if (picked[col]) {
                continue;
            }

            picked[col] = true;
            solve(row + 1, sum + board[row][col]);
            // THIS: is backtracking step, we need to unmark the column after exploring that path
            picked[col] = false;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] tokens = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        picked = new boolean[M];
        result = Integer.MIN_VALUE;
        solve(0, 0);
        System.out.println(result);
    }
}
