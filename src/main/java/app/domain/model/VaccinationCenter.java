package app.domain.model;


import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.store.PerformanceRecordsStore;
import app.domain.model.store.VaccineSchedulerStore;
import app.domain.model.utils.TimeHour;
import app.domain.model.utils.Validations;
import app.domain.shared.VaccinationCenterTypes;
import app.interfaces.Constants;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author José Silva <1060568@isep.ipp.pt>
 * @author Fernando Ribeiro <1060064@isep.ipp.pt>
 * @author Edgar Moreira <1010100@isep.ipp.pt>
 * @author Pedro Gomes <1060588@isep.ipp.pt>
 */
public class VaccinationCenter implements Constants, Serializable {

    /***
     * Name of vaccination center
     */
    private String name;

    /***
     * Address of vaccination center
     */
    private String address;

    /***
     * Phone Number of vaccination center
     */
    private long phoneNumber;

    /***
     * Email of vaccination center
     */
    private String emailAddress;

    /***
     * Fax Number of vaccination center
     */
    private long faxNumber;

    /***
     * Website address of vaccination center
     */
    private String websiteAddress;

    /***
     * Open Hour of vaccination center
     */
    private TimeHour openHour;

    /***
     * Close Hour of vaccination center
     */
    private TimeHour closeHour;

    /***
     * Slot duration of vaccination center
     */
    private int slotDuration;

    /***
     * Maximum number of vaccines per slot of vaccination center
     */
    private int maxVaccinesPerSlot;

    /***
     * Type of vaccination center
     */
    private String vaccinationCenterType;

    /***
     * Designation of Healthcenter "Administração Regional de Saúde"
     */
    private String designation;

    /***
     * List of the Sns Users in the waiting room after registered the arrival
     */
    private List<SNSUser> lWaitingList;

    /***
     * List of the Sns Users in the recovery room after the vaccine administration given by nurse
     */
    private List<SNSUser> lRecoveryList;

    /***
     * Vaccination Center identifier
     */
    private int vaccinationCenterID;

    /***
     * Instance of the performance records store
     */
    private PerformanceRecordsStore perfRecordsStore;

    /***
     * Instance of the vaccine scheduler store
     */
    private VaccineSchedulerStore vacSchedulerStore;

    /***
     * Vaccination Center identifier by omission
     */
    private final int VACCINATION_CENTER_OMISSION = 0;

