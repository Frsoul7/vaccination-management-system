package app.controller;

import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.store.SNSUserStore;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.Validations;
import app.interfaces.Constants;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.AuthFacade;

/***
 * @authors Edgar Moreira <1010100@isep.ipp.pt>
 *          Fernando Ribeiro <1060064@isep.ipp.pt>
 *          José Silva <1060568@isep.ipp.pt>
 *          Pedro Gomes <1060588@isep.ipp.pt>
 */

public class RegisterSNSUserController implements Constants {

    /**
     * Instance of the app
     */
    private final App app;

    /**
     * Instance of the company
     */
    private final Company company;

    /**
     * Instance of sns user store
     */
    private final SNSUserStore snsUStore;

    /**
     * Instance of sns user
     */
    private SNSUser snsUser;

    /***
     * Instance of the AuthFacade class
     */
    private final AuthFacade authFacade;

    /**
     * Constructor initializing instances
     */
    public RegisterSNSUserController() {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.snsUStore = this.company.getSnsUserStore();
        this.authFacade = company.getAuthFacade();
    }

    /**
     * Complete registerSNSUser method used to register a new sns user
     *
     * @param name              - name of the sns user
     * @param gender            - sns user gender
     * @param phoneNumber       - sns user phone number
     * @param email             - sns user email
     * @param citizenCardNumber - sns user citizen card number
     * @param snsUserNumber     - sns user number
     * @param birthDate         - sns user birthdate
     *
     * @return boolean
     */
    public boolean registerSNSUser(String name, String address, String gender, long phoneNumber, String email,
                                   long citizenCardNumber, long snsUserNumber, String birthDate) {
        try {
            if(Validations.isDateFormatValid(birthDate, true, false)) {
                DateCustom birthDateConverted = new DateCustom(birthDate);
                this.snsUser =
                        this.snsUStore.registerSNSUser(name, address, gender, phoneNumber, email, citizenCardNumber,
                                                       snsUserNumber, birthDateConverted);
                return true;
            }
            return false;
        }
        catch(OperationCanceledByUserException|RuntimeException e) {
            Utils.showText(e.getMessage());
            return false;
        }
    }

    /**
     * Partial registerSNSUser method used to register a new sns user
     *
     * @param name              - name of the sns user
     * @param phoneNumber       - sns user phone number
     * @param email             - sns user email
     * @param citizenCardNumber - sns user citizen card number
     * @param snsUserNumber     - sns user number
     * @param birthDate         - sns user birthdate
     *
     * @return boolean
     */

    public boolean registerSNSUser(String name, String address, long phoneNumber, String email, long citizenCardNumber,
                                   long snsUserNumber, String birthDate) {
        try {

            if(Validations.isDateFormatValid(birthDate, true, false)) {
                DateCustom birthDateConverted = new DateCustom(birthDate);
                this.snsUser = this.snsUStore.registerSNSUser(name, address, phoneNumber, email, citizenCardNumber,
                                                              snsUserNumber, birthDateConverted);
                return true;
            }
            return false;
        }
        catch(OperationCanceledByUserException|RuntimeException e) {
            Utils.showText(e.getMessage());
            return false;
        }
    }

    /**
     * Method used to add a new sns user to sns user store
     *
     * @return boolean (true if sns user is registered with success false if not)
     */
    public boolean saveSnsUser() {
        return this.snsUStore.saveSnsUser(this.snsUser, authFacade);
    }

    /**
     * Method to get sns user information
     *
     * @return String
     */

    // todo@josé:falta verificar este fluxo
    public String getSNSUserString() {
        return this.snsUser.toString();
    }

}
