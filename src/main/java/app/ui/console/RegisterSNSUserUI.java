package app.ui.console;


import app.controller.RegisterSNSUserController;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Validations;
import app.interfaces.Constants;
import app.ui.console.utils.Utils;

/**
 * @authors Pedro Gomes <1060588@isep.ipp.pt>
 */
public class RegisterSNSUserUI implements Runnable, Constants {

    /**
     * SNS User controller instance
     */
    private final RegisterSNSUserController controller;


    /**
     * Empty register sns user ui constructor
     */
    public RegisterSNSUserUI() {
        controller = new RegisterSNSUserController();
    }

    /**
     * Method used to run the register sns user UI
     */
    public void run() {
        Utils.showText("\n# # Register a new sns user # #");

        if(fillData()) {
            showData();

            if(Utils.confirm("Do you want to confirm the filled data? (Y/N): ")) {
                try {
                    if(controller.saveSnsUser()) {
                        Utils.showText("SNS User registered with success");
                    } else {
                        Utils.showText("SNS User not registered, please try again!");
                    }
                }
                catch(RuntimeException ex) {
                    Utils.showText(ex.getMessage());
                }
            } else {
                Utils.showText("SNS User not registered!");
            }
        } else {
            Utils.showText("[warning] SNS User not registered, please try again!");
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
            while(!Validations.isStringLengthValid(name, true, true, MAX_LENGTH_NAME) ||
                  !Validations.isNameValid(name, true, true)) {
                name = Utils.readLineFromConsole("[error] - Invalid content\nPlease try again: ");
            }

            // street address: input & validation
            String address = Utils.readLineFromConsole("Street address *: ");
            while(!Validations.isStringLengthValid(address, true, true, MAX_LENGTH_ADDRESS)) {
                address = Utils.readLineFromConsole("[error] - Invalid content\nPlease try again: ");
            }

            // gender: input & validation
            String gender = Utils.readLineFromConsole("Gender (male/female) : ");
            while(!Validations.isGenderValid(gender, false, true)) {
                gender = Utils.readLineFromConsole("[error] - Invalid content\nPlease try again: ");
            }

            // phone number: input & validation
            long phoneNumber =
                    Utils.readLongFromConsole("Phone number (9 digits number) *: ", DEFAULT_VALUE_PHONE_NUMBER);
            while(!Validations.isLengthValidEquals(phoneNumber, true, true, SIZE_PHONE_NUMBER)) {
                phoneNumber = Utils.readLongFromConsole("[error] - Invalid phone number\nPlease try again: ",
                                                        DEFAULT_VALUE_PHONE_NUMBER);
            }

            // e-mail: input & validation
            String email = Utils.readLineFromConsole("Email address (E.g.: email@email.com) *: ");
            while(!Validations.isEmailFormatValid(email, true, true)) {
                email = Utils.readLineFromConsole("[error] - Invalid email\nPlease try again: ");
            }

            // citizen card number: input & validation
            long citizenCardNumber = Utils.readLongFromConsole("Citizen card number (E.g.: 12345678) *: ",
                                                               DEFAULT_VALUE_CITIZEN_CARD_NUMBER);
            while(!Validations.isLengthValidEquals(citizenCardNumber, true, true, SIZE_CITIZEN_CARD_NUMBER)) {
                citizenCardNumber =
                        Utils.readLongFromConsole("[error] - Invalid citizen card number\nPlease try again: ",
                                                  DEFAULT_VALUE_CITIZEN_CARD_NUMBER);
            }

            // sns user number: input & validation
            long snsUserNumber =
                    Utils.readLongFromConsole("SNS user number (9 digits number) *: ", DEFAULT_VALUE_SNS_USER_NUMBER);
            while(!Validations.isLengthValidEquals(snsUserNumber, true, true, SIZE_SNS_USER_NUMBER)) {
                snsUserNumber = Utils.readLongFromConsole("[error] - Invalid sns user number\nPlease try again: ",
                                                          DEFAULT_VALUE_SNS_USER_NUMBER);
            }

            // birthdate: input & validation
            String birthDate = Utils.readLineFromConsole("Birth date in dd/mm/yyyy format (E.g.: 15/05/1988) *: ");
            while(!Validations.isDateFormatValid(birthDate, true, true)) {
                birthDate = Utils.readLineFromConsole("[error] - Invalid birth date\\nPlease try again: ");
            }

            if(gender == null || gender.length() == 0) {
                return controller.registerSNSUser(name, address, phoneNumber, email, citizenCardNumber, snsUserNumber,
                                                  birthDate);

            } else {
                return controller.registerSNSUser(name, address, gender, phoneNumber, email, citizenCardNumber,
                                                  snsUserNumber, birthDate);
            }

        }
        catch(OperationCanceledByUserException ex) {
            Utils.showText(ex.getMessage());
            return false;
        }
    }

    /**
     * Method used to show sns user information created
     */
    private void showData() {
        Utils.showText("\nSNS User information:\n " + controller.getSNSUserString());
    }

}
