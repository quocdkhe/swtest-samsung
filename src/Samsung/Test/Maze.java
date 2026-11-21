package Samsung.Test;

import java.io.*;
import java.util.*;

/**
 *
 * Cho đồ thị vô hướng gồm V đỉnh và E cạnh, mỗi cạnh nối hai đỉnh u, v. Người
 * chơi bắt đầu tại đỉnh S và tung xúc sắc K lần. Mỗi lần tung, xúc sắc cho ra
 * một giá trị x từ 1 đến 4; người chơi phải đi đúng x cạnh liên tiếp, mỗi lần
 * đi sang một đỉnh kề với đỉnh hiện tại. Được phép đi lặp cạnh và lặp đỉnh.
 *
 * VD: Nếu đang ở đỉnh 1 và có cạnh nối 1 với 4, khi tung được 3 thì có thể đi 1
 * -> 4 -> 1 -> 4, tức là sau đúng 3 cạnh có thể đến đỉnh 4.
 *
 * Hãy in ra tất cả các đỉnh có thể đứng tại vị trí kết thúc sau khi hoàn tất cả
 * K lần tung xúc sắc, bắt đầu từ đỉnh S. Các giá trị của K lần tung được cho
 * trong input; tổng số cạnh cần đi bằng tổng các giá trị này.
 */
public class Maze {

    static int V, E, K, S, step;
    static List<Integer>[] adj;

    public static void solve() {
        boolean[][] dp = new boolean[step + 1][V + 1];
        dp[0][S] = true;

        for (int k = 0; k < step; k++) {
            for (int u = 1; u <= V; u++) {
                if (!dp[k][u]) {
                    continue;
                }
                for (int v : adj[u]) {
                    dp[k + 1][v] = true;
                }
            }
        }

        for (int v = 1; v <= V; v++) {
            if (dp[step][v]) {
                System.out.printf(" %d", v);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/TC/MazeTC.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            adj = new List[V + 1];
            step = 0;
            for (int i = 1; i <= V; i++) {
                adj[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj[u].add(v);
                adj[v].add(u);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                step += Integer.parseInt(st.nextToken());
            }

            System.out.printf("#%d", tc);
            solve();
            System.out.println();
        }
    }

}
