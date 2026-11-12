
package Practice;

import java.util.*;
import java.io.*;

public class OlyaAndEnergyDrinks {

    // ===== Static variables =====
    static int n, m, k;
    static int startR, startC;
    static int endR, endC;

    static char[][] grid;
    static int[][] dist;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static final int INF = Integer.MAX_VALUE;

    // ===== Core logic =====
    static int solve() {

        dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        // TODO: BFS tìm số bước ít nhất từ start đến end
        
        return dist[endR][endC] == INF ? -1 : dist[endR][endC];
    }

    // ===== Main =====
    public static void main(String[] args) throws IOException {

        // System.setIn(new FileInputStream("src/TC/OlyaAndEnergyDrinksTC.txt"));

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        grid = new char[n][m];

        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        st = new StringTokenizer(br.readLine());

        startR = Integer.parseInt(st.nextToken()) - 1;
        startC = Integer.parseInt(st.nextToken()) - 1;

        endR = Integer.parseInt(st.nextToken()) - 1;
        endC = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(solve());
    }
}
