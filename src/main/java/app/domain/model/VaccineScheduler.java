package app.domain.model;

import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.domain.model.utils.Validations;
import app.interfaces.Constants;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.Serializable;

/***
 * @authors Edgar Moreira <1010100@isep.ipp.pt>
 *          Fernando Ribeiro <1060064@isep.ipp.pt>
 *          José Silva <1060568@isep.ipp.pt>
 *          Pedro Gomes <1060588@isep.ipp.pt>
 */

public class VaccineScheduler implements Constants, Serializable {

    /***
     * SNS number of SNS User
     */
    private long snsUserNumber;

    /***
     * Designation of the vaccine type (e.g. Covid-19)
     */
    private String vaccineTypeDesignation;

    /***
     * Name of the Vaccination Center
     */
    private String vaccinationCenterName;

    /***
     * date according to the DateCustom format and class parameters
     */
    private DateCustom date;

    /***
     * Scheduler time according to the TimeHour format and class parameters
     */
    private TimeHour schedulerTime;

    /***
     * Constructor of the Vaccine Scheduler
     * @param snsUserNumber - sns user number inserted on the vaccine schedule
     * @param vaccineTypeDesignation - designation of the vaccine type inserted on the vaccine schedule
     * @param vaccinationCenterName - name of the vaccination center inserted on the vaccine schedule
     * @param date - date inserted on the vaccine schedule
     * @param schedulerTime - time/hour inserted on the vaccine schedule
     */
    @ExcludeFromJacocoGeneratedReport
    public VaccineScheduler(long snsUserNumber, String vaccineTypeDesignation, String vaccinationCenterName,
                            DateCustom date, TimeHour schedulerTime) throws OperationCanceledByUserException {
        this.setSnsUserNumber(snsUserNumber);
        this.setVaccineTypeDesignation(vaccineTypeDesignation);
        this.setVaccinationCenterName(vaccinationCenterName);
        this.setDate(date);
        this.setSchedulerTime(schedulerTime);
    }

    public VaccineScheduler(VaccineScheduler otherVaccineScheduler) throws OperationCanceledByUserException {
        this.snsUserNumber = otherVaccineScheduler.getSnsUserNumber();
        this.vaccineTypeDesignation = otherVaccineScheduler.getVaccineTypeDesignation();
        this.vaccinationCenterName = otherVaccineScheduler.vaccinationCenterName;
        this.date = otherVaccineScheduler.getDate();
        this.schedulerTime = otherVaccineScheduler.getSchedulerTime();
    }

    /***
     * Get of the SNS user number
     * @return SNS user number
     */
    @ExcludeFromJacocoGeneratedReport
    public long getSnsUserNumber() {
        return snsUserNumber;
    }

    /***
     * Set for the SNS user number
     * @param snsUserNumber - SNS user number
     */
    @ExcludeFromJacocoGeneratedReport
    public void setSnsUserNumber(long snsUserNumber) throws OperationCanceledByUserException {
        if(Validations.isLengthValidEquals(snsUserNumber, true, false, SIZE_SNS_USER_NUMBER)) {
            this.snsUserNumber = snsUserNumber;
        } else {
            throw new IllegalArgumentException("The Sns User number is not valid!");
        }
    }

    /***
     * Get for the vaccine type designation
     * @return vaccineTypeDesignation
     */
    @ExcludeFromJacocoGeneratedReport
    public String getVaccineTypeDesignation() {
        return vaccineTypeDesignation;
    }

    /***
     * Set for the vaccine type designation
     * @param vaccineTypeDesignation - Designation of the vaccine type
     */
    @ExcludeFromJacocoGeneratedReport
    public void setVaccineTypeDesignation(String vaccineTypeDesignation) throws OperationCanceledByUserException {
        if(Validations.isStringLengthValid(vaccineTypeDesignation, false, false, 60)) {
            this.vaccineTypeDesignation = vaccineTypeDesignation;
        } else {
            throw new IllegalArgumentException("The designation is not valid!");
        }
    }

    /***
     * Get the name of vaccination center
     * @return vaccinationCenterName
     */
    @ExcludeFromJacocoGeneratedReport
    public String getVaccinationCenterName() {
        return vaccinationCenterName;
    }

    /***
     * Set the vaccination center name on the vaccine scheduler
     * @param vaccinationCenterName - Name of the vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public void setVaccinationCenterName(String vaccinationCenterName) throws OperationCanceledByUserException {

        if(Validations.isStringLengthValid(vaccinationCenterName, true, false, MAX_LENGTH_NAME) &&
           Validations.isNameValid(vaccinationCenterName, true, false)) {
            this.vaccinationCenterName = vaccinationCenterName;
        } else {
            throw new IllegalArgumentException("The vaccination center name is not valid!");
        }
    }

    /***
     * Get the date according to DateCustom parameters
     * @return date
     */
    @ExcludeFromJacocoGeneratedReport
    public DateCustom getDate() {
        return new DateCustom(date);
    }

    /***
     * Set the date according to DateCustom parameters
     * @param date - date for the vaccine schedule
     */
    @ExcludeFromJacocoGeneratedReport
    public void setDate(DateCustom date) {
        if(date.isBigger(DateCustom.getActualDate())) {
            this.date = new DateCustom(date);
        } else {
            throw new IllegalArgumentException("The date is earlier than the current date!");
        }
    }

    /***
     * Get the time of scheduler
     * @return TimeHour
     */
    @ExcludeFromJacocoGeneratedReport
    public TimeHour getSchedulerTime() {
        return new TimeHour(schedulerTime);
    }

    /***
     * Set the time/hour of a vaccine scheduler
     * @param schedulerTime - Time inserted to the vaccine schedule
     */
    @ExcludeFromJacocoGeneratedReport
    public void setSchedulerTime(TimeHour schedulerTime) {
        if(schedulerTime.getHour() > 0 && schedulerTime.getHour() < 24 && schedulerTime.getMinutes() >= 0 &&
           schedulerTime.getMinutes() <= 59) {
            this.schedulerTime = new TimeHour(schedulerTime);
        } else {
            throw new IllegalArgumentException("The time for the scheduling is not valid!");
        }

    }

    /***
     * Gives back the scheduler's description
     * @return all information about the scheduler: SNS user number, vaccine type, vaccination center, date, time
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("\nSNS User number: %d\nVaccine Type designation: %s\nVaccination Center name: %s\nDate:" +
                             " %s\nSchedule Time: %s", this.getSnsUserNumber(), this.getVaccineTypeDesignation(),
                             this.getVaccinationCenterName(), this.getDate().toString(),
                             this.getSchedulerTime().toString());
    }


    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject) {
            return true;
        }
        if(otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        VaccineScheduler vaccineScheduler = (VaccineScheduler)otherObject;
        return this.getDate().equals(vaccineScheduler.getDate()) &&
               this.getSnsUserNumber() == vaccineScheduler.getSnsUserNumber() &&
               this.getVaccineTypeDesignation().equals(vaccineScheduler.getVaccineTypeDesignation());
    }
}
