package app.dto;

import app.interfaces.ExcludeFromJacocoGeneratedReport;

public class VaccinationCenterDTO {

    /***
     * Name of vaccination center
     */
    private String name;

    /***
     * Type of vaccination center
     */
    private String vaccinationCenterType;

    /***
     * Vaccination Center identifier
     */
    private int vaccinationCenterID;


    /***
     *
     * @param name                  - Name of vaccination center
     * @param vaccinationCenterType - Type of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public VaccinationCenterDTO(String name, String vaccinationCenterType, int id) {
        this.name = name;
        this.vaccinationCenterType = vaccinationCenterType;
        this.vaccinationCenterID = id;
    }

    /***
     * Get name of vaccination center
     * @return name of vaccination center, is unique
     */
    @ExcludeFromJacocoGeneratedReport
    public String getName() {
        return this.name;
    }

    /***
     * Get Type of vaccination center
     * @return vaccinationCenterType of vaccination center
     */
    @ExcludeFromJacocoGeneratedReport
    public String getVaccinationCenterType() {
        return vaccinationCenterType;
    }


    @ExcludeFromJacocoGeneratedReport
    public int getVaccinationCenterId() {
        return this.vaccinationCenterID;
    }


    /**
     * Gives back the mass vaccination center text description
     *
     * @return Unique attributes of the vaccination center
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("Name: %s", this.getName());
    }


}
