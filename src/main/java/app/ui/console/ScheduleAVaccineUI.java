package app.ui.console;

import app.controller.ScheduleAVaccineController;
import app.dto.VaccinationCenterDTO;
import app.dto.VaccineTypeDTO;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Validations;
import app.domain.shared.VaccinationCenterTypes;
import app.interfaces.Constants;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAVaccineUI implements Runnable, Constants {

    /**
     * Schedule a Vaccine controller instance
     */
    private final ScheduleAVaccineController scheduleAVaccineController;

    boolean smsNotification;


    /**
     * Empty register specify vaccine scheduler constructor
     */
    public ScheduleAVaccineUI() {
        this.scheduleAVaccineController = new ScheduleAVaccineController();
    }

    @Override
    public void run() {

        Utils.showText("\n# # Schedule a Vaccine # #");


        if(fillData()) {

            showData();

            if(Utils.confirm("Do you want to confirm the filled data? (Y/N): ")) {

                try {
                    if(scheduleAVaccineController.saveVaccineScheduler(smsNotification)) {
                        Utils.showText("Schedule process registered with success!");

                    } else {
                        Utils.showText("Schedule process not registered, please try again!");
                    }
                }
                catch(RuntimeException ex) {
                    Utils.showText(ex.getMessage());
                }
            } else {

                Utils.showText("Schedule process not registered!");
            }
        } else {
            Utils.showText("[warning] Schedule process not registered, please try again!");
        }


    }


    /**
     * Method used to read the input from the user
     *
     * @return true if all the data is filled correctly
     */
    private boolean fillData() {

        Utils.showText("All fields are mandatory\n");

        try {

            // SnsUserNumber: input & validation
            long snsUserNumber =
                    Utils.readLongFromConsole("SNS user number (9 digit numbers): ", DEFAULT_VALUE_SNS_USER_NUMBER);
            while(!Validations.isLengthValidEquals(snsUserNumber, true, true, SIZE_SNS_USER_NUMBER)) {
                snsUserNumber = Utils.readLongFromConsole("[error] - Invalid sns user number\nPlease try again: ",
                                                          DEFAULT_VALUE_SNS_USER_NUMBER);
            }

            if(!scheduleAVaccineController.validateSnsUserNumber(snsUserNumber)) {
                Utils.showText("You are not registered on the system, please contact the Administrator");
                return false;
            }

            List<VaccineTypeDTO> lVacType = scheduleAVaccineController.getVaccineTypes();

            // Vaccine Type: input
            int option;
            String vaccineType = "";

            if(!lVacType.isEmpty()) {

                do {
                    option = Utils.showAndSelectIndexWithoutCancel(lVacType, "\nList of Vaccine Types:");

                    if((option >= 0) && (option < lVacType.size())) {
                        vaccineType = lVacType.get(option).getDesignation();
                        option = -1;
                    }
                } while(option != -1);


            } else {
                Utils.showText(
                        "There are no vaccine types registered on the system, please contact the " + "Administrator");
                return false;
            }

            List<VaccinationCenterDTO> lVaccinationCenters = scheduleAVaccineController.getVaccinationCenters();

            // Vaccine Type: input
            option = 0;
            int vaccinationCenterID = 0;

            if(!lVaccinationCenters.isEmpty()) {
                do {
                    option = Utils.showAndSelectIndexWithoutCancel(lVaccinationCenters,
                                                                   "\nList of Vaccination Centers:");

                    if((option >= 0) && (option < lVaccinationCenters.size())) {
                        vaccinationCenterID = lVaccinationCenters.get(option).getVaccinationCenterId();

                        //verification of vaccination center type because of types of vaccines that could be
                        // administrated
                        if(!vaccineType.equalsIgnoreCase(COVID_VACCINE_TYPE_DESCRIPTION)) {
                            if(lVaccinationCenters.get(option).getVaccinationCenterType().compareTo(
                                    VaccinationCenterTypes.MASSVACCINATIONCENTER.getDescription()) == 0) {
                                Utils.showText(
                                        "The selected vaccination center is not in accordance with the " + "vaccine" +
                                        " type. Select a vaccination center that can administrate the " +
                                        "selected vaccine type");
                            } else {
                                option = -1;
                            }
                        } else {
                            option = -1;
                        }
                    }
                } while(option != -1);

            } else {
                Utils.showText("There are no vaccination centers registered on the system, please contact the" + " " +
                               "Administrator");
                return false;
            }

            //SMS Notification
            smsNotification =
                    Utils.confirm("Do you want to receive a SMS confirmation of vaccine scheduler? " + "(Y/N): ");

            // date: input & validation
            String date = Utils.readLineFromConsole("Insert the date for taking a vaccine (E.g.: dd/mm/yyyy):" + " ");
            while(!Validations.isDateFormatValid(date, true, true)) {
                date = Utils.readLineFromConsole("[error] - Invalid date\nPlease try again: ");
            }

            int[] selectedSlot = null;
            try {
                int[][] lTimeOfFreeSlots = scheduleAVaccineController.getTimeOfFreeSlots(date, vaccinationCenterID);

                if(lTimeOfFreeSlots == null) {
                    Utils.showText("The date or vaccination center that you choose are not valid!");
                    return false;
                } else {

                    List<String> options = new ArrayList<>();
                    List<int[]> slotHoursMinutes = new ArrayList<>();


                    for(int[] lTimeOfFreeSlot : lTimeOfFreeSlots) {

                        options.add(String.format("%02d:%02d - %02d:%02d", lTimeOfFreeSlot[0], lTimeOfFreeSlot[1],
                                                  lTimeOfFreeSlot[2], lTimeOfFreeSlot[3]));
                        slotHoursMinutes.add(lTimeOfFreeSlot);
                    }

                    option = 0;
                    do {
                        option = Utils.showAndSelectIndex(options, "\nAvailable Slots: ");

                        if((option >= 0) && (option < slotHoursMinutes.size())) {

                            selectedSlot = slotHoursMinutes.get(option);

                            option = -1;
                        }
                    } while(option != -1);

                }

            }
            catch(IllegalArgumentException e) {
                Utils.showText(e.getMessage());
                return false;
            }


            return scheduleAVaccineController.registerVaccineScheduler(snsUserNumber, vaccinationCenterID, vaccineType,
                                                                       date, selectedSlot);

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

        Utils.showText("\nSchedule information:\n" + this.scheduleAVaccineController.getScheduleInformation());


    }


}


