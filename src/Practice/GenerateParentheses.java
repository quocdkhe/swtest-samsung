package Practice;

import java.io.*;
import java.util.*;

/**
 * Leetcode 22
 */
public class GenerateParentheses {
	
	
	public List<String> result;
	
	public void backtrack(int open, int close, String current, int n) {
		if(current.length() == n * 2) {
			result.add(current);
			return;
		}
		if(open < n) {
			backtrack(open + 1, close, current + "(", n);
		}
		if(close < open) {
			backtrack(open, close + 1, current + ")", n);
		}
	}
	
	public List<String> generateParenthesis(int n) {
		return null;
    }

	public static void main(String[] args) {
		GenerateParentheses app = new GenerateParentheses();
		app.result = new ArrayList<>();
		app.backtrack(0, 0, "", 3);
		System.out.println(app.result);
	
		
		
	}

}
