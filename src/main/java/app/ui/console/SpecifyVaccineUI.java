package app.ui.console;

import app.controller.SpecifyVaccineController;
import app.domain.model.VaccineType;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Validations;
import app.interfaces.Constants;
import app.ui.console.utils.Utils;

import java.util.List;

/**
 * Specify vaccine and its administration process
 *
 * @authors Fernando Ribeiro <1060064@isep.ipp.pt> José Silva <1060568@isep.ipp.pt> Pedro Gomes <1060588@isep.ipp.pt>
 */
public class SpecifyVaccineUI implements Runnable, Constants {

    /**
     * SpecifyVaccineController controller instance
     */
    private final SpecifyVaccineController specifyVaccineController;

    /**
     * Empty register specify vaccine UI constructor
     */
    public SpecifyVaccineUI() {
        this.specifyVaccineController = new SpecifyVaccineController();
    }

    /**
     * Method used to run the SpecifyVaccineUI
     */
    @Override
    public void run() {
        Utils.showText("\n##### REGISTER A NEW VACCINE #####");

        if(fillData()) {
            showData();

            if(Utils.confirm("Do you want to confirm the filled data? (Y/N): ")) {

                try {
                    if(specifyVaccineController.saveVaccine()) {
                        Utils.showText("Vaccine registered with success!");

                        //Launch AdministrationProcessUI
                        new RegisterAdministrationProcessUI(specifyVaccineController).run();
                        if(specifyVaccineController.getVaccineAdministrationProcessList().isEmpty()) {
                            specifyVaccineController.deleteVaccine();
                            Utils.showText("[warning!] Vaccine was deleted because it was not defined an " +
                                           "Administration Process");
                        }
                    } else {
                        Utils.showText("Vaccine not registered, please try again!");
                    }
                }
                catch(RuntimeException ex) {
                    Utils.showText(ex.getMessage());
                }
            } else {
                Utils.showText("Vaccine not registered!");
            }

        } else {
            Utils.showText("[warning] Vaccine not registered, please try again!");
        }

    }


    /**
     * Method used to read the input from the user
     *
     * @return true if all the data is filled correctly
     */
    private boolean fillData() {
        try {

            // Vaccine Type: input
            int option = 0;
            String vaccineType = "";

            List<VaccineType> vacTypes = specifyVaccineController.getVaccinesTypes();
            if(!vacTypes.isEmpty()) {
                do {
                    option = Utils.showAndSelectIndex(vacTypes, "\nSelect an option from the list of Vaccine's Types:");

                    if((option >= 0) && (option < vacTypes.size())) {
                        vaccineType = vacTypes.get(option).getDesignation();
                        option = -1;
                    }
                } while(option != -1);
            } else {
                Utils.showText("There are no vaccine's types registered! Please register first a vaccine's type.");
                return false;
            }

            Utils.showText("All fields with '*' are mandatory\n");

            // name: input & validation
            String name = Utils.readLineFromConsole("Fill name (max. 60 characters) *: ");

            while(!Validations.isStringLengthValid(name, true, true, MAX_LENGTH_NAME) ||
                  !Validations.isNameValid(name, true, true)) {
                name = Utils.readLineFromConsole("[error] - Invalid name\nPlease try again: ");
            }


            // lotNumber: input & validation
            String lotNumber = Utils.readLineFromConsole("Fill lot number (E.g.: 21d5d-05) *: ");

            while(!Validations.isLotNumberValid(lotNumber, true, true)) {
                lotNumber = Utils.readLineFromConsole("[error] - Invalid lot number\nPlease try again: ");
            }

            // id: input & validation
            int id = Utils.readIntegerFromConsole("ID  (máx. 100000)*: ", DEFAULT_VALUE_IDVACCINE_NUMBER);
            while(!Validations.isLengthValidLesserThen(id, true, true, SIZE_IDVACCINE)) {
                id = Utils.readIntegerFromConsole("[error] - Invalid vaccine id\nPlease try again: ",
                                                  DEFAULT_VALUE_IDVACCINE_NUMBER);
            }

            // brand: input & validation
            String brand = Utils.readLineFromConsole("Fill brand (máx. 60 characters) *: ");

            while(!Validations.isStringLengthValid(brand, true, true, MAX_LENGTH_NAME)) {
                brand = Utils.readLineFromConsole("[error] - Invalid brand\nPlease try again: ");
            }

            // number of doses: input & validation
            int numberDoses =
                    Utils.readIntegerFromConsole("Number of Doses  (máx. 100000)*: ", DEFAULT_VALUE_NUMBERDOSES_NUMBER);
            while(!Validations.isLengthValidLesserThen(numberDoses, true, true, SIZE_NUMBERDOSES)) {
                numberDoses = Utils.readIntegerFromConsole("[error] - Invalid number of doses\nPlease try again: ",
                                                           DEFAULT_VALUE_NUMBERDOSES_NUMBER);
            }

            // vaccine dosage: input & validation
            int vaccineDosage =
                    Utils.readIntegerFromConsole("Vaccine Dosage  (máx. 1000)*: ", DEFAULT_VALUE_VACCINEDOSAGE_NUMBER);
            while(!Validations.isLengthValidLesserThen(vaccineDosage, true, true, SIZE_VACCINEDOSAGE)) {
                vaccineDosage = Utils.readIntegerFromConsole("[error] - Invalid vaccine dosage\nPlease try again: ",
                                                             DEFAULT_VALUE_VACCINEDOSAGE_NUMBER);
            }

            // ageGroup: input & validation
            String ageGroup = Utils.readLineFromConsole("Specify an age group (E.g.: 20,55) *: ");

            while(!Validations.isAgeGroupValid(ageGroup, true, true)) {
                ageGroup = Utils.readLineFromConsole("[error] - Invalid age group\nPlease try again: ");
            }

            return this.specifyVaccineController.specifyVaccine(name, lotNumber, id, brand, vaccineType, ageGroup,
                                                                numberDoses, vaccineDosage);
        }
        catch(OperationCanceledByUserException ex) {
            Utils.showText(ex.getMessage());
            return false;
        }
    }

    /**
     * Method used to show vaccine information created
     */
    private void showData() {

        Utils.showText("\nVaccine information:\n" + this.specifyVaccineController.vaccineInformation());
    }
}






