package Practice;

import java.util.*;

/**
 * Leetcode problem 39
 */
public class CombinationSum {
	
//	public int currentSum;
	public List<List<Integer>> result;
	
	public void solve(int[] candidates, int target, int start, Stack<Integer> current) {
		if(target == 0) {
			result.add(new ArrayList<>(current));
			return;
		}
		for(int i = start; i < candidates.length; i++) {
			if(candidates[i] > target) {
				continue;
			}
			current.push(candidates[i]);
			solve(candidates, target - candidates[i], i, current);
			current.pop();
		}
	}
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
//		currentSum = 0;
		result = new ArrayList<>();
		solve(candidates, target, 0, new Stack<Integer>());
        return result;
    }

	public static void main(String[] args) {
		CombinationSum app = new CombinationSum();
		
		System.out.println(app.combinationSum(new int[] {2,3,5}, 8));
		System.out.println(app.combinationSum(new int[] {10,1,2,7,6,1,5}, 10));
		

	}

}
