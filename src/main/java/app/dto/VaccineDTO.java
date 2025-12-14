package app.dto;

import app.interfaces.ExcludeFromJacocoGeneratedReport;

public class VaccineDTO {
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
     * VaccineDTO constructor
     * @param name - name of the vaccine
     * @param lotNumber - lot number of the vaccine
     * @param id - identifier of the vaccine
     * @param brand - brand of the vaccine
     * @param vaccineType - type of the vaccine (e.g. Covid-19)
     * @param ageGroup - age group specification of the vaccine
     */
    public VaccineDTO(String name, String lotNumber, int id, String brand, String vaccineType, String ageGroup) {
        setName(name);
        setLotNumber(lotNumber);
        setId(id);
        setBrand(brand);
        setVaccineType(vaccineType);
        setAgeGroup(ageGroup);
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
     * Set the name of the vaccine
     * @param name - name of the vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public void setName(String name) {
        this.name = name;
    }

    /***
     * Set the lot number of the vaccine
     * @param lotNumber - lot number of the vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    /***
     * Set the identifier (ID) of the vaccine
     * @param id - identifier (ID) of the vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public void setId(int id) {
        Id = id;
    }

    /***
     * Set the brand of the vaccine
     * @param brand - brand of the vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /***
     * Set the type of the vaccine
     * @param vaccineType - type of the vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    /***
     * Set the age group defined for a vaccine
     * @param ageGroup - age group for a specific vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    /***
     * Gives back some attributes of the vaccine (the ones selected for DTO)
     * @return partial description of a vaccine
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format(
                "- Vaccine type: %s | Vaccine name: %s | Lot number: %s | Age group: %s | Brand: %s | " + "Id: %d",
                this.getVaccineType(), this.getName(), this.getLotNumber(), this.getAgeGroup(), this.getBrand(),
                this.getId());
    }
}
