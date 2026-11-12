package Practice;
/**
 * BÀI TOÁN: CÀ CHUA (TOMATOES)
 *
 * Trong một trang trại, có một nhà kho lớn dùng để bảo quản cà chua.
 * Các quả cà chua được đặt vào từng ô của một chiếc hộp dạng lưới
 * có kích thước N x M.
 *
 * Trong kho có cả cà chua đã chín và cà chua còn xanh.
 * Sau mỗi ngày, những quả cà chua xanh nằm liền kề với cà chua chín
 * sẽ bị ảnh hưởng và trở thành cà chua chín.
 *
 * Một quả cà chua được xem là liền kề với quả khác nếu chúng nằm ở
 * một trong 4 hướng: trái, phải, trên hoặc dưới.
 * Các quả cà chua nằm chéo nhau không ảnh hưởng đến nhau.
 *
 * Quá trình lây lan diễn ra đồng thời mỗi ngày:
 * - Cà chua xanh tiếp xúc với cà chua chín sẽ chín sau 1 ngày.
 * - Cà chua đã chín không thay đổi trạng thái.
 * - Các ô không có cà chua không ảnh hưởng đến quá trình lây lan.
 *
 * Giả sử không có quả cà chua nào tự chín nếu không chịu ảnh hưởng
 * từ những quả cà chua chín khác.
 *
 * YÊU CẦU:
 * Cho biết trạng thái ban đầu của các quả cà chua trong kho,
 * hãy tính số ngày ít nhất để tất cả cà chua đều chín.
 *
 * LƯU Ý:
 * - Nếu ngay từ đầu tất cả cà chua đều đã chín, kết quả là 0.
 * - Nếu tồn tại cà chua xanh không thể chín, kết quả là -1.
 *
 * INPUT:
 * - Dòng đầu tiên gồm hai số nguyên M, N lần lượt là số cột
 *   và số hàng của nhà kho (2 <= M, N <= 1000).
 * - N dòng tiếp theo mô tả trạng thái các ô trong nhà kho.
 * - Mỗi dòng gồm M số nguyên:
 *   +  1: Cà chua đã chín.
 *   +  0: Cà chua còn xanh.
 *   + -1: Ô không có cà chua.
 * - Đề bài đảm bảo có ít nhất một quả cà chua trong kho.
 *
 * OUTPUT:
 * - In ra số ngày ít nhất để tất cả cà chua đều chín.
 * - Nếu tất cả cà chua đã chín ngay từ đầu, in ra 0.
 * - Nếu không thể làm cho tất cả cà chua chín, in ra -1.
 */
public class BOJ7576 {

}
