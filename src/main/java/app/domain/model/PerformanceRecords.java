package app.domain.model;

import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.Serializable;

public class PerformanceRecords implements Serializable {

    /***
     * SNS user number
     */
    private long snsUserNumber;

    /***
     * Vaccine name
     */
    private String vaccineName;

    /***
     * Dose step related with administration process rules
     */
    private int doseStep;

    /***
     * Lot number of a vaccine
     */
    private String lotNumber;

    /***
     * SNS user vaccine scheduled date: dd/mm/yyyy
     */
    private DateCustom scheduledDate;

    /***
     * SNS user vaccine scheduled timehour: hh:mm
     */
    private TimeHour scheduledTimeHour;

    /***
     * SNS user arrival to vaccination center date: dd/mm/yyyy
     */
    private DateCustom arrivalDate;

    /***
     * SNS user arrival  to vaccination center timehour: hh:mm
     */
    private TimeHour arrivalTimeHour;

    /***
     *  SNS user vaccine administration given by nurse date: dd/mm/yyyy
     */
    private DateCustom nurseAdministrationDate;

    /***
     *  SNS user vaccine administration given by nurse timehour: hh:mm
     */
    private TimeHour nurseAdministrationTimeHour;

    /***
     *  SNS user recovery room leaving date: dd/mm/yyyy
     */
    private DateCustom leavingDate;

    /***
     *  SNS user recovery room leaving timehour: hh:mm
     */
    private TimeHour leavingTimeHour;

    /***
     * Vaccine name by default
     */
    private static final String VACC_NAME_BY_DEFAULT = "#empty_name#";

    /***
     * Dose step by default
     */
    private static final int DOSE_STEP_BY_DEFAULT = -1;

    /***
     * Lot number by default
     */
    private static final String LOT_NUMBER_BY_DEFAULT = "#empty_lotNumber";

    /***
     * DateCustom by default: dd/mm/yyyy
     */
    private static final DateCustom DATE_CUSTOM_BY_DEFAULT = new DateCustom("01/01/1511");

    /***
     * TimeHour by default: hh:mm
     */
    private static final TimeHour TIME_HOUR_BY_DEFAULT = new TimeHour(0, 0);

    /***
     * Partial PerformanceRecords constructor, created when an SNS User schedules the vaccine administration
     *
     * @param snsUserNumber
     * @param scheduledDate
     * @param scheduledTimeHour
     */
    @ExcludeFromJacocoGeneratedReport
    public PerformanceRecords(long snsUserNumber, DateCustom scheduledDate, TimeHour scheduledTimeHour) {
        setSnsUserNumber(snsUserNumber);
        setVaccineName(VACC_NAME_BY_DEFAULT);
        setDoseStep(DOSE_STEP_BY_DEFAULT);
        setLotNumber(LOT_NUMBER_BY_DEFAULT);
        setScheduledDate(scheduledDate);
        setScheduledTimeHour(scheduledTimeHour);
        setArrivalDate(DATE_CUSTOM_BY_DEFAULT);
        setArrivalTimeHour(TIME_HOUR_BY_DEFAULT);
        setNurseAdministrationDate(DATE_CUSTOM_BY_DEFAULT);
        setNurseAdministrationTimeHour(TIME_HOUR_BY_DEFAULT);
        setLeavingDate(DATE_CUSTOM_BY_DEFAULT);
        setLeavingTimeHour(TIME_HOUR_BY_DEFAULT);
    }

