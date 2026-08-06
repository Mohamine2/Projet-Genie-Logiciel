package geomed.app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoadNetworkTest {

    @Test
    void testAddIntersectionAndDuplicates() {
        RoadNetwork network = new RoadNetwork();
        Point p1 = new Point(10, 10);

        network.addIntersection(p1);
        network.addIntersection(new Point(10, 10));

        assertEquals(1, network.getIntersections().size());
    }

    @Test
    void testFindNearestIntersection() {
        RoadNetwork network = new RoadNetwork();
        Point p1 = new Point(0, 0);
        Point p2 = new Point(100, 100);

        network.addIntersection(p1);
        network.addIntersection(p2);

        Point target = new Point(5, 5);
        Point nearest = network.findNearestIntersection(target);

        assertEquals(p1, nearest);
    }
}