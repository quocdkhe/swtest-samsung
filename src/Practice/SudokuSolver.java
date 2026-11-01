package Practice;

import java.util.*;

/**
 * Leetcode 37 - Sudoku solver
 * Formula to calculate box index (knowing row and col): (r/3)*3 + c/3
 */
public class SudokuSolver {
	
	public List<int[]> blank; // cell to fill
	public boolean[][] boxes; // number used in 3x3 box
	public boolean[][] rows; // number used in a row
	public boolean[][] cols; // number used in a col
	
	
	public void setup(char[][] board) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(board[i][j] == '.') {
					blank.add(new int[] {i,j});
				}else {
					int value = board[i][j] - '0';
					rows[i][value] = true;
					cols[j][value] = true;
					boxes[(i/3)*3 + j/3][value] = true;
				}
			}
		}
	}
	
	public boolean solve(int index, char[][] board) {
		if(index == blank.size()) {
			return true;
		}
		int[] current = blank.get(index);
		int r = current[0];
		int c = current[1];
		for(int d = 1; d <= 9; d++) {
			if(rows[r][d] || cols[c][d] || boxes[(r/3)*3 + c/3][d]) {
				continue;
			}
			// put d
			board[r][c] = (char)(d + '0');
			rows[r][d] = cols[c][d] = boxes[(r/3)*3 + c/3][d] = true;
			
			// recursive
			if(solve(index + 1, board)) {
				return true;
			}
			//backtrack
			board[r][c] = '.';
			rows[r][d] = cols[c][d] = boxes[(r/3)*3 + c/3][d] = false;
		}
		return false;
		
	}
	
    public void solveSudoku(char[][] board) {
        rows = new boolean[9][10];
        cols = new boolean[9][10];
        boxes = new boolean[9][10];
        blank = new ArrayList<>();
        setup(board);
        solve(0, board);
    }


	public static void main(String[] args) {
		SudokuSolver app = new SudokuSolver();
		char[][] board = {
		    {'5','3','.','.','7','.','.','.','.'},
		    {'6','.','.','1','9','5','.','.','.'},
		    {'.','9','8','.','.','.','.','6','.'},
		    {'8','.','.','.','6','.','.','.','3'},
		    {'4','.','.','8','.','3','.','.','1'},
		    {'7','.','.','.','2','.','.','.','6'},
		    {'.','6','.','.','.','.','2','8','.'},
		    {'.','.','.','4','1','9','.','.','5'},
		    {'.','.','.','.','8','.','.','7','9'}
		};
		app.solveSudoku(board);
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

}