    /***
     * Constructor for the vaccination center type of massive vaccination center
     * @param name                  - Name of vaccination center
     * @param address               - Address of vaccination center
     * @param phoneNumber           - Phone number of vaccination center
     * @param emailAddress          - Email of vaccination center
     * @param faxNumber             - Fax number of vaccination center
     * @param websiteAddress        - Website address of vaccination center
     * @param openHour              - Open hours of vaccination center
     * @param closeHour             - Close hours of vaccination center
     * @param slotDuration          - Slot duration of vaccination center
     * @param maxVaccinesPerSlot    - Maximum number of vaccines per slot of vaccination center
     * @param vaccinationCenterType - Type of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public VaccinationCenter(String name, String address, long phoneNumber, String emailAddress, long faxNumber,
                             String websiteAddress, TimeHour openHour, TimeHour closeHour, int slotDuration,
                             int maxVaccinesPerSlot, String vaccinationCenterType)
            throws OperationCanceledByUserException {
        setName(name);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setEmailAddress(emailAddress);
        setFaxNumber(faxNumber);
        setWebsiteAddress(websiteAddress);
        setOpenHour(openHour);
        setCloseHour(closeHour);
        setSlotDuration(slotDuration);
        setMaxVaccinesPerSlot(maxVaccinesPerSlot);
        setVaccinationCenterType(vaccinationCenterType);
        this.lWaitingList = new ArrayList<>();
        this.lRecoveryList = new ArrayList<>();
        this.vaccinationCenterID = VACCINATION_CENTER_OMISSION;
        this.vacSchedulerStore = new VaccineSchedulerStore();
        this.perfRecordsStore = new PerformanceRecordsStore();
    }

    /***
     * Constructor for the vaccination center type of healthcare center
     * @param name                  - Name of vaccination center
     * @param address               - Address of vaccination center
     * @param phoneNumber           - Phone number of vaccination center
     * @param emailAddress          - Email of vaccination center
     * @param faxNumber             - Fax number of vaccination center
     * @param websiteAddress        - Website address of vaccination center
     * @param openHour              - Open hours of vaccination center
     * @param closeHour             - Close hours of vaccination center
     * @param slotDuration          - Slot duration of vaccination center
     * @param maxVaccinesPerSlot    - Maximum number of vaccines per slot of vaccination center
     * @param vaccinationCenterType - Type of vaccination center
     * @param designation           - Designation of Healthcare Centers

     */
    @ExcludeFromJacocoGeneratedReport
    public VaccinationCenter(String name, String address, long phoneNumber, String emailAddress, long faxNumber,
                             String websiteAddress, TimeHour openHour, TimeHour closeHour, int slotDuration,
                             int maxVaccinesPerSlot, String vaccinationCenterType, String designation)
            throws OperationCanceledByUserException {
        setName(name);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setEmailAddress(emailAddress);
        setFaxNumber(faxNumber);
        setWebsiteAddress(websiteAddress);
        setOpenHour(openHour);
        setCloseHour(closeHour);
        setSlotDuration(slotDuration);
        setMaxVaccinesPerSlot(maxVaccinesPerSlot);
        setVaccinationCenterType(vaccinationCenterType);
        setDesignation(designation);
        this.lWaitingList = new ArrayList<>();
        this.lRecoveryList = new ArrayList<>();
        this.vaccinationCenterID = VACCINATION_CENTER_OMISSION;
        this.vacSchedulerStore = new VaccineSchedulerStore();
        this.perfRecordsStore = new PerformanceRecordsStore();
    }

    @ExcludeFromJacocoGeneratedReport
    public VaccinationCenter(VaccinationCenter otherObj) {
        this.name = otherObj.name;
        this.address = otherObj.address;
        this.phoneNumber = otherObj.phoneNumber;
        this.emailAddress = otherObj.emailAddress;
        this.faxNumber = otherObj.faxNumber;
        this.websiteAddress = otherObj.websiteAddress;
        this.openHour = new TimeHour(otherObj.openHour);
        this.closeHour = new TimeHour(otherObj.closeHour);
        this.slotDuration = otherObj.slotDuration;
        this.maxVaccinesPerSlot = otherObj.maxVaccinesPerSlot;
        this.vaccinationCenterType = otherObj.vaccinationCenterType;
        if(vaccinationCenterType.compareTo(VaccinationCenterTypes.HEALTHCARECENTER.getDescription()) == 0) {
            this.designation = otherObj.designation;
        }
        this.lWaitingList = new ArrayList<>(otherObj.lWaitingList);
        this.lRecoveryList = new ArrayList<>(otherObj.lRecoveryList);
        this.vaccinationCenterID = otherObj.vaccinationCenterID;
        this.vacSchedulerStore = new VaccineSchedulerStore(otherObj.vacSchedulerStore);
        this.perfRecordsStore = new PerformanceRecordsStore();
    }

