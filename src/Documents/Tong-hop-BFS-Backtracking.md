# Tổng hợp các dạng bài đã làm: BFS, đồ thị và Backtracking

Tài liệu tổng hợp các mẫu bài thể hiện trong `Practice/`, `Samsung/`, `TC/` và tài liệu ôn tập `Documents/on-tap.html`. Trọng tâm là nhận diện trạng thái, chọn đúng biến thể BFS, sinh hoán vị/tổ hợp; phần đệ quy giữ ở mức nền tảng.

## 1. Bản đồ chọn thuật toán

| Yêu cầu | Mô hình | Ví dụ |
|---|---|---|
| Ít bước nhất, mỗi bước cùng giá | BFS lưới/đồ thị | `BOJ2178`, `ThirdAvenue`, `Unicorn`, `ShortestPath` |
| Tìm các ô/đỉnh tới được, gom vùng | BFS/DFS flood fill | `Pixel`, `BOJ16234`, `BOJ7576` |
| Nhiều nguồn lan đồng thời | Multi-source BFS | `BOJ7576`, `Monsters`, `Greening` |
| Quyền đi phụ thuộc vật phẩm, phá tường, thời gian | BFS trên state mở rộng | `LeavingOffice3DimArr`, `Excalibur`, `BOJ2206`, `BOJ16933` |
| Cạnh có phí 0 hoặc 1 | 0-1 BFS dùng deque | `Labyrinth` |
| Cần in lại đường đi | BFS + mảng cha/hướng | `Monsters`, `ShortestPath`, `SolveTheMaze` |
| Chọn nhóm, thứ tự không quan trọng | Sinh tổ hợp / backtracking | `CombinationSum`, `CombinationSum2`, `BOJ14502` |
| Xếp các phần tử, thứ tự quan trọng | Sinh hoán vị / backtracking | `PhoneNumber`, `Pair`, `ValidPath` |
| Liệt kê cấu hình hợp lệ | DFS/backtracking và cắt nhánh | `GenerateParentheses`, `NQueensII`, `SudokuSolver` |

BFS tối ưu số bước trong đồ thị không trọng số (hoặc cạnh cùng trọng số). Backtracking xét nhiều cách chọn/xếp, đếm hoặc tìm nghiệm. Nếu cần đường ngắn nhất trên đồ thị lớn, ưu tiên BFS khi mô hình phù hợp.

## 2. BFS cơ bản trên lưới

Mỗi ô đi được là một đỉnh `(r,c)`, các ô kề hợp lệ tạo cạnh. Khởi tạo `dist` là `-1`, đặt `dist[start] = 0`, enqueue start. Khi tìm được hàng xóm mới, gán khoảng cách và đánh dấu ngay lúc enqueue. Độ phức tạp lưới 4 hướng là `O(R*C)`.

- Mê cung/đường ngắn nhất: `Practice/BOJ2178.java`, `Practice/ThirdAvenue.java`.
- Nước đi không chuẩn như mã/unicorn: `Samsung/Unicorn.java`.
- Đi trong phạm vi năng lượng: `Practice/OlyaAndEnergyDrinks.java`.
- Duyệt từ đích để kiểm tra đường: `Practice/SolveTheMaze.java`.
- BFS đồ thị danh sách kề: `Practice/NearestOppositeParity.java`, `Practice/EscapeFromMars.java`, `Samsung/Excel.java`.

Phân biệt số bước (`dist[đích]`) với số ô trên đường (`dist[đích]+1`); BOJ 2178 tính cả ô đầu/cuối. Lỗi thường gặp: đánh dấu khi dequeue khiến trạng thái được thêm nhiều lần, quên `dist[start]=0`, kiểm tra biên sau khi truy cập mảng, và không xử lý riêng `start == end`.

## 3. BFS theo cạnh và state lịch sử

