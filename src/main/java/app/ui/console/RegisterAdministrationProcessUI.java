package app.ui.console;

import app.controller.SpecifyVaccineController;
import app.domain.model.Vaccine;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Validations;
import app.interfaces.Constants;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Specify an administration process
 *
 * @authors Fernando Ribeiro <1060064@isep.ipp.pt> José Silva <1060568@isep.ipp.pt>
 */
public class RegisterAdministrationProcessUI implements Runnable, Constants {

    /**
     * SpecifyVaccineController controller instance
     */
    private final SpecifyVaccineController specifyVaccineController;

    private Vaccine vaccine;
    /**
     * Empty register specify vaccine UI constructor
     */
    public RegisterAdministrationProcessUI() {
        this.specifyVaccineController = new SpecifyVaccineController();
    }

    /**
     * register specify vaccine UI constructor
     */
    public RegisterAdministrationProcessUI(SpecifyVaccineController specifyVaccineController) {
        this.specifyVaccineController = specifyVaccineController;
        this.vaccine=specifyVaccineController.getVac();
    }

    /**
     * Method used to run the SpecifyVaccineUI
     */
    @Override
    public void run() {

        Utils.showText("\n# # Specify New Administration Process # #");

        int option;
        List<String> options = new ArrayList<>();

        options.add("Specify New Administration Process");

        do {
            option = Utils.showAndSelectIndex(options, "\nSelect an option:");

            if((option >= 0) && (option < options.size())) {

                if(fillData()) {
                    showData();

                    if(Utils.confirm("Do you want to confirm the filled data? (Y/N): ")) {

                        try {
                            if(specifyVaccineController.saveAdministrationProcess()) {
                                Utils.showText("Administration process registered with success!");

                            } else {
                                Utils.showText("Administration process not registered, please try again!");
                            }
                        }
                        catch(RuntimeException ex) {
                            Utils.showText(ex.getMessage());
                        }
                    } else {
                        Utils.showText("Administration process not registered!");
                    }
                } else {
                    Utils.showText("[warning] Administration process not registered, please try again!");
                }

            }
        } while(option != -1);

    }


    /**
     * Method used to read the input from the user
     *
     * @return true if all the data is filled correctly
     */
    private boolean fillData() {

        Utils.showText("All fields with '*' are mandatory\n");

        try {
            // ageGroup: input & validation
            String ageGroup =
                    Utils.readLineFromConsole("Fill age group according with vaccine interval (E.g.: 20,55)" + " *: ");

            while(!Validations.isAgeGroupValid(ageGroup, true, true)) {
                ageGroup = Utils.readLineFromConsole("[error] - Invalid age group\nPlease try again: ");
            }

            // number of doses: input & validation
            int numberOfDoses =
                    Utils.readIntegerFromConsole("Number of Doses  (máx. 5)*: ", DEFAULT_VALUE_NUMBERDOSES_NUMBER);
            while(!Validations.isNumberDosesValidAdm(numberOfDoses, true, true, SIZE_NUMBEROFDOSESADM)) {
                numberOfDoses = Utils.readIntegerFromConsole("[error] - Invalid number of doses\nPlease try again: ",
                                                             DEFAULT_VALUE_NUMBERDOSES_NUMBER);
            }
            int[] vaccineDosage = new int[numberOfDoses];
            for(int i = 0; i < numberOfDoses; i++) {
                // vaccine dosage: input & validation

                vaccineDosage[i] = Utils.readIntegerFromConsole("Vaccine Dosage  (máx. 1000)*: ",
                                                                DEFAULT_VALUE_VACCINEDOSAGE_NUMBER);
                while(!Validations.isLengthValidLesserThen(vaccineDosage[i], true, true, SIZE_VACCINEDOSAGE)) {
                    vaccineDosage[i] =
                            Utils.readIntegerFromConsole("[error] - Invalid vaccine dosage\nPlease try again: ",
                                                         DEFAULT_VALUE_VACCINEDOSAGE_NUMBER);
                }
            }

            int[] timeIntervalBetweenDoses = new int[numberOfDoses - 1];

            for(int i = 0; i < numberOfDoses - 1; i++) {
                // vaccine dosage: input & validation

                timeIntervalBetweenDoses[i] = Utils.readIntegerFromConsole("Time Interval Between Doses  (máx. 366)*: ",
                                                                           DEFAULT_VALUE_VACCINEDOSAGE_NUMBER);
                while(!Validations.isTimeIntervalBetweenDosesValid(timeIntervalBetweenDoses[i], true, true,
                                                                   SIZE_INTERVALBETWEENDOSES)) {
                    timeIntervalBetweenDoses[i] = Utils.readIntegerFromConsole(
                            "[error] - Invalid Time Interval Between Doses\nPlease try again: ",
                            DEFAULT_VALUE_VACCINEDOSAGE_NUMBER);
                }
            }

            return this.specifyVaccineController.specifyAdministrationProcess(ageGroup, numberOfDoses, vaccineDosage,
                                                                              timeIntervalBetweenDoses);

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

        Utils.showText("\nAdministration Process information:\n" +
                       this.specifyVaccineController.vaccineAdministrationProcessInformation());
    }

}