    /***
     * Set name of vaccination center
     * @param name  - Name of vaccination center
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

    /***
     * Get name of vaccination center
     * @return name of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public String getName() {
        return this.name;
    }


    /***
     * Get Address of vaccination center
     * @return Address of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public String getAddress() {
        return this.address;
    }

    /***
     * Set address of vaccination center
     * @param address   - Address of vaccination center
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
     * Get Phone number of vaccination center
     * @return Phone number of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    /***
     * Set Phone number of vaccination center
     * @param phoneNumber   - Phone number of vaccination center
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
     * Get Email of vaccination center
     * @return Email Address of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public String getEmailAddress() {
        return this.emailAddress;
    }

    /***
     * Set Email of vaccination center
     * @param emailAddress - Email of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public void setEmailAddress(String emailAddress) throws OperationCanceledByUserException {
        if(Validations.isEmailFormatValid(emailAddress, true, false)) {
            this.emailAddress = emailAddress;
        } else {
            throw new IllegalArgumentException("The email is not valid!");
        }
    }

    /***
     * Get Fax number of vaccination center
     * @return Fax number of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public long getFaxNumber() {
        return this.faxNumber;
    }

    /***
     * Set Fax number of vaccination center
     * @param faxNumber - Fax number of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public void setFaxNumber(long faxNumber) throws OperationCanceledByUserException {
        if(Validations.isLengthValidEquals(faxNumber, true, false, Constants.SIZE_FAX_NUMBER)) {
            this.faxNumber = faxNumber;
        } else {
            throw new IllegalArgumentException("The fax number is not valid!");
        }
    }

    /***
     * Get Website address of vaccination center
     * @return Website address of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public String getWebsiteAddress() {
        return this.websiteAddress;
    }

    /***
     * Set Website address of vaccination center
     * @param websiteAddress    - Website address of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public void setWebsiteAddress(String websiteAddress) throws OperationCanceledByUserException {
        if(Validations.isWebSiteFormatValid(websiteAddress, true, false)) {
            this.websiteAddress = websiteAddress;
        } else {
            throw new IllegalArgumentException("The website is not valid!");
        }
    }

    /***
     * Get Open Hours of vaccination center
     * @return Open hours of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public TimeHour getOpenHour() {
        return new TimeHour(openHour);
    }

    /***
     * Set Open hours of vaccination center
     * @param openHour  - Open hours of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public void setOpenHour(TimeHour openHour) {
        if(openHour.getHour() > 0 && openHour.getHour() < 24 && openHour.getMinutes() >= 0 &&
           openHour.getMinutes() <= 59) {
            this.openHour = new TimeHour(openHour);
        } else {
            throw new IllegalArgumentException("The open hour is not valid!");
        }
    }

    /***
     * Get Close Hours of vaccination center
     * @return Close hours of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public TimeHour getCloseHour() {
        return new TimeHour(closeHour);
    }

    /***
     * Set Close Hours of vaccination center
     * @param closeHour - Close hours of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public void setCloseHour(TimeHour closeHour) {
        if(closeHour.getHour() > 0 && closeHour.getHour() < 24 && closeHour.getMinutes() >= 0 &&
           closeHour.getMinutes() <= 59) {
            this.closeHour = new TimeHour(closeHour);
        } else {
            throw new IllegalArgumentException("The close hour is not valid!");
        }
    }

    /***
     * Get Slot duration of vaccination center
     * @return Slot duration of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public int getSlotDuration() {
        return this.slotDuration;
    }

    /***
     * Set Slot duration of vaccination center
     * @param slotDuration  - Slot duration of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public void setSlotDuration(int slotDuration) throws OperationCanceledByUserException {
        if(Validations.isValueValid(slotDuration, true, true, MAX_SLOTDURATION_NUMBER)) {
            this.slotDuration = slotDuration;
        } else {
            throw new IllegalArgumentException("The slot duration is not valid!");
        }
    }

    /***
     * Get Maximum number of vaccines per slot of vaccination center
     * @return Maximum number of vaccines per slot of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public int getMaxVaccinesPerSlot() {
        return this.maxVaccinesPerSlot;
    }

    /***
     * Set Maximum number of vaccines per slot of vaccination center
     * @param maxVaccinesPerSlot    - Maximum number of vaccines per slot of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public void setMaxVaccinesPerSlot(int maxVaccinesPerSlot) throws OperationCanceledByUserException {
        if(Validations.isValueValid(maxVaccinesPerSlot, true, true, MAX_MAXVACPERSLOT_NUMBER)) {
            this.maxVaccinesPerSlot = maxVaccinesPerSlot;
        } else {
            throw new IllegalArgumentException("The maximum number of vaccines per slot is not valid!");
        }
    }

    /***
     * Get Type of vaccination center
     * @return vaccinationCenterType of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public String getVaccinationCenterType() {
        return vaccinationCenterType;
    }

    /***
     * Set Type of vaccination center
     * @param vaccinationCenterType  - Type of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public void setVaccinationCenterType(String vaccinationCenterType) {
        if(vaccinationCenterType.compareTo(VaccinationCenterTypes.HEALTHCARECENTER.getDescription()) == 0 ||
           vaccinationCenterType.compareTo(VaccinationCenterTypes.MASSVACCINATIONCENTER.getDescription()) == 0) {
            this.vaccinationCenterType = vaccinationCenterType;
        } else {
            throw new IllegalArgumentException("The vaccination center type is not valid!");
        }
    }

    /***
     * Get Healthcare Center designation
     * @return designation
     */
    @ExcludeFromJacocoGeneratedReport
    public String getDesignation() {
        return this.designation;
    }

