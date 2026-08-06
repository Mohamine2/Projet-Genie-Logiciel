package geomed.app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void testPointGettersAndSetters() {
        Point p = new Point(10.5, 20.0);
        assertEquals(10.5, p.getX());
        assertEquals(20.0, p.getY());

        p.setX(5.0);
        p.setY(8.2);
        assertEquals(5.0, p.getX());
        assertEquals(8.2, p.getY());
    }

    @Test
    void testDistanceSquaredTo() {
        Point p1 = new Point(0, 0);
        assertEquals(25.0, p1.distanceSquaredTo(3, 4), "3^2 + 4^2 = 9 + 16 = 25");
    }

    @Test
    void testEqualsAndHashCode() {
        Point p1 = new Point(10.0, 15.0);
        Point p2 = new Point(10.0, 15.0);
        Point p3 = new Point(10.0, 16.0);

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertEquals(p1.hashCode(), p2.hashCode());
    }
}