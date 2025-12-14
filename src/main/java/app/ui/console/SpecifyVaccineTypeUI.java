package app.ui.console;

import app.controller.SpecifyVaccineTypeController;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Validations;
import app.interfaces.Constants;
import app.ui.console.utils.Utils;

/**
 * @authors Fernando Ribeiro <1060064@isep.ipp.pt> Jose Silva <1060568@isep.ipp.pt> Pedro Gomes <1060588@isep.ipp.pt>
 */


public class SpecifyVaccineTypeUI implements Runnable, Constants {
    /**
     * SpecifyVaccineTypeController controller instance
     */
    private final SpecifyVaccineTypeController specifyVaccineTypeController;

    /**
     * Empty register specify vaccine type UI constructor
     */
    public SpecifyVaccineTypeUI() {
        this.specifyVaccineTypeController = new SpecifyVaccineTypeController();
    }

    /**
     * Method used to run the SpecifyVaccineTypeUI
     */
    @Override
    public void run() {
        Utils.showText("\n##### REGISTER A NEW VACCINE'S TYPE #####");

        if(fillData()) {
            showData();

            if(Utils.confirm("Do you want to confirm the filled data? (Y/N): ")) {
                try {
                    if(specifyVaccineTypeController.saveVaccineType()) {
                        Utils.showText("Vaccine's type registered with success!");
                    } else {
                        Utils.showText("Vaccine's type not registered, please try again!");
                    }
                }
                catch(RuntimeException ex) {
                    Utils.showText(ex.getMessage());
                }
            } else {
                Utils.showText("Vaccine's type not registered!");
            }
        } else {
            Utils.showText("[warning] Vaccine's type not registered, please try again!");
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
            String designation = Utils.readLineFromConsole("Fill designation (max. 60 characters) *: ");

            while(!Validations.isStringLengthValid(designation, true, true, MAX_LENGTH_DESIGNATION)) {
                designation = Utils.readLineFromConsole("[error] - Invalid content\nPlease try again: ");
            }

            return this.specifyVaccineTypeController.registerVaccineType(designation);
        }
        catch(OperationCanceledByUserException ex) {
            Utils.showText(ex.getMessage());
            return false;
        }
    }

    /**
     * Method used to show vaccine type information created
     */
    private void showData() {
        Utils.showText("\nVaccine's type information:\n " + this.specifyVaccineTypeController.vaccineTypeInformation());
    }
}