    /***
     * Set Healthcare Center designation
     * @param designation - designation of the healthcare center
     */
    @ExcludeFromJacocoGeneratedReport
    public void setDesignation(String designation) throws OperationCanceledByUserException {
        if(Validations.isStringLengthValid(designation, true, false, MAX_LENGTH_HEALTHCAREDESIGNATION)) {
            this.designation = designation;
        } else {
            throw new IllegalArgumentException("The center type designation is not valid!");
        }
    }

    /***
     * Set the vaccination center identifier (ID)
     * @param id - unique identifier for each vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public void setVaccinationCenterId(int id) {
        if(id > 0) {
            this.vaccinationCenterID = id;
        } else {
            throw new IllegalArgumentException("The vaccination center id is not valid!");
        }
    }

    /***
     * Get the vaccination center identifier (ID)
     * @return vaccination center ID
     */
    @ExcludeFromJacocoGeneratedReport
    public int getVaccinationCenterId() {
        return this.vaccinationCenterID;
    }

    /***
     * Get the waiting list of SNS users after registered the arrival to vaccination center
     * @return waiting list
     */
    @ExcludeFromJacocoGeneratedReport
    public List<SNSUser> getWaitingList() {
        return new ArrayList<>(lWaitingList);
    }

    @ExcludeFromJacocoGeneratedReport
    public List<SNSUser> getlRecoveryList() {
        return new ArrayList<>(lRecoveryList);
    }

    /***
     * Get the vaccine scheduler store
     * @return vaccine scheduler store
     */
    @ExcludeFromJacocoGeneratedReport
    public VaccineSchedulerStore getVacineSchedulerStore() {
        return vacSchedulerStore;
    }

    /***
     * Get the performance records strore
     * @return performance records strore
     */
    @ExcludeFromJacocoGeneratedReport
    public PerformanceRecordsStore getPerformanceRecordsStore() {
        return perfRecordsStore;
    }

