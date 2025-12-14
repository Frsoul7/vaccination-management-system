package app.ui.console;

import app.controller.RecordAdministrationOfAVaccineToSnsUserController;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Validations;
import app.dto.*;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class RecordAdministrationOfAVaccineToSnsUserUI implements Runnable {

    /***
     * Vaccination Center Id attribute
     */
    private int vaccinationCenterId;

    private String vaccAdministratrionTime;

    private WaitingRoomDTO snsUserSelected;

    private VaccineDTO vaccineWithLotNumberSelected;
    /**
     * Record administration of a vaccine to sns user instance
     */
    private final RecordAdministrationOfAVaccineToSnsUserController controller;

    private static final String VACC_ADMINISTRATION_TIME_BY_DEFAULT = "00:00";

    /***
     * RecordAdministrationOfAVaccineToSnsUserUI constructor
     * @param vaccinationCenterId
     */
    public RecordAdministrationOfAVaccineToSnsUserUI(int vaccinationCenterId) {
        this.vaccinationCenterId = vaccinationCenterId;
        controller = new RecordAdministrationOfAVaccineToSnsUserController();
        this.snsUserSelected = null;
        this.vaccineWithLotNumberSelected = null;
        vaccAdministratrionTime = VACC_ADMINISTRATION_TIME_BY_DEFAULT;
    }

    public void run() {
        Utils.showText("\n##### RECORD ADMINISTRATION OF A VACCINE TO SNS USER #####");

        if(interactionSequence()) {
            Utils.showText("\nVaccine administration record success");
        }else{
            Utils.showText("\n[warning] vaccine administration record failed, please try again or contact your " +
                           "maintenance team");
        }
    }

    private boolean interactionSequence() {
        //region 1. Shows sns users in waiting room and picks one --------------------------------------------
        Utils.showText("Please, select a sns user from the waiting room list\n");

        try {
            List<WaitingRoomDTO> options = new ArrayList<>();
            List<WaitingRoomDTO> listWaitingRoomDTO = controller.getListOfSnsUsers(vaccinationCenterId);
            if(!listWaitingRoomDTO.isEmpty()) {
                for(WaitingRoomDTO obj : listWaitingRoomDTO) {
                    options.add(obj);
                }

                int optionSelected;
                do {
                    optionSelected = Utils.showAndSelectIndex(options, "List of sns users in waiting room:");
                    if((optionSelected >= 0) && (optionSelected < options.size())) {

                        snsUserSelected = options.get(optionSelected);
                        optionSelected = -1;
                    }
                } while(optionSelected != -1);

                Utils.showText("\nSelected sns user: " + snsUserSelected.toString());
            } else {
                Utils.showText("Attention!: The waiting room list is empty");
                return false;
            }
        }
        catch(RuntimeException ex) {
            Utils.showText(ex.getMessage());
            Utils.showText("[warning] the list could not be presented, please contact your maintenance team!");
            return false;
        }
        //endregion 1.
        //region 2. Shows sns user info and health conditions and confirms ----------------------------------

        InfoAndHealthConditionsDTO infoAndHealthConditionsDto =
                controller.getInfoAndHealthConditions(snsUserSelected.getSnsUserNumber());
        Utils.showText("\nSns user info and health conditions: \n" + infoAndHealthConditionsDto.toString());


        //endregion 2.
        //region 3. Shows vaccines list and picks one vaccine by lot number ----------------------------------
        if(Utils.confirm("Do you want to confirm the presented data? (Y/N): ")) {
            try {
                // date: input & validation
                String date =
                        Utils.readLineFromConsole("\nInsert the date for taking the vaccine (E.g.: dd/mm/yyyy):" + " ");
                while(!Validations.isDateFormatValid(date, true, true)) {
                    date = Utils.readLineFromConsole("[error] - Invalid date\nPlease try again: ");
                }

                List<VaccineDTO> options = new ArrayList<>();
                List<VaccineDTO> listVaccineDTO =
                        controller.getListOfVaccines(snsUserSelected.getSnsUserNumber(), date);
                if(!listVaccineDTO.isEmpty()) {
                    for(VaccineDTO obj : listVaccineDTO) {
                        options.add(obj);
                    }

                    int optionSelected;
                    do {
                        optionSelected = Utils.showAndSelectIndex(options, "List of vaccines with lot number:");
                        if((optionSelected >= 0) && (optionSelected < options.size())) {

                            vaccineWithLotNumberSelected = options.get(optionSelected);
                            optionSelected = -1;
                        }
                    } while(optionSelected != -1);

                    Utils.showText("\nSelected vaccine with lot number: " + vaccineWithLotNumberSelected.toString());
                } else {
                    Utils.showText("Attention!: The vaccines list is empty");
                    return false;
                }
            }
            catch(RuntimeException ex) {
                Utils.showText(ex.getMessage());
                Utils.showText("[warning] the list could not be presented, please contact your maintenance team!");
                return false;
            }
            catch(OperationCanceledByUserException ex) {
                Utils.showText(ex.getMessage());
                return false;
            }
        } else {
            Utils.showText("Attention!: Nurse canceled the vaccine administration process after checking\n- sns user " +
                           "info and health conditions");
            return false;
        }
        //endregion 3.

        //region 4. Shows vaccine administration dosage for that sns user and types vaccination hour -----------
        VaccAdministrationDosageDTO vaccAdministrationDosageDto =
                controller.getVaccineAdministrationProcess(vaccineWithLotNumberSelected.getLotNumber());

        Utils.showText(
                "\nSns user vaccine administration for taken resume: \n" + vaccAdministrationDosageDto.toString());

        try {
            // Time of vaccine administration Hour: input & validation
            vaccAdministratrionTime =
                    Utils.readLineFromConsole("\nInsert the vaccine administered hour (E.g.: hh:mm " + "am) *: ");

            while(!Validations.isHourFormatValid(vaccAdministratrionTime, true, true)) {
                vaccAdministratrionTime =
                        Utils.readLineFromConsole("[error] - Invalid hour format\nPlease try again: ");
            }
        }
        catch(OperationCanceledByUserException ex) {
            Utils.showText(ex.getMessage());
            return false;
        }
        //endregion 4.

        //region 5. Shows vaccine administration resume and asks to permanently record the information ---------
        VaccRecordsDTO vaccRecordsDto = controller.recordAdministrationOfAVaccine(vaccAdministratrionTime);

        Utils.showText("\nSns user vaccine administration info to record: \n" + vaccRecordsDto.toString());
        //endregion 5.

        //region 6. Permanently save vaccine records and start TIMER -----------------------------------------
        if(Utils.confirm("\nSave sns user vaccination records permanently? (Y/N): ")) {


            if(controller.saveVaccineRecords()) {
                return true;
            } else {
                return false;
            }
        } else {
            Utils.showText("Attention!: Nurse canceled the vaccine administration permanently record");
            return false;
        }
        //endregion 6.
    }

}
