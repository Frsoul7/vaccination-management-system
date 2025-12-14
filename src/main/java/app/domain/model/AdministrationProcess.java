package app.domain.model;

import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Validations;
import app.interfaces.Constants;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.Serializable;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class AdministrationProcess implements Constants, Serializable {

    /**
     * Age groups of administration process
     */
    private String ageGroup;

    /**
     * Number of doses per age group
     */
    private int numberOfDoses;

    /**
     * Vaccine dosage of administration process could vary in accordance with the number of doses
     */
    private int[] vaccineDosage;

    /**
     * Time interval between doses of administration process, could vary in accordance with the number of doses
     */
    private int[] timeIntervalBetweenDoses;

    @ExcludeFromJacocoGeneratedReport
    public AdministrationProcess(String ageGroup, int numberOfDoses, int[] vaccineDosage,
                                 int[] timeIntervalBetweenDoses, String vaccineAgeGroup)
            throws OperationCanceledByUserException {
        this.setAgeGroup(ageGroup, vaccineAgeGroup);
        this.setNumberOfDoses(numberOfDoses);
        this.setVaccineDosage(vaccineDosage);
        if(timeIntervalBetweenDoses.length == 0) {
            this.timeIntervalBetweenDoses = new int[] {0};
        } else {
            this.setTimeIntervalBetweenDoses(timeIntervalBetweenDoses);
        }
    }

    public AdministrationProcess(AdministrationProcess otherAdministrationProcess)
            throws OperationCanceledByUserException {
        this.ageGroup = otherAdministrationProcess.getAgeGroup();
        this.numberOfDoses = otherAdministrationProcess.getNumberOfDoses();
        this.vaccineDosage = otherAdministrationProcess.getVaccineDosage();
        this.timeIntervalBetweenDoses = otherAdministrationProcess.getTimeIntervalBetweenDoses();
    }

    @ExcludeFromJacocoGeneratedReport
    public String getAgeGroup() {
        return ageGroup;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setAgeGroup(String ageGroup, String vaccineAgeGroup) throws OperationCanceledByUserException {
        if(Validations.isAgeGroupValid(ageGroup, true, false) &&
           Validations.isAgeGroupValid(vaccineAgeGroup, true, false)) {
            String[] ageGroupParts = ageGroup.split(",");
            int numI = Integer.parseInt(ageGroupParts[0]);
            int numS = Integer.parseInt(ageGroupParts[1]);
            String[] vaccineAgeGroupParts = vaccineAgeGroup.split(",");
            int numIVaccine = Integer.parseInt(vaccineAgeGroupParts[0]);
            int numSVaccine = Integer.parseInt(vaccineAgeGroupParts[1]);
            if(numI >= numIVaccine && numI <= numSVaccine && numS >= numIVaccine && numS <= numSVaccine) {
                this.ageGroup = ageGroup;
            } else {
                throw new IllegalArgumentException("The age group doesn't belong to the vaccine age group range!");
            }
        } else {
            throw new IllegalArgumentException("The age group is not valid!");
        }
    }

    @ExcludeFromJacocoGeneratedReport
    public int getNumberOfDoses() {
        return numberOfDoses;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setNumberOfDoses(int numberOfDoses) throws OperationCanceledByUserException {
        if(Validations.isNumberDosesValidAdm(numberOfDoses, true, false, SIZE_NUMBEROFDOSESADM)) {
            this.numberOfDoses = numberOfDoses;
        } else {
            throw new IllegalArgumentException("The number of doses is not valid!");
        }
    }

    @ExcludeFromJacocoGeneratedReport
    public int[] getVaccineDosage() {
        return vaccineDosage;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setVaccineDosage(int[] vaccineDosage) throws OperationCanceledByUserException {
        for(int i = 0; i < this.numberOfDoses; i++) {
            if(Validations.isLengthValidLesserThen(vaccineDosage[i], true, false, SIZE_VACCINEDOSAGE)) {
                this.vaccineDosage = vaccineDosage;
            } else {
                throw new IllegalArgumentException("The vaccine dosage is not valid!");
            }
        }
    }

    @ExcludeFromJacocoGeneratedReport
    public int[] getTimeIntervalBetweenDoses() {
        return timeIntervalBetweenDoses;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setTimeIntervalBetweenDoses(int[] timeIntervalBetweenDoses) throws OperationCanceledByUserException {
        for(int i = 0; i < this.numberOfDoses - 1; i++) {
            if(Validations.isTimeIntervalBetweenDosesValid(timeIntervalBetweenDoses[i], true, false,
                                                           SIZE_INTERVALBETWEENDOSES)) {
                this.timeIntervalBetweenDoses = timeIntervalBetweenDoses;
            } else {
                throw new IllegalArgumentException("The time interval between doses is not valid!");
            }
        }
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("Age group: %s\nNumber of doses: %d\nVaccine dosage for the different number of doses: " +
                             "%s\nTime intervals between doses: %s\n", ageGroup, numberOfDoses,
                             Arrays.toString(vaccineDosage), Arrays.toString(timeIntervalBetweenDoses));

    }

    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject) {
            return true;
        }
        if(otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        AdministrationProcess otherAdministrationProcess = (AdministrationProcess)otherObject;
        return this.ageGroup.equals(otherAdministrationProcess.ageGroup);
    }

    /***
     * Method to validate if an age is inside the ageGroup interval limits
     * @param age - current sns user age
     * @return true if age is inside the ageGroup interval limits, false if not
     */
    public boolean isAgeValid(int age) {
        String[] ageGroupArray;
        ageGroupArray = getAgeGroup().trim().split(",");
        int minAge = parseInt(ageGroupArray[0]);
        int maxAge = parseInt(ageGroupArray[1]);

        return ((age >= minAge) && age <= maxAge) ? true : false;
    }

    /***
     * Get vaccine dosage looking for the specific vaccination step of the administration process
     * @param nextDoseStep - next dose step
     * @return vaccine dosage looking for the specific vaccination step of the administration process
     */
    public int getVaccineDosageByDoseStep(int nextDoseStep) {
        if(isDoseStepValid(nextDoseStep)) {
            int[] vaccineDosage = this.getVaccineDosage();
            return vaccineDosage[nextDoseStep];
        }
        return 0;
        //TODO@pedro: lançar exceção
    }

    /***
     * Checks if next dose step is inside the dose step array with index between 0 and (NumberOfDoses-1)
     * @param nextDoseStep - next dose step
     * @return true if next dose step is inside the dose step array with index between 0 and (NumberOfDoses-1),
     * otherwise false
     */
    private boolean isDoseStepValid(int nextDoseStep) {
        return ((nextDoseStep >= 0) && nextDoseStep < this.getNumberOfDoses()) ? true : false;
    }
}
