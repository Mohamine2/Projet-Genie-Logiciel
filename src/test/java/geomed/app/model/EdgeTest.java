package geomed.app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    @Test
    void testEdgeSymmetricEquality() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);

        Edge edge1 = new Edge(p1, p2);
        Edge edge2 = new Edge(p2, p1);

        assertEquals(edge1, edge2, "The order of the points must not matter for equality.");
        assertEquals(edge1.hashCode(), edge2.hashCode());
    }

    @Test
    void testNullEndpoints() {
        assertThrows(IllegalArgumentException.class, () -> new Edge(null, new Point(0,0)));
    }
}