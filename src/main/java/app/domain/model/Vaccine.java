package app.domain.model;

import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Validations;
import app.interfaces.Constants;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/***
 * @authors Edgar Moreira <1010100@isep.ipp.pt>
 *          Fernando Ribeiro <1060064@isep.ipp.pt>
 *          José Silva <1060568@isep.ipp.pt>
 *          Pedro Gomes <1060588@isep.ipp.pt>
 */

public class Vaccine implements Constants, Serializable {

    /***
     * Designation/name of vaccine
     */
    private String name;

    /***
     * Lot number of vaccine
     */
    private String lotNumber;

    /***
     * ID of vaccine
     */
    private int Id;

    /***
     * Brand of vaccine
     */
    private String brand;

    /***
     * Vaccine Type of vaccine : designation
     */
    private String vaccineType;

    /***
     * Age Group of vaccine
     */
    private String ageGroup;

    /***
     * Number of ampoules per vaccination pack
     */
    private int numberDoses;

    /***
     * Dosage of each ampoule [ml]
     */
    private int vaccineDosage;

    /***
     * List of administration processes
     */
    private List<AdministrationProcess> admProcList;

    /***
     * Constructor with all parameters for Vaccine
     * @param name          - name of the vaccine
     * @param lotNumber     - lot number of the vaccine
     * @param id            - identifier of the vaccine
     * @param brand         - brand of the vaccine
     * @param vaccineType   - type of the vaccine (e.g. Covid-19)
     * @param ageGroup      - age group specification of the vaccine
     * @param numberDoses   - number of doses specified for the vaccine
     * @param vaccineDosage - dosage of the vaccine specified
     */
    @ExcludeFromJacocoGeneratedReport
    public Vaccine(String name, String lotNumber, int id, String brand, String vaccineType, String ageGroup,
                   int numberDoses, int vaccineDosage) throws OperationCanceledByUserException {
        this.setName(name);
        this.setLotNumber(lotNumber);
        this.setId(id);
        this.setBrand(brand);
        this.setVaccineType(vaccineType);
        this.setAgeGroup(ageGroup);
        this.setNumberDoses(numberDoses);
        this.setVaccineDosage(vaccineDosage);
        this.admProcList = new ArrayList<>();
    }

    @ExcludeFromJacocoGeneratedReport
    public Vaccine(Vaccine otherVaccine) {
        this.name = otherVaccine.getName();
        this.lotNumber = otherVaccine.getLotNumber();
        this.Id = otherVaccine.getId();
        this.brand = otherVaccine.getBrand();
        this.vaccineType = otherVaccine.getVaccineType();
        this.ageGroup = otherVaccine.getAgeGroup();
        this.numberDoses = otherVaccine.getNumberDoses();
        this.vaccineDosage = otherVaccine.getVaccineDosage();
        this.admProcList = otherVaccine.getAdmProcList();
    }


    /***
     * Get the name of the vaccine
     * @return name of the vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public String getName() {
        return name;
    }

    /***
     * Get the lot number of the vaccine
     * @return lot number of the vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public String getLotNumber() {
        return lotNumber;
    }

    /***
     * Get the identifier (ID) of the vaccine
     * @return the identifier (ID) of the vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public int getId() {
        return Id;
    }

    /***
     * Get the brand of the vaccine
     * @return the brand of the vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public String getBrand() {
        return brand;
    }

    /***
     * Get the type of the vaccine
     * @return type of the vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public String getVaccineType() {
        return vaccineType;
    }

    /***
     * Get the age group defined for a vaccine
     * @return the age group
     */
    @ExcludeFromJacocoGeneratedReport
    public String getAgeGroup() {
        return ageGroup;
    }

    /***
     * Get the number of doses of a vaccine
     * @return the number of doses
     */
    @ExcludeFromJacocoGeneratedReport
    public int getNumberDoses() {
        return numberDoses;
    }

