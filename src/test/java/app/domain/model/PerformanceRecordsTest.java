package app.domain.model;

import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PerformanceRecordsTest {

    private static PerformanceRecords performanceRecord;
    private static PerformanceRecords performanceRecord2;

    @BeforeEach
    void setUp() {
        performanceRecord = new PerformanceRecords(123456789, DateCustom.getActualDate(), TimeHour.getActualTime());
        performanceRecord2 = new PerformanceRecords(234567890, DateCustom.getActualDate(), TimeHour.getActualTime());
    }

    @Test
    void testEquals_SameObject() {
        PerformanceRecords performanceRecordsToTest = performanceRecord;
        assertTrue(performanceRecord.equals(performanceRecordsToTest));
    }

    @Test
    void testEquals_Null() {
        PerformanceRecords performanceRecordsToTest = null;
        assertFalse(performanceRecord.equals(performanceRecordsToTest));
    }

    @Test
    void testEquals_DifferentType() {
        String performanceRecordsToTest = "new performance record";
        assertFalse(performanceRecord.equals(performanceRecordsToTest));
    }

    @Test
    void testEquals_SameValues() {
        PerformanceRecords performanceRecordsToTest = new PerformanceRecords(performanceRecord);
        assertTrue(performanceRecord.equals(performanceRecordsToTest));
    }

    @Test
    void testEquals_DifferentValues() {
        PerformanceRecords performanceRecordsToTest = new PerformanceRecords(performanceRecord2);
        assertFalse(performanceRecord.equals(performanceRecordsToTest));
    }

    @Test
    void testEqualsUniqueAttributes_Sameobject() {
        PerformanceRecords performanceRecordsToTest = performanceRecord;
        assertTrue(performanceRecord.equalsUniqueAttributes(performanceRecordsToTest));
    }

    @Test
    void testEqualsUniqueAttributes_Null() {
        PerformanceRecords performanceRecordsToTest = null;
        assertFalse(performanceRecord.equalsUniqueAttributes(performanceRecordsToTest));
    }

    @Test
    void testEqualsUniqueAttributes_SameValues() {
        PerformanceRecords performanceRecordsToTest = new PerformanceRecords(performanceRecord);
        assertTrue(performanceRecord.equalsUniqueAttributes(performanceRecordsToTest));
    }

    @Test
    void testEqualsUniqueAttributes_DifferentValues() {
        PerformanceRecords performanceRecordsToTest = new PerformanceRecords(performanceRecord2);
        assertFalse(performanceRecord.equalsUniqueAttributes(performanceRecordsToTest));
    }

}