    /***
     * Complete PerformanceRecords constructor
     *
     * @param snsUserNumber
     * @param vaccineName
     * @param doseStep
     * @param lotNumber
     * @param scheduledDate
     * @param scheduledTimeHour
     * @param arrivalDate
     * @param arrivalTimeHour
     * @param nurseAdministrationDate
     * @param nurseAdministrationTimeHour
     * @param leavingDate
     * @param leavingTimeHour
     */
    @ExcludeFromJacocoGeneratedReport
    public PerformanceRecords(long snsUserNumber, String vaccineName, int doseStep, String lotNumber,
                              DateCustom scheduledDate, TimeHour scheduledTimeHour, DateCustom arrivalDate,
                              TimeHour arrivalTimeHour, DateCustom nurseAdministrationDate,
                              TimeHour nurseAdministrationTimeHour, DateCustom leavingDate, TimeHour leavingTimeHour) {
        setSnsUserNumber(snsUserNumber);
        setVaccineName(vaccineName);
        setDoseStep(doseStep);
        setLotNumber(lotNumber);
        setScheduledDate(scheduledDate);
        setScheduledTimeHour(scheduledTimeHour);
        setArrivalDate(arrivalDate);
        setArrivalTimeHour(arrivalTimeHour);
        setNurseAdministrationDate(nurseAdministrationDate);
        setNurseAdministrationTimeHour(nurseAdministrationTimeHour);
        setLeavingDate(leavingDate);
        setLeavingTimeHour(leavingTimeHour);
    }

    @ExcludeFromJacocoGeneratedReport
    public PerformanceRecords(PerformanceRecords otherPerformanceRecords) {
        this.snsUserNumber = otherPerformanceRecords.getSnsUserNumber();
        this.vaccineName = otherPerformanceRecords.getVaccineName();
        this.doseStep = otherPerformanceRecords.getDoseStep();
        this.lotNumber = otherPerformanceRecords.getLotNumber();
        this.scheduledDate = otherPerformanceRecords.getScheduledDate();
        this.scheduledTimeHour = otherPerformanceRecords.getScheduledTimeHour();
        this.arrivalDate = otherPerformanceRecords.getArrivalDate();
        this.arrivalTimeHour = otherPerformanceRecords.getArrivalTimeHour();
        this.nurseAdministrationDate = otherPerformanceRecords.getNurseAdministrationDate();
        this.nurseAdministrationTimeHour = otherPerformanceRecords.getNurseAdministrationTimeHour();
        this.leavingDate = otherPerformanceRecords.getLeavingDate();
        this.leavingTimeHour = otherPerformanceRecords.leavingTimeHour;
    }

    /***
     * Get sns user number
     * @return sns user number
     */
    @ExcludeFromJacocoGeneratedReport
    public long getSnsUserNumber() {
        return snsUserNumber;
    }

    /***
     * Get vaccine name
     * @return
     */
    @ExcludeFromJacocoGeneratedReport
    public String getVaccineName() {
        return vaccineName;
    }

    /***
     * Get vaccination dose step
     * @return vaccination dose step
     */
    @ExcludeFromJacocoGeneratedReport
    public int getDoseStep() {
        return doseStep;
    }

    /***
     * Get vaccine lot number
     * @return vaccine lot number
     */
    @ExcludeFromJacocoGeneratedReport
    public String getLotNumber() {
        return lotNumber;
    }

    /***
     * Get vaccine scheduled DateCustom
     * @return vaccine scheduled DateCustom
     */
    @ExcludeFromJacocoGeneratedReport
    public DateCustom getScheduledDate() {
        return new DateCustom(scheduledDate);
    }

    /***
     * Get vaccine Scheduled TimeHour
     * @return vaccine Scheduled TimeHour
     */
    @ExcludeFromJacocoGeneratedReport
    public TimeHour getScheduledTimeHour() {
        return new TimeHour(scheduledTimeHour);
    }

    /***
     * Get sns user arrival DateCustom
     * @return sns user arrival DateCustom
     */
    @ExcludeFromJacocoGeneratedReport
    public DateCustom getArrivalDate() {
        return new DateCustom(arrivalDate);
    }

    /***
     * Get sns user arrival TimeHour
     * @return sns user arrival TimeHour
     */
    @ExcludeFromJacocoGeneratedReport
    public TimeHour getArrivalTimeHour() {
        return new TimeHour(arrivalTimeHour);
    }

    /***
     * Get nurse vaccine administration DateCustom
     * @return nurse vaccine administration DateCustom
     */
    @ExcludeFromJacocoGeneratedReport
    public DateCustom getNurseAdministrationDate() {
        return new DateCustom(nurseAdministrationDate);
    }

