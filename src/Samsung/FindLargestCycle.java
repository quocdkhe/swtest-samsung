package Samsung;

import java.io.*;
import java.util.*;

/**
 * Một ví dụ kinh điển về backtracking: tìm thành phần liên thông dài nhất của
 * đồ thị. Phát hiện chu trình: gặp một thằng đã đi qua rồi, nhưng không phải
 * thằng trực tiếp đi đến nó
 */
public class FindLargestCycle {

    /**
     *
     * 4
     *        / |
     *       / |
     *      / |
     * 3 |
     *    / \ |
     *   / \ |
     *  / \ |
     * / \| 1 ------ 2
     */
    static int[] steps;
    static List<Integer>[] edges;
    static int largest = 0;

    static void solve(int node, int parent, int step) {
        steps[node] = step;

        for (int next : edges[node]) {
            if (next == parent) { // nếu là node cha thì bỏ qua
                continue;
            }

            if (steps[next] > 0) { // nếu phát hiện node đã đi qua, nhưng ko phải là node cha => phát hiện cycle
                int found_cycle = step - steps[next] + 1;
                largest = Math.max(largest, found_cycle);
                continue;
            }
            solve(next, node, step + 1);
        }
        steps[node] = 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges[u].add(v);
            edges[v].add(u);
        }
        steps = new int[n + 1];
        solve(1, -1, 1);
        System.out.println(largest);
    }
}