Trong đồ thị thường, state là đỉnh `u`. Nếu bước tiếp theo phụ thuộc lịch sử, chỉ lưu `u` là thiếu thông tin. Ví dụ luật cấm phụ thuộc hai đỉnh gần nhất cần state `(prev, cur)`; `Practice/ShortestPath.java` theo dõi cặp đỉnh. Với nước nhảy/teleport, mỗi lựa chọn di chuyển hợp lệ được xem là cạnh; BFS thường dùng được nếu mỗi cạnh có cùng giá.

Câu hỏi nhận diện: “Hai lần đến cùng một đỉnh/ô nhưng lịch sử khác nhau có thể dẫn đến các bước đi tiếp hoặc kết quả khác nhau không?” Nếu có, bổ sung phần lịch sử tối thiểu vào state và mảng `dist/visited`.

## 4. BFS nhiều nguồn

Khi nhiều vị trí lan đồng thời, đưa tất cả nguồn vào queue với `dist=0`, rồi chạy một BFS. Kết quả là thời điểm/khoảng cách ngắn nhất tới nguồn gần nhất.

- Cà chua: `Practice/BOJ7576.java`.
- Quái vật và người chơi: `Practice/Monsters.java`.
- Lan truyền trên lưới: `Samsung/Greening.java`.

Trong Monsters, BFS nhiều nguồn cho quái trước để tính `monsterTime[r][c]`. BFS người chơi chỉ tới ô kế tiếp nếu `playerTime + 1 < monsterTime[nr][nc]`; tới cùng thời điểm vẫn thua. Ô biên đầu tiên tới được là lối thoát an toàn ít bước nhất.

## 5. BFS có state: thêm chiều vào dist

Mỗi state đầy đủ là một đỉnh riêng. Cùng ô nhưng khác quyền đi tiếp phải được xem là các state khác nhau.

### State nhị phân hoặc giới hạn số lần

- Có/không có chìa: `dist[r][c][hasKey]`, `hasKey ∈ {0,1}`; cửa chỉ qua được khi có chìa.
- Phá tối đa `K` tường: `dist[r][c][broken]`, `broken ∈ [0,K]`.
- Nếu còn ngày/đêm hoặc lượt: thêm chiều tương ứng.
- Bài liên quan: `Samsung/LeavingOffice3DimArr.java`, `Practice/BOJ2206.java`, `Practice/BOJ16933.java`.

### Nén state bằng bitmask

Với `k` vật phẩm/điểm cần thu thập, mask có `2^k` giá trị. Nhặt vật `i` cập nhật `mask | (1 << i)`. Dùng `dist[r][c][mask]`; điều kiện đích kiểm tra cả tọa độ lẫn mask. Độ phức tạp thường là `O(R*C*2^k)`.

- Lấy vật phẩm để vượt địa hình: `Samsung/Excalibur.java`.
- Chìa, cửa, giấy tờ: `Practice/BOJ9328.java`.

Các lỗi state thường gặp: không lấy vật phẩm ở ô xuất phát; `visited/dist` thiếu chiều; kiểm tra visited bằng state cũ thay vì state mới; chỉ kiểm tra tọa độ đích mà quên điều kiện mask. BFS không trọng số đánh dấu state khi enqueue.

## 6. 0-1 BFS

Dùng khi mỗi cạnh có trọng số `0` hoặc `1` và cần tổng chi phí nhỏ nhất. Thay queue bằng deque: cạnh phí 0 đưa lên đầu (`addFirst`), cạnh phí 1 đưa xuống cuối (`addLast`). Dùng `dist` và chỉ cập nhật khi tìm được đường tốt hơn; không dùng visited cố định như BFS thường.

`Practice/Labyrinth.java` tối thiểu số lần đi trái. Các hướng còn lại có phí 0 theo đại lượng đang tối ưu; số lần đi phải suy ra từ độ dời ngang và số lần đi trái. 0-1 BFS là lựa chọn giữa BFS thường và Dijkstra, độ phức tạp `O(V+E)`.

## 7. Ghép hai BFS