    /***
     * Get the dosage of a vaccine
     * @return vaccine dosage
     */
    @ExcludeFromJacocoGeneratedReport
    public int getVaccineDosage() {
        return vaccineDosage;
    }

    /***
     * Get the list of administration processes
     * @return list of administration processes
     */
    @ExcludeFromJacocoGeneratedReport
    public List<AdministrationProcess> getAdmProcList() {
        return new ArrayList<>(admProcList);
    }

    /***
     * Set the name of the vaccine
     * @param name - name of the vaccine
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
     * Set the lot number of the vaccine
     * @param lotNumber - lot number of the vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public void setLotNumber(String lotNumber) throws OperationCanceledByUserException {
        if(Validations.isLotNumberValid(lotNumber, true, false)) {
            this.lotNumber = lotNumber;
        } else {
            throw new IllegalArgumentException("The lot number is not valid!");
        }
    }

    /***
     * Set the identifier (ID) of the vaccine
     * @param id - identifier (ID) of the vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public void setId(int id) throws OperationCanceledByUserException {
        if(Validations.isLengthValidLesserThen(id, true, true, SIZE_IDVACCINE)) {
            Id = id;
        } else {
            throw new IllegalArgumentException("The id is not valid!");
        }
    }

    /***
     * Set the brand of the vaccine
     * @param brand - brand of the vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public void setBrand(String brand) throws OperationCanceledByUserException {
        if(Validations.isStringLengthValid(brand, true, true, MAX_LENGTH_NAME)) {
            this.brand = brand;
        } else {
            throw new IllegalArgumentException("The brand is not valid!");
        }
    }

    /***
     * Set the type of the vaccine
     * @param vaccineType - type of the vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public void setVaccineType(String vaccineType) throws OperationCanceledByUserException {
        if(Validations.isStringLengthValid(vaccineType, true, false, 60)) {
            this.vaccineType = vaccineType;
        } else {
            throw new IllegalArgumentException("The vaccine type is not valid!");
        }
    }

    /***
     * Set the age group defined for a vaccine
     * @param ageGroup - age group for a specific vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public void setAgeGroup(String ageGroup) throws OperationCanceledByUserException {
        if(Validations.isAgeGroupValid(ageGroup, true, false)) {
            this.ageGroup = ageGroup;
        } else {
            throw new IllegalArgumentException("The age group is not valid!");
        }
    }

    /***
     * Set the number of doses of a vaccine
     * @param numberDoses - number of doses of a vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public void setNumberDoses(int numberDoses) throws OperationCanceledByUserException {
        if(Validations.isLengthValidLesserThen(numberDoses, true, false, SIZE_NUMBERDOSES)) {
            this.numberDoses = numberDoses;
        } else {
            throw new IllegalArgumentException("The number of doses is not valid!");
        }
    }

    /***
     * Set the vaccine dosage
     * @param vaccineDosage - dosage of a vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public void setVaccineDosage(int vaccineDosage) throws OperationCanceledByUserException {
        if(Validations.isLengthValidLesserThen(vaccineDosage, true, false, SIZE_VACCINEDOSAGE)) {
            this.vaccineDosage = vaccineDosage;
        } else {
            throw new IllegalArgumentException("The vaccine dosage is not valid!");
        }
    }

    /***
     * Set for the list of the administration processes
     * @param admProcList
     */
    @ExcludeFromJacocoGeneratedReport
    public void setAdmProcList(List<AdministrationProcess> admProcList) {
        //todo:validations of this set
        this.admProcList = new ArrayList<>(admProcList);
    }

