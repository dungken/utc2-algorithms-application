import javax.swing.*;
import java.io.File;
import java.util.Arrays;

public class FileSorter {

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn thư mục để sắp xếp tệp");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File directory = fileChooser.getSelectedFile();

            File[] files = directory.listFiles();
            if (files != null && files.length > 0) {
                Arrays.sort(files);

                System.out.println("Danh sách tệp đã sắp xếp:");
                for (File file : files) {
                    System.out.println(file.getName());
                }
            } else {
                System.out.println("Không có tệp nào trong thư mục.");
            }
        } else {
            System.out.println("Không có thư mục nào được chọn.");
        }
    }
}
