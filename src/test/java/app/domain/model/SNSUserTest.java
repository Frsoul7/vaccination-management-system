package app.domain.model;

import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.DateCustom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SNSUserTest {

    private static SNSUser sns1;
    private static String name;
    private static String name2;
    private static String address;
    private static String address2;
    private static String gender;
    private static String gender2;
    private static long phoneNumber;
    private static long phoneNumber2;
    private static String email;
    private static String email2;
    private static long citizenCardNumber;
    private static long citizenCardNumber2;
    private static long snsUserNumber;
    private static long snsUserNumber2;
    private static DateCustom birthDate;
    private static DateCustom birthDate2;


    @BeforeAll
    static void setUp() {
        name = "SNS User";
        name2 = "SNS User x";
        address = "Morada SNS User 1";
        address2 = "Morada SNS User 2";
        gender = "Male";
        gender2 = "Female";
        phoneNumber = 228769977;
        phoneNumber2 = 218789934;
        email = "snsuser1@email.pt";
        email2 = "snsuser2@email.pt";
        citizenCardNumber = 65940275;
        citizenCardNumber2 = 67549753;
        snsUserNumber = 435889700;
        snsUserNumber2 = 657881234;
        birthDate = new DateCustom(DateCustom.getActualDate());
        birthDate.setYear(birthDate.getYear() - 20);
        birthDate2 = new DateCustom(DateCustom.getActualDate());
        birthDate2.setYear(birthDate.getYear() - 25);
        try {
            sns1 = new SNSUser(name, address, gender, phoneNumber, email, citizenCardNumber, snsUserNumber, birthDate);
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void testEquals_Null() {
        SNSUser sns2 = null;
        assertFalse(sns1.equals(sns2));
    }

    @Test
    void testEquals_Same() {
        SNSUser sns2 = sns1;
        assertTrue(sns1.equals(sns2));
    }

    @Test
    void testEquals_DifferentTypes() {
        assertFalse(sns1.equals(name));
    }

    @Test
    void testEquals_SameValues() {
        try {
            SNSUser sns2 =
                    new SNSUser(name, address, gender, phoneNumber, email, citizenCardNumber, snsUserNumber, birthDate);
            assertTrue(sns1.equals(sns2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void testEquals_DifferentValues() {
        try {
            SNSUser sns2 =
                    new SNSUser(name2, address2, gender2, phoneNumber2, email2, citizenCardNumber2, snsUserNumber2,
                                birthDate2);
            assertFalse(sns1.equals(sns2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void testEquals_DifferentSNSUserNumber() {
        try {
            SNSUser sns2 = new SNSUser(name, address, gender, phoneNumber, email, citizenCardNumber, snsUserNumber2,
                                       birthDate);
            assertFalse(sns1.equals(sns2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void testEquals_DifferentBirthDate() {
        try {
            SNSUser sns2 = new SNSUser(name, address, gender, phoneNumber, email, citizenCardNumber, snsUserNumber,
                                       birthDate2);
            assertFalse(sns1.equals(sns2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void testEquals_DifferentGender() {
        try {
            SNSUser sns2 = new SNSUser(name, address, gender2, phoneNumber, email, citizenCardNumber, snsUserNumber,
                                       birthDate);
            assertFalse(sns1.equals(sns2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void equalsUniqueAttributes_Null() {
        SNSUser sns2 = null;
        assertFalse(sns1.equalsUniqueAttributes(sns2));
    }

    @Test
    void equalsUniqueAttributes_Same() {
        SNSUser sns2 = sns1;
        assertTrue(sns1.equalsUniqueAttributes(sns2));
    }


    @Test
    void equalsUniqueAttributes_DifferentPhoneNumberEmailAndCitizenCardNumber() {
        try {
            SNSUser sns2 =
                    new SNSUser(name, address, gender, phoneNumber, email, citizenCardNumber, snsUserNumber, birthDate);
            assertTrue(sns1.equalsUniqueAttributes(sns2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void equalsUniqueAttributes_SameSNSUserNumber() {
        try {
            SNSUser sns2 =
                    new SNSUser(name2, address2, gender2, phoneNumber2, email2, citizenCardNumber2, snsUserNumber,
                                birthDate2);
            assertFalse(sns1.equalsUniqueAttributes(sns2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void equalsUniqueAttributes_DifferentValues() {
        try {
            SNSUser sns2 =
                    new SNSUser(name2, address2, gender2, phoneNumber2, email2, citizenCardNumber2, snsUserNumber2,
                                birthDate2);
            assertFalse(sns1.equalsUniqueAttributes(sns2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void SNSUser_Copy() {
        try {
            SNSUser snsCopy = new SNSUser(sns1);
            assertTrue(snsCopy.equals(sns1));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void calculateAge() {
        DateCustom twoYears = new DateCustom(DateCustom.getActualDate());
        twoYears.setYear(twoYears.getYear() - 2);
        twoYears.addDays(-1);
        try {
            SNSUser sns =
                    new SNSUser(name, address, gender, phoneNumber, email, citizenCardNumber, snsUserNumber, twoYears);
            assertEquals(sns.calculateAge(), 2);
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void calculateAgeInDate_OK() {
        DateCustom fourYears = new DateCustom(DateCustom.getActualDate());
        fourYears.setYear(fourYears.getYear() - 4);
        DateCustom inDate = new DateCustom(DateCustom.getActualDate());
        inDate.setYear(inDate.getYear() - 1);
        inDate.addDays(1);
        try {
            SNSUser sns =
                    new SNSUser(name, address, gender, phoneNumber, email, citizenCardNumber, snsUserNumber, fourYears);
            assertEquals(sns.calculateAge(inDate), 3);
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void calculateAgeInDate_Null() {
        DateCustom fourYears = new DateCustom(DateCustom.getActualDate());
        fourYears.setYear(fourYears.getYear() - 4);
        DateCustom inDate = null;
        try {
            SNSUser sns =
                    new SNSUser(name, address, gender, phoneNumber, email, citizenCardNumber, snsUserNumber, fourYears);
            assertEquals(sns.calculateAge(inDate), -1);
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

}