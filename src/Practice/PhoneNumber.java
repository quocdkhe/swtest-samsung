package Practice;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Leetcode 17
 */
public class PhoneNumber {
	
	public Map<Integer, char[]> map;
    public List<String> combinations;
    public char[] digitsArr;

    public void solve(List<Character> current) {
        if (current.size() == digitsArr.length) {
            String result = current.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining());
            combinations.add(result);
            return;
        }
        int index = current.size();
        int currentNum = Integer.parseInt(Character.toString((digitsArr[index])));
        for (int i = 0; i < map.get(currentNum).length; i++) {
            char currentChar = map.get(currentNum)[i];
            current.add(currentChar);
            solve(current);
            // backtrack
            current.remove(current.size() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        map = new HashMap<>();
        combinations = new ArrayList<>();
        map.put(2, new char[]{'a', 'b', 'c'});
        map.put(3, new char[]{'d', 'e', 'f'});
        map.put(4, new char[]{'g', 'h', 'i'});
        map.put(5, new char[]{'j', 'k', 'l'});
        map.put(6, new char[]{'m', 'n', 'o'});
        map.put(7, new char[]{'p', 'q', 'r', 's'});
        map.put(8, new char[]{'t', 'u', 'v'});
        map.put(9, new char[]{'w', 'x', 'y', 'z'});

        digitsArr = digits.toCharArray();
        solve(new ArrayList<>());
        return combinations;
    }

	public static void main(String[] args) {
		PhoneNumber app = new PhoneNumber();
		System.out.println(app.letterCombinations("23"));

	}

}
