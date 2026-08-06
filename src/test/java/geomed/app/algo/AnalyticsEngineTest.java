package geomed.app.algo;

import geomed.app.model.Hospital;
import geomed.app.model.VictimIncident;
import geomed.app.model.MedicalSpecialty;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnalyticsEngineTest {

    @Test
    void testGetIncidentCountForHospital() {
        Hospital target = new Hospital(0, 0, 1, 10);
        Hospital other = new Hospital(10, 10, 2, 10);

        VictimIncident inc1 = new VictimIncident(0, 0, "INC1", MedicalSpecialty.GENERAL, null);
        inc1.setClosestHospital(target);

        VictimIncident inc2 = new VictimIncident(0, 0, "INC2", MedicalSpecialty.GENERAL, null);
        inc2.setClosestHospital(other);

        List<VictimIncident> incidents = Arrays.asList(inc1, inc2);

        int count = AnalyticsEngine.getIncidentCountForHospital(target, incidents);
        assertEquals(1, count, "Should only count incidents assigned to the target hospital");
    }

    @Test
    void testComputeIncidentDensity() {
        double area = 5000.0;
        int incidents = 5;

        double expectedDensity = (5.0 / 5000.0) * 10000;
        assertEquals(expectedDensity, AnalyticsEngine.computeIncidentDensity(area, incidents), 0.001);
        assertEquals(0.0, AnalyticsEngine.computeIncidentDensity(0.0, 5), "Zero area should yield zero density");
    }
}