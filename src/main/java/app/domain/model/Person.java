package app.domain.model;

import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Validations;
import app.interfaces.Constants;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.Serializable;

/**
 * @author Pedro Gomes <1060588@isep.ipp.pt>
 */
public class Person implements Constants, Serializable {

    /**
     * Person's name
     */
    private String name;

    /**
     * Person's Street address
     */
    private String address;

    /**
     * Person's Phone number
     */
    private long phoneNumber;

    /***
     * Person's Email
     */
    private String email;

    /**
     * Citizen Card Number
     */
    private long citizenCardNumber;

    /**
     * Person complete constructor
     *
     * @param name              - name of the Person
     * @param address           - address of the Person
     * @param phoneNumber       - Person's phone number
     * @param email             - Person's email
     * @param citizenCardNumber - Person's citizen card number
     */
    @ExcludeFromJacocoGeneratedReport
    public Person(String name, String address, long phoneNumber, String email, long citizenCardNumber)
            throws OperationCanceledByUserException {
        setName(name);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCitizenCardNumber(citizenCardNumber);
    }

    /***
     * Get Name of Person
     * @return Name of Person
     */
    @ExcludeFromJacocoGeneratedReport
    public String getName() {
        return this.name;
    }

    /**
     * Get Address of the Person
     *
     * @return Address of the Person
     */
    @ExcludeFromJacocoGeneratedReport
    public String getAddress() {
        return this.address;
    }

    /***
     * Get Phone number of Person
     * @return Phone number of Person
     */
    @ExcludeFromJacocoGeneratedReport
    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    /***
     * Get Email of Person
     * @return Email of Person
     */
    @ExcludeFromJacocoGeneratedReport
    public String getEmail() {
        return this.email;
    }

    /***
     * Get Citizen card number of Person
     * @return Citizen card number of Person
     */
    @ExcludeFromJacocoGeneratedReport
    public long getCitizenCardNumber() {
        return this.citizenCardNumber;
    }

    /***
     * Set Name of Person
     * @param name  - Name of SNS User
     */
    @ExcludeFromJacocoGeneratedReport
    public void setName(String name) throws OperationCanceledByUserException {

        if(Validations.isStringLengthValid(name, true, false, MAX_LENGTH_NAME) &&
           Validations.isNameValid(name, true, false)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("The name is not valid!");
        }
    }

    /**
     * Set Person's street address
     *
     * @param address - Address of Person
     */
    @ExcludeFromJacocoGeneratedReport
    public void setAddress(String address) throws OperationCanceledByUserException {

        if(Validations.isStringLengthValid(address, true, false, MAX_LENGTH_ADDRESS)) {
            this.address = address;
        } else {
            throw new IllegalArgumentException("The address is not valid!");
        }
    }

    /***
     * Set Phone number of Person
     * @param phoneNumber   - Phone number of Person
     */
    @ExcludeFromJacocoGeneratedReport
    public void setPhoneNumber(long phoneNumber) throws OperationCanceledByUserException {

        if(Validations.isLengthValidEquals(phoneNumber, true, false, SIZE_PHONE_NUMBER)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("The phone number is not valid!");
        }
    }

    /***
     * Set Email of Person
     * @param email - Email of Person
     */
    @ExcludeFromJacocoGeneratedReport
    public void setEmail(String email) throws OperationCanceledByUserException {

        if(Validations.isEmailFormatValid(email, true, false)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("The email is not valid!");
        }
    }

    /***
     * Set Citizen card number of Person
     * @param citizenCardNumber - Citizen card number of Person
     */
    @ExcludeFromJacocoGeneratedReport
    public void setCitizenCardNumber(long citizenCardNumber) throws OperationCanceledByUserException {

        if(Validations.isLengthValidEquals(citizenCardNumber, true, false, SIZE_CITIZEN_CARD_NUMBER)) {
            this.citizenCardNumber = citizenCardNumber;
        } else {
            throw new IllegalArgumentException("The email is not valid!");
        }
    }

    /**
     * Gives back the Person's text description
     *
     * @return full description of the Person
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("%nName: %s%nStreet address: %s%nPhone number : %d%nEmail: %s%nCitizen card number: %s%n",
                             this.getName(), this.getAddress(), this.getPhoneNumber(), this.getEmail(),
                             this.getCitizenCardNumber());
    }

    /**
     * Compare the Person with the received object
     *
     * @param otherObject the object to compare with Person
     *
     * @return true if the received object represents "otherPerson" like the person itself, otherwise returns false
     */
    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject) {
            return true;
        }
        if(otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }

        Person otherPerson = (Person)otherObject;
        return this.name.equals(otherPerson.name) && this.address.equals(otherPerson.address) &&
               this.phoneNumber == otherPerson.phoneNumber && this.citizenCardNumber == otherPerson.citizenCardNumber &&
               this.email.equalsIgnoreCase(otherPerson.email);
    }


    public boolean equalsUniqueAttributes(Person otherObject) {
        if(this == otherObject) {
            return true;
        }

        if(otherObject == null) {
            return false;
        }
        return phoneNumber == otherObject.phoneNumber || email.equalsIgnoreCase(otherObject.email) ||
               citizenCardNumber == otherObject.citizenCardNumber;
    }

}
