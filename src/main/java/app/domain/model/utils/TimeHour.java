package app.domain.model.utils;

import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TimeHour implements Serializable {

    private int hour;
    private int minutes;

    @ExcludeFromJacocoGeneratedReport
    public TimeHour(int hour, int minutes) {
        setHour(hour);
        setMinutes(minutes);
    }

    @ExcludeFromJacocoGeneratedReport
    public TimeHour(TimeHour otherTime) {
        this.hour = otherTime.hour;
        this.minutes = otherTime.minutes;
    }

    /***
     * TimeHour constructor where it's passed a String value with "hh:mm am" format
     * @param time - String value with "hh:mm am" format
     */
    @ExcludeFromJacocoGeneratedReport
    public TimeHour(String time, String format) {
        if(format.equals("24")) {
            setHour(getHourIn24Format(time));
            setMinutes(getMinutesIn24Format(time));
        } else if(format.equals("12")) {
            setHour(TimeHour.hourTo24Format(time));
            setMinutes(TimeHour.minutes(time));
        }
    }


    @ExcludeFromJacocoGeneratedReport
    public int getHour() {
        return hour;
    }

    @ExcludeFromJacocoGeneratedReport
    public int getMinutes() {
        return minutes;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setHour(int hour) {

        if(hour < 0 || hour > 24) {
            throw new IllegalArgumentException("Invalid Hour");
        }
        this.hour = hour;

    }

    @ExcludeFromJacocoGeneratedReport
    public void setMinutes(int minutes) {
        if(minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Invalid Minutes");
        }
        this.minutes = minutes;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setTimeHour(int hour, int minutes) {
        setHour(hour);
        setMinutes(minutes);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("%02d:%02d", this.hour, this.minutes);
    }

    /**
     * Compare the TimeHour with the received object
     *
     * @param otherObject the object to compare with TimeHour
     *
     * @return true if the received object represents "otherTimeHour" like the TimeHour itself. Otherwise returns false
     */
    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject) {
            return true;
        }
        if(otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }

        TimeHour otherTimeHour = (TimeHour)otherObject;
        return hour == otherTimeHour.hour && minutes == otherTimeHour.minutes;
    }

    @ExcludeFromJacocoGeneratedReport
    public int diffTimeInMinutes(TimeHour otherTime) {

        if(this.getHour() < otherTime.getHour()) {
            return Math.abs(((otherTime.getHour() - this.getHour()) * 60 - this.getMinutes() + otherTime.getMinutes()));
        } else {
            return Math.abs(((this.getHour() - otherTime.getHour()) * 60 - otherTime.getMinutes() + this.getMinutes()));
        }

    }

    public int calculatesMinutes() {
        return Math.abs(hour * 60 + minutes);
    }

    public int calculateDifferenceInMinutes(TimeHour otherTimeHour) {
        return Math.abs(calculatesMinutes() - otherTimeHour.calculatesMinutes());
    }

    public boolean isBigger(int hour, int minutes) {
        TimeHour otherTimeHour = new TimeHour(hour, minutes);
        return calculatesMinutes() > otherTimeHour.calculatesMinutes();
    }

    public boolean isBigger(TimeHour otherTimeHour) {
        return calculatesMinutes() > otherTimeHour.calculatesMinutes();
    }

    /**
     * Calculate hour in 24 hours format
     *
     * @param hour -  to change
     *
     * @return hour in the 24 format
     */
    public static int hourTo24Format(String hour) throws IllegalArgumentException {
        // remove the spaces in beginning and end
        hour = hour.trim();
        String[] hourElements = hour.split(":| ");
        int hourInt = Integer.parseInt(hourElements[0]);
        String indicator = hourElements[2];

        if((indicator.compareToIgnoreCase("am") == 0)) {
            if(hourInt == 12) {
                return 0;
            } else {
                return hourInt;
            }
        } else {
            if(hourInt == 12) {
                return hourInt;
            } else {
                return hourInt + 12;
            }
        }
    }


    @ExcludeFromJacocoGeneratedReport
    public static int getHourIn24Format(String time) {
        // remove the spaces in beginning and end
        time = time.trim();
        String[] hourElements = time.split(":| ");
        int hourInt = Integer.parseInt(hourElements[0]);
        return hourInt;
    }

    @ExcludeFromJacocoGeneratedReport
    public static int getMinutesIn24Format(String time) {
        // remove the spaces in beginning and end
        time = time.trim();
        String[] hourElements = time.split(":| ");
        int minutesInt = Integer.parseInt(hourElements[1]);
        return minutesInt;
    }

    /**
     * Return minutes
     *
     * @param hour -  hour to validate
     *
     * @return minutes
     */
    public static int minutes(String hour) throws IllegalArgumentException {
        // remove the spaces in beginning and end
        int minutesInt;
        hour = hour.trim();
        String[] hourElements = hour.split(":| ");
        return minutesInt = Integer.parseInt(hourElements[1]);

    }

    @ExcludeFromJacocoGeneratedReport
    public static TimeHour getActualTime() {
        LocalDateTime now = LocalDateTime.now();
        return new TimeHour(now.getHour(), now.getMinute());
    }

}
