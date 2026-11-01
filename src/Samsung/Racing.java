package Samsung;

import java.io.*;
import java.util.*;

/**
 * Bài toán thay lốp
 *
 * Có T bộ test. Mỗi test:
 *
 * Dòng đầu: N tire0, với N là số trạm nằm giữa điểm xuất phát S và đích G,
 * tire0 là độ bền lốp ban đầu. N dòng tiếp theo, dòng thứ i gồm tire_i time_i:
 * độ bền của lốp mới nếu thay ở trạm i, và thời gian mất để thay.
 *
 * Độ bền d nghĩa là lốp đi được tối đa d chặng liên tiếp thì phải thay (hoặc
 * phải tới đích). Xuất phát ở vị trí 0, các trạm là 1..N, đích ở vị trí N+1.
 *
 * Yêu cầu: tìm tổng thời gian thay lốp nhỏ nhất để tới đích.
 */
public class Racing {

    static int N;
    static int[] tire;
    static int[] time;
    static int tire0;
    static int best;

    static void Try(int pos, int d, int sum) {
        if (sum >= best) {
            return;
        }
        if (pos + d >= N + 1) {
            best = sum;
            return;
        }

        for (int j = pos + 1; j <= Math.min(pos + d, N); j++) {
            Try(j, tire[j], sum + time[j]);
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            tire0 = Integer.parseInt(st.nextToken());

            tire = new int[N + 1];
            time = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                tire[i] = Integer.parseInt(st.nextToken());
                time[i] = Integer.parseInt(st.nextToken());
            }
            best = Integer.MAX_VALUE;
            Try(0, tire0, 0);
            System.out.println("#" + tc + " " + best);
        }
    }
}