    /***
     * Gives back all the attributes of the vaccine
     * @return the full description of a vaccine
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    //TODO: Age Group criar uns gets para apresentar melhor o intervalo
    public String toString() {
        return String.format("Vaccine Type: %s\nname: %s\nlot number: %s\nID: %d\nBrand: %s\nAge Group: %s\nNumber of" +
                             " Doses: %d\nVaccine Dosage: %d\n", vaccineType, name, lotNumber, Id, brand, ageGroup,
                             numberDoses, vaccineDosage);
    }


    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject) {
            return true;
        }
        if(otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        Vaccine otherVaccine = (Vaccine)otherObject;
        return this.lotNumber.equals(otherVaccine.lotNumber);
    }


    /***
     * Constructor of the Administration process
     * @param ageGroup                      - age group specified for an administration process
     * @param numberOfDoses                 - number of doses specified for an administration process
     * @param vaccineDosage                 - vaccine dosage for a vaccine, specified for an administration process
     * @param timeIntervalBetweenDoses      - time interval between doses specified for an administration process
     * @return Administration process
     */
    public AdministrationProcess specifyAdministrationProcess(String ageGroup, int numberOfDoses, int[] vaccineDosage,
                                                              int[] timeIntervalBetweenDoses, String vaccineAgeGroup)
            throws OperationCanceledByUserException {
        return new AdministrationProcess(ageGroup, numberOfDoses, vaccineDosage, timeIntervalBetweenDoses,
                                         vaccineAgeGroup);
    }


    /***
     * method to save a validated administration process of a vaccine
     * @param administrationProcess - administration process for a vaccine
     * @return true if added the validated administration process; false if operation is not successful
     */
    public boolean saveAdministrationProcess(AdministrationProcess administrationProcess) {
        if(!this.validateAdministrationProcess(administrationProcess)) {
            return this.addAdministrationProcess(administrationProcess);
        }
        return false;
    }

    /***
     * validates the administration process
     * @param administrationProcess - administration process of a specific vaccine
     * @return true if administration process doesn't exist
     */
    public boolean validateAdministrationProcess(AdministrationProcess administrationProcess) {
        try {
            if(administrationProcess == null) {
                return true;
            }
            List<AdministrationProcess> listAdministrationProcess = this.getAdmProcList();
            if(!listAdministrationProcess.isEmpty()) {
                for(AdministrationProcess adm : listAdministrationProcess) {
                    if(administrationProcess.equals(adm)) {
                        return true;
                    } else {
                        //checking if there is overlap
                        String[] ageGroupParts = administrationProcess.getAgeGroup().split(",");
                        int numI = parseInt(ageGroupParts[0]);
                        int numS = parseInt(ageGroupParts[1]);
                        String[] otherAgeGroupParts = adm.getAgeGroup().split(",");
                        int numIVaccine = parseInt(otherAgeGroupParts[0]);
                        int numSVaccine = parseInt(otherAgeGroupParts[1]);
                        if((numS >= numIVaccine && numS <= numSVaccine) ||
                           (numI >= numIVaccine && numI <= numSVaccine)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        catch(NullPointerException e) {
            return true;
        }
    }

    /***
     * adds the administration process for a vaccine
     * @param administrationProcess - administration process of a vaccine
     * @return true if administration process is added
     */
    public boolean addAdministrationProcess(AdministrationProcess administrationProcess) {
        return this.admProcList.add(administrationProcess);
    }

    /***
     * Method to validate if an age is inside the ageGroup interval limits
     * @param age - sns user age
     * @return true if age is inside the ageGroup interval limits, false if not
     */
    public boolean isAgeValid(int age) {
        String[] ageGroupArray;
        ageGroupArray = getAgeGroup().trim().split(",");
        int minAge = parseInt(ageGroupArray[0]);
        int maxAge = parseInt(ageGroupArray[1]);

        return (age >= minAge && age <= maxAge);
    }

    /***
     * Get vaccine administration obj by searching group age valid
     * @param age - current sns user age
     * @return - vaccine administration obj
     */
    public AdministrationProcess getAdministrationProcess(int age) {
        for(AdministrationProcess obj : admProcList) {
            if(obj.isAgeValid(age)) {
                return obj;
            }
        }
        return null;
    }
}
