package geomed.app.algo;

import geomed.app.model.Hospital;
import geomed.app.model.Triangle;
import geomed.app.model.VoronoiCell;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VoronoiEngineTest {

    @Test
    void testGenerateVoronoiCellsEmptyInput() {
        VoronoiEngine engine = new VoronoiEngine();
        Set<Hospital> hospitals = new HashSet<>();
        List<Triangle> triangles = new ArrayList<>();

        List<VoronoiCell> cells = engine.generateVoronoiCells(hospitals, triangles);
        assertTrue(cells.isEmpty(), "Empty input should yield empty Voronoi cells list");
    }
}