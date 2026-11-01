package Samsung;

import java.io.*;
import java.util.*;

/**
 * Cho đồ thị, điểm xuất phát bắt đầu từ 1, trong đó một số điểm được đánh dấu
 * là điểm du lịch
 *
 * Tìm đường để đi nhiều điểm du lịch nhất có thể. Đồng thời đảm bảo đường đi
 * đến các điểm đó ít bước đi nhất.
 *
 * bestCount - số điểm du lịch nhiều nhất đi được
 *
 * bestSteps - số bước ngắn nhất để đi đến các điểm đó
 *
 */
public class MinsuTourism {

    static List<Integer>[] adj;
    static Set<Integer> isSight;
    static boolean[] visited;
    static int bestCount;
    static int bestSteps;

    static void dfs(int u, int count, int steps) {
        if (count > bestCount || (count == bestCount && steps < bestSteps)) {
            bestCount = count;
            bestSteps = steps;
        }

        for (int v : adj[u]) {
            if (visited[v]) {
                continue;
            }
            visited[v] = true;
            if (isSight.contains(v)) {
                dfs(v, count + 1, steps + 1);
            } else {
                dfs(v, count, steps + 1);
            }
            visited[v] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }

            isSight = new HashSet<>();
            for (String s : br.readLine().split(" ")) {
                isSight.add(Integer.parseInt(s));
            }

            for (int r = 0; r < m; r++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj[u].add(v);
                adj[v].add(u);
            }

            visited = new boolean[n + 1];
            visited[1] = true;
            bestCount = 0;
            bestSteps = 0;

            dfs(1, 0, 0);
            System.out.println("#" + t + " " + bestCount + " " + bestSteps);
        }
    }
}
