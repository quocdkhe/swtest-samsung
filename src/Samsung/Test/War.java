package Samsung.Test;

import java.io.*;
import java.util.*;

/**
 * Hai quốc gia A và B có mỗi bên một base trên đồ thị vô hướng. Mỗi ngày, mỗi
 * bên chiếm các base trung lập kề với vùng mình đã chiếm. Base bị cả hai bên
 * chiếm cùng ngày trở thành conflict và chặn việc lan truyền. Khi không bên
 * nào lan tiếp được, in số base conflict và số base A cần thêm (có thể tính cả
 * base conflict) để có nhiều base hơn B; nếu không thể thì in -1.
 */
public class War {

    static List<Integer>[] graph;
    static int N, M, startA, startB, zoneA, zoneB, conflictCount;
    static int state[]; // 1 = A, 2 = B, 0 = conflict, -1 = neutral

    public static void bfs() {
        Queue<Integer> queueA = new LinkedList<>();
        Queue<Integer> queueB = new LinkedList<>();
        queueA.offer(startA);
        queueB.offer(startB);

        while (!queueA.isEmpty() || !queueB.isEmpty()) {
            Set<Integer> nextA = new HashSet<>();
            for (int s = queueA.size(); s > 0; s--) {
                int currentA = queueA.poll();
                for (int next : graph[currentA]) {
                    if (state[next] == -1) {
                        nextA.add(next);
                    }
                }
            }

            Set<Integer> nextB = new HashSet<>();
            for (int s = queueB.size(); s > 0; s--) {
                int currentB = queueB.poll();
                for (int next : graph[currentB]) {
                    if (state[next] == -1) {
                        nextB.add(next);
                    }
                }
            }

            Set<Integer> conflicts = new HashSet<>(nextA);
            conflicts.retainAll(nextB);

            conflictCount += conflicts.size();
            conflicts.forEach(base -> {
                state[base] = 0;
            });

            for (int base : nextA) {
                if (!conflicts.contains(base) && state[base] == -1) {
                    state[base] = 1;
                    queueA.offer(base);
                    zoneA++;
                }
            }

            for (int base : nextB) {
                if (!conflicts.contains(base) && state[base] == -1) {
                    state[base] = 2;
                    queueB.offer(base);
                    zoneB++;
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            startA = Integer.parseInt(st.nextToken());
            startB = Integer.parseInt(st.nextToken());
            conflictCount = 0;
            graph = new ArrayList[N + 1];
            state = new int[1001];
            Arrays.fill(state, -1);
            state[startA] = 1;
            state[startB] = 2;
            zoneA = 1;
            zoneB = 1;

            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            bfs();

            int free = N - zoneB - zoneA;
            int need = Math.max(0, zoneB - zoneA + 1);
            int addition = (need <= free) ? need : -1;

            System.out.printf("#%d %d %d\n", tc, conflictCount, addition);

        }
    }
}
