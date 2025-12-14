package app.ui.console;

import app.controller.RegisterVaccinationCenterController;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.TimeHour;
import app.domain.model.utils.Validations;
import app.domain.shared.VaccinationCenterTypes;
import app.interfaces.Constants;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/***
 * @authors Edgar Moreira <1010100@isep.ipp.pt>
 *          Fernando Ribeiro <1060064@isep.ipp.pt>
 *          José Silva <1060568@isep.ipp.pt>
 *          Pedro Gomes <1060588@isep.ipp.pt>
 */

public class RegisterVaccinationCenterUI implements Runnable, Constants {

    /***
     * Vaccination Center controller instance
     */
    private final RegisterVaccinationCenterController controller;

    /***
     * Register vaccination center ui constructor
     */
    public RegisterVaccinationCenterUI() {
        controller = new RegisterVaccinationCenterController();
    }

    /***
     * Method used to run the Vaccination center register
     */
    @Override
    public void run() {
        Utils.showText("\n##### REGISTER A NEW VACCINATION CENTER #####");

        if(fillData()) {
            showData();

            if(Utils.confirm("Do you want to confirm the filled data? (Y/N): ")) {
                try {
                    if(controller.saveVaccinationCenter()) {
                        Utils.showText("Vaccination center registered with success");
                    } else {
                        Utils.showText("Vaccination center not registered, please try again!");
                    }
                }
                catch(RuntimeException ex) {
                    Utils.showText(ex.getMessage());
                }
            } else {
                Utils.showText("Vaccination center not registered!");
            }
        } else {
            Utils.showText("[warning] Vaccination center not registered, please try again!");
        }
    }

