package Samsung;

import java.io.*;
import java.util.*;

public class TurningOnTheLight {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/TC/TurningOnTheLightTC.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	int n = Integer.parseInt(br.readLine());
        	int[][] a = new int[n][n];
        	int[] row = new int[n];
        	int[] col = new int[n];
        	for(int i = 0; i < n; i++) {
        		String s = br.readLine();
        		for(int j = 0; j < n; j++) {
        			a[i][j] = s.charAt(j) == 'O' ? 1 : 0;
        			row[i] ^= a[i][j];
        			col[j] ^= a[i][j];
        		}
        	}
        	
        	 int ans = 0;
             List<int[]> positions = new ArrayList<>();

             for (int i = 0; i < n; i++) {
                 for (int j = 0; j < n; j++) {
                     int x = a[i][j] ^ row[i] ^ col[j];

                     if (x == 1) {
                         ans++;
                         positions.add(new int[]{i + 1, j + 1});
                     }
                 }
             }
             
             System.out.print("#" + tc);
             System.out.print(" " + ans);
             for(int[] pos: positions) {
            	 System.out.print(" " + pos[0] + " " + pos[1]);
             }
             System.out.println();

        }
	}
}
