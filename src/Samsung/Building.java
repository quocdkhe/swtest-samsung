package Samsung;

import java.io.*;
import java.util.*;

/**
10
5
3 3 3 5 5
4 4 4 4 5
6
1 6 6 6 6 6
2 2 5 5 5 6
2
1 2
2 2
8
1 8 8 8 8 8 8 8
4 4 4 4 6 6 7 8
11
1 3 3 5 5 11 11 11 11 11 11
4 4 4 4 6 6 8 8 10 10 11
10
3 3 3 9 9 9 9 9 9 10
4 4 4 4 5 7 7 8 10 10
10
2 2 5 5 5 8 8 8 10 10
3 3 3 4 6 6 9 9 9 10
11
1 3 3 4 10 10 10 10 10 10 11
2 2 5 5 5 7 7 11 11 11 11
10
1 3 3 6 6 6 9 9 9 10
4 4 4 4 8 8 8 8 10 10 
11
3 3 3 4 7 7 7 10 10 10 11
2 2 5 5 5 8 8 8 11 11 11

 */

public class Building {
	
	
	static int fixedLeft;
	static int fixedRight;
	
	static List<Integer> left;
	static List<Integer> right;
	static boolean[] used;
	static int N; 
	static int answer;
	
	public static boolean isValid(Stack<Integer> nums) {
		List<Integer> added = new ArrayList<>(nums);
		
		List<Integer> numsLeft = new ArrayList<>();
		List<Integer> numsRight = new ArrayList<>();
		added.add(0, fixedLeft);
		added.add(fixedRight);
		
		int cur = 0;
		
		for(int x : added) {
			if(x > cur) {
				for(int i = 0; i < x - cur;i++) {
					numsLeft.add(x);
				}
				cur = x;
			}
		}
		
		cur = 0;
		
		for(int i = N - 1; i >= 0; i--) {
			if(added.get(i) > cur) {
				for(int j = 0; j < added.get(i) - cur; j++) {
					numsRight.add(added.get(i));
				}
				cur = added.get(i);
			}
		}
		
		
		// check
		for(int i = 0; i < added.size(); i++) {
			if(left.get(i) != numsLeft.get(i) || right.get(i) != numsRight.get(i)) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void generate(Stack<Integer> nums) {
		if(nums.size() == N - 2 && isValid(nums)) {
//			System.out.println(nums);
			answer++;
			return;
		}
		for(int i = 1; i <= N; i++) {
			if(used[i]) {
				continue;
			}
			used[i] = true;
			nums.push(i);
			generate(nums);
			used[i] = false;
			nums.pop();
		}
	}

	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	N = Integer.parseInt(br.readLine());
        	st = new StringTokenizer(br.readLine());
        	left = new ArrayList<>();
        	right = new ArrayList<>();
        	answer = 0;
        	used = new boolean[N+1];
        	
        	
        	for(int i = 0; i < N; i++) {
        		left.add(Integer.parseInt(st.nextToken()));
        	}
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < N; i++) {
        		right.add(Integer.parseInt(st.nextToken()));
        	}
        	
        	fixedLeft = left.get(0);
        	fixedRight = right.get(0);
        	used[fixedLeft] = used[fixedRight] = true;
        	
        	generate(new Stack<Integer>());
        	
        	System.out.printf("#%d %d\n", tc, answer); 
//        	Stack s = new Stack<Integer>();
//        	s.add(3);
//        	s.add(5);
//        	s.add(1);
//        	s.add(2);
//        	s.add(4);
//        	
//        	System.out.println(isValid(s));
        }
	}

}


