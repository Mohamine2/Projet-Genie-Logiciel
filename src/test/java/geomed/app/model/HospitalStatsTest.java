package geomed.app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HospitalStatsTest {

    @Test
    void testStatsCarrier() {
        HospitalStats stats = new HospitalStats(15, 2.5, 45.0, 12.3);

        assertEquals(15, stats.getAssignedIncidentsCount());
        assertEquals(2.5, stats.getMinDistance());
        assertEquals(45.0, stats.getMaxDistance());
        assertEquals(12.3, stats.getAverageDistance());
    }
}