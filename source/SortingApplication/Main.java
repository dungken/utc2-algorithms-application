import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String[] options = {
                "2.5.21 Multidimensional sort",
                "2.5.22 Stock market trading",
                "2.5.23 Sampling for selection",
                "2.5.24 Stable priority queue",
                "2.5.25 Points in the plane",
                "2.5.26 Simple polygon",
                "2.5.27 Sorting parallel arrays",
                "2.5.28 Sort files by name",
                "2.5.29 Sort files by size and date of last modification"
        };

        // Hiển thị hộp thoại để chọn bài
        String selectedOption = (String) JOptionPane.showInputDialog(null,
                "Chọn bài bạn muốn chạy:",
                "Chọn bài",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        // Kiểm tra lựa chọn của người dùng và gọi phương thức tương ứng
        if (selectedOption != null) {
            switch (selectedOption) {
                case "2.5.21 Multidimensional sort":
                    Vector.main(null);
                    break;
                case "2.5.22 Stock market trading":
                    StockMarket.main(null);
                    break;
                case "2.5.23 Sampling for selection":
                    SamplingSelection.main(null);
                    break;
                case "2.5.24 Stable priority queue":
                    StablePriorityQueue.main(null);
                    break;
                case "2.5.25 Points in the plane":
                    Point2D.main(null);
                    break;
                case "2.5.26 Simple polygon":
                    SimplePolygon.main(null);
                    break;
                case "2.5.27 Sorting parallel arrays":
                    Insertion.main(null);
                    break;
                case "2.5.28 Sort files by name":
                    FileSorter.main(null);
                    break;
                case "2.5.29 Sort files by size and date of last modification":
                    FileSorter29.main(null);
                    break;
                default:
                    System.out.println("Không có bài nào được chọn.");
                    break;
            }
        } else {
            System.out.println("Hành động đã bị hủy.");
        }
    }
}
