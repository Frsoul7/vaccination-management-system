package app.domain.model.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateCustomTest {

    private static final String stringNull = null;
    private static final String stringEmpty = "";
    private static DateCustom dataHoje;

    @BeforeAll
    static void setUp() {
        dataHoje = DateCustom.getActualDate();
    }

    @Test
    void splitString_Null() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            int[] result = dataHoje.splitString(stringNull);
        });
    }

    @Test
    void splitString_Empty() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            int[] result = dataHoje.splitString(stringEmpty);
        });
    }

    @Test
    void splitString_DateNotNumeric() {
        String dateToSplit = "A/B/C";
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            int[] result = dataHoje.splitString(dateToSplit);
        });
    }

    @Test
    void splitString_DateValid() {
        try {
            String dateToSplit = "1/2/1980";
            int[] dataSplited = dataHoje.splitString(dateToSplit);
        }
        catch(IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    void isLeapYear_Ok() {
        int year = 2000;
        assertTrue(DateCustom.isLeapYear(year));
    }

    @Test
    void isLeapYear_No() {
        int year = 2001;
        assertFalse(DateCustom.isLeapYear(year));
    }

    @Test
    void isBigger_Yes() {
        DateCustom dateToValidate = new DateCustom(DateCustom.getActualDate());
        dateToValidate.setYear(dateToValidate.getYear() - 1);
        assertTrue(dataHoje.isBigger(dateToValidate));
    }

    @Test
    void isBigger_No() {
        DateCustom dateToValidate = new DateCustom(DateCustom.getActualDate());
        dateToValidate.setYear(dateToValidate.getYear() + 1);
        assertFalse(dataHoje.isBigger(dateToValidate));
    }

    @Test
    void equals_Null() {
        DateCustom dateToValidate = null;
        assertFalse(dataHoje.equals(dateToValidate));
    }

    @Test
    void equals_SameObject() {
        DateCustom dateToValidate = dataHoje;
        assertTrue(dataHoje.equals(dateToValidate));
    }

    @Test
    void equals_DifferentType() {
        String dateToValidate = dataHoje.toString();
        assertFalse(dataHoje.equals(dateToValidate));
    }

    @Test
    void equals_DifferentYear() {
        DateCustom dateToValidate = new DateCustom(dataHoje);
        dateToValidate.setYear(dateToValidate.getYear() + 1);
        assertFalse(dataHoje.equals(dateToValidate));
    }

    @Test
    void equals_DifferentMonth() {
        DateCustom dateToValidate = new DateCustom(dataHoje);
        if(dateToValidate.getMonth() != 12) {
            dateToValidate.setMonth(dateToValidate.getMonth() + 1);
        } else {
            dateToValidate.setMonth(1);
        }
        assertFalse(dataHoje.equals(dateToValidate));
    }

    @Test
    void equals_DifferentDay() {
        DateCustom dateToValidate = new DateCustom(dataHoje);
        if(dateToValidate.getDay() != 15) {
            dateToValidate.setDay(15);
        } else {
            dateToValidate.setDay(16);
        }
        assertFalse(dataHoje.equals(dateToValidate));
    }

    @Test
    void equals_SameDate() {
        DateCustom dateToValidate = new DateCustom(dataHoje);
        assertTrue(dataHoje.equals(dateToValidate));
    }

    @Test
    void compareTo_Less() {
        DateCustom dateToValidate = new DateCustom(dataHoje);
        dateToValidate.setYear(dateToValidate.getYear() - 1);
        int expectedResult = 1;
        assertEquals(dataHoje.compareTo(dateToValidate), expectedResult);
    }

    @Test
    void compareTo_Bigger() {
        DateCustom dateToValidate = new DateCustom(dataHoje);
        dateToValidate.setYear(dateToValidate.getYear() + 1);
        int expectedResult = -1;
        assertEquals(dataHoje.compareTo(dateToValidate), expectedResult);
    }

    @Test
    void addDays_Positive() {
        DateCustom dateToValidate = new DateCustom("05/01/2000");
        DateCustom expectedResult = new DateCustom("06/01/2000");
        dateToValidate.addDays(1);
        assertTrue(dateToValidate.equals(expectedResult));
    }

    @Test
    void addDays_Negative() {
        DateCustom dateToValidate = new DateCustom("05/01/2000");
        DateCustom expectedResult = new DateCustom("04/01/2000");
        dateToValidate.addDays(-1);
        assertTrue(dateToValidate.equals(expectedResult));
    }

    @Test
    void addDays_Zero() {
        DateCustom dateToValidate = new DateCustom("05/01/2000");
        DateCustom expectedResult = new DateCustom("05/01/2000");
        dateToValidate.addDays(0);
        assertTrue(dateToValidate.equals(expectedResult));
    }

    @Test
    void convertDateFormat_AllNull() {
        String stringNull = null;
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            DateCustom result = DateCustom.convertDateFormat(stringNull, stringNull);
        });
    }

    @Test
    void convertDateFormat_AllEmpty() {
        String stringEmpty = "";
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            DateCustom result = DateCustom.convertDateFormat(stringEmpty, stringEmpty);
        });
    }


    @Test
    void convertDateFormat_FormatOrigNull() {
        String stringNull = null;
        String originalDate = "20/01/2022";
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            DateCustom result = DateCustom.convertDateFormat(originalDate, stringNull);
        });
    }

    @Test
    void convertDateFormat_FormatOrigEmpty() {
        String stringNull = "";
        String originalDate = "20/01/2022";
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            DateCustom result = DateCustom.convertDateFormat(originalDate, stringNull);
        });
    }

    @Test
    void convertDateFormat_OrigDateNull() {
        String stringNull = null;
        String dateFormatOrig = "dd/MM/yyyy";
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            DateCustom result = DateCustom.convertDateFormat(stringNull, dateFormatOrig);
        });
    }

    @Test
    void convertDateFormat_OrigDateEmpty() {
        String stringNull = "";
        String dateFormatOrig = "dd/MM/yyyy";
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            DateCustom result = DateCustom.convertDateFormat(stringEmpty, dateFormatOrig);
        });
    }

    @Test
    void convertDateFormat_Ok() {
        String dateOrig = "01/20/2001";
        DateCustom expectedDate = new DateCustom(20, 1, 2001);
        String dateFormatOrig = "MM/dd/yyyy";
        DateCustom result = DateCustom.convertDateFormat(dateOrig, dateFormatOrig);
        assertTrue(result.equals(expectedDate));
    }

    @Test
    void differenceOfDays_Equals() {
        DateCustom date1 = new DateCustom(20, 1, 2000);
        DateCustom date2 = new DateCustom(20, 1, 2000);
        assertEquals(date1.differenceOfDays(date2), 0);
    }

    @Test
    void differenceOfDays_Bigger() {
        DateCustom date1 = new DateCustom(20, 1, 2000);
        DateCustom date2 = new DateCustom(21, 1, 2000);
        assertEquals(date1.differenceOfDays(date2), 1);
    }

    @Test
    void differenceOfDays_Lower() {
        DateCustom date1 = new DateCustom(21, 1, 2000);
        DateCustom date2 = new DateCustom(20, 1, 2000);
        assertEquals(date1.differenceOfDays(date2), 1);
    }

    @Test
    void differenceOfYears_Zero() {
        DateCustom dateToValidate = new DateCustom(DateCustom.getActualDate());
        if(dateToValidate.getMonth() == 12) {
            dateToValidate.setMonth(2);
            dateToValidate.setYear(dateToValidate.getYear() + 1);
        } else {
            dateToValidate.setMonth(dateToValidate.getMonth() + 1);
        }
        assertEquals(dateToValidate.differenceOfYears(), 0);
    }

    @Test
    void differenceOfYears_Lower() {
        DateCustom dateToValidate = new DateCustom(DateCustom.getActualDate());
        dateToValidate.setYear(dateToValidate.getYear() - 2);
        if(dateToValidate.getMonth() == 2) {
            dateToValidate.setMonth(10);
            dateToValidate.setYear(dateToValidate.getYear() - 1);
        } else {
            dateToValidate.setMonth(dateToValidate.getMonth() - 2);
        }
        assertEquals(dateToValidate.differenceOfYears(), 2);
    }

    @Test
    void differenceOfYears_LowerTwo() {
        DateCustom dateToValidate = new DateCustom(DateCustom.getActualDate());
        dateToValidate.setYear(dateToValidate.getYear() - 2);
        if(dateToValidate.getMonth() == 12) {
            dateToValidate.setMonth(2);
            dateToValidate.setYear(dateToValidate.getYear() + 1);
        } else {
            dateToValidate.setMonth(dateToValidate.getMonth() + 1);
        }
        assertEquals(dateToValidate.differenceOfYears(), 1);
    }

    @Test
    void differenceOfYears_Bigger() {
        DateCustom dateToValidate = new DateCustom(DateCustom.getActualDate());
        dateToValidate.setYear(dateToValidate.getYear() + 4);
        if(dateToValidate.getMonth() == 1) {
            dateToValidate.setMonth(10);
            dateToValidate.setYear(dateToValidate.getYear() - 1);
        } else {
            dateToValidate.setMonth(dateToValidate.getMonth() - 1);
        }
        assertEquals(dateToValidate.differenceOfYears(), 3);
    }


}