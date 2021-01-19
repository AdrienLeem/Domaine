package Test;

import Model.Pion;
import Model.PionChateau;
import Model.PionChevalier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PionTest {

    protected Pion p1;
    protected Pion p2;
    @BeforeEach
    void setUp() {
        p1 = new PionChateau();
        p1.setX(1);
        p1.setY(2);
        p2 = new PionChevalier();
        p2.setX(3);

    }


    @AfterEach
    void tearDown() {
        p1=null;
        p2=null;
    }

    @Test
    void estPlace() {
        assertTrue(p1.estPlace());
        assertFalse(p2.estPlace());
    }
}