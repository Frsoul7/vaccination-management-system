package app.domain.model;

import app.domain.model.exceptions.OperationCanceledByUserException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private static String name;
    private static String name2;
    private static String address;
    private static String address2;
    private static long phoneNumber;
    private static long phoneNumber2;
    private static String email;
    private static String email2;
    private static long citizenCardnumber;
    private static long citizenCardnumber2;
    private static Person p1;

    @BeforeAll
    static void setUp() {
        name = "Nome da pessoa";
        name2 = "Nome da pessoa x";
        address = "Morada da pessoa";
        address2 = "Morada da pessoa 2";
        phoneNumber = 219475629;
        phoneNumber2 = 215421544;
        email = "email@pessoa.pt";
        email2 = "email2@pessoa2.pt";
        citizenCardnumber = 12345678;
        citizenCardnumber2 = 87654321;
        try {
            p1 = new Person(name, address, phoneNumber, email, citizenCardnumber);
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void testEquals_SameObject() {
        Person p2 = p1;
        assertTrue(p1.equals(p2));
    }

    @Test
    void testEquals_Null() {
        Person p2 = null;
        assertFalse(p1.equals(p2));
    }

    @Test
    void testEquals_DifferentTypes() {
        assertFalse(p1.equals(name));
    }

    @Test
    void testEquals_SameValues() {
        try {
            Person p2 = new Person(name, address, phoneNumber, email, citizenCardnumber);
            assertTrue(p1.equals(p2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void testEquals_DifferentValues() {
        try {
            Person p2 = new Person(name2, address2, phoneNumber2, email2, citizenCardnumber2);
            assertFalse(p1.equals(p2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void testEquals_DifferentName() {
        try {
            Person p2 = new Person(name2, address, phoneNumber, email, citizenCardnumber);
            assertFalse(p1.equals(p2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void testEquals_DifferentAddress() {
        try {
            Person p2 = new Person(name, address2, phoneNumber, email, citizenCardnumber);
            assertFalse(p1.equals(p2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void testEquals_DifferentPhoneNumber() {
        try {
            Person p2 = new Person(name, address, phoneNumber2, email, citizenCardnumber);
            assertFalse(p1.equals(p2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void testEquals_DifferentEmail() {
        try {
            Person p2 = new Person(name, address, phoneNumber, email2, citizenCardnumber);
            assertFalse(p1.equals(p2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void testEquals_DifferentCitizenCardNumber() {
        try {
            Person p2 = new Person(name, address, phoneNumber, email, citizenCardnumber2);
            assertFalse(p1.equals(p2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void testEqualsUniqueAttributes_SameObject() {
        Person p2 = p1;
        assertTrue(p1.equalsUniqueAttributes(p2));
    }

    @Test
    void testEqualsUniqueAttributes_Null() {
        Person p2 = null;
        assertFalse(p1.equalsUniqueAttributes(p2));
    }

    @Test
    void testEqualsUniqueAttributes_AllDifferentValues() {
        try {
            Person p2 = new Person(name2, address2, phoneNumber2, email2, citizenCardnumber2);
            assertFalse(p1.equalsUniqueAttributes(p2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void testEqualsUniqueAttributes_AllEqualValues() {
        try {
            Person p2 = new Person(name, address, phoneNumber, email, citizenCardnumber);
            assertTrue(p1.equalsUniqueAttributes(p2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void testEqualsUniqueAttributes_SamePhoneNumber() {
        try {
            Person p2 = new Person(name2, address2, phoneNumber, email2, citizenCardnumber2);
            assertTrue(p1.equalsUniqueAttributes(p2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void testEqualsUniqueAttributes_SameEmail() {
        try {
            Person p2 = new Person(name2, address2, phoneNumber2, email, citizenCardnumber2);
            assertTrue(p1.equalsUniqueAttributes(p2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void testEqualsUniqueAttributes_SameCitizenCardNumber() {
        try {
            Person p2 = new Person(name2, address2, phoneNumber2, email2, citizenCardnumber);
            assertTrue(p1.equalsUniqueAttributes(p2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

}