    /***
     * Get nurse vaccine administration TimeHour
     * @return nurse vaccine administration TimeHour
     */
    @ExcludeFromJacocoGeneratedReport
    public TimeHour getNurseAdministrationTimeHour() {
        return new TimeHour(nurseAdministrationTimeHour);
    }

    /***
     * Get sns user leaving DateCUstom
     * @return sns user leaving DateCUstom
     */
    @ExcludeFromJacocoGeneratedReport
    public DateCustom getLeavingDate() {
        return new DateCustom(leavingDate);
    }

    /***
     * Get sns user leaving TimeHour
     * @return sns user leaving TimeHour
     */
    @ExcludeFromJacocoGeneratedReport
    public TimeHour getLeavingTimeHour() {
        return new TimeHour(leavingTimeHour);
    }

    /***
     * Set sns user number
     * @param snsUserNumber - sns user number
     */
    @ExcludeFromJacocoGeneratedReport
    public void setSnsUserNumber(long snsUserNumber) {
        this.snsUserNumber = snsUserNumber;
    }

    /***
     *Set vaccine name
     * @param vaccineName - vaccine name
     */
    @ExcludeFromJacocoGeneratedReport
    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    /***
     * Set vaccination dose step
     * @param doseStep - vaccination dose step
     */
    @ExcludeFromJacocoGeneratedReport
    public void setDoseStep(int doseStep) {
        this.doseStep = doseStep;
    }

    /***
     * Set vaccine lot number
     * @param lotNumber - vaccine lot number
     */
    @ExcludeFromJacocoGeneratedReport
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    /***
     * Set vaccine scheduled DateCustom
     * @param scheduledDate - vaccine scheduled DateCustom
     */
    @ExcludeFromJacocoGeneratedReport
    public void setScheduledDate(DateCustom scheduledDate) {
        this.scheduledDate = new DateCustom(scheduledDate);
    }

    /***
     * Set vaccine scheduled TimeHour
     * @param scheduledTimeHour - vaccine scheduled TimeHour
     */
    @ExcludeFromJacocoGeneratedReport
    public void setScheduledTimeHour(TimeHour scheduledTimeHour) {
        this.scheduledTimeHour = new TimeHour(scheduledTimeHour);
    }

    /***
     * Set sns user arrival DateCustom
     * @param arrivalDate - sns user arrival DateCustom
     */
    @ExcludeFromJacocoGeneratedReport
    public void setArrivalDate(DateCustom arrivalDate) {
        this.arrivalDate = new DateCustom(arrivalDate);
    }

    /***
     * Set sns user arrival TimeHour
     * @param arrivalTimeHour - sns user arrival TimeHour
     */
    @ExcludeFromJacocoGeneratedReport
    public void setArrivalTimeHour(TimeHour arrivalTimeHour) {
        this.arrivalTimeHour = new TimeHour(arrivalTimeHour);
    }

    /***
     * Set nurse vaccine administration DateCustom
     * @param nurseAdministrationDate - nurse vaccine administration DateCustom
     */
    @ExcludeFromJacocoGeneratedReport
    public void setNurseAdministrationDate(DateCustom nurseAdministrationDate) {
        this.nurseAdministrationDate = new DateCustom(nurseAdministrationDate);
    }

    /***
     * Set nurse vaccine administration TimeHour
     * @param nurseAdministrationTimeHour - nurse vaccine administration TimeHour
     */
    @ExcludeFromJacocoGeneratedReport
    public void setNurseAdministrationTimeHour(TimeHour nurseAdministrationTimeHour) {
        this.nurseAdministrationTimeHour = new TimeHour(nurseAdministrationTimeHour);
    }

    /***
     * Set sns user leaving DateCustom
     * @param leavingDate - sns user leaving DateCustom
     */
    @ExcludeFromJacocoGeneratedReport
    public void setLeavingDate(DateCustom leavingDate) {
        this.leavingDate = new DateCustom(leavingDate);
    }

    /***
     * Set sns user leaving TimeHour
     * @param leavingTimeHour - sns user leaving TimeHour
     */
    @ExcludeFromJacocoGeneratedReport
    public void setLeavingTimeHour(TimeHour leavingTimeHour) {
        this.leavingTimeHour = new TimeHour(leavingTimeHour);
    }

