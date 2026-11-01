package Samsung;

import java.io.*;
import java.util.*;

/**
 * Bài toán unicorn: nâng cấp quân mã lên unicorn (có thể đi như knight bình
 * thường, cộng thêm việc nhảy nhiều bước như mã bình thường, tối đa 3 bước,
 * nhưng phải theo 1 đường thẳng - và tính là một bước)
 *
 * So sánh số bước đi của Knight và Unicorn trên bàn cờ NxN cần thiết để tiêu
 * diệt một con tốt
 */
public class Unicorn {

    public static int N, rUnicorn, cUnicorn, rPawn, cPawn;
    public static List<Integer> drKnight;
    public static List<Integer> dcKnight;
    public static List<Integer> drUnicorn;
    public static List<Integer> dcUnicorn;

    public static int bfs(List<Integer> dr, List<Integer> dc) {
        int ans = -1;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dist = new int[N][N];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }
        dist[rUnicorn][cUnicorn] = 0;
        queue.offer(new int[]{rUnicorn, cUnicorn});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int d = dist[r][c];
            for (int k = 0; k < dr.size(); k++) {
                int nr = r + dr.get(k);
                int nc = c + dc.get(k);
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }
                if (dist[nr][nc] != -1) {
                    continue;
                }
                if (nr == rPawn && nc == cPawn) {
                    ans = d + 1;
                    return ans;
                }
                dist[nr][nc] = d + 1;
                queue.offer(new int[]{nr, nc});
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            rUnicorn = Integer.parseInt(st.nextToken()) - 1;
            cUnicorn = Integer.parseInt(st.nextToken()) - 1;
            rPawn = Integer.parseInt(st.nextToken()) - 1;
            cPawn = Integer.parseInt(st.nextToken()) - 1;
            drKnight = Arrays.asList(-2, -2, -1, -1, 1, 1, 2, 2);
            dcKnight = Arrays.asList(-1, 1, -2, 2, -2, 2, -1, 1);
            drUnicorn = new ArrayList<>();
            dcUnicorn = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                for (int num : drKnight) {
                    drUnicorn.add(num * i);
                }
                for (int num : dcKnight) {
                    dcUnicorn.add(num * i);
                }
            }

            int res = bfs(drKnight, dcKnight) - bfs(drUnicorn, dcUnicorn);
            System.out.printf("#%d %d\n", t, res);
        }

    }
}
