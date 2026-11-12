package Practice;
/**
 * BÀI TOÁN: ĐƯỜNG ĐI TRONG MÊ CUNG (MAZE)
 *
 * Cho một mê cung có kích thước N x M, được biểu diễn bằng một ma trận
 * gồm các số nguyên 0 và 1.
 *
 * Ý nghĩa:
 * - 1: Ô có thể di chuyển qua.
 * - 0: Ô không thể di chuyển qua (tường).
 *
 * Người chơi bắt đầu tại vị trí (1, 1) và cần di chuyển đến vị trí (N, M).
 *
 * Tại mỗi bước, người chơi chỉ được di chuyển sang một ô liền kề
 * theo 4 hướng: trên, dưới, trái, phải.
 * Không được di chuyển vào những ô có giá trị 0.
 *
 * YÊU CẦU:
 * Tìm số lượng ô ít nhất phải đi qua để di chuyển từ (1, 1)
 * đến (N, M).
 *
 * Lưu ý:
 * - Số ô được tính bao gồm cả vị trí bắt đầu và vị trí đích.
 * - Đề bài đảm bảo luôn tồn tại ít nhất một đường đi đến đích.
 *
 * VÍ DỤ:
 *
 * Mê cung ban đầu (N = 4, M = 6):
 *
 * 1 0 1 1 1 1
 * 1 0 1 0 1 0
 * 1 0 1 0 1 1
 * 1 1 1 0 1 1
 *
 * Trong ví dụ trên, có thể di chuyển từ (1, 1) đến (4, 6)
 * bằng cách đi qua 15 ô.
 *
 * INPUT:
 * - Dòng đầu tiên gồm hai số nguyên N, M (2 <= N, M <= 100).
 * - N dòng tiếp theo, mỗi dòng gồm M số nguyên (0 hoặc 1)
 *   mô tả mê cung.
 * - Các số nguyên trên cùng một dòng được viết liền nhau.
 *
 * OUTPUT:
 * - Một số nguyên duy nhất: số lượng ô ít nhất phải đi qua
 *   để di chuyển từ vị trí (1, 1) đến vị trí (N, M).
 */
public class BOJ2178 {

}
