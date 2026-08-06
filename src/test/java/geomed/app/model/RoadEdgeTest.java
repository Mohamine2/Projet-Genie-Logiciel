package geomed.app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoadEdgeTest {

    @Test
    void testDistanceAndWeightCalculation() {
        Point start = new Point(0, 0);
        Point end = new Point(3, 4); // Distance de 5

        RoadEdge road = new RoadEdge(start, end, 2.0); // Trafic x2

        assertEquals(5.0, road.getBaseDistance());
        assertEquals(10.0, road.getWeight(), "The weight must be baseDistance * trafficFactor");
    }

    @Test
    void testTrafficFactorConstraints() {
        RoadEdge road = new RoadEdge(new Point(0, 0), new Point(1, 1), 0.5);
        assertEquals(1.0, road.getTrafficFactor(), "The initial traffic can't be under 1.0");

        road.setTrafficFactor(-5.0);
        assertEquals(1.0, road.getTrafficFactor(), "The modified traffic can't be under 1.0");

        road.setTrafficFactor(3.0);
        assertEquals(3.0, road.getTrafficFactor());
    }
}