package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.store.EmployeeStore;
import app.domain.shared.EmployeeRoles;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.AuthFacade;

/**
 * @authors Edgar Moreira <1010100@isep.ipp.pt> Fernando Ribeiro <1060064@isep.ipp.pt> José Silva <1060568@isep.ipp.pt>
 * Pedro Gomes <1060588@isep.ipp.pt>
 */
public class RegisterEmployeeController {
    /**
     * Instance of the app
     */
    private final App app;

    /**
     * Instance of the company
     */
    private final Company company;

    /**
     * Instance of employee store
     */
    private EmployeeStore eStore;

    /**
     * Instance of employee
     */
    private Employee empl;

    /***
     * Instance of the AuthFacade class
     */
    private final AuthFacade authFacade;

    /**
     * RegisterEmployee constructor with default values
     */
    public RegisterEmployeeController() {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.eStore = this.company.getEmployeeStore();
        this.authFacade = company.getAuthFacade();
    }

    /**
     * Method used to register a new employee
     *
     * @param name              name of the employee
     * @param address           address of the employee
     * @param phoneNumber       phone number of the employee
     * @param email             email of the employee
     * @param citizenCardNumber employee citizen card number
     *
     * @return boolean
     */


    public boolean registerEmployee(String name, String address, long phoneNumber, String email, long citizenCardNumber,
                                    EmployeeRoles employeeRole) {
        try {

            this.empl = eStore.registerEmployee(name, address, phoneNumber, email, citizenCardNumber, employeeRole);
            return true;

        }
        catch(OperationCanceledByUserException|RuntimeException e) {
            Utils.showText(e.getMessage());
            return false;
        }
    }


    /**
     * Method used to add a new employee to employee store
     *
     * @return boolean (true if employee is register with success false if not)
     */


    public boolean saveEmployee() {

        return this.eStore.saveEmployee(this.empl, authFacade);
    }

    /**
     * Method to get employee information
     *
     * @return String
     */
    public String getEmployeeString() {
        return this.empl.toString();
    }


}
