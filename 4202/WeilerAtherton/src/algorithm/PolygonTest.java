package algorithm;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class PolygonTest {
    private Polygon polygon;

    public void before() {
        // Create polygon vertices
        List<Point2D.Float> subjectVertices = new ArrayList<>();
        subjectVertices.add(new Point2D.Float(1.56f, 4.76f));
        subjectVertices.add(new Point2D.Float(6.00f, 4.00f));
        subjectVertices.add(new Point2D.Float(5.80f, 0.90f));
        subjectVertices.add(new Point2D.Float(0.82f, 0.66f));
        subjectVertices.add(new Point2D.Float(0.40f, 4.04f));
        subjectVertices.add(new Point2D.Float(0.80f, 6.08f));
        polygon = new Polygon(subjectVertices);

        // Create clipping polygon vertices
    /*List<Point2D.Float> clippingVertices = new ArrayList<>();
    clippingVertices.add(new Point2D.Float(5.26f, 5.68f));
    clippingVertices.add(new Point2D.Float(2.06f, 3.52f));
    clippingVertices.add(new Point2D.Float(2.60f, -0.72f));
    clippingVertices.add(new Point2D.Float(3.26f, 1.64f));
    clippingVertices.add(new Point2D.Float(3.92f, -1.46f));
    clippingVertices.add(new Point2D.Float(5.28f, 2.56f));
    clippingVertices.add(new Point2D.Float(7.68f, 2.68f));
    clippingVertices.add(new Point2D.Float(7.26f, 5.10f));*/
    }

    public void test_toSegments() {
        for (PolygonSegment segment : polygon.toSegments()) {
            System.out.println(segment);
        }
    }
}
