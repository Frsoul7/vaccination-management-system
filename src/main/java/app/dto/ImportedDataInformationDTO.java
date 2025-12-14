package app.dto;

import app.domain.model.ImportedDataInformation;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

public class ImportedDataInformationDTO {

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


    public ImportedDataInformationDTO(String name, long snsUserNumber, String designation, String vaccineName,
                                      int doseStep, String lotNumber, DateCustom scheduledDate,
                                      TimeHour scheduledTimeHour, DateCustom arrivalDate, TimeHour arrivalTimeHour,
                                      DateCustom nurseAdministrationDate, TimeHour nurseAdministrationTimeHour,
                                      DateCustom leavingDate, TimeHour leavingTimeHour) {

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

    public ImportedDataInformationDTO(ImportedDataInformation obj) {

        this.name = obj.getName();
        this.snsUserNumber = obj.getSnsUserNumber();
        this.designation = obj.getDesignation();
        this.vaccineName = obj.getVaccineName();
        doseStep = obj.getDoseStep();
        lotNumber = obj.getLotNumber();
        scheduledDate = obj.getScheduledDate();
        scheduledTimeHour = obj.getScheduledTimeHour();
        arrivalDate = obj.getArrivalDate();
        arrivalTimeHour = obj.getArrivalTimeHour();
        nurseAdministrationDate = obj.getNurseAdministrationDate();
        nurseAdministrationTimeHour = obj.getNurseAdministrationTimeHour();
        leavingDate = obj.getLeavingDate();
        leavingTimeHour = obj.getLeavingTimeHour();
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


    /***
     * Gives back the Imported data description
     *
     * @return all information/attributes about the imported data
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("\nSNS User Name: %s | SNS User Number: %d | Vaccine type: %s | Vaccine Name: %s | Dose " +
                             "Step: %d | Lot " + "Number: " + "%s | " + "Scheduled " +
                             "DateTime: %s %s | Arrival DateTime: %s %s | Nurse Vaccine Administration DateTime: %s " +
                             "%s | Leaving DateTime: %s %s", this.name, this.snsUserNumber, this.designation,
                             this.vaccineName, (this.doseStep), this.lotNumber, this.scheduledDate.toString(),
                             this.scheduledTimeHour.toString(), this.arrivalDate.toString(),
                             this.arrivalTimeHour.toString(), this.nurseAdministrationDate.toString(),
                             this.nurseAdministrationTimeHour.toString(), this.leavingDate.toString(),
                             this.leavingTimeHour.toString());
    }

    public String getName() {
        return name;
    }

    public long getSnsUserNumber() {
        return snsUserNumber;
    }

    public String getDesignation() {
        return designation;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public int getDoseStep() {
        return doseStep;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public DateCustom getScheduledDate() {
        return scheduledDate;
    }

    public TimeHour getScheduledTimeHour() {
        return scheduledTimeHour;
    }

    public DateCustom getArrivalDate() {
        return arrivalDate;
    }

    public TimeHour getArrivalTimeHour() {
        return arrivalTimeHour;
    }

    public DateCustom getNurseAdministrationDate() {
        return nurseAdministrationDate;
    }

    public TimeHour getNurseAdministrationTimeHour() {
        return nurseAdministrationTimeHour;
    }

    public DateCustom getLeavingDate() {
        return leavingDate;
    }

    public TimeHour getLeavingTimeHour() {
        return leavingTimeHour;
    }

}
