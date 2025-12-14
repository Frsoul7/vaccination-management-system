package app.domain.model;

import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.domain.model.utils.Validations;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

public class ImportedDataInformation {

    /**
     * SNS user name
     */
    private String name;

    /***
     * SNS user number
     */
    private long snsUserNumber;

    /***
     * Designation of the vaccine type
     */
    private String designation;

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



    public ImportedDataInformation(String name, long snsUserNumber, String designation, String vaccineName,
                                   int doseStep, String lotNumber, DateCustom scheduledDate, TimeHour scheduledTimeHour,
                                   DateCustom arrivalDate, TimeHour arrivalTimeHour, DateCustom nurseAdministrationDate,
                                   TimeHour nurseAdministrationTimeHour, DateCustom leavingDate,
                                   TimeHour leavingTimeHour) {

        setName(name);
        setSnsUserNumber(snsUserNumber);
        setDesignation(designation);
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

    /***
     * Get Name of Sns User
     * @return Name of Sns User
     */
    @ExcludeFromJacocoGeneratedReport
    public String getName() {
        return this.name;
    }

    /***
     * Get the designation of the vaccine type
     * @return designation
     */
    @ExcludeFromJacocoGeneratedReport
    public String getDesignation() {
        return designation;
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
     * Set Name of Sns User
     * @param name  - Name of SNS User
     */
    @ExcludeFromJacocoGeneratedReport
    public void setName(String name) {
        this.name = name;
    }

    /***
     * Set the designation of the vaccine type
     * @param designation - designation of the vaccine type
     */
    @ExcludeFromJacocoGeneratedReport
    public void setDesignation(String designation) {
        this.designation = designation;
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





}
