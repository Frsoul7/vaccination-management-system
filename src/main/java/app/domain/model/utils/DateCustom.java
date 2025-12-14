package app.domain.model.utils;

import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;


public class DateCustom implements Comparable<DateCustom>, Serializable {

    /**
     * Year of date
     */
    private int year;

    /**
     * Month of date
     */
    private int month;

    /**
     * Day of date
     */
    private int day;

    /**
     * Year by omission
     */
    private static final int YEAR_BY_OMISSION = 1;

    /**
     * Month by omission
     */
    private static final int MONTH_BY_OMISSION = 1;

    /**
     * Day by omission
     */
    private static final int DAY_BY_OMISSION = 1;

    /**
     * Number of days for each month
     */
    private static final int[] daysByMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    /**
     * Build an object of type date
     *
     * @param year  year of Date.
     * @param month month of Date.
     * @param day   day of Date.
     */
    @ExcludeFromJacocoGeneratedReport
    public DateCustom(int day, int month, int year) {
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    /**
     * Build an object of type date with values by omission
     */
    @ExcludeFromJacocoGeneratedReport
    public DateCustom() {
        year = YEAR_BY_OMISSION;
        month = MONTH_BY_OMISSION;
        day = DAY_BY_OMISSION;
    }


    /**
     * Converts a string in a DateCustom type with dd/mm/yyyy
     *
     * @param source
     *
     * @throws ArrayIndexOutOfBoundsException
     */
    @ExcludeFromJacocoGeneratedReport
    public DateCustom(String source) {
        int[] data = splitString(source);
        try {
            setDateCustom(data[0], data[1], data[2]);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Invalid Date");
        }
    }


    /**
     * Build an object of type date by copy
     *
     * @param otherDateCustom date to copy
     */
    @ExcludeFromJacocoGeneratedReport
    public DateCustom(DateCustom otherDateCustom) {
        year = otherDateCustom.year;
        month = otherDateCustom.month;
        day = otherDateCustom.day;
    }

    /**
     * Return year of Date
     *
     * @return year of Date
     */
    @ExcludeFromJacocoGeneratedReport
    public int getYear() {
        return year;
    }

    /**
     * Return month of Date
     *
     * @return month of date.
     */
    @ExcludeFromJacocoGeneratedReport
    public int getMonth() {
        return month;
    }

    /**
     * Return day of Date
     *
     * @return day of date.
     */
    @ExcludeFromJacocoGeneratedReport
    public int getDay() {
        return day;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setYear(int year) {

        if(year < 1500) {
            throw new IllegalArgumentException("Invalid Year");
        }
        this.year = year;

    }

    @ExcludeFromJacocoGeneratedReport
    public void setMonth(int month) {

        if(month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid Month");
        }

        this.month = month;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setDay(int day) {

        if(day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid Day");
        }
        this.day = day;
    }

    /**
     * Change year, month and day
     *
     * @param year  o novo year da data.
     * @param month o novo mês da data.
     * @param day   o novo day da data.
     */
    @ExcludeFromJacocoGeneratedReport
    public void setDateCustom(int day, int month, int year) {

        setYear(year);
        setMonth(month);
        setDay(day);

        if(day > daysByMonth[month] || (month == 2 && day == 29 && !isLeapYear(year))) {
            throw new IllegalArgumentException("Invalid Date");
        }
    }

    /***
     *
     * @return the date in format day/month/year
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("%02d/%02d/%04d", this.getDay(), this.getMonth(), this.getYear());
    }


    /**
     * Return date in format:%02d/%02d/%04d.
     *
     * @return Date
     */
    public String toDayMonthYearFormat() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }

    /**
     * Compares the date with another receive object
     *
     * @param anotherObject object to compare with date
     *
     * @return true if the object have the same date
     */
    @Override
    public boolean equals(Object anotherObject) {
        if(this == anotherObject) {
            return true;
        }
        if(anotherObject == null || getClass() != anotherObject.getClass()) {
            return false;
        }
        DateCustom otherDateCustom = (DateCustom)anotherObject;
        return year == otherDateCustom.year && month == otherDateCustom.month && day == otherDateCustom.day;
    }

    /**
     * Compares the date with another date received by parameter
     *
     * @param otherDateCustom date to compare
     *
     * @return value 0 if otherDate is equals to the date; value -1 if otherDate is after the date; value 1 if otherDate
     * is before the date.
     */
    @Override
    public int compareTo(DateCustom otherDateCustom) {
        return (otherDateCustom.isBigger(this)) ? -1 : (isBigger(otherDateCustom)) ? 1 : 0;
    }


    /**
     * Returns true if the date is bigger than the date received by parameter
     *
     * @param otherDateCustom date to compare
     *
     * @return true if the date is bigger than the date received by parameter
     */
    public boolean isBigger(DateCustom otherDateCustom) {
        int totalDays = countingDays();
        int totalDays1 = otherDateCustom.countingDays();

        return totalDays > totalDays1;
    }

    /**
     * Returns the difference of days between the date and the received date by parameter
     *
     * @param otherDateCustom the date to be compared with other date in way to obtain the number of days between
     *                        dates.
     *
     * @return difference of days between the date and the received date by parameter
     */
    public int differenceOfDays(DateCustom otherDateCustom) {
        int totalDays = countingDays();
        int totalDays1 = otherDateCustom.countingDays();

        return Math.abs(totalDays - totalDays1);
    }

    public int differenceOfYears() {
        int totalYears = this.countingYears(getActualDate());
        return Math.abs(totalYears);
    }

    public int countingYears(DateCustom otherDate) {

        LocalDate date1 = LocalDate.of(this.getYear(), this.getMonth(), this.getDay());
        LocalDate date2 = LocalDate.of(otherDate.getYear(), otherDate.getMonth(), otherDate.getDay());

        int difYears;
        if (date2.isAfter(date1)){
            difYears = Period.between(date1, date2).getYears();
        } else {
            difYears = Period.between(date2, date1).getYears();
        }

        return difYears;
    }


    /**
     * Returns the number of days since the day 1/1/1 until the date
     *
     * @return the number of days since the day 1/1/1 until the date
     */
    @ExcludeFromJacocoGeneratedReport
    public int countingDays() {
        int totalDays = 0;

        for(int i = 1; i < year; i++) {
            totalDays += isLeapYear(i) ? 366 : 365;
        }
        for(int i = 1; i < month; i++) {
            totalDays += daysByMonth[i];
        }
        totalDays += (isLeapYear(year) && month > 2) ? 1 : 0;
        totalDays += day;

        return totalDays;
    }


    public int[] splitString(String source) throws IllegalArgumentException {

        if(source == null || source.isEmpty()) {
            throw new IllegalArgumentException("Invalid date");
        }

        String[] dateSourceDecomposed = source.split("/");
        int[] data = new int[dateSourceDecomposed.length];

        for(int i = 0; i < dateSourceDecomposed.length; i++) {

            try {
                data[i] = Integer.parseInt(dateSourceDecomposed[i]);
            }
            catch(NumberFormatException e) {
                throw new IllegalArgumentException("Invalid date");
            }
        }
        return data;
    }

    /***
     * Method that get the actual LocalDateTime and converts to DateCustom type
     * @return DateCustom actual date dd/mm/yyyy
     */
    @ExcludeFromJacocoGeneratedReport
    public static DateCustom getActualDate() {
        LocalDateTime today = LocalDateTime.now();
        return new DateCustom(today.getDayOfMonth(), today.getMonthValue(), today.getYear());
    }

    /**
     * Returns true if the year received by parameter is a leap year
     *
     * @param year year to validate
     *
     * @return eturns true if the year received by parameter is a leap year
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public void addDays(int numeroDias) {
        try {
            String aux = this.toDayMonthYearFormat();
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(aux));
            cal.add(Calendar.DATE, numeroDias);
            DateCustom dateAaux = new DateCustom(sdf.format(cal.getTime()));
            this.setYear(dateAaux.getYear());
            this.setMonth(dateAaux.getMonth());
            this.setDay(dateAaux.getDay());
        }
        catch(ParseException e) {

        }
    }

    /***
     *
     * @param originalDate          String - Original date
     * @param originalDateFormat    String - Format of original date
     * @return DateCustom - Date converted to finalDateFormat
     */
    public static DateCustom convertDateFormat(String originalDate, String originalDateFormat)
            throws IllegalArgumentException {
        if(originalDate == null || originalDateFormat == null) {
            throw new IllegalArgumentException("Invalid argument received");
        }
        if(originalDate.isEmpty() || originalDateFormat.isEmpty()) {
            throw new IllegalArgumentException("Invalid argument received");
        }
        String finalDateFormat = "dd/MM/yyyy";
        try {
            SimpleDateFormat sdfOriginalDate = new SimpleDateFormat(originalDateFormat);
            sdfOriginalDate.setLenient(false);
            Date origDate = sdfOriginalDate.parse(originalDate);
            SimpleDateFormat sdfFinalDate = new SimpleDateFormat(finalDateFormat);
            sdfFinalDate.setLenient(false);
            return new DateCustom(sdfFinalDate.format(origDate));
        }
        catch(ParseException e) {
            throw new IllegalArgumentException("Invalid arguments received.");
        }
    }

}

