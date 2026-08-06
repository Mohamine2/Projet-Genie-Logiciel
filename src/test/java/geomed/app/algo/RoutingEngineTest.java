package geomed.app.algo;

import geomed.app.model.Point;
import geomed.app.model.RoadEdge;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RoutingEngineTest {

    @Test
    void testComputeOptimalPathValidRoute() {
        Point start = new Point(0, 0);
        Point mid = new Point(10, 0); // Distance 10
        Point end = new Point(10, 10); // Distance 10

        RoadEdge edge1 = new RoadEdge(start, mid, 1.0); // Cost 10
        RoadEdge edge2 = new RoadEdge(mid, end, 2.0);   // Cost 20 (distance 10 * factor 2)

        RoutingEngine engine = new RoutingEngine(Arrays.asList(edge1, edge2));
        RoutingResult result = engine.computeOptimalPath(start, end);

        assertEquals(30.0, result.totalCost(), "Total cost should be 10 + 20 = 30");
        assertEquals(3, result.path().size(), "Path should contain 3 points");
        assertEquals(end, result.path().get(2), "Destination should be the last point");
    }

    @Test
    void testComputeOptimalPathUnreachable() {
        Point start = new Point(0, 0);
        Point end = new Point(100, 100);

        RoutingEngine engine = new RoutingEngine(Arrays.asList()); // Empty network
        RoutingResult result = engine.computeOptimalPath(start, end);

        assertEquals(Double.MAX_VALUE, result.totalCost(), "Unreachable destination should cost MAX_VALUE");
        assertTrue(result.path().isEmpty(), "Unreachable path should be empty");
    }
}