    /***
     * Gives back the PerformanceRecords description
     *
     * @return all information/attributes about the PerformanceRecords
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("\nSNS User Number: %d\nVaccine Name: %s\nDose Step: %d\nLot Number: %s\nScheduled " +
                             "DateTime: %s %s\nArrival DateTime: %s %s\nNurse Vaccine Administration DateTime: %s " +
                             "%s\nLeaving DateTime: %s %s", getSnsUserNumber(), getVaccineName(), getDoseStep(),
                             getLotNumber(), getScheduledDate().toString(), getScheduledTimeHour().toString(),
                             getArrivalDate().toString(), getArrivalTimeHour().toString(),
                             getNurseAdministrationDate().toString(), getNurseAdministrationTimeHour().toString(),
                             getLeavingDate().toString(), getLeavingTimeHour().toString());
    }


    /***
     * Gives back the PerformanceRecords description
     *
     * @return all information/attributes about the PerformanceRecords
     */

    @ExcludeFromJacocoGeneratedReport
    public String toStringLine() {
        return String.format("\nSNS User Number: %d|Vaccine Name: %s|Dose Step: %d|Lot Number: %s|Scheduled " +
                             "DateTime: %s %s|Arrival DateTime: %s %s|Nurse Vaccine Administration DateTime: %s " +
                             "%s|Leaving DateTime: %s %s", getSnsUserNumber(), getVaccineName(), getDoseStep(),
                             getLotNumber(), getScheduledDate().toString(), getScheduledTimeHour().toString(),
                             getArrivalDate().toString(), getArrivalTimeHour().toString(),
                             getNurseAdministrationDate().toString(), getNurseAdministrationTimeHour().toString(),
                             getLeavingDate().toString(), getLeavingTimeHour().toString());
    }

    /**
     * Compare the PerformanceRecords with the received object
     *
     * @param otherObject the object to compare with PerformanceRecords
     *
     * @return true if the received object represents "otherPerfRecords" like the PerformanceRecords itself, otherwise
     * returns false
     */
    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject) {
            return true;
        }
        if(otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }

        PerformanceRecords otherPerfRecords = (PerformanceRecords)otherObject;
        return this.getSnsUserNumber() == otherPerfRecords.getSnsUserNumber() &&
               this.getVaccineName().equals(otherPerfRecords.getVaccineName()) &&
               this.getDoseStep() == otherPerfRecords.getDoseStep() &&
               this.getLotNumber().equals(otherPerfRecords.getLotNumber()) &&
               this.getScheduledDate().equals(otherPerfRecords.getScheduledDate()) &&
               this.getScheduledTimeHour().equals(otherPerfRecords.getScheduledTimeHour()) &&
               this.getArrivalDate().equals(otherPerfRecords.getArrivalDate()) &&
               this.getArrivalTimeHour().equals(otherPerfRecords.getArrivalTimeHour()) &&
               this.getNurseAdministrationDate().equals(otherPerfRecords.getNurseAdministrationDate()) &&
               this.getNurseAdministrationTimeHour().equals(otherPerfRecords.getNurseAdministrationTimeHour()) &&
               this.getLeavingDate().equals(otherPerfRecords.getLeavingDate()) &&
               this.getLeavingTimeHour().equals(otherPerfRecords.getLeavingTimeHour());
    }

    /***
     *Compares the unique/key attributes from PerformanceRecords with the ones from the received object: snsUserNumber
     * and schedulerDate
     * @param otherPerfRecords the object to compare with PerformanceRecords
     * @return true if the specific attibutes from the received object represents "otherPerfRecords" attributes like
     * the PerformanceRecords itself, otherwise returns false
     */
    public boolean equalsUniqueAttributes(PerformanceRecords otherPerfRecords) {
        if(this == otherPerfRecords) {
            return true;
        }
        if(otherPerfRecords == null) {
            return false;
        }
        return this.getSnsUserNumber() == otherPerfRecords.getSnsUserNumber() &&
               this.getScheduledDate().equals(otherPerfRecords.getScheduledDate());
    }
}
