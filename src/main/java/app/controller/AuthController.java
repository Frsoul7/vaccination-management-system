package app.controller;

import app.interfaces.ExcludeFromJacocoGeneratedReport;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

/**
 * @author Paulo Maio <pam@isep.ipp.pt> Edgar Moreira <1010100@isep.ipp.pt> Fernando Ribeiro <1060064@isep.ipp.pt> José
 * Silva <1060568@isep.ipp.pt> Pedro Gomes <1060588@isep.ipp.pt>
 */

public class AuthController {

    /***
     * instances the App
     */
    private final App app;

    /***
     * Instances the AuthController
     */
    public AuthController() {
        this.app = App.getInstance();
    }

    /***
     * Method to verify if the login is OK
     * @param email - email of the user (employee or sns user)
     * @param pwd   - password of the user (employee or sns user)
     * @return true if login is OK, false if it fails
     */
    public boolean doLogin(String email, String pwd) {
        try {
            return this.app.doLogin(email, pwd);
        }
        catch(IllegalArgumentException ex) {
            return false;
        }
    }

    /***
     * Data Transfer Object for list of user roles
     * @return User roles if verified and logged in current session, return null if not
     */
    @ExcludeFromJacocoGeneratedReport
    public List<UserRoleDTO> getUserRoles() {
        if(this.app.getCurrentUserSession().isLoggedIn()) {
            return this.app.getCurrentUserSession().getUserRoles();
        }
        return null;
    }

    /***
     * method to do logout on the App
     */
    public void doLogout() {
        this.app.doLogout();
    }
}
