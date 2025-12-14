package app.domain.model;

import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.domain.model.utils.Validations;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

/***
 * @authors Edgar Moreira <1010100@isep.ipp.pt>
 *          Fernando Ribeiro <1060064@isep.ipp.pt>
 *          José Silva <1060568@isep.ipp.pt>
 *          Pedro Gomes <1060588@isep.ipp.pt>
 */
public class SNSUser extends Person {

    /***
     * Gender of SNS User
     */
    private String gender;

    /***
     * SNS Number of SNS User
     */
    private long snsUserNumber;

    /***
     * Birthdate of SNS User
     */
    private DateCustom birthDate;

    /***
     * SNSUser arrival time
     */
    private TimeHour snsUserArrivalTime;

    /***
     * Instance of sns user health records
     */
    private HealthRecords healthRecords;
    /***
     * Constant by default for SNS user gender
     */
    private static final String DEFAULT_GENDER = "#not defined#";

    /***
     * SNSUser arrival default hour
     */
    private static final int DEFAULT_ARRIVAL_HOUR = 0;

    /***
     * SNSUser arrival default minutes
     */
    private static final int DEFAULT_ARRIVAL_MINUTES = 0;

    /***
     * SNS User complete constructor
     * @param name              - name of the sns user
     * @param address           - address of the sns user
     * @param gender            - sns user gender
     * @param phoneNumber       - sns user phone number
     * @param email             - sns user email
     * @param citizenCardNumber - sns user citizen card number
     * @param snsUserNumber     - sns user number
     * @param birthDate         - sns user birthdate
     */
    @ExcludeFromJacocoGeneratedReport
    public SNSUser(String name, String address, String gender, long phoneNumber, String email, long citizenCardNumber,
                   long snsUserNumber, DateCustom birthDate) throws OperationCanceledByUserException {
        super(name, address, phoneNumber, email, citizenCardNumber);
        setGender(gender);
        setSnsUserNumber(snsUserNumber);
        this.birthDate = new DateCustom(birthDate);
        this.snsUserArrivalTime = new TimeHour(DEFAULT_ARRIVAL_HOUR, DEFAULT_ARRIVAL_MINUTES);
        this.healthRecords = new HealthRecords();
    }

    /***
     * SNS User partial constructor, without the Gender field
     * @param name              - name of the sns user
     * @param phoneNumber       - sns user phone number
     * @param email             - sns user email
     * @param citizenCardNumber - sns user citizen card number
     * @param snsUserNumber     - sns user number
     * @param birthDate         - sns user birthdate
     */
    @ExcludeFromJacocoGeneratedReport
    public SNSUser(String name, String address, long phoneNumber, String email, long citizenCardNumber,
                   long snsUserNumber, DateCustom birthDate) throws OperationCanceledByUserException {
        super(name, address, phoneNumber, email, citizenCardNumber);
        setGender(DEFAULT_GENDER);
        setSnsUserNumber(snsUserNumber);
        this.birthDate = new DateCustom(birthDate);
        this.snsUserArrivalTime = new TimeHour(DEFAULT_ARRIVAL_HOUR, DEFAULT_ARRIVAL_MINUTES);
        this.healthRecords = new HealthRecords();
    }

    public SNSUser(SNSUser otherSnsUser) throws OperationCanceledByUserException {
        super(otherSnsUser.getName(), otherSnsUser.getAddress(), otherSnsUser.getPhoneNumber(), otherSnsUser.getEmail(),
              otherSnsUser.getCitizenCardNumber());
        this.gender = otherSnsUser.getGender();
        this.snsUserNumber = otherSnsUser.getSnsUserNumber();
        this.birthDate = otherSnsUser.getBirthDate();
        this.snsUserArrivalTime = otherSnsUser.getSnsUserArrivalTime();
        this.healthRecords = otherSnsUser.getHealthRecords();
    }


    /***
     * Get Gender of SNS User
     * @return Gender of SNS User
     */
    @ExcludeFromJacocoGeneratedReport
    public String getGender() {
        return gender;
    }

    /***
     * Get SNS Number of SNS User
     * @return SNS Number of SNS User
     */
    @ExcludeFromJacocoGeneratedReport
    public long getSnsUserNumber() {
        return this.snsUserNumber;
    }

