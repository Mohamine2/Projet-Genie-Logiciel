package geomed.app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SiteTest {

    @Test
    void testSiteCreation() {
        Site site = new Site(10, 20, 1);
        assertEquals(10.0, site.getX());
        assertEquals(20.0, site.getY());
        assertEquals(1, site.getId());
    }

    @Test
    void testNegativeIdThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Site(0, 0, -5)
        );
        assertTrue(exception.getMessage().contains("negative"));
    }
}