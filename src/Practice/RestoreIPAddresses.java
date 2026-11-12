package Practice;

import java.util.*;
import java.util.stream.Collectors;

public class RestoreIPAddresses {
	
	public List<String> result;
	
	
	public void solve(String s, int dot, Stack<Integer> parts) {
		if(dot == 0 || s.length() > 3) {
			if(s.isEmpty()) return;
			if(s.startsWith("0") && s.length() > 1) {
				return;
			}
			int part = Integer.parseInt(s);
			if(part <= 255) {
				parts.add(part);
				String answer = parts.stream().map(String::valueOf).collect(Collectors.joining("."));
				parts.pop();
				result.add(answer);
			}
			return;
		}
		
		for(int i = 1; i <= Math.min(3, s.length()); i++) {
			String partStr = s.substring(0, i);
			if(partStr.startsWith("0") && partStr.length() > 1) {
				continue;
			}
			int part = Integer.parseInt(partStr);
			if(part > 255) {
				continue;
			}
			parts.push(part);
			solve(s.substring(i), dot - 1, parts);
			parts.pop();
		}
	}
	
	
	
	public List<String> restoreIpAddresses(String s) {
        result = new ArrayList<>();
        solve(s, 3, new Stack<Integer>());
        return result;
    }

	public static void main(String[] args) {
		RestoreIPAddresses app = new RestoreIPAddresses();
		System.out.println(app.restoreIpAddresses("9245587303"));

	}

}
