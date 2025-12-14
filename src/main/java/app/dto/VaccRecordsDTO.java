package app.dto;

import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.domain.shared.StepDosesPTtoStringConversion;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

public class VaccRecordsDTO {
    /***
     * Vaccine name
     */
    private String vaccineName;
    /***
     * Vaccine dose step
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
     * VaccRecordsDTO constructor
     *
     * @param vaccineName - vaccine name
     * @param doseStep - vaccine dose step (related to administration process plan)
     * @param vaccineLotNumber - vaccine lot number
     * @param nurseAdministrationDate - nurse vaccine administration date
     * @param nurseAdministrationTimeHour - nurse vaccine administration hour
     */
    public VaccRecordsDTO(String vaccineName, int doseStep, String vaccineLotNumber, DateCustom nurseAdministrationDate,
                          TimeHour nurseAdministrationTimeHour) {
        setVaccineName(vaccineName);
        setDoseStep(doseStep);
        setVaccineLotNumber(vaccineLotNumber);
        setNurseAdministrationDate(nurseAdministrationDate);
        setNurseAdministrationTimeHour(nurseAdministrationTimeHour);
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
     * Get vaccine dose step
     * @return vaccine dose step
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
     * Set vaccine dose step
     * @param doseStep - vaccine dose step
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
                "Vaccine Name: %s\nDose Step: %s\nVaccine Lot Number: %s\nNurse Administration " + "DateTime: %s %s",
                this.getVaccineName(),
                StepDosesPTtoStringConversion.getPtDescriptionByStepDoseIndex(this.getDoseStep()),
                                                                              this.getVaccineLotNumber(), this.getNurseAdministrationDate().toString(),
                                                                              this.getNurseAdministrationTimeHour().toString());
    }
}
