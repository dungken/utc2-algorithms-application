import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public class SimplePolygon extends JPanel {
    private Point[] points;

    public SimplePolygon(Point[] points) {
        this.points = points;
        Arrays.sort(this.points, new PolarAngleComparator());
        setPreferredSize(new Dimension(600, 600));
    }

    private class PolarAngleComparator implements Comparator<Point> {
        private Point pivot;

        public PolarAngleComparator() {
            pivot = Arrays.stream(points).min(Comparator.comparingInt(p -> p.y)).orElse(null);
        }

        @Override
        public int compare(Point p1, Point p2) {
            double angle1 = Math.atan2(p1.y - pivot.y, p1.x - pivot.x);
            double angle2 = Math.atan2(p2.y - pivot.y, p2.x - pivot.x);
            return Double.compare(angle1, angle2);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (points.length > 0) {
            g2d.setColor(Color.BLUE);
            Polygon polygon = new Polygon();

            for (Point point : points) {
                polygon.addPoint(point.x, point.y);
            }
            g2d.drawPolygon(polygon);

            g2d.setColor(Color.RED);
            for (Point point : points) {
                g2d.fillOval(point.x - 5, point.y - 5, 10, 10); // Vẽ điểm
            }
        }
    }

    //Test
    public static void main(String[] args) {
        Point[] points = {
                new Point(100, 300),
                new Point(200, 250),
                new Point(150, 200),
                new Point(200, 300),
                new Point(300, 150),
                new Point(300, 400)
        };

        JFrame frame = new JFrame("Simple Polygon");
        SimplePolygon panel = new SimplePolygon(points);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
