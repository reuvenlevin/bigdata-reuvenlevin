package ds264.bigdata.storage;

import ds264.bigdata.Storeable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StoreInArrayTest {

    @Test
    void addRow() {
        StoreInArray sia = new StoreInArray(3);
        sia.addRow("444463215", "HPD:Report inadequate cooling in Lander College");
        assertEquals(1, sia.getNumRows(), "Expected 1 row after adding");

    }

    @Test
    void getNumRows_empty() {
        StoreInArray sia = new StoreInArray(3);
        assertEquals(0, sia.getNumRows(), "Didnt get 0 for getNumRows on new/empty object");
    }

    @Test
    void getDescByID() {
        StoreInArray sia = new StoreInArray(3);
        sia.addRow("444463215", "HPD:Report inadequate cooling in Lander College");
        assertEquals("HPD:Report inadequate cooling in Lander College", sia.getDescByID("444463215"), "Did not get the expected description");
        assertNull(sia.getDescByID("742"), "Expected null for a non-existent ID");
    }
    @Test
    void deleteRow() {
        StoreInArray sia = new StoreInArray(3);
        sia.addRow("444463215", "HPD:Report inadequate cooling in Lander College");
        sia.deleteRow("444463215");
        assertNull(sia.getDescByID("444463215"), "Expected null after deletion");
        assertEquals(0, sia.getNumRows(), "Expected 0 rows after deletion");

    }
}
