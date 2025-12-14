package app.domain.model.store;

import app.domain.model.SNSUser;
import app.domain.model.VaccineType;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VaccineTypeStore implements Serializable {

    /**
     * List of vaccination types
     */
    private List<VaccineType> vaccineTypesList;

    /**
     * Empty VaccinationTypeStore constructor with default values
     */
    @ExcludeFromJacocoGeneratedReport
    public VaccineTypeStore() {
        vaccineTypesList = new ArrayList<>();
    }

    public VaccineTypeStore(VaccineTypeStore vaccineTypeStore) {
        vaccineTypesList = vaccineTypeStore.getVaccineTypesList();
    }


    /**
     * xx
     *
     * @return
     */
    @ExcludeFromJacocoGeneratedReport
    public List<VaccineType> getVaccineTypesList() {
        return new ArrayList<>(vaccineTypesList);
    }

    public VaccineType registerVaccineType(String designation) throws OperationCanceledByUserException {
        return new VaccineType(designation);
    }


    /**
     * Method used to add a vaccination type
     *
     * @param vaccineType VaccineType Object
     *
     * @return boolean (true if the vaccination type has been created and, false if not)
     */
    public boolean saveVaccineType(VaccineType vaccineType) {
        if(validateVaccineType(vaccineType)) {
            return addVaccinationType(vaccineType);
        }
        return false;
    }

    /**
     * xx
     *
     * @param vaccineType
     *
     * @return
     */
    public boolean validateVaccineType(VaccineType vaccineType) {
        try {
            if(!vaccineTypesList.isEmpty()) {
                for(VaccineType vaccineType1 : vaccineTypesList) {
                    if(vaccineType1.getDesignation().equals(vaccineType.getDesignation())) {
                        return false;
                    }
                }
            }
        }
        catch(NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * Method used to add a vaccine type to the vaccine types list
     *
     * @param vaccineType VaccineType Object
     *
     * @return boolean (true if has been added, false if not)
     */
    public boolean addVaccinationType(VaccineType vaccineType) {
        return vaccineTypesList.add(vaccineType);
    }

    public boolean addVaccineType(VaccineTypeStore vaccineTypeStore) throws OperationCanceledByUserException {
        if(!vaccineTypeStore.getVaccineTypesList().isEmpty()) {
            for(VaccineType vacT : vaccineTypeStore.vaccineTypesList) {
                new VaccineType(vacT);
            }
            return true;
        }
        return false;
    }


}