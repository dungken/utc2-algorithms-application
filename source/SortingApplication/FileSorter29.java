import javax.swing.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class FileSorter29 {

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn thư mục để sắp xếp tệp");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File directory = fileChooser.getSelectedFile();

            File[] files = directory.listFiles();
            if (files != null && files.length > 0) {
                String[] options = {"Sắp xếp theo kích thước tăng dần", "Sắp xếp theo kích thước giảm dần",
                        "Sắp xếp theo ngày sửa đổi tăng dần", "Sắp xếp theo ngày sửa đổi giảm dần"};

                int choice = JOptionPane.showOptionDialog(null, "Chọn cách sắp xếp:", "Sắp xếp tệp",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);

                switch (choice) {
                    case 0:
                        Arrays.sort(files, Comparator.comparingLong(File::length));
                        break;
                    case 1:
                        Arrays.sort(files, Comparator.comparingLong(File::length).reversed());
                        break;
                    case 2:
                        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
                        break;
                    case 3:
                        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
                        break;
                    default:
                        System.out.println("Không có tùy chọn nào được chọn.");
                        return;
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                System.out.printf("%-40s %-20s %-20s%n", "Tên tệp", "Kích thước (bytes)", "Ngày sửa đổi");
                System.out.println("---------------------------------------------------------------------------");
                for (File file : files) {
                    String formattedDate = dateFormat.format(new Date(file.lastModified()));
                    System.out.printf("%-40s %-20d %-20s%n",
                            file.getName(), file.length(), formattedDate);
                }
            } else {
                System.out.println("Không có tệp nào trong thư mục.");
            }
        } else {
            System.out.println("Không có thư mục nào được chọn.");
        }
    }
}
