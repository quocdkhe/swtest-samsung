package Samsung;

import java.io.*;

/**
 * Cho một thành phố cỡ NxM, trong đó mỗi ô là một thị trấn. Mỗi thị trấn sẽ có
 * quantity và price. Cho D ngày, trong D ngày đó, tìm cách để profit đạt được
 * là lớn nhất.
 *
 * Có 3 hành động, nếu thực hiện 1 hành động thì sẽ mất 1 ngày.
 *
 * 3 hành động gồm:
 *
 * Di chuyển lên xuống trái phải qua các ô (thị trấn).
 *
 * Sản xuất: cộng thêm vào quantity of goods của merchants, và giảm số quantity
 * của thành phố đó đi 2 lần.
 *
 * Bán: nhân số quantity của nhóm merchant với price của trị trấn đó, đồng thời
 * giảm price của thị trấn đi 1 đơn vị
 *
 *
 *
 */
public class Merchant {

    static class Town {

        public int quantity;
        public int price;

        Town() {
            this.quantity = 0;
            this.price = 0;
        }
    }

    static int R, C, startR, startC, D, I, best;
    static Town[][] city;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};

    static void dfs(int r, int c, int day, int goods, int profit) {
        if (day == D) {
            best = Math.max(best, profit);
            return;
        }

        // di chuyen
        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                continue;
            }
            dfs(nr, nc, day + 1, goods, profit);
        }

        // ban
        int oldPrice = city[r][c].price;
        if (city[r][c].price < 0) {
            return;
        }
        city[r][c].price -= 1;
        dfs(r, c, day + 1, 0, goods * oldPrice);
        city[r][c].price = oldPrice;

        // san xuat
        if (city[r][c].quantity > 0) {
            int oldQuantity = city[r][c].quantity;
            city[r][c].quantity /= 2;
            dfs(r, c, day + 1, goods + oldQuantity, profit);
            city[r][c].quantity = oldQuantity;
        }
    }

    static int ni(StreamTokenizer z) throws Exception {
        z.nextToken();
        return (int) z.nval;
    }

    public static void main(String[] args) throws Exception {
        StreamTokenizer stz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int T = ni(stz);
        for (int tc = 1; tc <= T; tc++) {
            R = ni(stz);
            C = ni(stz);
            startR = ni(stz);
            startC = ni(stz);
            D = ni(stz);
            I = ni(stz);

            city = new Town[R][C];
            best = 0;

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    Town town = new Town();
                    town.quantity = ni(stz);
                    city[r][c] = town;
                }
            }

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    Town town = city[r][c];
                    town.price = ni(stz);
                }
            }

            dfs(startR - 1, startC - 1, 0, I, 0);
            System.out.printf("#%d %d\n", tc, best);
        }

    }
}