    /***
     * Get Birth date of SNS User
     * @return Birth date of SNS User
     */
    @ExcludeFromJacocoGeneratedReport
    public DateCustom getBirthDate() {
        return new DateCustom(birthDate);
    }

    /***
     * Get the SNS User Arrival time to a specific vaccination center
     * @return SNS User arrival time to a specific vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public TimeHour getSnsUserArrivalTime() {
        return new TimeHour(snsUserArrivalTime);
    }

    @ExcludeFromJacocoGeneratedReport
    public HealthRecords getHealthRecords() {
        return healthRecords;
    }

    /***
     * Set Gender of SNS User
     * @param gender    - Gender of SNS User
     */
    @ExcludeFromJacocoGeneratedReport
    public void setGender(String gender) throws OperationCanceledByUserException {
        if(Validations.isGenderValid(gender, false, false) || gender.equals("#not defined#")) {
            this.gender = gender;
        } else {
            throw new IllegalArgumentException("The gender is not valid!");
        }
    }

    /***
     * Set SNS Number of SNS User
     * @param snsUserNumber - SNS Number of SNS User
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
     * Set Birth date of SNS User
     * @param birthDate - Birth date of SNS User
     */
    @ExcludeFromJacocoGeneratedReport
    public void setBirthDate(DateCustom birthDate) {
        if(Validations.isBirthDateValid(birthDate)) {
            this.birthDate.setDateCustom(birthDate.getDay(), birthDate.getMonth(), birthDate.getYear());
        } else {
            throw new IllegalArgumentException("The birth date is not valid!");
        }
    }

    /***
     * Set the SNS User Arrival time to a specific vaccination center
     * @param snsUserArrivalTime    - Arrival time of a SNS user to a specific vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public void setSnsUserArrivalTime(TimeHour snsUserArrivalTime) {
        if(snsUserArrivalTime.getHour() > 0 && snsUserArrivalTime.getHour() < 24 &&
           snsUserArrivalTime.getMinutes() >= 0 && snsUserArrivalTime.getMinutes() <= 59) {
            this.snsUserArrivalTime.setTimeHour(snsUserArrivalTime.getHour(), snsUserArrivalTime.getMinutes());
        } else {
            throw new IllegalArgumentException("The time for the scheduling is not valid!");
        }

    }

    @ExcludeFromJacocoGeneratedReport
    public void setSnsUserArrivalTimeDefaultValues() {
        this.snsUserArrivalTime = new TimeHour(DEFAULT_ARRIVAL_HOUR, DEFAULT_ARRIVAL_MINUTES);
    }

    /***
     * Gives back the sns user information of Gender, SNS user number and Birthdate
     * @return partial description of the sns user
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("%sGender: %s%nSNS user number: %d%nBirthdate: %s%n", super.toString(), this.getGender(),
                             this.getSnsUserNumber(), this.getBirthDate());
    }

    /***
     * Method to verify if an object SNS User is equal to an another
     * @param otherObject the object to compare with SNSUser
     * @return true if the received object represents "otherSNSUser" like the SNSUser itself, otherwise returns false
     */
    @Override
    public boolean equals(Object otherObject) {
        if(!super.equals(otherObject)) {
            return false;
        }

        SNSUser otherSnsUser = (SNSUser)otherObject;
        return this.snsUserNumber == otherSnsUser.snsUserNumber && this.birthDate.equals(otherSnsUser.birthDate) &&
               this.snsUserArrivalTime.equals(otherSnsUser.snsUserArrivalTime) &&
               this.gender.equals(otherSnsUser.gender);
    }


    public boolean equalsUniqueAttributes(SNSUser otherObject) {
        if(!super.equalsUniqueAttributes(otherObject)) {
            return false;
        }
        return snsUserNumber == otherObject.snsUserNumber;
    }

    /***
     * Method to calculate age in comparison between birthDate and Taday
     *
     * @return sns user age, in years
     */
    public int calculateAge() {
        DateCustom actualDate = DateCustom.getActualDate();
        if(actualDate != null && birthDate != null) {
            return this.birthDate.countingYears(actualDate);
        }
        return -1;
    }

    /***
     *
     * @param inDate Date in which you want to calculate age
     * @return age in the specified date
     */
    public int calculateAge(DateCustom inDate) {
        if(inDate != null && birthDate != null) {
            return this.birthDate.countingYears(inDate);
        }
        return -1;
    }
}
