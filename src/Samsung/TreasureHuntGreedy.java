package Samsung;

import java.io.*;
import java.util.*;

public class TreasureHuntGreedy {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            int[][] zones = new int[N + 1][];
            for (int i = 1; i <= N; i++) {
                int size = Integer.parseInt(br.readLine().trim());
                StringTokenizer st = new StringTokenizer(br.readLine());
                zones[i] = new int[size];
                for (int j = 0; j < size; j++) zones[i][j] = Integer.parseInt(st.nextToken());
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            long sum = 0;
            for (int k = N; k >= 1; k--) {
                for (int v : zones[k]) pq.add(v);
                sum += pq.poll();
            }
            System.out.printf("#%d %d\n", tc, sum);
        }
    }
}
/**
4
5
1
3000
4
8000 4000 5000 1000
2
2000 6000
1
2500
2
7000 5500
5
4
1049 8409 8444 6054
3
5983 2726 3627
2
8591 4074
3
4610 7157 3958
2
8562 1304
5
2
7490 5898
3
4970 9393 1788
1
3155
3
5837 7236 5894
3
6293 6630 3662
10
4
8284 2317 7755 3301
3
1040 8514 2311
2
2786 3048
2
2967 9401
3
9123 9614 5931
4
1536 8973 4761 4840
3
5688 2072 7870
1
8669
3
9216 5805 9908
3
8187 5501 9890
 */