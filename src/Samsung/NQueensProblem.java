package Samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Bài toán N quân hậu NQueenProblem, đặt quân hậu trên bàn cờ NxN để không ăn
 * nhau. Có bao nhiêu cách ?
 */
public class NQueensProblem {

    static int N;
    static boolean[] colUsed;
    static boolean[] diag1Used;
    static boolean[] diag2Used;

    // static int[] pos;
    /**
     * Cách dễ hiểu trước, check mỗi lần mà đặt hậu
     */
    // static boolean isSafe(int row, int col, int[] pos) {
    //     for (int r = 0; r < row; r++) {
    //         int c = pos[r];
    //         if (c == col) { // check cùng cột
    //             return false;
    //         }
    //         if (Math.abs(c - col) == row - r) { // check đường chéo
    //             return false;
    //         }
    //     }
    //     return true;
    // }
    // static int place(int row, int n, int[] pos) {
    //     if (row == n) {
    //         return 1;
    //     }
    //     int count = 0;
    //     for (int col = 0; col < n; col++) {
    //         if (isSafe(row, col, pos)) {
    //             pos[row] = col;
    //             count += place(row + 1, n, pos);
    //         }
    //     }
    //     return count;
    // }
    static int place(int row) {

        int count = 0;
        for (int col = 0; col < N; col++) {
            if (row == N) {
                return 1;
            }

            int d1 = row + col;
            int d2 = row - col + N - 1;
            if (colUsed[col] || diag1Used[d1] || diag2Used[d2]) {
                continue;
            }
            // place queen
            colUsed[col] = diag1Used[d1] = diag2Used[d2] = true;

            count += place(row + 1);

            // backtrack
            colUsed[col] = diag1Used[d1] = diag2Used[d2] = false;
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	 N = Integer.parseInt(br.readLine());
             // pos = new int[N]; // cột của quân ở hàng thứ r
             // System.out.println(place(0, N, pos));

             colUsed = new boolean[N];
             diag1Used = new boolean[2 * N - 1];
             diag2Used = new boolean[2 * N - 1];
             int result = place(0);
             System.out.printf("#%d %d\n",tc, result);
        }
       
    }

}
