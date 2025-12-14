package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccineType;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.store.VaccineTypeStore;
import app.domain.model.utils.Validations;
import app.ui.console.utils.Utils;

/**
 * @authors Fernando Ribeiro <1060064@isep.ipp.pt> José Silva <1060568@isep.ipp.pt> Pedro Gomes <1060588@isep.ipp.pt>
 */

public class SpecifyVaccineTypeController {

    /**
     * Instance of vaccine type store
     */
    private final VaccineTypeStore vaccineTypeStore;

    /**
     * Instance of vaccine type
     */
    private VaccineType vaccineType;

    /**
     * SpecifyVaccineTypeController constructor with default values
     */
    public SpecifyVaccineTypeController() {
        Company company = App.getInstance().getCompany();
        this.vaccineTypeStore = company.getVaccineTypeStore();
    }

    /**
     * Method used to register a new vaccination type
     *
     * @param designation of the vaccine type (ex: Covid19, Dengue, etc)
     *
     * @return boolean
     */
    public boolean registerVaccineType(String designation) {
        try {

            this.vaccineType = this.vaccineTypeStore.registerVaccineType(designation);
            return true;

        }
        catch(RuntimeException|OperationCanceledByUserException ex) {
            Utils.showText(ex.getMessage());
            return false;
        }
    }

    /**
     * Method used to save a new vaccine type to the vaccine type store
     *
     * @return boolean (true if vaccine type is registered with success, false if not)
     */
    public boolean saveVaccineType() {
        return this.vaccineTypeStore.saveVaccineType(this.vaccineType);
    }

    /**
     * Method to get vaccine type information
     *
     * @return String
     */
    public String vaccineTypeInformation() {
        return this.vaccineType.toString();
    }

}
