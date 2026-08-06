package geomed.app.model;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class VoronoiCellTest {

    @Test
    void testGetArea() {
        Hospital h = new Hospital(5, 5, 1, 10);

        // 10x10 square, expected area = 100
        List<Point> vertices = Arrays.asList(
                new Point(0, 0),
                new Point(10, 0),
                new Point(10, 10),
                new Point(0, 10)
        );

        VoronoiCell cell = new VoronoiCell(h, vertices);
        assertEquals(100.0, cell.getArea(), 0.001);
    }

    @Test
    void testGetAreaInvalidPolygon() {
        Hospital h = new Hospital(0, 0, 1, 10);
        VoronoiCell cell = new VoronoiCell(h, Arrays.asList(new Point(0,0), new Point(1,1)));
        assertEquals(0.0, cell.getArea());
    }
}