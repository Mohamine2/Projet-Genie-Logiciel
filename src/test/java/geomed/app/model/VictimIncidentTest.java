package geomed.app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VictimIncidentTest {

    @Test
    void testIncidentCreationAndDefaults() {
        VictimIncident incident = new VictimIncident(5, 5, "INC-001", null, 42);

        assertEquals("INC-001", incident.getIncidentId());
        assertEquals(MedicalSpecialty.GENERAL, incident.getEmergencyType(), "The specialty must be GENERAL by default if null.");
        assertTrue(incident.hasMedicalHistory());
        assertEquals(42, incident.getPreferredHospitalId());
        assertNotNull(incident.getMedicalNotes());
    }

    @Test
    void testNoMedicalHistory() {
        VictimIncident incident = new VictimIncident(5, 5, "INC-002", MedicalSpecialty.TRAUMATOLOGY, null);
        assertFalse(incident.hasMedicalHistory());
        assertNull(incident.getPreferredHospitalId());
    }
}