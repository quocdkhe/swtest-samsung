package Practice;

public class NQueensII {
	/**
	 * Leetcode 52
	 */
		
	public int count;
	public boolean[] diag1Used;
	public boolean[] diag2Used;
	public boolean[] colUsed;
	
	public void solve(int n, int row){
        
		if(row == n) {
			count++;
			return;
		}
		
		for(int col = 0; col < n; col++) {
			int diag1 = row + col;
			int diag2 = row - col + n - 1;
			
			if(diag1Used[diag1] || diag2Used[diag2] || colUsed[col]) {
				continue;
			}
			
			diag1Used[diag1] = diag2Used[diag2] = colUsed[col] = true;
			
			solve(n, row + 1);
			
			diag1Used[diag1] = diag2Used[diag2] = colUsed[col] = false;
		}
		
    } 
	
	public int totalNQueens(int n) {
        diag1Used = new boolean[2*n - 1];
        diag2Used = new boolean[2*n - 1];
        count = 0;
        colUsed = new boolean[n];
		solve(n, 0);
		return count;
    }
	
	
	public static void main(String[] args) {
		NQueensII app = new NQueensII();
		System.out.println(app.totalNQueens(4));
		
	}
}
