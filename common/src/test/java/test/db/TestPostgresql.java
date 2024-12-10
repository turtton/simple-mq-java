package test.db;

import net.turtton.simplemq.common.db.Postgresql;
import org.junit.jupiter.api.Test;

public class TestPostgresql {
    @Test
    void testInitialization() {
        if (!System.getenv("TEST_DATABASE").equals("1")) {
            return;
        }
        new Postgresql();
    }
}
