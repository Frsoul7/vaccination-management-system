package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.utils.TimeHour;
import app.domain.model.utils.Validations;
import app.interfaces.Constants;
import app.interfaces.ExcludeFromJacocoGeneratedReport;
import app.ui.console.utils.Utils;

/***
 * @authors Edgar Moreira <1010100@isep.ipp.pt>
 *          Fernando Ribeiro <1060064@isep.ipp.pt>
 *          José Silva <1060568@isep.ipp.pt>
 *          Pedro Gomes <1060588@isep.ipp.pt>
 */

public class RegisterVaccinationCenterController implements Constants {

    /**
     * Instance of the app
     */
    private final App app;

    /**
     * Instance of the company
     */
    private final Company company;

    /**
     * Instance of the vaccination center store
     */
    private final VaccinationCenterStore vcStore;

    /**
     * Instance of the vaccination center
     */
    private VaccinationCenter vaccenter;

    /***
     * Instance of the controller to register a new vaccination center
     */
    public RegisterVaccinationCenterController() {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.vcStore = company.getVaccinationCenterStore();
    }

    /***
     * Method that verifies the list of parameters needed for registering a vaccination center of type "Massive"
     * @param name                         - name of the vaccination center
     * @param address                      - address of the vaccination center
     * @param phoneNumber                  - phone number of the vaccination center
     * @param emailAddress                 - email address of the vaccination center
     * @param faxNumber                    - fax number of the vaccination center
     * @param websiteAddress               - website address of the vaccination center
     * @param openHour                     - opening hour of the vaccination center
     * @param closeHour                    - closing hour of the vaccination center
     * @param slotDuration                 - slot duration (in minutes) of the vaccination center
     * @param maxVaccinesPerSlot           - maximum vaccines available per slot of time
     * @param vaccinationCenterType        - type of the vaccination center (massive or healthcare)
     * @return true if all parameters are verified, false if any fails verification
     */
    public boolean registerVaccinationCenter(String name, String address, long phoneNumber, String emailAddress,
                                             long faxNumber, String websiteAddress, String openHour, String closeHour,
                                             int slotDuration, int maxVaccinesPerSlot, String vaccinationCenterType) {

        try {
            //validated in isHourFormatValid method
            int hourOAux = TimeHour.hourTo24Format(openHour);
            int hourFAux = TimeHour.hourTo24Format(closeHour);
            int minOAux = TimeHour.minutes(openHour);
            int minFAux = TimeHour.minutes(closeHour);

            TimeHour openHour24 = new TimeHour(hourOAux, minOAux);
            TimeHour closeHour24 = new TimeHour(hourFAux, minFAux);

            this.vaccenter = vcStore.registerVaccinationCenter(name, address, phoneNumber, emailAddress, faxNumber,
                                                               websiteAddress, openHour24, closeHour24, slotDuration,
                                                               maxVaccinesPerSlot, vaccinationCenterType);
            return true;

        }
        catch(RuntimeException|OperationCanceledByUserException ex) {
            Utils.showText(ex.getMessage());
            return false;
        }
    }

    /***
     * Method that verifies the list of parameters needed for registering a vaccination center of type "Healthcare"
     * @param name                         - name of the vaccination center
     * @param address                      - address of the vaccination center
     * @param phoneNumber                  - phone number of the vaccination center
     * @param emailAddress                 - email address of the vaccination center
     * @param faxNumber                    - fax number of the vaccination center
     * @param websiteAddress               - website address of the vaccination center
     * @param openHour                     - opening hour of the vaccination center
     * @param closeHour                    - closing hour of the vaccination center
     * @param slotDuration                 - slot duration (in minutes) of the vaccination center
     * @param maxVaccinesPerSlot           - maximum vaccines available per slot of time
     * @param vaccinationCenterType        - type of the vaccination center (massive or healthcare)
     * @param designation                  - designation of the healthcare center (ARS or ARSE)
     * @return true if all parameters are verified, false if any fails verification
     */
    public boolean registerVaccinationCenter(String name, String address, long phoneNumber, String emailAddress,
                                             long faxNumber, String websiteAddress, String openHour, String closeHour,
                                             int slotDuration, int maxVaccinesPerSlot, String vaccinationCenterType,
                                             String designation) {

        try {

            //validated in isHourFormatValid method
            int hourOAux = TimeHour.hourTo24Format(openHour);
            int hourFAux = TimeHour.hourTo24Format(closeHour);
            int minOAux = TimeHour.minutes(openHour);
            int minFAux = TimeHour.minutes(closeHour);

            TimeHour openHour24 = new TimeHour(hourOAux, minOAux);
            TimeHour closeHour24 = new TimeHour(hourFAux, minFAux);

            this.vaccenter = vcStore.registerVaccinationCenter(name, address, phoneNumber, emailAddress, faxNumber,
                                                               websiteAddress, openHour24, closeHour24, slotDuration,
                                                               maxVaccinesPerSlot, vaccinationCenterType, designation);
            return true;

        }
        catch(RuntimeException|OperationCanceledByUserException ex) {
            Utils.showText(ex.getMessage());
            return false;
        }
    }

    /***
     * Verifies if vaccination center is of type "Massive", in order to choose which type of vaccination center to
     * present
     * @param vaccinationCenterType     - Type of vaccination center
     * @return true if is of type "Massive", false if it of type "Healthcare Center"
     */
    public boolean checkVaccinationType(String vaccinationCenterType) {
        return vaccinationCenterType.compareTo("Healthcare Center") != 0;
    }

    /***
     * Method to save a vaccination center in the store of vaccination centers
     * @return the vaccination center to be saved in the store
     */
    public boolean saveVaccinationCenter() {
        return this.vcStore.saveVaccinationCenter(vaccenter);
    }

    /***
     * Method to get the vaccination center's information in String format, according to its type
     * @return Information of "massive" vaccination center if true; Information of "healthcare" vaccination center if
     * false
     */
    @ExcludeFromJacocoGeneratedReport
    public String getVaccinationCenterString() {
        if(checkVaccinationType(this.vaccenter.getVaccinationCenterType())) {
            return this.vaccenter.toStringTotal();
        } else {
            return this.vaccenter.toStringHealth();
        }
    }


}
