package app.domain.model;


import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Validations;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.Serializable;

/***
 * @authors Edgar Moreira <1010100@isep.ipp.pt>
 *          Fernando Ribeiro <1060064@isep.ipp.pt>
 *          José Silva <1060568@isep.ipp.pt>
 *          Pedro Gomes <1060588@isep.ipp.pt>
 */

public class VaccineType implements Serializable {

    /***
     * Designation of the vaccine type
     */
    private String designation;

    /***
     * Defines designation for vaccine type
     * @param designation - designation of the vaccine type
     */
    @ExcludeFromJacocoGeneratedReport
    public VaccineType(String designation) throws OperationCanceledByUserException {
        this.setDesignation(designation);
    }

    @ExcludeFromJacocoGeneratedReport
    public VaccineType(VaccineType otherVaccineType) throws OperationCanceledByUserException {
        this.designation = otherVaccineType.getDesignation();
    }

    /***
     * Get the designation of the vaccine type
     * @return designation
     */
    @ExcludeFromJacocoGeneratedReport
    public String getDesignation() {
        return designation;
    }

    /***
     * Set the designation of the vaccine type
     * @param designation - designation of the vaccine type
     */
    @ExcludeFromJacocoGeneratedReport
    public void setDesignation(String designation) throws OperationCanceledByUserException {

        if(Validations.isStringLengthValid(designation, true, false, 60)) {
            this.designation = designation;
        } else {
            throw new IllegalArgumentException("The designation is not valid!");
        }
    }

    /***
     * Gives back the designation of the vaccine type
     * @return designation
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("%nDesignation: %s", this.getDesignation());
    }

    /***
     * Verifies if the vaccine type designation already exists
     * @param otherObject - object vaccine type for comparison
     * @return true if designation of vaccine type object exists; false if it doesn't exist
     */
    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject) {
            return true;
        }
        if(otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        VaccineType vaccineType = (VaccineType)otherObject;
        return getDesignation().equals(vaccineType.getDesignation());
    }

}
