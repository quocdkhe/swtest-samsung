package Practice;

import java.util.*;

public class LeetCode815 {

    public int numBusesToDestination(int[][] routes, int source, int target) {
        int length = routes.length;
        Set<Integer>[] routeSet = new Set[length];
        int[] dist = new int[length];
        Arrays.fill(dist, -1);

        for (int i = 0; i < length; i++) {
            routeSet[i] = new HashSet<>();
        }
        for (int i = 0; i < length; i++) {
            for (int route : routes[i]) {
                routeSet[i].add(route);
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        // find start
        for (int i = 0; i < length; i++) {
            if (routeSet[i].contains(source)) {
                queue.offer(i);
                dist[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            int d = dist[current];

            if (routeSet[current].contains(target)) {
                return d + 1;
            }

            for (int route : routeSet[current]) {
                for (int i = 0; i < length; i++) {

                    if (i != current && routeSet[i].contains(route) && dist[i] == -1) {
                        queue.offer(i);
                        dist[i] = d + 1;
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        LeetCode815 app = new LeetCode815();
        int[][] routes = new int[][]{{1, 7}, {3, 5}};
        int source = 15;
        int target = 12;
        int res = app.numBusesToDestination(routes, source, target);
        System.out.println(res);
    }
}
