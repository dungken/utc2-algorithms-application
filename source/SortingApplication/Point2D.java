import java.util.Arrays;
import java.util.Comparator;

public class Point2D {
    private final double x;
    private final double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static Comparator<Point2D> compareByX() {
        return Comparator.comparingDouble(Point2D::getX);
    }

    public static Comparator<Point2D> compareByY() {
        return Comparator.comparingDouble(Point2D::getY);
    }

    public static Comparator<Point2D> compareByDistance() {
        return Comparator.comparingDouble(Point2D::distanceFromOrigin);
    }

    // Test
    public static void main(String[] args) {
        Point2D[] points = {
                new Point2D(3, 4),
                new Point2D(1, 2),
                new Point2D(5, 1),
                new Point2D(0, 0)
        };

        // Sắp xếp theo tọa độ x
        Arrays.sort(points, Point2D.compareByX());
        System.out.println("Sắp xếp theo tọa độ x: " + Arrays.toString(points));

        // Sắp xếp theo tọa độ y
        Arrays.sort(points, Point2D.compareByY());
        System.out.println("Sắp xếp theo tọa độ y: " + Arrays.toString(points));

        // Sắp xếp theo khoảng cách từ gốc tọa độ
        Arrays.sort(points, Point2D.compareByDistance());
        System.out.println("Sắp xếp theo khoảng cách từ gốc: " + Arrays.toString(points));
    }
}
