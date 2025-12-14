package app.dto;

import app.domain.shared.StepDosesPTtoStringConversion;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

public class VaccAdministrationDosageDTO {
    /***
     * Vaccine Type of vaccine : designation
     */
    private String vaccineType;

    /***
     * Designation/name of vaccine
     */
    private String vaccineName;

    /***
     * Lot number of vaccine
     */
    private String vaccineLotNumber;

    /***
     * Vaccine dose step in accordance with administration process and sns user vaccination history
     */
    private int vaccineDoseStep;
    /***
     * Vaccine dosage [mL] in accordance with administration process
     */
    private int vaccineDosage;

    /***
     * VaccAdministrationDosageDT constructor
     *
     * @param vaccineType - Vaccine Type of vaccine : designation
     * @param vaccineName - Designation/name of vaccine
     * @param vaccineLotNumber - Lot number of vaccine
     * @param vaccineDoseStep - Vaccine dose step in accordance with administration process and sns user vaccination
     *                        history
     * @param vaccineDosage - Vaccine dosage [mL] in accordance with administration process
     */
    public VaccAdministrationDosageDTO(String vaccineType, String vaccineName, String vaccineLotNumber,
                                       int vaccineDoseStep, int vaccineDosage) {
        setVaccineType(vaccineType);
        setVaccineName(vaccineName);
        setVaccineLotNumber(vaccineLotNumber);
        setVaccineDoseStep(vaccineDoseStep);
        setVaccineDosage(vaccineDosage);
    }

    /***
     * Get vaccine type
     * @return vaccine type
     */
    @ExcludeFromJacocoGeneratedReport
    public String getVaccineType() {
        return vaccineType;
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
     * Get vaccine lot number
     * @return vaccine lot number
     */
    @ExcludeFromJacocoGeneratedReport
    public String getVaccineLotNumber() {
        return vaccineLotNumber;
    }

    /***
     * Get vaccine dose step
     * @return vaccine dose step
     */
    @ExcludeFromJacocoGeneratedReport
    public int getVaccineDoseStep() {
        return vaccineDoseStep;
    }

    /***
     * Get vaccine dosage
     * @return vaccine dosage
     */
    @ExcludeFromJacocoGeneratedReport
    public int getVaccineDosage() {
        return vaccineDosage;
    }

    /***
     * Set vaccine type
     * @param vaccineType - vaccine type
     */
    @ExcludeFromJacocoGeneratedReport
    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
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
     * Set vaccine lot number
     * @param vaccineLotNumber - vaccine lot number
     */
    @ExcludeFromJacocoGeneratedReport
    public void setVaccineLotNumber(String vaccineLotNumber) {
        this.vaccineLotNumber = vaccineLotNumber;
    }

    /***
     * Set vaccine dose step
     * @param vaccineDoseStep - vaccine dose step
     */
    @ExcludeFromJacocoGeneratedReport
    public void setVaccineDoseStep(int vaccineDoseStep) {
        this.vaccineDoseStep = vaccineDoseStep;
    }

    /***
     * Set vaccine dosage
     * @param vaccineDosage - vaccine dosage
     */
    @ExcludeFromJacocoGeneratedReport
    public void setVaccineDosage(int vaccineDosage) {
        this.vaccineDosage = vaccineDosage;
    }

    /***
     * Gives back some vaccine attributes and vaccine dosage information
     * @return some vaccine attributes and vaccine dosage information
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("Vaccine type: %s\nVaccine name: %s\nVaccine lot number: %s\nVaccine dose step: " +
                             "%s\nVaccine dosage: %d [mL]", this.getVaccineType(), this.getVaccineName(),
                             this.getVaccineLotNumber(),
                             StepDosesPTtoStringConversion.getPtDescriptionByStepDoseIndex(this.getVaccineDoseStep()),
                             this.getVaccineDosage());
    }
}