    /***
     * Method used to read the input from the user
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

            // phone number: input & validation
            long phoneNumber =
                    Utils.readLongFromConsole("Phone number (9 digit numbers) *: ", DEFAULT_VALUE_PHONE_NUMBER);
            while(!Validations.isLengthValidEquals(phoneNumber, true, true, SIZE_PHONE_NUMBER)) {
                phoneNumber = Utils.readLongFromConsole("[error] - Invalid phone number\nPlease try again: ",
                                                        DEFAULT_VALUE_PHONE_NUMBER);
            }
            // fax number: input & validation
            long faxNumber = Utils.readLongFromConsole("Fax number (9 digit numbers) *: ", DEFAULT_VALUE_FAX_NUMBER);
            while(!Validations.isLengthValidEquals(faxNumber, true, true, SIZE_FAX_NUMBER)) {
                faxNumber = Utils.readLongFromConsole("[error] - Invalid fax number\nPlease try again: ",
                                                      DEFAULT_VALUE_FAX_NUMBER);
            }
            //webSite: input & validation
            String webSite = Utils.readLineFromConsole("Website address (E.g.: www.something.pt) *: ");
            while(!Validations.isWebSiteFormatValid(webSite, true, true)) {
                webSite = Utils.readLineFromConsole("[error] - Invalid Website\nPlease try again: ");
            }

            // e-mail: input & validation
            String email = Utils.readLineFromConsole("Email address (E.g.: email@dgs.pt) *: ");
            while(!Validations.isEmailFormatValid(email, true, true)) {
                email = Utils.readLineFromConsole("[error] - Invalid email\nPlease try again: ");
            }

            // Open Hour and Close Hour check
            String openHour;
            String closeHour;
            int control;
            do {
                control = 0;
                // Open Hour: input & validation
                openHour = Utils.readLineFromConsole("Opening Hours (E.g.: hh:mm am) *: ");
                while(!Validations.isHourFormatValid(openHour, true, true)) {
                    openHour = Utils.readLineFromConsole("[error] - Invalid hour format\nPlease try again: ");
                }
                // Close Hour: input & validation
                closeHour = Utils.readLineFromConsole("Closing Hours (E.g.: hh:mm am) *: ");
                while(!Validations.isHourFormatValid(closeHour, true, true)) {
                    closeHour = Utils.readLineFromConsole("[error] - Invalid hour format\nPlease try again: ");
                }
                if(TimeHour.hourTo24Format(openHour) > TimeHour.hourTo24Format(closeHour)) {
                    control = 1;
                    System.out.println("[error] - Invalid open schedule\n");
                } else if(TimeHour.hourTo24Format(openHour) == TimeHour.hourTo24Format(closeHour)) {
                    if(TimeHour.minutes(openHour) >= TimeHour.minutes(closeHour)) {
                        control = 1;
                        System.out.println("[error] - Invalid open schedule\n");
                    }
                }

            } while(control != 0);

            // Slot Duration: input & validation
            int slotDuration = Utils.readIntegerFromConsole("Slot duration in minutes (máx. 60 minutes)*: ",
                                                            DEFAULT_VALUE_SLOTDURATION_NUMBER);
            while(!Validations.isValueValid(slotDuration, true, true, MAX_SLOTDURATION_NUMBER)) {
                slotDuration = Utils.readIntegerFromConsole("[error] - Invalid slot duration time\nPlease try again: ",
                                                            DEFAULT_VALUE_SLOTDURATION_NUMBER);
            }

            // Max Vaccines per Slot: input & validation
            int maxNumberOfVaccinesPerSlot =
                    Utils.readIntegerFromConsole("Max number of vaccines per slot  (máx. 10000)*: ",
                                                 DEFAULT_VALUE_MAXVACPERSLOT_NUMBER);
            while(!Validations.isValueValid(maxNumberOfVaccinesPerSlot, true, true, MAX_MAXVACPERSLOT_NUMBER)) {
                maxNumberOfVaccinesPerSlot = Utils.readIntegerFromConsole(
                        "[error] - Invalid number of " + "vaccines per slot\nPlease try again: ",
                        DEFAULT_VALUE_MAXVACPERSLOT_NUMBER);
            }

            // Vaccination Center Type: input
            int option = 0;
            String vaccinationCenterType = "";

            List<String> options = new ArrayList<>();

            for(VaccinationCenterTypes opt : VaccinationCenterTypes.values()) {
                options.add(opt.getDescription());
            }

            do {
                option = Utils.showAndSelectIndex(options, "\nPlease choose a type of vaccination center:");

                if((option >= 0) && (option < options.size())) {
                    vaccinationCenterType = options.get(option);
                    option = -1;
                }
            } while(option != -1);


            if(vaccinationCenterType.compareTo(VaccinationCenterTypes.HEALTHCARECENTER.getDescription()) == 0) {
                // healthcare center designation: input & validation
                String healthcareDesignation = Utils.readLineFromConsole("Healthcare designation *: ");
                while(!Validations.isStringLengthValid(healthcareDesignation, true, true,
                                                       MAX_LENGTH_HEALTHCAREDESIGNATION)) {
                    healthcareDesignation = Utils.readLineFromConsole("[error] - Invalid content\nPlease try again: ");
                }

                return controller.registerVaccinationCenter(name, address, phoneNumber, email, faxNumber, webSite,
                                                            openHour, closeHour, slotDuration,
                                                            maxNumberOfVaccinesPerSlot, vaccinationCenterType,
                                                            healthcareDesignation);

            }

            return controller.registerVaccinationCenter(name, address, phoneNumber, email, faxNumber, webSite, openHour,
                                                        closeHour, slotDuration, maxNumberOfVaccinesPerSlot,
                                                        vaccinationCenterType);

        }


        catch(OperationCanceledByUserException ex) {
            Utils.showText(ex.getMessage());
            return false;
        }

    }

    /***
     * Method that shows the vaccination center information which was inserted, for confirmation by user
     */
    private void showData() {
        Utils.showText("\nVaccination Center information inserted:\n " + controller.getVaccinationCenterString());
    }
}
