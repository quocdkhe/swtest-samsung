package Samsung;

import java.io.*;
import java.util.*;

/**
 * Tìm đường hợp lệ trên đồ thị có trọng số, tìm các đường thoả mãn tổng weight
 * nhỏ hơn k ValidPath
 */
public class ValidPath {

    static class Edge {

        public int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static boolean visited[];
    static List<Edge>[] adj;
    static int n, m, k, count;

    static void dfs(int u, int weight) {
        for (Edge v : adj[u]) {
            if (visited[v.to]) {
                continue;
            }
            visited[v.to] = true;
            if (weight + v.weight <= k) {
                count++;
                dfs(v.to, weight + v.weight);
            }
            //backtrack
            visited[v.to] = false;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // khai bao bien
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            count = 0;
            adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }
            visited = new boolean[50];
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                adj[u].add(new Edge(v, w));
                adj[v].add(new Edge(u, w));
            }
            visited[1] = true;
            dfs(1, 0);
            System.out.println(count);
        }
    }
}
