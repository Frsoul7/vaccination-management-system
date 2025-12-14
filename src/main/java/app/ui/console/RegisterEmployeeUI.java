package app.ui.console;

import app.controller.RegisterEmployeeController;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Validations;
import app.domain.shared.EmployeeRoles;
import app.interfaces.Constants;
import app.ui.console.utils.Utils;

/**
 * @authors Edgar Moreira <1010100@isep.ipp.pt> Fernando Ribeiro <1060064@isep.ipp.pt> José Silva <1060568@isep.ipp.pt>
 * Pedro Gomes <1060588@isep.ipp.pt>
 */
public class RegisterEmployeeUI implements Runnable, Constants {

    /**
     * Employee controller instance
     */
    private final RegisterEmployeeController controller;

    /**
     * Empty register employee ui constructor
     */
    public RegisterEmployeeUI() {
        controller = new RegisterEmployeeController();
    }

    /**
     * Method used to run the register employee UI
     */
    public void run() {
        Utils.showText("\n##### REGISTER A NEW EMPLOYEE #####");

        if(fillData()) {
            showData();

            if(Utils.confirm("Do you want to confirm the filled data? (Y/N): ")) {
                try {
                    if(controller.saveEmployee()) {
                        Utils.showText("Employee registered with success");
                    } else {
                        Utils.showText("Employee not registered, please try again!");
                    }
                }
                catch(RuntimeException ex) {
                    Utils.showText(ex.getMessage());
                }
            } else {
                Utils.showText("Employee not registered!");
            }
        } else {
            Utils.showText("[warning] Employee not registered, please try again!");
        }
    }

    /**
     * Method used to read the input from the user
     *
     * @return true if all the data is filled correctly
     */
    private boolean fillData() {
        try {
            Utils.showText("All fields with '*' are mandatory\n");

            // name: input & validation
            String name = Utils.readLineFromConsole("Full name (máx. 60 characters) *: ");
            while(!Validations.isStringLengthValid(name, true, true, MAX_LENGTH_NAME)|
                  !Validations.isNameValid(name, true, true)) {
                name = Utils.readLineFromConsole("[error] - Invalid content\nPlease try again: ");
            }

            // street address: input & validation
            String address = Utils.readLineFromConsole("Street address *: ");
            while(!Validations.isStringLengthValid(address, true, true, MAX_LENGTH_ADDRESS)) {
                address = Utils.readLineFromConsole("[error] - Invalid content\nPlease try again: ");
            }

            // phone number: input & validation
            long phoneNumber =
                    Utils.readLongFromConsole("Phone number (9 digit numbers) *: ", DEFAULT_VALUE_PHONE_NUMBER);
            while(!Validations.isLengthValidEquals(phoneNumber, true, true, SIZE_PHONE_NUMBER)) {
                phoneNumber = Utils.readLongFromConsole("[error] - Invalid phone number\nPlease try again: ",
                                                        DEFAULT_VALUE_PHONE_NUMBER);
            }

            // e-mail: input & validation
            String email = Utils.readLineFromConsole("Email address (E.g.: email@dgs.pt) *: ");
            while(!Validations.isEmailFormatValid(email, true, true)) {
                email = Utils.readLineFromConsole("[error] - Invalid email\nPlease try again: ");
            }

            // citizen card number: input & validation
            long citizenCardNumber = Utils.readLongFromConsole("Citizen card number (8 digit number) *: ",
                                                               DEFAULT_VALUE_CITIZEN_CARD_NUMBER);
            while(!Validations.isLengthValidEquals(citizenCardNumber, true, true, SIZE_CITIZEN_CARD_NUMBER)) {
                citizenCardNumber =
                        Utils.readLongFromConsole("[error] - Invalid citizen card number\nPlease try again: ",
                                                  DEFAULT_VALUE_CITIZEN_CARD_NUMBER);
            }

            // role: input & validation
            String roleString = Utils.readLineFromConsole("Employee role (E.g.: NURSE) *: ");
            while(!EmployeeRoles.isRoleDescriptionValid(roleString)) {
                roleString =
                        Utils.readLineFromConsole("[error] - Invalid employee role description\nPlease try again: ");
            }
            EmployeeRoles employeeRole = EmployeeRoles.getRoleIdFromDescription(roleString);


            return controller.registerEmployee(name, address, phoneNumber, email, citizenCardNumber, employeeRole);

        }
        catch(OperationCanceledByUserException ex) {
            Utils.showText(ex.getMessage());
            return false;
        }

    }

    /**
     * Method used to show employee information created
     */
    private void showData() {
        Utils.showText("\nEmployee information:\n " + controller.getEmployeeString());
    }
}