Nếu đường đi hai chiều và chỉ cần qua một điểm trung gian, BFS từ start và end; với mỗi điểm trung gian `x`, xét `distStart[x]+distEnd[x]`. `Samsung/LeavingOffice.java` so sánh đường đi trực tiếp với đường qua chìa. Đây là cách gọn cho một điểm trung gian; nhiều vật phẩm tương tác thường phù hợp với BFS có state hơn.

## 8. Truy vết đường đi

Muốn in đường đi, lưu `parent` khi phát hiện state mới: `parent[v]=u`. Từ đích lần ngược về start, lưu các state rồi đảo danh sách. Nếu cần chuỗi hướng, lưu hướng từ cha tới con và đảo chuỗi sau khi truy ngược. Với BFS có state, cha phải giữ đủ state, chẳng hạn `(r,c,mask)`, chứ không chỉ tọa độ.

Các ví dụ: `Practice/Monsters.java` lưu cha và hướng; `Practice/ShortestPath.java` truy vết trên đồ thị. Chỉ truy vết khi đích đã tới được.

## 9. Vùng liên thông và mô phỏng bằng BFS

Flood fill dùng queue/visited để gom vùng, đếm diện tích, biên hoặc thuộc tính; không nhất thiết cần lưu dist tới đích.

- Đếm vùng pixel: `Samsung/Pixel.java`.
- Vùng dân cư/liên minh theo ngày: `Practice/BOJ16234.java`.
- Chọn tường rồi mô phỏng virus: `Practice/BOJ14502.java`.
- Mô phỏng lan/lượt: `Samsung/Greening.java`, `Samsung/War.java`.

Trong bài theo thời gian, xác định rõ mỗi lớp BFS ứng với một đơn vị thời gian hay mỗi cạnh; nếu tác nhân có luật an toàn riêng, lưu thời điểm tới từng ô.

## 10. Khung backtracking

```text
search(state):
    nếu state hoàn chỉnh: ghi nhận nghiệm; return
    với mỗi lựa chọn hợp lệ:
        chọn
        search(state mới)
        hoàn tác lựa chọn
```

Khác BFS, `visited` của một nhánh thường phải bỏ khi quay lui vì phần tử có thể xuất hiện trong nhiều nghiệm. Nếu cần liệt kê/đếm/tối ưu thì xét đủ nhánh, trừ khi có cắt nhánh đúng. Backtracking thường phù hợp khi kích thước nhỏ.

## 11. Sinh hoán vị

Hoán vị dùng khi thứ tự quan trọng. Mỗi phần tử thường chỉ được dùng một lần trong một hoán vị; dùng `used[]` theo index:

```text
permute(path):
    nếu path đủ n: lưu path; return
    với i từ 0 đến n-1:
        nếu used[i]: tiếp tục
        used[i] = true; thêm a[i]
        permute(path)
        bỏ a[i]; used[i] = false
```

Ví dụ đường đi đơn/chọn thứ tự: `Samsung/Pair.java`, `Samsung/ValidPath.java`, `Samsung/MinsuTourism.java`. Xếp quân hoặc vị trí xung đột: `Samsung/NQueensProblem.java`, `Practice/NQueensII.java`.

Nếu dữ liệu có giá trị trùng, `used[index]` ngăn dùng lại vị trí nhưng vẫn có thể sinh hoán vị giá trị giống nhau. Muốn nghiệm giá trị duy nhất, sort và bỏ qua phần tử trùng ở cùng độ sâu.

## 12. Sinh tổ hợp và chọn nhóm

Tổ hợp dùng khi thứ tự không quan trọng. Truyền `start` để chỉ chọn index từ vị trí kế tiếp, nhờ đó không sinh lại cùng nhóm theo thứ tự khác:

```text
combine(start, path):
    nếu path đủ k: lưu path; return
    với i từ start đến n-1:
        thêm a[i]
        combine(i + 1, path)
        bỏ phần tử cuối
```

