package app.dto;

import app.interfaces.ExcludeFromJacocoGeneratedReport;

public class VaccineTypeDTO {

    private String designation;

    @ExcludeFromJacocoGeneratedReport
    public VaccineTypeDTO(String designation) {
        this.designation = designation;
    }

    /**
     * Get the designation of the vaccine type
     *
     * @return designation
     */
    @ExcludeFromJacocoGeneratedReport
    public String getDesignation() {
        return designation;
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("\nDesignation: %s", this.getDesignation());
    }


}
