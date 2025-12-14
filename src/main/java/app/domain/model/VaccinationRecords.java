package app.domain.model;

import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.Serializable;

public class VaccinationRecords implements Serializable {
    /***
     * Vaccine name
     */
    private String vaccineName;
    /***
     * Dose step
     */
    private int doseStep;
    /***
     * Vaccine lot number
     */
    private String vaccineLotNumber;
    /***
     * Nurse vaccine administration date: dd/mm/yyyy
     */
    private DateCustom nurseAdministrationDate;
    /***
     * Nurse vaccine administration timeHour: hh:mm
     */
    private TimeHour nurseAdministrationTimeHour;

    /***
     * Complete VaccinationRecords constructor
     * @param vaccineName - vaccine name
     * @param doseStep - vaccine dose step (related to administration process plan)
     * @param vaccineLotNumber - vaccine lot number
     * @param nurseAdministrationDate - nurse vaccine administration date
     * @param nurseAdministrationTimeHour - nurse vaccine administration hour
     */
    public VaccinationRecords(String vaccineName, int doseStep, String vaccineLotNumber,
                              DateCustom nurseAdministrationDate, TimeHour nurseAdministrationTimeHour) {
        setVaccineName(vaccineName);
        setDoseStep(doseStep);
        setVaccineLotNumber(vaccineLotNumber);
        setNurseAdministrationDate(nurseAdministrationDate);
        setNurseAdministrationTimeHour(nurseAdministrationTimeHour);
    }

    /***
     * Complete VaccinationRecords constructor where it is passed an obj from the same type
     * @param otherObjVaccinationRecords - other VaccinationRecords obj
     */
    public VaccinationRecords(VaccinationRecords otherObjVaccinationRecords) {
        setVaccineName(otherObjVaccinationRecords.getVaccineName());
        setDoseStep(otherObjVaccinationRecords.getDoseStep());
        setVaccineLotNumber(otherObjVaccinationRecords.getVaccineLotNumber());
        setNurseAdministrationDate(otherObjVaccinationRecords.getNurseAdministrationDate());
        setNurseAdministrationTimeHour(otherObjVaccinationRecords.getNurseAdministrationTimeHour());
    }

    /***
     * Get vaccine name
     * @return vaccine name
     */
    @ExcludeFromJacocoGeneratedReport
    public String getVaccineName() {
        return vaccineName;
    }

    /***
     * Get dose step
     * @return dose step
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
    public String getVaccineLotNumber() {
        return vaccineLotNumber;
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
     * Set vaccine name
     * @param vaccineName - vaccine name
     */
    @ExcludeFromJacocoGeneratedReport
    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    /***
     * Set dose step
     * @param doseStep - dose step
     */
    @ExcludeFromJacocoGeneratedReport
    public void setDoseStep(int doseStep) {
        this.doseStep = doseStep;
    }

    /***
     * Set vaccine lot number
     * @param vaccineLotNumber - vaccine lot number
     */
    @ExcludeFromJacocoGeneratedReport
    public void setVaccineLotNumber(String vaccineLotNumber) {
        this.vaccineLotNumber = vaccineLotNumber;
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
     * Gives back the VaccinationRecords description
     * @return all information/attributes about the VaccinationRecords
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format(
                "\nVaccine Name: %s\nDose Step: %d\nVaccine Lot Number: %s\nNurse Administration " + "DateTime: %s %s",
                getVaccineName(), getDoseStep(), getVaccineLotNumber(), getNurseAdministrationDate().toString(),
                getNurseAdministrationTimeHour().toString());
    }

    /**
     * Compare the VaccinationRecords with the received object
     *
     * @param otherObject the object to compare with VaccinationRecords
     *
     * @return true if the received object represents "otherVaccRecords" like the VaccinationRecords itself, otherwise
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

        VaccinationRecords otherVaccRecords = (VaccinationRecords)otherObject;
        return this.getVaccineName().equals(otherVaccRecords.getVaccineName()) &&
               this.getDoseStep() == otherVaccRecords.getDoseStep() &&
               this.getVaccineLotNumber().equals(otherVaccRecords.getVaccineLotNumber()) &&
               this.getNurseAdministrationDate().equals(otherVaccRecords.getNurseAdministrationDate()) &&
               this.getNurseAdministrationTimeHour().equals(otherVaccRecords.getNurseAdministrationTimeHour());
    }

    /**
     * Get next expected/potential vaccine dose step
     * @return next expected/potential vaccine dose step
     */
    public int getNextDoseStep() {
        return this.getDoseStep()+1;
    }
}
