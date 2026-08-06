package geomed.app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void testTriangleArea() {
        Point a = new Point(0, 0);
        Point b = new Point(10, 0);
        Point c = new Point(0, 10);

        Triangle triangle = new Triangle(a, b, c);

        // A right-angled triangle with a base of 10 and a height of 10 has an area of ​​50.
        assertEquals(50.0, triangle.getArea(), 0.001);
    }

    @Test
    void testEdgeLengths() {
        Point a = new Point(0, 0);
        Point b = new Point(3, 4);
        Point c = new Point(0, 0);

        Triangle triangle = new Triangle(a, b, c);

        assertEquals(5.0, triangle.getEdgeABLength(), 0.001);
    }
}