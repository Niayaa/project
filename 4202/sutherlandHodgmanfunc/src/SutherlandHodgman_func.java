import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SutherlandHodgman_func extends JFrame {
    SutherlandHodgmanPanels panel;

    public static void main(String[] args) {
        JFrame f = new SutherlandHodgman_func();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public SutherlandHodgman_func() {
        Container content = getContentPane();
        content.setLayout(new BorderLayout());
        panel = new SutherlandHodgmanPanels();
        content.add(panel, BorderLayout.CENTER);
        setTitle("SutherlandHodgman");
        pack();
        setLocationRelativeTo(null);
    }
}

class SutherlandHodgmanPanels extends JPanel{
    List<double[]> subject, clipper, result;
    public SutherlandHodgmanPanels() {
        long startTime = System.nanoTime();
        setPreferredSize(new Dimension(500, 500));

        // these subject and clip points are assumed to be valid
        double[][] subjPoints = {{50, 150}, {200, 50}, {350, 150}, {350, 300},
                {250, 300}, {200, 250}, {150, 350}, {100, 250}, {100, 200}};
       // double[][] simplePolygon = {{60,60},{150,100},{330,60},{240,210},{120,300}};

        double[][] clipPoints = {{100, 100}, {300, 100}, {300, 300}, {100, 300}};

        subject = new ArrayList<>(Arrays.asList(subjPoints));
        result  = new ArrayList<>(subject);
        clipper = new ArrayList<>(Arrays.asList(clipPoints));
        clipPolygon();
        long endTime   = System.nanoTime();
        long totalTime = (endTime - startTime);
        double elapsedTimeInSecond = (double) totalTime/ 1_000_000_000;
      //  long convert = TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
        System.out.println("func run!!!!!:"+totalTime);
    }

    private void clipPolygon() {
        int len = clipper.size();
        for (int i = 0; i < len; i++) {

            int len2 = result.size();
            List<double[]> input = result;
            result = new ArrayList<>(len2);

            double[] A = clipper.get((i + len - 1) % len);
            double[] B = clipper.get(i);

            for (int j = 0; j < len2; j++) {

                double[] P = input.get((j + len2 - 1) % len2);
                double[] Q = input.get(j);

                if (isInside(A, B, Q)) {
                    if (!isInside(A, B, P))
                        result.add(intersection(A, B, P, Q));
                    result.add(Q);
                } else if (isInside(A, B, P))
                    result.add(intersection(A, B, P, Q));
            }
        }
    }

    private boolean isInside(double[] a, double[] b, double[] p) {
        double cross =  ((a[0] - p[0]) * (b[1] - p[1]) - (a[1] - p[1]) * (b[0] - p[0]));
        if (cross > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    private double[] intersection(double[] a, double[] b, double[] p, double[] q) {
        double m1 = (a[1]-b[1])/(a[0]-b[0]);
        double m2 = (q[1]-p[1])/(q[0]-p[0]);
        double b1 = a[1] - (m1*a[0]);
        double b2 = q[1] - (m2*q[0]);
        double x = (b2-b1)/(m1-m2);
        double y = m1 * x + b1;
        return new double[]{x, y};
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(80, 60);
        g2.setStroke(new BasicStroke(3));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        drawPolygon(g2, subject, Color.blue);
        drawPolygon(g2, clipper, Color.red);
        drawPolygon(g2, result, Color.green);
    }

    private void drawPolygon(Graphics2D g2, List<double[]> points, Color color) {
        g2.setColor(color);
        int len = points.size();
        Line2D line = new Line2D.Double();
        for (int i = 0; i < len; i++) {
            double[] p1 = points.get(i);
            double[] p2 = points.get((i + 1) % len);
            line.setLine(p1[0], p1[1], p2[0], p2[1]);
            g2.draw(line);
        }
    }
}
