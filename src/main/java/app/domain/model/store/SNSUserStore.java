package app.domain.model.store;

import app.controller.App;
import app.domain.model.*;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.Notifications;
import app.interfaces.Constants;
import app.interfaces.ExcludeFromJacocoGeneratedReport;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/***
 * @author Paulo Maio <pam@isep.ipp.pt>
 *         Edgar Moreira <1010100@isep.ipp.pt>
 *         Fernando Ribeiro <1060064@isep.ipp.pt>
 *         José Silva <1060568@isep.ipp.pt>
 *         Pedro Gomes <1060588@isep.ipp.pt>
 */

public class SNSUserStore implements Constants, Serializable {


    /***
     * List of SNS Users
     */
    private List<SNSUser> snsUserList;

    /***
     * Constructor initializing instances
     * @param
     */
    @ExcludeFromJacocoGeneratedReport
    public SNSUserStore() {
        snsUserList = new ArrayList<>();
    }


    @ExcludeFromJacocoGeneratedReport
    public SNSUserStore(SNSUserStore otherSnsUserStore) {
        snsUserList = otherSnsUserStore.getSnsUserList();
    }

    /***
     * Complete constructor to register a new SNS User
     * @param name              - name of the sns user
     * @param address           - sns user's address
     * @param gender            - sns user's gender
     * @param phoneNumber       - sns user's phone number
     * @param email             - sns user's email address
     * @param citizenCardNumber - sns user's citizen card number
     * @param snsUserNumber     - sns user's number
     * @param birthDate         - sns user's birthdate
     * @return new SNS User instance
     */
    public SNSUser registerSNSUser(String name, String address, String gender, long phoneNumber, String email,
                                   long citizenCardNumber, long snsUserNumber, DateCustom birthDate)
            throws OperationCanceledByUserException {
        return new SNSUser(name, address, gender, phoneNumber, email, citizenCardNumber, snsUserNumber, birthDate);
    }

    /***
     * Partial constructor to register a new SNS User (without the gender field)
     * @param name              - name of the sns user
     * @param address           - sns user street address
     * @param phoneNumber       - sns user phone number
     * @param email             - sns user email
     * @param citizenCardNumber - sns user citizen card number
     * @param snsUserNumber     - sns user number
     * @param birthDate         - sns user birthdate
     * @return new SNS User instance
     */
    public SNSUser registerSNSUser(String name, String address, long phoneNumber, String email, long citizenCardNumber,
                                   long snsUserNumber, DateCustom birthDate) throws OperationCanceledByUserException {
        return new SNSUser(name, address, phoneNumber, email, citizenCardNumber, snsUserNumber, birthDate);
    }

    /***
     * Get the SNS user current list
     * @return the SNS current user list
     */
    @ExcludeFromJacocoGeneratedReport
    public List<SNSUser> getSnsUserList() {
        return new ArrayList<>(snsUserList);
    }

    /***
     * Method used to save a sns user
     * @param snsUser - Sns User instance
     * @return boolean (true if the sns user has been saved; false if not)
     */

    public boolean saveSnsUser(SNSUser snsUser, AuthFacade authFacade) {
        if(!authFacade.existsUser(snsUser.getEmail()) && !this.validateSnsUser(snsUser)) {
            try {
                String password = PasswordGenerator.getPassword();
                Notifications.sendSNSUserPassword(snsUser.getName(), snsUser.getEmail(), password);
                if(authFacade.addUserWithRole(snsUser.getName(), snsUser.getEmail(), password, ROLE_SNSUSER)) {
                    return addSnsUser(snsUser);
                }
            }
            catch(RuntimeException|IOException ex) {
                return false;
            }
        }
        return false;
    }

    /***
     * Method used to add a sns user to the sns user's list
     * @param snsUser - Sns User object
     * @return boolean (true if has been added, false if not)
     */
    private boolean addSnsUser(SNSUser snsUser) {
        return snsUserList.add(snsUser);
    }

    /***
     * Method to verify the existence of a specific sns user in a sns users' list
     * @param snsUser - SNS User object
     * @return true if a certain unique attribute is found in an existent sns user, false if not
     */
    public boolean validateSnsUser(SNSUser snsUser) {
        try {
            if(snsUser == null) {
                return true;
            }
            List<SNSUser> listSnsU = this.getSnsUserList();
            if(!listSnsU.isEmpty()) {
                for(SNSUser snsu : listSnsU) {
                    if(snsUser.equalsUniqueAttributes(snsu)) {
                        return true;
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
     * Checks the existence of an SNS User in SNS User Store based on SNS User Number
     * @param snsUserNumber     - SNS User Number
     * @return true is a certain unique attribute is found in an existent sns user, false if not
     */
    public boolean validateSnsUser(long snsUserNumber) {
        try {
            List<SNSUser> listSnsU = this.getSnsUserList();
            if(!listSnsU.isEmpty()) {
                for(SNSUser snsu : listSnsU) {
                    if(snsUserNumber == snsu.getSnsUserNumber()) {
                        return true;
                    }
                }
            }
            return false;
        }
        catch(NullPointerException e) {
            return false;
        }
    }

    public boolean addSnsUsersAndAllOtherObjects(SNSUserStore snsUserStore) throws OperationCanceledByUserException {
        if(!snsUserStore.getSnsUserList().isEmpty()) {
            for(SNSUser snsU : snsUserStore.snsUserList) {
                SNSUser snsAux = new SNSUser(snsU);
                if(!snsAux.getHealthRecords().getVaccRecordsStore().getListVaccRecords().isEmpty()) {
                    for(VaccinationRecords vaccRecord : snsAux.getHealthRecords().getVaccRecordsStore()
                                                              .getListVaccRecords()) {
                        new VaccinationRecords(vaccRecord);
                    }
                }
            }
            return true;
        }
        return false;
    }


    public SNSUser getSnsUserBySnsUserNumber(long snsUserNumber) {
        SNSUser resultSnsUser = null;

        for(SNSUser snsUser : snsUserList) {
            if(snsUserNumber == snsUser.getSnsUserNumber()) {
                resultSnsUser = snsUser;
            }
        }

        return resultSnsUser;
    }

}
