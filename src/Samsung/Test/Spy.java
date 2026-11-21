package Samsung.Test;

import java.io.*;
import java.util.*;

/*

2
7 6
######
####O#
#O####
#####O
O#OO#O
######
OO####
7 9
#########
O###O####
#O#######
#O###O###
###O###O#
#O#######
#####O###

 */
/**
 * Bản đồ có R hàng, C cột. Hàng đầu tiên là phía quân địch, hàng cuối cùng là
 * phía nhà mình. Các thiết bị được ký hiệu là 'O'.
 *
 * Mỗi thiết bị có vùng ảnh hưởng gồm ô đặt thiết bị và các ô kề trên, dưới,
 * trái, phải (trong phạm vi bản đồ). Có thể đi từ một thiết bị sang thiết bị
 * khác nếu vùng ảnh hưởng của chúng kết nối được với nhau. Theo điều kiện đề
 * bài, hai thiết bị kết nối khi khoảng cách Manhattan giữa chúng không quá 3.
 * Chỉ có thể di chuyển qua các thiết bị; cần tìm đường đi từ phía quân địch
 * đến phía nhà mình.
 *
 * Vì vùng ảnh hưởng của thiết bị ở hàng 1 có thể chạm tới hàng đầu tiên, điểm
 * xuất phát được xét ở hai hàng đầu. Tương tự, vùng ảnh hưởng của thiết bị ở
 * hàng cuối hoặc áp cuối có thể chạm tới hàng cuối, nên đích được xét ở hai
 * hàng cuối.
 *
 * Hãy tính số thiết bị ít nhất cần đi qua để sang được phía nhà mình. Nếu
 * không thể đi được thì in ra -1.
 */
public class Spy {

    static int R, C;

    static class Device {

        int row;
        int col;
        int distance;

        public Device(int row, int col) {
            this.row = row;
            this.col = col;
            this.distance = -1;
        }

        public Device(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }

    public static boolean canPass(Device a, Device b) {
        int distance = Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
        return distance <= 3;
    }

    public static int bfs(List<Device> devices) {
        Queue<Device> queue = new LinkedList<>();
        for (Device device : devices) {
            if (device.row == 1 || device.row == 0) {
                queue.offer(device);
                device.distance = 1;
            }
        }
        while (!queue.isEmpty()) {
            Device current = queue.poll();
            int d = current.distance;
            for (Device next : devices) {
                if (!canPass(next, current)) {
                    continue;
                }
                if (next.distance != -1) {
                    continue;
                }
                if (next.row == R - 2 || next.row == R - 1) {
                    return d + 1;
                }
                next.distance = d + 1;
                queue.offer(next);
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            List<Device> devices = new ArrayList<>();
            for (int i = 0; i < R; i++) {
                String line = br.readLine();
                for (int j = 0; j < C; j++) {
                    if (line.charAt(j) == 'O') {
                        devices.add(new Device(i, j));
                    }
                }
            }
            System.out.printf("#%d %d", tc, bfs(devices));
        }
    }
}
