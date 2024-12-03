import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

public class RandomConnections extends JPanel {
    private int N;
    private double p;
    private static final double DOT_RADIUS = 0.05; // Size of the dots

    public RandomConnections(int N, double p) {
        this.N = N;
        this.p = p;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        double radius = Math.min(width, height) / 2.5;

        // Draw the dots
        for (int i = 0; i < N; i++) {
            double angle = 2 * Math.PI * i / N;
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            g2d.fill(new Ellipse2D.Double(x - DOT_RADIUS * width, y - DOT_RADIUS * height, DOT_RADIUS * width * 2, DOT_RADIUS * height * 2));
        }

        // Draw lines with probability p
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (rand.nextDouble() < p) {
                    double angle1 = 2 * Math.PI * i / N;
                    double angle2 = 2 * Math.PI * j / N;
                    int x1 = (int) (centerX + radius * Math.cos(angle1));
                    int y1 = (int) (centerY + radius * Math.sin(angle1));
                    int x2 = (int) (centerX + radius * Math.cos(angle2));
                    int y2 = (int) (centerY + radius * Math.sin(angle2));
                    g2d.draw(new Line2D.Double(x1, y1, x2, y2));
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java RandomConnections <number_of_points> <probability>");
            return;
        }

        int N = Integer.parseInt(args[0]);
        double p = Double.parseDouble(args[1]);

        if (N <= 0 || p < 0 || p > 1) {
            System.out.println("Error: N must be positive and p must be between 0 and 1.");
            return;
        }

        JFrame frame = new JFrame("Random Connections");
        RandomConnections panel = new RandomConnections(N, p);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(panel);
        frame.setVisible(true);
    }
}
