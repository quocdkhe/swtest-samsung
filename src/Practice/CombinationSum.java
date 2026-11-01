package Practice;

import java.util.*;

/**
 * Leetcode problem 39
 */
public class CombinationSum {
	
//	public int currentSum;
	public List<List<Integer>> result;
	
//	public boolean isDuplicate(List<Integer> current) {
//		for(List<Integer> nums : result) {
//			if(current.size() != nums.size()) {
//				continue;
//			}
//			boolean matchFound = true;
//			for(int i = 0; i < nums.size(); i++) {
//				if(nums.get(i) != current.get(i)) {
//					matchFound = false;
//					break;
//				}
//			}
//			if(matchFound) {
//				return true;
//			}
//		}
//		return false;
//	}
//		
//	public void solve(int[] candidates, int target, List<Integer> current) {
//		if(currentSum == target) {
//			Collections.sort(current);
//			if(!isDuplicate(current)) {
//				result.add(new ArrayList<>(current));				
//			}
//			return;
//		}
//		
//		for(int candidate: candidates) {
//			if(currentSum + candidate > target) {
//				continue;
//			}
//			currentSum += candidate;
//			current.add(candidate);
//			solve(candidates, target, current);
//			
//			currentSum -= candidate;
//			current.remove(Integer.valueOf(candidate));
//		}
//	}
	
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

	}

}
