package app.ui.console;

import app.controller.RegisterArrivalOfSnsUserToTakeVaccineController;
import app.dto.VaccineSchedulerDTO;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Validations;
import app.interfaces.Constants;
import app.interfaces.ExcludeFromJacocoGeneratedReport;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/***
 * @authors Edgar Moreira <1010100@isep.ipp.pt>
 *          Fernando Ribeiro <1060064@isep.ipp.pt>
 *          José Silva <1060568@isep.ipp.pt>
 *          Pedro Gomes <1060588@isep.ipp.pt>
 */

public class RegisterArrivalOfSnsUserToTakeVaccineUI implements Runnable, Constants {
    private VaccineSchedulerDTO vaccScedulerSelected;
    /***
     * Vaccination Center Id attribute
     */
    private final int vaccinationCenterId;

    private long snsUserNumber;
    /***
     * Controller of the register the arrival of a SNS user to a vaccination center to take a vaccine
     */
    private final RegisterArrivalOfSnsUserToTakeVaccineController registerArrivalOfSnsUserToTakeVaccineController;


    /***
     * Register the arrival of a SNS User to take a vaccine ui
     * @param vaccinationCenterId
     */
    public RegisterArrivalOfSnsUserToTakeVaccineUI(int vaccinationCenterId) {
        this.registerArrivalOfSnsUserToTakeVaccineController = new RegisterArrivalOfSnsUserToTakeVaccineController();
        this.vaccinationCenterId = vaccinationCenterId;
        this.vaccScedulerSelected=null;
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public void run() {
        Utils.showText("\n# # Register Arrival Of SNS User To Take Vaccine # #");

        if(fillData()) {

            try {

                if(this.registerArrivalOfSnsUserToTakeVaccineController.addSnsUserToWaitingList(snsUserNumber)) {
                    Utils.showText("Sns user added to waiting list with success!");

                } else {
                    Utils.showText("Sns user not added to waiting list!");
                }
            }
            catch(RuntimeException ex) {
                Utils.showText(ex.getMessage());
            }

        } else {
            Utils.showText("[warning] Arrival Of SNS User To Take Vaccine not registered, please try again!");
        }
    }

    /***
     * method to verify if data fields are filled
     * @return true if all fields are fulfilled, false if they are not
     */
    private boolean fillData() {
        Utils.showText("All fields are mandatory\n");

        try {
            // SnsUserNumber: input & validation
             this.snsUserNumber =
                    Utils.readLongFromConsole("SNS user number (9 digit numbers): ", DEFAULT_VALUE_SNS_USER_NUMBER);
            while(!Validations.isLengthValidEquals(snsUserNumber, true, true, SIZE_SNS_USER_NUMBER)) {
                snsUserNumber = Utils.readLongFromConsole("[error] - Invalid sns user number\nPlease try again: ",
                                                          DEFAULT_VALUE_SNS_USER_NUMBER);
            }

//            List<VaccineSchedulerDTO> lVacScheSnsUser =
//                    registerArrivalOfSnsUserToTakeVaccineController.getVaccineScheduler(snsUserNumber,
//                                                                                        vaccinationCenterId);
//            if(lVacScheSnsUser == null) {
//                Utils.showText("The Sns User doesn't have any scheduling!");
//                return false;
//            }
//
//            int option;
//            boolean confirmation = false;
//            TimeHour selectedSchedulerTime = null;
//
//            do {
//                option = Utils.showAndSelectIndex(lVacScheSnsUser, "\nPlease select the appointment: ");
//
//                if((option >= 0) && (option < lVacScheSnsUser.size())) {
//                    selectedSchedulerTime = lVacScheSnsUser.get(option).getSchedulerTime();
//                    confirmation = true;
//                    option = -1;
//                }
//            } while(option != -1);
//
//            if(!confirmation) {
//                Utils.showText("Arrival time registration canceled by Receptionist!");
//                return false;
//            }
//
//            String arrivalTime;
//            // Time of arrival Hour: input & validation
//            arrivalTime = Utils.readLineFromConsole("Time of arrival (E.g.: hh:mm am) *: ");
//
//            while(!Validations.isHourFormatValid(arrivalTime, true, true)) {
//                arrivalTime = Utils.readLineFromConsole("[error] - Invalid hour format\nPlease try again: ");
//            }
//
//            return registerArrivalOfSnsUserToTakeVaccineController.setSnsUserArrivalTime(arrivalTime,
//                                                                                         selectedSchedulerTime,
//                                                                                         snsUserNumber);

            //new instructions
            List<VaccineSchedulerDTO> options=new ArrayList<>();
            List<VaccineSchedulerDTO> lVacScheSnsUser =
                    registerArrivalOfSnsUserToTakeVaccineController.getVaccineScheduler(snsUserNumber,
                                                                                        vaccinationCenterId);
            if(lVacScheSnsUser == null) {
                Utils.showText("The Sns User doesn't have any scheduling!");
                return false;
            }
            if(!lVacScheSnsUser.isEmpty()) {
                for(VaccineSchedulerDTO obj : lVacScheSnsUser) {
                    options.add(obj);
                }
                int optionSelected;
                boolean confirmation = false;
                do {
                    optionSelected = Utils.showAndSelectIndex(options, "Please select the appointment:");
                    if((optionSelected >= 0) && (optionSelected < options.size())) {

                        vaccScedulerSelected = options.get(optionSelected);
                        confirmation = true;
                        optionSelected = -1;
                    }
                } while(optionSelected != -1);

                if(!confirmation) {
                    Utils.showText("Arrival time registration canceled by Receptionist!");
                    return false;
                }
            } else {
                Utils.showText("The Sns User doesn't have any scheduling!");
                return false;
            }


            String arrivalTime;
            // Time of arrival Hour: input & validation
            arrivalTime = Utils.readLineFromConsole("Time of arrival (E.g.: hh:mm am) *: ");

            while(!Validations.isHourFormatValid(arrivalTime, true, true)) {
                arrivalTime = Utils.readLineFromConsole("[error] - Invalid hour format\nPlease try again: ");
            }


            return registerArrivalOfSnsUserToTakeVaccineController.setSnsUserArrivalTime(arrivalTime,vaccScedulerSelected.getDate(),
                                                                                         vaccScedulerSelected.getSchedulerTime(),
                                                                                         snsUserNumber);

        }
        catch(OperationCanceledByUserException ex) {
            Utils.showText(ex.getMessage());
            return false;
        }
    }

}
