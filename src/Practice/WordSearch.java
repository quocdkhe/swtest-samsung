package Practice;

public class WordSearch {

    int[] dr = new int[]{-1, 0, 0, 1};
    int[] dc = new int[]{0, -1, 1, 0};
    boolean[][] visited;

    public boolean dfs(int r, int c, char[][] board, int index, String target) {
        if (index == target.length()) {
            return true;
        }
        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if (nr < 0 || nr >= board.length || nc < 0 || nc >= board[0].length) {
                continue;
            }
            if (visited[nr][nc]) {
                continue;
            }
            if (board[nr][nc] != target.charAt(index)) {
                continue;
            }
            System.out.println("Current:" + board[nr][nc] + " Index:" + index);
            visited[nr][nc] = true;
            if (dfs(nr, nc, board, index + 1, target)) {
                visited[nr][nc] = false;
                return true;
            }
            visited[nr][nc] = false;
        }
        return false;
    }

    public boolean exist(char[][] board, String word) {
        int R = board.length;
        int C = board[0].length;
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                visited[i][j] = true;
                if (board[i][j] == word.charAt(0) && dfs(i, j, board, 1, word)) {
                    return true;
                }
                visited[i][j] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordSearch app = new WordSearch();
        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCB";
        System.out.println(app.exist(board, word));
    }
}