    /***
     * Gives back the vaccination center partial description
     * @return Unique attributes of the vaccination center: name, phone number and email
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("\nName: %s\nPhone Number: %d\nEmail: %s", this.getName(), this.getPhoneNumber(),
                             this.getEmailAddress());
    }

    /***
     * Gives back the massive vaccination center full description
     * @return Full description of the vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public String toStringTotal() {
        return String.format("\nName: %s\nStreet Address: %s\nPhone Number: %d\nEmail: %s\nFax Number: %d\nWebsite " +
                             "Address: %s\nOpen Hour: %s\nClose Hour: %s\nSlot Duration: %d\nMax Vaccines per " +
                             "Slot: %d\nVaccination Center Type: %s\nVaccination Center ID: %s", this.getName(),
                             this.getAddress(), this.getPhoneNumber(), this.getEmailAddress(), this.faxNumber,
                             this.websiteAddress, this.openHour.toString(), this.closeHour.toString(),
                             this.slotDuration, this.maxVaccinesPerSlot, this.vaccinationCenterType,
                             (this.vaccinationCenterID == 0) ? "Not yet defined" :
                             String.format("%d", this.vaccinationCenterID));
    }

    /***
     * Gives back the healthcare center full description
     * @return Full description of the healthcare center
     */
    @ExcludeFromJacocoGeneratedReport
    public String toStringHealth() {
        return String.format("\nName: %s\nStreet Address: %s\nPhone Number: %d\nEmail: %s\nFax Number: %d\nWebsite " +
                             "Address: %s\nOpen Hour: %s\nClose Hour: %s\nSlot Duration: %d\nMax Vaccines per " +
                             "Slot: %d\nVaccination Center Type: %s\nDesignation: %s\nVaccination Center ID: %s",
                             this.getName(), this.getAddress(), this.getPhoneNumber(), this.getEmailAddress(),
                             this.faxNumber, this.websiteAddress, this.openHour.toString(), this.closeHour.toString(),
                             this.slotDuration, this.maxVaccinesPerSlot, this.vaccinationCenterType, this.designation,
                             (this.vaccinationCenterID == 0) ? "Not yet defined" :
                             String.format("%d", this.vaccinationCenterID));
    }


    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject) {
            return true;
        }
        if(otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        VaccinationCenter otherVaccinationCenter = (VaccinationCenter)otherObject;
        if(otherVaccinationCenter.vaccinationCenterType.compareTo(
                VaccinationCenterTypes.HEALTHCARECENTER.getDescription()) == 0) {
            return name.equals(otherVaccinationCenter.name) &&
                   emailAddress.equalsIgnoreCase(otherVaccinationCenter.emailAddress) &&
                   phoneNumber == otherVaccinationCenter.phoneNumber &&
                   this.address.equalsIgnoreCase(otherVaccinationCenter.address) &&
                   this.faxNumber == otherVaccinationCenter.faxNumber &&
                   this.websiteAddress.equalsIgnoreCase(otherVaccinationCenter.websiteAddress) &&
                   this.openHour.equals(otherVaccinationCenter.openHour) &&
                   this.closeHour.equals(otherVaccinationCenter.closeHour) &&
                   this.slotDuration == otherVaccinationCenter.slotDuration &&
                   this.maxVaccinesPerSlot == otherVaccinationCenter.maxVaccinesPerSlot &&
                   this.vaccinationCenterType.equals(otherVaccinationCenter.vaccinationCenterType) &&
                   this.designation.equals(otherVaccinationCenter.designation) &&
                   this.vaccinationCenterID == otherVaccinationCenter.vaccinationCenterID &&
                   this.lWaitingList.equals(otherVaccinationCenter.lWaitingList) &&
                   this.vacSchedulerStore.equals(otherVaccinationCenter.vacSchedulerStore);
        } else {
            return name.equals(otherVaccinationCenter.name) &&
                   emailAddress.equalsIgnoreCase(otherVaccinationCenter.emailAddress) &&
                   phoneNumber == otherVaccinationCenter.phoneNumber &&
                   this.address.equalsIgnoreCase(otherVaccinationCenter.address) &&
                   this.faxNumber == otherVaccinationCenter.faxNumber &&
                   this.websiteAddress.equalsIgnoreCase(otherVaccinationCenter.websiteAddress) &&
                   this.openHour.equals(otherVaccinationCenter.openHour) &&
                   this.closeHour.equals(otherVaccinationCenter.closeHour) &&
                   this.slotDuration == otherVaccinationCenter.slotDuration &&
                   this.maxVaccinesPerSlot == otherVaccinationCenter.maxVaccinesPerSlot &&
                   this.vaccinationCenterType.equals(otherVaccinationCenter.vaccinationCenterType) &&
                   this.vaccinationCenterID == otherVaccinationCenter.vaccinationCenterID &&
                   this.lWaitingList.equals(otherVaccinationCenter.lWaitingList) &&
                   this.vacSchedulerStore.equals(otherVaccinationCenter.vacSchedulerStore);
        }
    }

    public boolean equalsUniqueAttributes(VaccinationCenter otherVaccinationCenter) {
        if(this == otherVaccinationCenter) {
            return true;
        }
        return name.equals(otherVaccinationCenter.name) ||
               emailAddress.equalsIgnoreCase(otherVaccinationCenter.emailAddress) ||
               phoneNumber == otherVaccinationCenter.phoneNumber;
    }

    public boolean addToWaitingList(SNSUser snsUser) {

        if(!lWaitingList.isEmpty()) {
            for(SNSUser sns : lWaitingList) {
                if(sns.equalsUniqueAttributes(snsUser)) {
                    return false;
                }
            }
        }
        return this.lWaitingList.add(snsUser);
    }

    /***
     * Method to add the sns user to the recovery room list
     * @param snsUser - sns user obj
     * @return add the sns user to the recovery room list
     */
    public boolean addToRecoveryList(SNSUser snsUser) {
        if(!lRecoveryList.isEmpty()) {
            for(SNSUser obj : lRecoveryList) {
                if(obj.equalsUniqueAttributes(snsUser)) {
                    return false;
                }
            }
        }
        return this.lRecoveryList.add(snsUser);
    }

    public boolean moveSnsUserFromWaitingRoomToRecoveryRoom(SNSUser snsUser) {
        if(!lWaitingList.isEmpty()) {
            for(SNSUser sns : lWaitingList) {
                if(sns.equalsUniqueAttributes(snsUser)) {
                    if(this.lWaitingList.remove(snsUser)) {

                        snsUser.setSnsUserArrivalTimeDefaultValues();

                        for(SNSUser obj : lRecoveryList) {
                            if(obj.equalsUniqueAttributes(snsUser)) {
                                return false;
                            }
                        }

                        return this.lRecoveryList.add(snsUser);
                    }
                    return false;
                }
            }
        }
        return false;
    }

    public boolean removeFromRecoveryList(SNSUser snsUser) {
        if(!lRecoveryList.isEmpty()) {
            for(SNSUser obj : lRecoveryList) {
                if(obj.equalsUniqueAttributes(snsUser)) {
                    return this.lRecoveryList.remove(snsUser);
                }
            }
        }
        return false;

    }

    /**
     * @param timeInterval period of time to evaluate in minutes
     *
     * @return if time interval is a multiple of opening hours
     */
    public boolean validateTimeInterval(int timeInterval) {
        if(timeInterval > 0 && getOpenHour().diffTimeInMinutes(getCloseHour()) > timeInterval) {
            if((getOpenHour().diffTimeInMinutes(getCloseHour()) / timeInterval) % 2 == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param Time
     * @param timeInterval
     *
     * @return the index that the arrival time belongs in accordance with the time intervals defined
     */
    public int slotIndex(TimeHour Time, int timeInterval) {
        int index;
        if(Time.isBigger(getCloseHour()) || Time.equals(getCloseHour())) {
            return ((getOpenHour().diffTimeInMinutes(getCloseHour())) / timeInterval)-1;
        }
        index = (getOpenHour().diffTimeInMinutes(Time)) / timeInterval;
        return index;
    }


    public int numberOfSlotsByTimeInterval(int timeInterval) {
        int openHours;
        int numberOfSlots;
        openHours = getOpenHour().diffTimeInMinutes(getCloseHour());
        numberOfSlots = openHours / timeInterval;
        return numberOfSlots;
    }

}
