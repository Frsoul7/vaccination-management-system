package app.domain.model.utils;

import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.shared.EmployeeRoles;
import app.interfaces.Constants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationsTest implements Constants {

    private static String emailValid;
    private static String stringEmpty;
    private static String stringNull;
    private static long id;
    private static String websiteValid;
    private static String websiteInvalid;
    private static String websiteInvalidWrongBegin;
    private static String hourFormatValid;
    private static String hourFormatInvalidException;
    private static String hourFormatInvalidWithoutSpace;


    @BeforeAll
    static void setUp() {
        emailValid = "email@teste.pt";
        stringEmpty = "";
        stringNull = null;
        id = 123;
        websiteValid = "www.exemplo.com";
        websiteInvalid = "exemplo.com";
        websiteInvalidWrongBegin = "ww.exemplo.com";
        hourFormatValid = "05:30 am";
        hourFormatInvalidException = "AM:30 01";
        hourFormatInvalidWithoutSpace = "05:30am";
    }


    @Test
    void isEmailValid_OKMandatory() {
        try {
            assertTrue(Validations.isEmailFormatValid(emailValid, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isEmailValid_OKNotMandatory() {
        try {
            assertTrue(Validations.isEmailFormatValid(emailValid, false, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isEmailValid_NullMandatory() {
        try {
            assertFalse(Validations.isEmailFormatValid(stringNull, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isEmailValid_NullNotMandatory() {
        try {
            assertTrue(Validations.isEmailFormatValid(stringNull, false, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isEmailValid_EmptyMandatory() {
        try {
            assertFalse(Validations.isEmailFormatValid(stringEmpty, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isEmailValid_EmptyNotMandatory() {
        try {
            assertTrue(Validations.isEmailFormatValid(stringEmpty, false, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isEmailValid_CancelMandatory() {
        Exception e = assertThrows(OperationCanceledByUserException.class, () -> {
            boolean result = Validations.isEmailFormatValid(VALUE_TO_CANCEL, true, true);
        });
    }

    @Test
    void isEmailValid_CancelNotMandatory() {
        Exception e = assertThrows(OperationCanceledByUserException.class, () -> {
            boolean result = Validations.isEmailFormatValid(VALUE_TO_CANCEL, false, true);
        });
    }

    @Test
    void isEmployeeRoleValid() {
        assertTrue(Validations.isEmployeeRoleValid(EmployeeRoles.ROLE_ADMIN));
    }

    @Test
    void isEmployeeIdValid_Zero() {
        assertFalse(Validations.isEmployeeIdValid(0));
    }

    @Test
    void isEmployeeIdValid_NotNull() {
        assertTrue(Validations.isEmployeeIdValid(id));
    }

    @Test
    void isGenderValid_GenderNullMandatory() {
        try {
            assertFalse(Validations.isGenderValid(stringNull, true, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isGenderValid_GenderNullNotMandatory() {
        try {
            assertTrue(Validations.isGenderValid(stringNull, false, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isGenderValid_GenderEmptyMandatory() {
        try {
            assertFalse(Validations.isGenderValid(stringEmpty, true, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isGenderValid_GenderEmptyNotMandatory() {
        try {
            assertTrue(Validations.isGenderValid(stringEmpty, false, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isGenderValid_GenderValidNotMandatory() {
        try {
            assertTrue(Validations.isGenderValid(GENDER_FEMALE, false, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }


    @Test
    void isGenderValid_GenderValidMandatory() {
        try {
            assertTrue(Validations.isGenderValid(GENDER_MALE, true, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isGenderValid_CancelOK() {
        Exception e = assertThrows(OperationCanceledByUserException.class, () -> {
            boolean result = Validations.isGenderValid(VALUE_TO_CANCEL, true, true);
        });
    }

    @Test
    void isGenderValid_CancelNOK() {
        try {
            assertFalse(Validations.isGenderValid(VALUE_TO_CANCEL, false, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isWebSiteFormatValid_OK() {
        try {
            assertTrue(Validations.isWebSiteFormatValid(websiteValid, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isWebSiteFormatValid_NOK() {
        try {
            assertFalse(Validations.isWebSiteFormatValid(websiteInvalid, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isWebSiteFormatValid_NOKWrongBegin() {
        try {
            assertFalse(Validations.isWebSiteFormatValid(websiteInvalidWrongBegin, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isHourFormatValid_OK() {
        try {
            assertTrue(Validations.isHourFormatValid(hourFormatValid, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isHourFormatValid_CancelOK() {
        Exception e = assertThrows(OperationCanceledByUserException.class, () -> {
            boolean result = Validations.isHourFormatValid(VALUE_TO_CANCEL, true, true);
        });
    }

    @Test
    void isHourFormatValid_NOKWithoutSpace() {
        try {
            assertFalse(Validations.isHourFormatValid(hourFormatInvalidWithoutSpace, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isHourFormatValid_NOKInvalidFormat() {
        try {
            assertFalse(Validations.isHourFormatValid(hourFormatInvalidException, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isHourFormatValid_NOKEmpty() {
        try {
            assertFalse(Validations.isHourFormatValid(stringEmpty, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isDateFormatValid_NullMandatory() {
        try {
            assertFalse(Validations.isDateFormatValid(stringNull, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isDateFormatValid_NullNotMandatory() {
        try {
            assertTrue(Validations.isDateFormatValid(stringNull, false, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isDateFormatValid_EmptyMandatory() {
        try {
            assertFalse(Validations.isDateFormatValid(stringEmpty, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isDateFormatValid_EmptyNotMandatory() {
        try {
            assertTrue(Validations.isDateFormatValid(stringEmpty, false, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isDateFormatValid_CancelOK() {
        Exception e = assertThrows(OperationCanceledByUserException.class, () -> {
            boolean result = Validations.isDateFormatValid(VALUE_TO_CANCEL, true, true);
        });
    }


    @Test
    void isDateFormatValid_WrongNumberOfDelimiters() {
        String date3Delimeters = "///";
        try {
            assertFalse(Validations.isDateFormatValid(date3Delimeters, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isDateFormatValid_LetterInCorrectFormat() {
        String dateToValidate = "12/JAN/1980";
        try {
            assertFalse(Validations.isDateFormatValid(dateToValidate, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isDateFormatValid_CorrectFormatExceedsDay() {
        String dateToValidate = "100/01/1980";
        try {
            assertFalse(Validations.isDateFormatValid(dateToValidate, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }


    @Test
    void isDateFormatValid_CorrectFormatExceedsMonth() {
        String dateToValidate = "10/100/1980";
        try {
            assertFalse(Validations.isDateFormatValid(dateToValidate, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isDateFormatValid_CorrectFormatExceedsDayAndMonth() {
        String dateToValidate = "100/100/1980";
        try {
            assertFalse(Validations.isDateFormatValid(dateToValidate, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isDateFormatValid_CorrectFormatExceedsYear() {
        String dateToValidate = "10/01/10000";
        try {
            assertFalse(Validations.isDateFormatValid(dateToValidate, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isDateFormatValid_CorrectFormatExceedsDayAndMonthAndYear() {
        String dateToValidate = "100/100/10000";
        try {
            assertFalse(Validations.isDateFormatValid(dateToValidate, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isDateFormatValid_CorrectFormatExceedsDayAndYear() {
        String dateToValidate = "100/10/10000";
        try {
            assertFalse(Validations.isDateFormatValid(dateToValidate, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isDateFormatValid_CorrectFormatLessYear() {
        String dateToValidate = "10/10/900";
        try {
            assertFalse(Validations.isDateFormatValid(dateToValidate, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }


    @Test
    void isAgeGroupValid_WrongNumberOfDelimiters() {
        try {
            String ageGroup = "10,20,";
            assertFalse(Validations.isAgeGroupValid(ageGroup, false, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }


    @Test
    void isAgeGroupValid_WrongGroup() {
        try {
            String ageGroup = "30,10,";
            assertFalse(Validations.isAgeGroupValid(ageGroup, true, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isAgeGroupValid_InicialNumberExceedsLimit() {
        try {
            String ageGroup = "130,20";
            assertFalse(Validations.isAgeGroupValid(ageGroup, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isAgeGroupValid_InicialNumberLessOne() {
        try {
            String ageGroup = "0,20";
            assertFalse(Validations.isAgeGroupValid(ageGroup, false, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isAgeGroupValid_ValidInicialNumberFinalNumberExceedsLimit() {
        try {
            String ageGroupDelmiters = "10,230";
            assertFalse(Validations.isAgeGroupValid(ageGroupDelmiters, false, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isAgeGroupValid_ValidInicialNumberFinalNumberLessOne() {
        try {
            String ageGroup = "10,0";
            assertFalse(Validations.isAgeGroupValid(ageGroup, false, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isAgeGroupValid_ValidInicialAndFinalNumbersFinalLessInitial() {
        try {
            String ageGroup = "10,1";
            assertFalse(Validations.isAgeGroupValid(ageGroup, false, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isAgeGroupValid_GroupWithLetters() {
        try {
            String ageGroup = "30,9A";
            assertFalse(Validations.isAgeGroupValid(ageGroup, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isAgeGroupValid_Valid() {
        try {
            String ageGroupDelmiters = "10,20";
            assertTrue(Validations.isAgeGroupValid(ageGroupDelmiters, false, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }


    @Test
    void isLotNumberValid_WrongNumberOfDelimiters() {
        try {
            String lotNumber = "ABC-X-";
            assertFalse(Validations.isLotNumberValid(lotNumber, true, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isLotNumberValid_LetterInSecondPart() {
        try {
            String lotNumber = "AB123-X";
            assertFalse(Validations.isLotNumberValid(lotNumber, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isLotNumberValid_WrongValueInSecondPart() {
        try {
            String lotNumber = "AB123-100";
            assertFalse(Validations.isLotNumberValid(lotNumber, false, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isLotNumberValid_WrongSizeInFirstPart() {
        try {
            String lotNumber = "AB12-10";
            assertFalse(Validations.isLotNumberValid(lotNumber, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isLotNumberValid_WrongCharInFirstPart() {
        try {
            String lotNumber = "AB#12-10";
            assertFalse(Validations.isLotNumberValid(lotNumber, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isLotNumberValid_WrongCharInSecondPart() {
        try {
            String lotNumber = "AB123-#0";
            assertFalse(Validations.isLotNumberValid(lotNumber, false, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isLotNumberValid_Valid() {
        try {
            String lotNumber = "AB123-10";
            assertTrue(Validations.isLotNumberValid(lotNumber, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isLengthValidEquals_CancelOK() {
        long numberToValidate = Long.parseLong(VALUE_TO_CANCEL);
        Exception e = assertThrows(OperationCanceledByUserException.class, () -> {
            boolean result = Validations.isLengthValidEquals(numberToValidate, true, true, 10);
        });
    }

    @Test
    void isLengthValidEquals_ZeroNotMandatory() {
        long numberToValidate = 0;
        try {
            assertTrue(Validations.isLengthValidEquals(numberToValidate, false, false, 10));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isLengthValidEquals_ZeroMandatoryWrongSize() {
        long numberToValidate = 0;
        try {
            assertFalse(Validations.isLengthValidEquals(numberToValidate, true, false, 10));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isLengthValidEquals_ZeroMandatoryCorrectSize() {
        long numberToValidate = 0;
        try {
            assertTrue(Validations.isLengthValidEquals(numberToValidate, true, false, 1));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isLengthValidEquals_Valid() {
        long numberToValidate = 123;
        try {
            assertTrue(Validations.isLengthValidEquals(numberToValidate, true, false, 3));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isLengthValidLesserThen_CancelOK() {
        int numberToValidate = Integer.parseInt(VALUE_TO_CANCEL);
        Exception e = assertThrows(OperationCanceledByUserException.class, () -> {
            boolean result = Validations.isLengthValidLesserThen(numberToValidate, true, true, 10);
        });
    }

    @Test
    void isLengthValidLesserThen_ZeroNotMandatory() {
        int numberToValidate = 0;
        try {
            assertTrue(Validations.isLengthValidLesserThen(numberToValidate, false, false, 10));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isLengthValidLesserThen_WrongSize() {
        int numberToValidate = 10;
        try {
            assertFalse(Validations.isLengthValidLesserThen(numberToValidate, true, false, 1));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isLengthValidLesserThen_Negative() {
        int numberToValidate = -10;
        try {
            assertFalse(Validations.isLengthValidLesserThen(numberToValidate, true, false, 2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isLengthValidLesserThen_Valid() {
        int numberToValidate = 10;
        try {
            assertTrue(Validations.isLengthValidLesserThen(numberToValidate, true, false, 2));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }


    @Test
    void isFilePathValid_FileNotExists() {
        try {
            String filePath = "teste.txt";
            assertFalse(Validations.isFilePathValid(filePath, true, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isFilePathValid_FileExistsWithContent() {
        try {
            String filePath = "config.properties";
            assertTrue(Validations.isFilePathValid(filePath, true, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isFilePathValid_FileExistsWithoutContent() {
        try {
            String filePath = "src/test/java/app/domain/model/utils/fileNoContent.txt";
            assertFalse(Validations.isFilePathValid(filePath, false, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isFilePathValid_ValidFileWrongType() {
        try {
            String filePath = "config.properties";
            assertFalse(Validations.isFilePathValid(filePath, false, false));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isFilePathValid_CancelOK() {
        String text = VALUE_TO_CANCEL;
        Exception e = assertThrows(OperationCanceledByUserException.class, () -> {
            boolean result = Validations.isFilePathValid(text, true, true);
        });
    }

    @Test
    void isStringLengthValid_CancelOK() {
        String text = VALUE_TO_CANCEL;
        Exception e = assertThrows(OperationCanceledByUserException.class, () -> {
            boolean result = Validations.isStringLengthValid(text, true, true, 10);
        });
    }

    @Test
    void isStringLengthValid_ZeroNotMandatory() {
        String text = "0";
        try {
            assertTrue(Validations.isStringLengthValid(text, false, false, 10));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isStringLengthValid_ZeroMandatoryWrongSize() {
        String text = "0";
        try {
            assertFalse(Validations.isStringLengthValid(text, true, false, 0));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isStringLengthValid_ZeroMandatoryCorrectSize() {
        String text = "0";
        try {
            assertTrue(Validations.isStringLengthValid(text, true, false, 1));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isStringLengthValid_Valid() {
        String text = "Test length of text.";
        try {
            assertTrue(Validations.isStringLengthValid(text, true, false, 20));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isStringLengthValid_NullMandatory() {
        try {
            assertFalse(Validations.isStringLengthValid(stringNull, true, false, 20));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isStringLengthValid_NullNotMandatory() {
        try {
            assertTrue(Validations.isStringLengthValid(stringNull, false, false, 20));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isStringLengthValid_EmptyMandatory() {
        try {
            assertFalse(Validations.isStringLengthValid(stringEmpty, true, false, 20));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isStringLengthValid_EmptyNotMandatory() {
        try {
            assertTrue(Validations.isStringLengthValid(stringEmpty, false, false, 20));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isNameValid_NullMandatory() {
        try {
            assertFalse(Validations.isNameValid(stringNull, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isNameValid_NullNotMandatory() {
        try {
            assertTrue(Validations.isNameValid(stringNull, false, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isNameValid_EmptyMandatory() {
        try {
            assertFalse(Validations.isNameValid(stringEmpty, true, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isNameValid_EmptyNotMandatory() {
        try {
            assertTrue(Validations.isNameValid(stringEmpty, false, true));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isNameValid_Cancel() {
        Exception e = assertThrows(OperationCanceledByUserException.class, () -> {
            boolean result = Validations.isNameValid(VALUE_TO_CANCEL, false, true);
        });
    }

    @Test
    void isNumberDosesValidAdm_WrongSize() {
        int numberOfDoses = 10;
        int maxLength = 1;
        try {
            assertFalse(Validations.isNumberDosesValidAdm(numberOfDoses, true, true, maxLength));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isNumberDosesValidAdm_NegativeNumberOfDoses() {
        int numberOfDoses = -1;
        int maxLength = 1;
        try {
            assertFalse(Validations.isNumberDosesValidAdm(numberOfDoses, false, false, maxLength));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isNumberDosesValidAdm_WrongLimitNumberOfDoses() {
        int numberOfDoses = 7;
        int maxLength = 2;
        try {
            assertFalse(Validations.isNumberDosesValidAdm(numberOfDoses, true, false, maxLength));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }


    @Test
    void isNumberDosesValidAdm_Valid() {
        int numberOfDoses = 5;
        int maxLength = 5;
        try {
            assertTrue(Validations.isNumberDosesValidAdm(numberOfDoses, false, false, maxLength));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isValueValid_Negative() {
        int valueToValidate = -10;
        try {
            assertFalse(Validations.isValueValid(valueToValidate, true, true, 10));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isValueValid_ExceedsMaxValue() {
        int valueToValidate = 11;
        try {
            assertFalse(Validations.isValueValid(valueToValidate, true, true, 10));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isValueValid_Valid() {
        int valueToValidate = 8;
        try {
            assertTrue(Validations.isValueValid(valueToValidate, true, true, 10));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isValueValid_ZeroNotMandatory() {
        int valueToValidate = 0;
        try {
            assertTrue(Validations.isValueValid(valueToValidate, false, false, 10));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isValueValid_Cancel() {
        Exception e = assertThrows(OperationCanceledByUserException.class, () -> {
            boolean result = Validations.isValueValid(Integer.parseInt(VALUE_TO_CANCEL), true, true, 1);
        });
    }

    @Test
    void isTimeIntervalBetweenDosesValid_WrongSize() {
        int timeInterval = 1000;
        int maxLength = 3;
        try {
            assertFalse(Validations.isTimeIntervalBetweenDosesValid(timeInterval, true, true, maxLength));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isTimeIntervalBetweenDosesValid_ExceedMaxValue() {
        int timeInterval = 367;
        int maxLength = 3;
        try {
            assertFalse(Validations.isTimeIntervalBetweenDosesValid(timeInterval, false, false, maxLength));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isTimeIntervalBetweenDosesValid_Negative() {
        int timeInterval = -1;
        int maxLength = 2;
        try {
            assertFalse(Validations.isTimeIntervalBetweenDosesValid(timeInterval, true, false, maxLength));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isTimeIntervalBetweenDosesValid_Valid() {
        int timeInterval = 300;
        int maxLength = 3;
        try {
            assertTrue(Validations.isTimeIntervalBetweenDosesValid(timeInterval, true, true, maxLength));
        }
        catch(OperationCanceledByUserException e) {
            fail();
        }
    }

    @Test
    void isBirthdayDateValid_DateInFuture() {
        DateCustom dateToValidate = DateCustom.getActualDate();
        dateToValidate.setYear(dateToValidate.getYear() + 1);
        assertFalse(Validations.isBirthDateValid(dateToValidate));
    }

    @Test
    void isBirthdayDateValid_DateToLong() {
        DateCustom dateToValidate = DateCustom.getActualDate();
        dateToValidate.setYear(1800);
        assertFalse(Validations.isBirthDateValid(dateToValidate));
    }

    @Test
    void isBirthdayDateValid_Valid20Years() {
        DateCustom dateToValidate = DateCustom.getActualDate();
        dateToValidate.setYear(dateToValidate.getYear() - 20);
        assertTrue(Validations.isBirthDateValid(dateToValidate));
    }

    @Test
    void isBirthdayDateValid_Null() {
        DateCustom dateToValidate = null;
        assertFalse(Validations.isBirthDateValid(dateToValidate));
    }

    @Test
    void isPeriodValid_startDateNull() {
        DateCustom startDate = null;
        DateCustom endDate = new DateCustom(DateCustom.getActualDate());
        assertFalse(Validations.isPeriodValid(startDate, endDate));
    }

    @Test
    void isPeriodValid_endDateNull() {
        DateCustom startDate = new DateCustom(DateCustom.getActualDate());
        DateCustom endDate = null;
        assertFalse(Validations.isPeriodValid(startDate, endDate));
    }

    @Test
    void isPeriodValid_bothDatesNull() {
        DateCustom startDate = null;
        DateCustom endDate = null;
        assertFalse(Validations.isPeriodValid(startDate, endDate));
    }

    @Test
    void isPeriodValid_invalid() {
        DateCustom startDate = new DateCustom(DateCustom.getActualDate());
        DateCustom endDate = new DateCustom(DateCustom.getActualDate());
        endDate.setYear(endDate.getYear()-1);
        assertFalse(Validations.isPeriodValid(startDate, endDate));
    }

    @Test
    void isPeriodValid_validAndDifferent() {
        DateCustom startDate = new DateCustom(DateCustom.getActualDate());
        DateCustom endDate = new DateCustom(DateCustom.getActualDate());
        startDate.setYear(endDate.getYear()-1);
        assertTrue(Validations.isPeriodValid(startDate, endDate));
    }

    @Test
    void isPeriodValid_validAndEqual() {
        DateCustom startDate = new DateCustom(DateCustom.getActualDate());
        DateCustom endDate = new DateCustom(DateCustom.getActualDate());
        assertTrue(Validations.isPeriodValid(startDate, endDate));
    }

    @Test
    void isFilePathNewCSV_Null(){
        String filePath = null;
        assertFalse(Validations.isFilePathNewCSV(filePath));
    }

    @Test
    void isFilePathNewCSV_Empty(){
        String filePath = "";
        assertFalse(Validations.isFilePathNewCSV(filePath));
    }

    @Test
    void isFilePathNewCSV_WrongSize(){
        String filePath = "a.bc";
        assertFalse(Validations.isFilePathNewCSV(filePath));
    }

    @Test
    void isFilePathNewCSV_WrongExtension(){
        String filePath = "file.txt";
        assertFalse(Validations.isFilePathNewCSV(filePath));
    }

    @Test
    void isFilePathNewCSV_FileExists(){
        String filePath = "src/test/java/app/domain/model/utils/file.csv";
        assertFalse(Validations.isFilePathNewCSV(filePath));
    }

    @Test
    void isFilePathNewCSV_FileNew(){
        String filePath = "fileNew.csv";
        assertTrue(Validations.isFilePathNewCSV(filePath));
    }

}