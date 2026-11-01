package Samsung;

import java.io.*;
import java.util.*;

/**
 * Gấp giấy origami theo kiểu stair fold
 */
// Step replay; helper nextInt is collapsed in the browser debugger.
public class Origami {

    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static int nextInt() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            String line = reader.readLine();
            if (line == null) {
                throw new EOFException("Missing input");
            }
            tokenizer = new StringTokenizer(line);
        }
        return Integer.parseInt(tokenizer.nextToken());
    }

    public static void main(String[] args) throws Exception {
        InputStream stream = System.in;
        if (args.length > 0) {
            stream = new FileInputStream(args[0]);
        }
        reader = new BufferedReader(new InputStreamReader(stream));
        int tests = nextInt();
        for (int tc = 1; tc <= tests; tc++) {
            int n = nextInt();
            int m = nextInt();
            int[][] a = new int[n][n];
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    a[r][c] = nextInt();
                }
            }
            int answer = solve(a, m);
            System.out.println("#" + tc + " " + answer);
        }
    }

    static int solve(int[][] a, int parts) {
        int n = a.length;
        int width = n / parts;
        int[][] current = new int[n][width];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < width; c++) {
                current[r][c] = a[r][(parts - 1) * width + c];
            }
        }
        for (int block = parts - 2; block >= 0; block--) {
            current = foldLeft(current, a, block, width);
        }
        int[][] result = current;
        int best = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < width; c++) {
                best = Math.max(best, result[r][c]);
            }
        }
        return best;
    }

    static int[][] foldLeft(int[][] current, int[][] a, int block, int width) {
        int[][] next = new int[a.length][width];
        for (int r = 0; r < a.length; r++) {
            for (int c = 0; c < width; c++) {
                int mirrored = width - 1 - c;
                int left = a[r][block * width + c];
                int right = current[r][mirrored];
                next[r][c] = left + right;
            }
        }
        return next;
    }
}
