package app.domain.model;

import app.domain.model.exceptions.OperationCanceledByUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTypeTest {

    private static VaccineType vaccineTypeTest;

    @BeforeEach
    void setUp() throws OperationCanceledByUserException {
        vaccineTypeTest = new VaccineType("Vaccine Type Test");
    }

    @Test
    void testEquals_SameObject() {
        VaccineType vaccineToValidate = vaccineTypeTest;
        assertTrue(vaccineTypeTest.equals(vaccineToValidate));
    }

    @Test
    void testEquals_Null() {
        VaccineType vaccineToValidate = null;
        assertFalse(vaccineTypeTest.equals(vaccineToValidate));
    }

    @Test
    void testEquals_DifferentType() {
        String vaccineToValidate = "Vaccine Type";
        assertFalse(vaccineTypeTest.equals(vaccineToValidate));
    }

    @Test
    void testEquals_SameDesignation() throws OperationCanceledByUserException {
        VaccineType vaccineToValidate = new VaccineType(vaccineTypeTest);
        assertTrue(vaccineTypeTest.equals(vaccineToValidate));
    }

    @Test
    void testEquals_DifferentDesignation() throws OperationCanceledByUserException {
        VaccineType vaccineToValidate = new VaccineType("New vaccine type to test");
        assertFalse(vaccineTypeTest.equals(vaccineToValidate));
    }

}