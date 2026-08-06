package geomed.app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HospitalTest {

    @Test
    void testCapacityValidation() {
        assertThrows(IllegalArgumentException.class, () -> new Hospital(0, 0, 1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Hospital(0, 0, 1, -10));
    }

    @Test
    void testAdmitAndDischargePatient() {
        Hospital hospital = new Hospital(0, 0, 1, 2);
        assertEquals(0, hospital.getCurrentPatients());

        assertTrue(hospital.admitPatient());
        assertTrue(hospital.admitPatient());
        assertFalse(hospital.admitPatient(), "The hospital is full; admission is bound to fail.");
        assertEquals(2, hospital.getCurrentPatients());

        hospital.dischargePatient();
        assertEquals(1, hospital.getCurrentPatients());

        hospital.dischargePatient();
        hospital.dischargePatient();
        assertEquals(0, hospital.getCurrentPatients());
    }

    @Test
    void testSaturation() {
        Hospital hospital = new Hospital(0, 0, 1, 10);
        for (int i = 0; i < 8; i++) hospital.admitPatient(); // 80%

        assertFalse(hospital.isSaturated());

        hospital.admitPatient(); // 90%
        assertTrue(hospital.isSaturated());
    }

    @Test
    void testSpecialties() {
        Hospital hospital = new Hospital(0, 0, 1, 10);
        assertFalse(hospital.canTreat(MedicalSpecialty.CARDIOLOGY));

        hospital.addSpecialty(MedicalSpecialty.CARDIOLOGY);
        assertTrue(hospital.canTreat(MedicalSpecialty.CARDIOLOGY));
        assertFalse(hospital.canTreat(MedicalSpecialty.GENERAL));
        assertFalse(hospital.canTreat(null));
    }
}