package geomed.app.algo;

import geomed.app.explainability.DispatchDecision;
import geomed.app.model.Hospital;
import geomed.app.model.MedicalSpecialty;
import geomed.app.model.VictimIncident;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DispatchEngineTest {

    @Test
    void testEvaluateBestDispatchNoHospitals() {
        DispatchEngine engine = new DispatchEngine();
        VictimIncident incident = new VictimIncident(0, 0, "INC1", MedicalSpecialty.GENERAL, null);
        Set<Hospital> hospitals = new HashSet<>();

        DispatchDecision decision = engine.evaluateBestDispatch(incident, hospitals, null, new ArrayList<>());

        assertNull(decision.getOptimalHospital(), "Chosen hospital should be null when list is empty");
        assertTrue(decision.getScoringMatrix().isEmpty(), "Scores map should be empty");
    }
}