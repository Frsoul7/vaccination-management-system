package app.dto;

import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

public class WaitingRoomDTO {
    /**
     * SNS User name
     */
    private String name;

    /**
     * Gender of SNS User
     */
    private String gender;

    /**
     * SNS Number of SNS User
     */
    private long snsUserNumber;

    /**
     * Birth date of SNS User
     */
    private DateCustom birthDate;

    /**
     * SNS User phone number
     */
    private long phoneNumber;

    /***
     * SNSUser arrival time
     */
    private TimeHour snsUserArrivalTime;

    /**
     * WaitingRoomDTO constructor
     *
     * @param name
     * @param gender
     * @param birthDate
     * @param snsUserNumber
     * @param phoneNumber
     */
    @ExcludeFromJacocoGeneratedReport
    public WaitingRoomDTO(String name, String gender, DateCustom birthDate, long snsUserNumber, long phoneNumber,
                          TimeHour snsUserArrivalTime) {
        setName(name);
        setGender(gender);
        setSnsUserNumber(snsUserNumber);
        setBirthDate(birthDate);
        setPhoneNumber(phoneNumber);
        setSnsUserArrivalTime(snsUserArrivalTime);
    }

    /**
     * Get Name of SNS user
     *
     * @return Name of SNS user
     */
    @ExcludeFromJacocoGeneratedReport
    public String getName() {
        return name;
    }

    /**
     * Get Gender of SNS user
     *
     * @return Gender of SNS user
     */
    @ExcludeFromJacocoGeneratedReport
    public String getGender() {
        return gender;
    }

    /**
     * Get SNS user number of SNS user
     *
     * @return SNS user number of SNS user
     */
    @ExcludeFromJacocoGeneratedReport
    public long getSnsUserNumber() {
        return snsUserNumber;
    }

    /**
     * Get Birthdate of SNS user
     *
     * @return Birthdate of SNS user
     */
    @ExcludeFromJacocoGeneratedReport
    public DateCustom getBirthDate() {
        return new DateCustom(birthDate);
    }

    /**
     * Get Phone number of SNS user
     *
     * @return Phone number of SNS user
     */
    @ExcludeFromJacocoGeneratedReport
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /***
     * Get sns user arrival TimeHour
     *
     * @return sns user arrival TimeHour
     */
    @ExcludeFromJacocoGeneratedReport
    public TimeHour getSnsUserArrivalTime() {
        return new TimeHour(snsUserArrivalTime);
    }

    /***
     * Set Name of SNS user
     *
     * @param name - Name of SNS user
     */
    @ExcludeFromJacocoGeneratedReport
    public void setName(String name) {
        this.name = name;
    }

    /***
     * Set Gender of SNS user
     *
     * @param gender - Gender of SNS user
     */
    @ExcludeFromJacocoGeneratedReport
    public void setGender(String gender) {
        this.gender = gender;
    }

    /***
     * Set SNS user number of SNS user
     *
     * @param snsUserNumber - SNS user number of SNS user
     */
    @ExcludeFromJacocoGeneratedReport
    public void setSnsUserNumber(long snsUserNumber) {
        this.snsUserNumber = snsUserNumber;
    }

    /***
     * Set Birthdate of SNS user
     *
     * @param birthDate - Birthdate of SNS user
     */
    @ExcludeFromJacocoGeneratedReport
    public void setBirthDate(DateCustom birthDate) {
        this.birthDate = birthDate;
    }

    /***
     * Set Phone number of SNS user
     *
     * @param phoneNumber - Phone number of SNS user
     */
    @ExcludeFromJacocoGeneratedReport
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /***
     * Set sns user arrival TimeHour
     *
     * @param snsUserArrivalTime - sns user arrival TimeHour
     */
    @ExcludeFromJacocoGeneratedReport
    public void setSnsUserArrivalTime(TimeHour snsUserArrivalTime) {
        this.snsUserArrivalTime = new TimeHour(snsUserArrivalTime);
    }

    /**
     * Gives back the sns user text description for waitingRoomDTO
     *
     * @return description of the sns user for waitingRoomDTO
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("- Arrival time: %s | Sns user number: %d | Name: %s | Gender: %s | Birthdate: %s | " +
                             "Phone number: %d%n", this.getSnsUserArrivalTime().toString(), this.getSnsUserNumber(),
                             this.getName(), this.getGender(), this.getBirthDate(), this.getPhoneNumber());
    }
}
