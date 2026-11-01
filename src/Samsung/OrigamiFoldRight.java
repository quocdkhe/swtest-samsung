package Samsung;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class OrigamiFoldRight {

    static int solve(int[][] matrix, int parts) {
    int rows = matrix.length;
    int width = rows / parts;
    int[][] current = new int[rows][width];
    // Lay khoi dau tien ben trai
    for (int r = 0; r < rows; r++) {
        for (int c = 0; c < width; c++) {
            current[r][c] = matrix[r][c];
        }
    }
    // Fold right: lan luot gap cac khoi ben phai
    for (int block = 1; block < parts; block++) {
        current = foldRight(matrix, current, block, width);
    }
    int best = Integer.MIN_VALUE;
    for (int r = 0; r < rows; r++) {
        for (int c = 0; c < width; c++) {
            best = Math.max(best, current[r][c]);
        }
    }
    return best;
}

static int[][] foldRight(
        int[][] matrix, int[][] current, int block, int width) {
    int[][] next = new int[matrix.length][width];
    for (int r = 0; r < matrix.length; r++) {
        for (int c = 0; c < width; c++) {
            int mirrored = width - 1 - c;
            int folded = current[r][mirrored];
            int incoming = matrix[r][block * width + c];
            next[r][c] = folded + incoming;
        }
    }
    return next;
}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // NxN matrix
            int M = Integer.parseInt(st.nextToken()); // parts
            int[][] matrix = new int[N][N];

            // enter the matrix
            for(int i = 0; i < N; i++){
                StringTokenizer st1 = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    matrix[i][j] = Integer.parseInt(st1.nextToken());
                }
            }

            bw.write(solve(matrix, M));
        }

        bw.flush();
    }
}
