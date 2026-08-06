package geomed.app.algo;

import geomed.app.model.Point;
import geomed.app.model.Triangle;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometryUtilsTest {

    @Test
    void testCalculateCircumcenter() {
        Point a = new Point(0, 0);
        Point b = new Point(10, 0);
        Point c = new Point(0, 10);

        // For a right triangle at origin, circumcenter should be exactly at the midpoint of the hypotenuse
        Point circumcenter = GeometryUtils.calculateCircumcenter(a, b, c);

        assertEquals(5.0, circumcenter.getX(), 0.001, "X should be 5.0");
        assertEquals(5.0, circumcenter.getY(), 0.001, "Y should be 5.0");
    }

    @Test
    void testIsPointInCircumcircle() {
        Triangle t = new Triangle(new Point(0, 0), new Point(10, 0), new Point(0, 10));

        Point insidePoint = new Point(2, 2);
        Point outsidePoint = new Point(20, 20);

        assertTrue(GeometryUtils.isPointInCircumcircle(insidePoint, t), "Point should be inside the circumcircle");
        assertFalse(GeometryUtils.isPointInCircumcircle(outsidePoint, t), "Point should be outside the circumcircle");
    }

    @Test
    void testSortByPolarAngle() {
        Point center = new Point(5, 5);
        Point p1 = new Point(10, 5); // Angle 0
        Point p2 = new Point(5, 10); // Angle PI/2
        Point p3 = new Point(0, 5);  // Angle PI

        List<Point> points = Arrays.asList(p3, p1, p2);
        GeometryUtils.sortByPolarAngle(center, points);

        assertEquals(p1, points.get(0), "Point at 0 radians should be first");
        assertEquals(p2, points.get(1), "Point at PI/2 radians should be second");
        assertEquals(p3, points.get(2), "Point at PI radians should be third");
    }
}