- Chọn ba vị trí đặt tường rồi BFS virus: `Practice/BOJ14502.java`.
- Tổng mục tiêu, mỗi giá trị dùng một lần: `Practice/CombinationSum2.java`.
- Tổng mục tiêu, cho phép dùng lại: `Practice/CombinationSum.java` (tiếp tục từ `i`, không phải `i+1`).
- Chọn vị trí/ô không xung đột: `Samsung/BoardGame.java`, `Samsung/RollABall.java`.

Phân biệt index và giá trị: hai phần tử cùng giá trị ở hai vị trí vẫn là hai lựa chọn nếu đề phân biệt vị trí. Combination Sum II tìm tập giá trị duy nhất nên thường sort và bỏ trùng ở cùng tầng đệ quy.

## 13. Một ít về đệ quy

Đệ quy giải bài toán bằng lời gọi lại trên bài toán nhỏ hơn. Mỗi hàm cần base case, bước tiến triển về phía điều kiện dừng, và cách kết hợp/cập nhật kết quả. Ví dụ sinh ngoặc hợp lệ (`Practice/GenerateParentheses.java`): thêm `(` khi chưa đủ `n`; thêm `)` khi số đóng nhỏ hơn số mở; dừng khi đạt độ dài `2n`.

Nếu nhiều lời gọi tính lại cùng state, cân nhắc memoization; `Samsung/RollABall.java` là ví dụ dạng đệ quy + memo trong tài liệu ôn tập. Nếu độ sâu có thể vượt stack, dùng BFS hoặc stack tường minh.

## 14. Checklist trước khi code

1. Đề tối ưu số bước, tổng chi phí, hay hỏi có đường/bao nhiêu cách?
2. Mỗi cạnh cùng giá không? Trọng số 0/1 gợi ý 0-1 BFS; trọng số bất kỳ không âm gợi ý Dijkstra.
3. State hiện tại đủ chưa? Có cần chìa, mask, số lần phá tường, thời gian, đỉnh trước?
4. Một nguồn hay nhiều nguồn? Có cần mô phỏng theo lớp/thời gian?
5. Đếm cạnh hay ô? Start có thể là đích hoặc có sẵn vật phẩm không?
6. Cần khoảng cách hay phải dựng đường đi? Nếu truy vết, lưu cha của state đầy đủ.
7. Với backtracking: thứ tự có quan trọng không, có dùng lại phần tử không, có giá trị trùng không?
8. Có reset `visited`, `dist`, đáp án và biến static giữa các test không?

## 15. Source tham khảo theo chủ đề

- **BFS lưới/đường đi:** `Practice/BOJ2178.java`, `Practice/ThirdAvenue.java`, `Practice/Labyrinth.java`, `Practice/SolveTheMaze.java`, `Practice/Monsters.java`.
- **BFS đồ thị/state lịch sử:** `Practice/ShortestPath.java`, `Practice/NearestOppositeParity.java`, `Practice/EscapeFromMars.java`.
- **BFS state nhiều chiều/bitmask:** `Samsung/LeavingOffice3DimArr.java`, `Samsung/Excalibur.java`, `Practice/BOJ2206.java`, `Practice/BOJ16933.java`, `Practice/BOJ9328.java`.
- **Lan truyền/flood fill:** `Practice/BOJ7576.java`, `Practice/BOJ16234.java`, `Samsung/Pixel.java`, `Samsung/Greening.java`.
- **Hoán vị/tổ hợp:** `Samsung/Pair.java`, `Samsung/ValidPath.java`, `Samsung/MinsuTourism.java`, `Practice/CombinationSum.java`, `Practice/CombinationSum2.java`, `Practice/BOJ14502.java`.
- **Đệ quy/sinh cấu hình:** `Practice/GenerateParentheses.java`, `Practice/PhoneNumber.java`, `Practice/SudokuSolver.java`, `Practice/NQueensII.java`.
