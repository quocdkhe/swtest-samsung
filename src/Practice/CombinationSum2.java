package Practice;

import java.util.*;

public class CombinationSum2 {
	
	public List<List<Integer>> result;
	
	
	public void solve(int[] candidates, int index, int target,Stack<Integer> ans) {
		if(target == 0) {
			result.add(new ArrayList<>(ans));
			return;
		}
		
		for(int i = index; i < candidates.length; i++) {
			if(candidates[i] > target) {
				break;
			}
			if(i > index && candidates[i] == candidates[i-1]) {
				continue;
			}
			ans.push(candidates[i]);
			solve(candidates, i + 1, target - candidates[i], ans);
			ans.pop();
		}
	}
	
	
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		result = new ArrayList<>();
		Arrays.sort(candidates);
		solve(candidates, 0, target, new Stack<Integer>());
        return result;
    }
	
	public static void main(String[] args) {
		CombinationSum2 app = new CombinationSum2();
		System.out.println(app.combinationSum2(new int[] {10,1,2,7,6,1,5}, 8));
	}
}
