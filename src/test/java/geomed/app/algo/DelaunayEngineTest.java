package geomed.app.algo;

import geomed.app.model.Hospital;
import geomed.app.model.Triangle;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DelaunayEngineTest {

    @Test
    void testTriangulateWithNullListThrowsException() {
        DelaunayEngine engine = new DelaunayEngine();
        assertThrows(IllegalArgumentException.class, () -> engine.triangulate(null), "Null input should throw exception");
    }

    @Test
    void testTriangulateWithInsufficientPointsReturnsEmpty() {
        DelaunayEngine engine = new DelaunayEngine();
        Set<Hospital> hospitals = new HashSet<>();
        hospitals.add(new Hospital(0, 0, 1, 10));

        List<Triangle> result = engine.triangulate(hospitals);
        assertTrue(result.isEmpty(), "Fewer than 3 points should return an empty triangulation list");
    }
}